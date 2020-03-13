package kafka.expert.exercise8;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.rest.exceptions.RestClientException;
import io.confluent.kafka.schemaregistry.testutil.MockSchemaRegistry;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;


public class Termometer {

    static String scope = "observations";
    static SchemaRegistryClient schemaRegistryClient = MockSchemaRegistry.getClientForScope(scope);

    static Lorem lorem = new LoremIpsum();

    public void createProducer() throws IOException, RestClientException {

        Random random = new Random(1);

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomPartitioner.class.getName());
        props.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "mock://" + scope);

        schemaRegistryClient.register(Observation.class.getName(), Observation.getClassSchema());
        schemaRegistryClient.register(Location.class.getName(), Location.getClassSchema());

        try (KafkaProducer<Location, Observation> termometer = new KafkaProducer<>(props)) {

            while (true) {


                Location key = new Location(lorem.getName(), random.nextInt(4), random.nextInt(40));

                double temperature = random.nextDouble();

                if (random.nextBoolean())
                    temperature = -1 * temperature;

                Observation value = new Observation(1L, temperature, "temperature", System.currentTimeMillis());

                ProducerRecord<Location, Observation> record = new ProducerRecord<>("observations", key, value);

                termometer.send(record);

                Thread.sleep(10000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException, RestClientException {
        Termometer helloProducer = new Termometer();
        helloProducer.createProducer();
    }
}
