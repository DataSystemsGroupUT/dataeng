package ee.ut.cs.dsg.dsg.exercise2;

import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Random;


public class ObservationProducer {

    public static String TOPIC = "temperature_avro2";

    public void createProducer() throws InterruptedException {

        Random random = new Random(1);

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka1:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
//        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomPartitioner.class.getName());
//        props.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "mock://" + scope);
        props.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://schema-registry:8081/");

        KafkaProducer<String, Observation> termometer = new KafkaProducer<>(props);
        long ts = 0;

        try {
            while (true) {

                String key = "room" + random.nextInt(5);

                double temperature = random.nextDouble();

                if (random.nextBoolean())
                    temperature = -1 * temperature;

                Observation value = new Observation(1L, temperature, TOPIC, ts += 2000);

                ProducerRecord<String, Observation> record = new ProducerRecord<>(TOPIC, key, value);

                termometer.send(record);

                Thread.sleep(10000);
            }
        } finally {
            termometer.close();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ObservationProducer helloProducer = new ObservationProducer();
        helloProducer.createProducer();
    }
}
