package kafka.advanced.exercise5.solution;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.UUID;


public class WordCounter {

    public static void main(String[] args) throws InterruptedException {
        HashMap<String, Integer> table = new HashMap<>();

        Properties props6a = new Properties();
        props6a.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props6a.put(ConsumerConfig.GROUP_ID_CONFIG, "wordcunter" + UUID.randomUUID());
        props6a.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props6a.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props6a.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props6a.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        Properties props2 = new Properties();
        props2.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props2.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        KafkaProducer<String, String> producer = new KafkaProducer<>(props2);
        props2.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        Consumer<String, String> consumer = new KafkaConsumer<>(props6a);

        consumer.subscribe(Arrays.asList("words"));


        while (true) {

            consumer.poll(Duration.ofMillis(100)).forEach(record -> {

                String r = record.value().replace(".", "").replace("\n", "");
                if (!"".equals(r))
                    if (!table.containsKey(r))
                        table.put(r, 1);
                    else
                        table.compute(r, (s, count) -> count + 1);


            });

            table.forEach((s, integer) -> System.out.print("<" + s + ":" + integer + ">"));

            Thread.sleep(5000);

        }
    }

}
