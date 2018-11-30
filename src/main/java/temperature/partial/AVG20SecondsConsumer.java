package temperature.partial;

import org.apache.kafka.clients.consumer.*;
import temperature.commons.exceptions.OutOfOrderException;
import temperature.solutions.model.TemperatureKey;
import temperature.solutions.model.TemperatureValue;
import temperature.commons.windowing.Window;
import temperature.commons.windowing.WindowImpl;

import java.util.*;

public class AVG20SecondsConsumer {

    private Map<TemperatureKey, Map<Window, List<TemperatureValue>>> active_windows_tp = new HashMap<>();

    private Map<TemperatureKey, Long> t0map = new HashMap<>();
    private Map<TemperatureKey, Long> curr_time = new HashMap<>();
    private Map<TemperatureKey, Long> last_open = new HashMap<>();

    private long a = 20000, b = 20000;

    private long toi = 0;

    private Set<Window> to_evict = new HashSet<>();

    public static void main(String[] args) {
        new AVG20SecondsConsumer().run();
    }

    private void run() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "avggroup2");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        // TODO: Create a new consumer, with the properties we've created above

        // TODO: Start Consuming from the beginning

        //Windowing 101

        //TODO assign elements to windows

        //TODO maintain status by key

        //Print the result so far
        report();

        //TODO Evict the closed windows
        to_evict.forEach(w -> {});

        to_evict.clear();


        while (true) {

            //TODO get records using poll

            //TODO assign methods to windows

        }
    }

    private void report() {
        active_windows_tp.forEach((key, active_windows) -> {
            System.out.println(key);
            active_windows.keySet().stream()
                    .filter(w -> w.getC() > curr_time.get(key))
                    //TODO .filter(w -> active_windows.get(w).size() > 0)
                    .forEach(w -> {
                        System.out.println(w);
                        List<TemperatureValue> temperatureValues = active_windows.get(w);
                        temperatureValues.stream().map(TemperatureValue::getValue).reduce((t1, t2) -> t1 + t2).ifPresent(integer -> {
                            System.out.println(integer / temperatureValues.size());
                        });

                    });



        });
    }


    protected void windowing(TemperatureKey key, TemperatureValue value, Long apptime, long t_e, Long tc0) throws OutOfOrderException {

        //log.debug("Received element (" + e + "," + timestamp + ")");

        if (apptime > t_e) {
            //log.error("OUT OF ORDER NOT HANDLED");
            throw new OutOfOrderException();
        }

        scope(key, t_e, tc0);

        //TODO add elements and evicts
        Map<Window, List<TemperatureValue>> active_windows = active_windows_tp.get(key);

        //if element is comprised between window boundaries
        //if is too late
    }

    private void schedule_for_eviction(Window w) {
        to_evict.add(w);
    }

    private void scope(TemperatureKey key, long t_e, long tc0) {
       //TODO calculating the scope
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
