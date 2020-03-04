package kafka.expert.exercise7;

import kafka.advanced.exercise5.exercise5b.deserialization.TemperatureKeyDeserializer;
import kafka.advanced.exercise5.exercise5b.deserialization.TemperatureValueDeserializer;
import kafka.advanced.exercise5.exercise5a.model.TemperatureKey;
import kafka.advanced.exercise5.exercise5a.model.Temperature;
import kafka.expert.exercise7.commons.exceptions.OutOfOrderException;
import kafka.expert.exercise7.commons.windowing.Window;
import kafka.expert.exercise7.commons.windowing.WindowImpl;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.*;

public class MovingAVGConsumerLast20Seconds {

    private Map<TemperatureKey, Map<Window, List<Temperature>>> active_windows_tp = new HashMap<>();

    private Map<TemperatureKey, Long> t0map = new HashMap<>();
    private Map<TemperatureKey, Long> curr_time = new HashMap<>();
    private Map<TemperatureKey, Long> last_open = new HashMap<>();

    private long a = 5000 * 60, b = 1000 * 60;

    private long toi = 0;

    private Set<Window> to_evict = new HashSet<>();

    public static void main(String[] args) {
        new MovingAVGConsumerLast20Seconds().run();
    }

    private void run() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "avggroup2");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, TemperatureKeyDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, TemperatureValueDeserializer.class);

        // TODO: Create a new consumer, with the properties we've created above

        Consumer<TemperatureKey, Temperature> consumer = new KafkaConsumer<>(props);

        consumer.subscribe(Arrays.asList("temperature"));

        consumer.poll(100);

        Set<TopicPartition> assignment = consumer.assignment();
        consumer.seekToBeginning(assignment);
        ConsumerRecords<TemperatureKey, Temperature> poll = consumer.poll(Duration.ofMillis(300));

        assignment.forEach(tp -> {
            List<ConsumerRecord<TemperatureKey, Temperature>> records = poll.records(tp);
            if (records != null && !records.isEmpty()) {
                records.stream()
                        .min(Comparator.comparingLong(r -> r.value().getTimestamp()))
                        .ifPresent(t0 -> {
                            long timestamp = t0.value().getTimestamp();
                            t0map.put(t0.key(), timestamp);
                            active_windows_tp.put(t0.key(), new HashMap<>());
                        });

                records.stream().sorted(Comparator.comparingLong(r -> r.value().getTimestamp()))
                        .forEach(record -> {
                            try {
                                TemperatureKey key = record.key();
                                Temperature value = record.value();
                                long t_e = record.value().getTimestamp();
                                curr_time.putIfAbsent(key, t_e);
                                t0map.putIfAbsent(key, t_e);
                                windowing(key, value, curr_time.get(key), t_e, t0map.get(key));
                            } catch (OutOfOrderException e) {
                                e.printStackTrace();
                            }
                        });
            }
        });

        report();

        to_evict.forEach(window -> active_windows_tp.forEach((key, value) -> {

            List<Temperature> remove = value.remove(window);
            if (remove != null)
                remove.clear();


        }));
        to_evict.clear();


        System.out.println("----");
        consumer.poll(Duration.ZERO);

        while (true) {
            ConsumerRecords<TemperatureKey, Temperature> poll1 = consumer.poll(Duration.ofMillis(300));
            if (poll1 != null && !poll1.isEmpty()) {
                assignment.forEach(tp -> poll1.records(tp).stream()
                        .sorted(Comparator.comparingLong(ConsumerRecord::timestamp))
                        .forEach(record -> {
                            try {
                                long t_e = record.value().getTimestamp();
                                TemperatureKey key = record.key();
                                Temperature value = record.value();
                                curr_time.putIfAbsent(key, t_e);
                                t0map.putIfAbsent(key, t_e);
                                windowing(key, value, curr_time.get(key), t_e, t0map.get(key));
                            } catch (OutOfOrderException e) {
                                e.printStackTrace();
                            }
                        }));

                report();
            }
        }
    }

    private void report() {
        active_windows_tp.forEach((key, active_windows) -> {
            System.out.println(key);
            active_windows.keySet().stream()
                    .filter(w -> w.getC() > curr_time.get(key))
                    .filter(w -> active_windows.get(w).size() > 0)
                    .sorted(Comparator.comparingLong(Window::getO))
                    .forEach(w -> {
                        System.out.print(" " + w + " ");
                        List<Temperature> temperatureValues = active_windows.get(w);
                        temperatureValues.stream().map(Temperature::getValue).reduce((t1, t2) -> t1 + t2).ifPresent(integer -> {
                            System.out.println(integer / temperatureValues.size());
                        });

                    });

            to_evict.forEach(w -> {
                // log.debug("Evicting [" + w.getO() + "," + w.getC() + ")");
                active_windows.remove(w);
                if (toi < w.getC())
                    toi = w.getC() + b;
            });

        });
    }


    protected void windowing(TemperatureKey key, Temperature value, Long apptime, long t_e, Long tc0) throws OutOfOrderException {

        //log.debug("Received element (" + e + "," + timestamp + ")");

        if (apptime > t_e) {
            //log.error("OUT OF ORDER NOT HANDLED");
            throw new OutOfOrderException();
        }

        scope(key, t_e, tc0);

        Map<Window, List<Temperature>> active_windows = active_windows_tp.get(key);
        active_windows.keySet().forEach(
                w -> {
                    //log.debug("Processing Window [" + w.getO() + "," + w.getC() + ") for element (" + e + "," + timestamp + ")");
                    if (w.getO() <= t_e && t_e < w.getC()) {
                        // log.debug("Adding element [" + e + "] to Window [" + w.getO() + "," + w.getC() + ")");
                        active_windows.get(w).add(value);
                    } else if (t_e > w.getC()) {
                        //log.debug("Scheduling for Eviction [" + w.getO() + "," + w.getC() + ")");
                        schedule_for_eviction(w);
                    }
                });

    }

    private void schedule_for_eviction(Window w) {
        to_evict.add(w);
    }

    private void scope(TemperatureKey key, long t_e, long tc0) {
        long o_i;
        if (!last_open.containsKey(key)) {
            long c_sup = ((long) Math.ceil(((double) Math.abs(t_e - tc0) / (double) b)) * b);
            o_i = tc0 + c_sup - a;
            //log.debug("Calculating the Windows to Open. First one opens at [" + o_i + "] and closes at [" + c_sup + "]");
        } else
            o_i = last_open.get(key);

        do {
            //log.debug("Computing Window [" + o_i + "," + (o_i + a) + ") if absent");

            active_windows_tp.computeIfAbsent(key, x -> new HashMap<>())
                    .computeIfAbsent(new WindowImpl(tc0, o_i, o_i + a), x -> new ArrayList<>());
            o_i += b;

        } while (o_i <= t_e);

        last_open.put(key, o_i);

    }
}
