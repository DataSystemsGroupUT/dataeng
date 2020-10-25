package kafka.advanced.exercise5.solution;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.UUID;


public class WordCounter30seconds {

    public static void main(String[] args) throws InterruptedException {
        HashMap<String, Integer> table = new HashMap<>();

        Properties props6d = new Properties();
        props6d.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props6d.put(ConsumerConfig.GROUP_ID_CONFIG, "wordconsumer" + UUID.randomUUID());
        props6d.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props6d.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props6d.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props6d.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        Consumer<String, String> consumer = new KafkaConsumer<>(props6d);

        consumer.subscribe(Arrays.asList("words"));


        while (true) {

            consumer.poll(Duration.ofMillis(100)).forEach(record -> {
                        String r = record.value().replace(".", "").replace("\n", "");
                        if (!"".equals(r))
                            if (!table.containsKey(r))
                                table.put(r, 1);
                            else table.compute(r, (s, count) -> count + 1);

                    }
            );

            table.forEach((s, integer) -> System.out.println(s + ":" + integer));

            Thread.sleep(30000);

            table.clear();
        }
    }

}
