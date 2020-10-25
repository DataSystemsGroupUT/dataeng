package kafka.experter.exercise9;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import kafka.advanced.exercise6.solution.exercise6a.model.Room;
import kafka.advanced.exercise6.solution.exercise6a.model.Temperature;
import kafka.advanced.exercise6.solution.exercise6b.deserialization.RoomDeserializer;
import kafka.advanced.exercise6.solution.exercise6b.deserialization.TemperatureDeserializer;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class MovingAVGConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "avggroup");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, RoomDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, TemperatureDeserializer.class);

        // TODO: Create a new consumer, with the properties we've created above

        Consumer<Room, Temperature> consumer = new KafkaConsumer<>(props);

        consumer.subscribe(Arrays.asList("temperature"));

        consumer.poll(1);
        consumer.seekToBeginning(consumer.assignment());

        Map<String, List<ConsumerRecord<Room, Temperature>>> collect = new HashMap<>();

        while (true) {

            ConsumerRecords<Room, Temperature> records = consumer.poll(Duration.ofMillis(500));
            if (!records.isEmpty()) {
                for (TopicPartition tp : consumer.assignment()) {

                    List<ConsumerRecord<Room, Temperature>> records1 = records.records(tp);

                    records1.stream().collect(Collectors.groupingBy(o -> o.key().getLocation())).forEach((String s, List<ConsumerRecord<Room, Temperature>> consumerRecords) -> {

                        if (collect.containsKey(s)) {
                            collect.get(s).addAll(consumerRecords);
                        } else
                            collect.put(s, consumerRecords);

                    });

                }
                collect.forEach((key, value) -> {
                    Integer reduce = value.stream().map(e -> e.value().getValue())
                            .reduce(0, (integer, integer2) -> integer + integer2);

                    System.out.println(key + ": " + reduce / value.size());
                });
                System.out.println("---");
            }
        }
    }
}
