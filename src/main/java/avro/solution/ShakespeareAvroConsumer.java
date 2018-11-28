package avro.solution;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import avro.solution.model.ShakespeareKey;
import avro.solution.model.ShakespeareValue;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;


public class ShakespeareAvroConsumer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "asdad");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
        props.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");


        try (KafkaConsumer<ShakespeareKey, ShakespeareValue> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Arrays.asList("shakespeare_avro_topic"));

            while (true) {
                ConsumerRecords<ShakespeareKey, ShakespeareValue> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<ShakespeareKey, ShakespeareValue> record : records) {

                    ShakespeareKey shakespeareKey = record.key();
                    ShakespeareValue shakespeareLine = record.value();

                    // Output the information with the SpecificRecords
                    System.out.println("From " + shakespeareKey.getWork() + " - "
                        + shakespeareKey.getYear() + " Line:"
                        + shakespeareLine.getLineNumber() + " "
                        + shakespeareLine.getLine());
                }
            }
        }
    }		
}
