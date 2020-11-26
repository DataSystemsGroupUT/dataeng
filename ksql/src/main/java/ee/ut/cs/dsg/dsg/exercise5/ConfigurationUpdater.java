package ee.ut.cs.dsg.dsg.exercise5;

import ee.ut.cs.dsg.dsg.exercise2.CustomPartitioner;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Random;


public class ConfigurationUpdater {

    public static String TOPIC = "configuration_avro";

    public void createProducer() throws InterruptedException {

        Random random = new Random(1);

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka1:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomPartitioner.class.getName());
//        props.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "mock://" + scope);
        props.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://schema-registry:8081/");

        KafkaProducer<String, Configuration> producer = new KafkaProducer<>(props);
        long ts = 0;

        try {

            String key = "room" + random.nextInt(5);

            double temperature = random.nextDouble();

            if (random.nextBoolean())
                temperature = -1 * temperature;


            Configuration build = Configuration.newBuilder()
                    .setAuthor("Fabiano")
                    .setMeasurement("temperature")
                    .setPrefVal(33)
                    .setTimestamp(System.currentTimeMillis()).build();

            producer.send(new ProducerRecord<>(TOPIC, "room0", build));
            Thread.sleep(5000);


            build = Configuration.newBuilder()
                    .setAuthor("Ragab")
                    .setMeasurement("temperature")
                    .setPrefVal(33)
                    .setTimestamp(System.currentTimeMillis()).build();

            producer.send(new ProducerRecord<>(TOPIC, "room1", build));
            Thread.sleep(5000);

            Thread.sleep(10000);
        } finally {
            producer.close();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ConfigurationUpdater helloProducer = new ConfigurationUpdater();
        helloProducer.createProducer();
    }
}
