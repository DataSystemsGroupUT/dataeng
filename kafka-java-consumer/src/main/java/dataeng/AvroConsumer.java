package dataeng;

import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.rest.exceptions.RestClientException;
import io.confluent.kafka.schemaregistry.testutil.MockSchemaRegistry;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;


public class AvroConsumer {

    static String scope = "observations";
    static SchemaRegistryClient schemaRegistryClient = MockSchemaRegistry.getClientForScope(scope);

    public void createProducer() throws IOException, RestClientException {


        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka1:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "generictempgroup"+ UUID.randomUUID().toString());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

//        props.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "mock://" + scope);
        props.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://schema-registry:8081");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);


//        schemaRegistryClient.register(Observation.class.getName(), Observation.getClassSchema());
//        schemaRegistryClient.register(Location.class.getName(), Location.getClassSchema());

        Consumer<String, GenericRecord> consumer = new KafkaConsumer<>(props);

        consumer.subscribe(Arrays.asList("avro_topic"));

        while (true) {
            ConsumerRecords<String, GenericRecord> records = consumer.poll(Duration.ofMillis(100));

            records.forEach(record -> {

                System.out.println(record.offset());
                System.out.println("Key "+record.key());
                System.out.println("Partition "+record.partition());
                System.out.println(record.value());
                System.out.println(record.value().get("measurement"));
                System.out.println(record.value().get("value"));
                System.out.println(record.value().get("value2"));
                System.out.println(record.value().get("id"));

                System.out.println(record.timestampType() + ": " + record.timestamp());

            });
        }

    }

    public static void main(String[] args) throws IOException, RestClientException {
        AvroConsumer helloProducer = new AvroConsumer();
        helloProducer.createProducer();
    }
}
