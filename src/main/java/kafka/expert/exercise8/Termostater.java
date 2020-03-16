package kafka.expert.exercise8;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.rest.exceptions.RestClientException;
import io.confluent.kafka.schemaregistry.testutil.MockSchemaRegistry;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import kafka.advanced.exercise5.exercise5a.model.Room;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;


public class Termostater {

    static String scope = "observations";
    static SchemaRegistryClient schemaRegistryClient = MockSchemaRegistry.getClientForScope(scope);

    static Lorem lorem = new LoremIpsum();

    public void createProducer() throws IOException, RestClientException {

        Random random = new Random(1);

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "tempgroup");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        props.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "mock://" + scope);

        schemaRegistryClient.register(Observation.class.getName(), Observation.getClassSchema());
        schemaRegistryClient.register(Room.class.getName(), Location.getClassSchema());

        Consumer<Room, Observation> consumer = new KafkaConsumer<>(props);

        consumer.subscribe(Arrays.asList("observations"));
        while (true) {
            ConsumerRecords<Room, Observation> records = consumer.poll(Duration.ofMillis(100));

            records.forEach(record -> {

                System.out.println(record.offset());
                System.out.println(record.key());
                System.out.println(record.value());
                System.out.println(record.timestampType() + ": " + record.timestamp());

            });
            // TODO: Read records using the poll() method

            // TODO: Loop around the records, printing out each record.offset, record.key, and record.value
        }

    }

    public static void main(String[] args) throws IOException, RestClientException {
        Termostater helloProducer = new Termostater();
        helloProducer.createProducer();
    }
}
