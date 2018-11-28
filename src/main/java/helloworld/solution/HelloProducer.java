package helloworld.solution;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;


public class HelloProducer {
    public void createProducer() {
        long numberOfEvents = 5;

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // TODO: Create a KafkaProducer

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);


        for (int i = 0; i < numberOfEvents; i++) {
            String key = "firstkey";
            String value = "firstvalue:" + i;

            ProducerRecord<String, String> record = new ProducerRecord<String, String>("topic1", key, value);
            // TODO: Create the ProducerRecord

            producer.send(record);

            System.out.printf("key = %s, value = %s\n", key, value);

            // TODO: Write the record

            // TODO: Print the key and value
        }

        producer.close();
    }

    public static void main(String[] args) {
        HelloProducer helloProducer = new HelloProducer();
        helloProducer.createProducer();
    }
}
