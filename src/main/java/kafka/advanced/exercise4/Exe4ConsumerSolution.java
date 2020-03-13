package kafka.advanced.exercise4;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.IntegerDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;


public class Exe4ConsumerSolution {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer1");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);

        Consumer<Integer, Integer> consumer = new KafkaConsumer<>(props);

        consumer.subscribe(Arrays.asList("module2"));

        while (true) {

            ConsumerRecords<Integer, Integer> poll = consumer.poll(Duration.ofMillis(0));

             poll.forEach(record ->
                    {
                        ConsumerRecord<Integer, Integer> record1 = record;



                        System.out.printf("offset = %s, key = %s, value = %s\n",
                                record1.offset(), record1.key(), record1.value());
                    });
        }
    }

}
