package kafka.expert.exercise9;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;


public class ShakespeareAvroConsumer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "consgroup");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"io.confluent.kafka.serializers.KafkaAvroDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.KafkaAvroDeserializer");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
        props.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");


        try (KafkaConsumer<PoemKey, Poem> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Arrays.asList("shakespeare_avro_topic"));

            while (true) {
                ConsumerRecords<PoemKey, Poem> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<PoemKey, Poem> record : records) {

                    PoemKey shakespeareKey = record.key();
                    Poem shakespeareLine = record.value();

                    // Output the information with the SpecificRecords
                    System.out.println("From " + shakespeareKey.getYear() + " - "+
                         " Line:"
                        + shakespeareLine.getLineNumber() + " "
                        + shakespeareLine.getLine());
                }
            }
        }
    }		
}
