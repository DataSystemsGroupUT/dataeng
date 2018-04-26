package partial;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;


public class HelloProducer {
    public void createProducer() {
        long numberOfEvents = 5;

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "broker101:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // TODO: Create a KafkaProducer

        for (int i = 0; i < numberOfEvents; i++) {
            String key = "firstkey";
            String value = "firstvalue:" + i;

            // TODO: Create the ProducerRecord

            // TODO: Write the record

            // TODO: Print the key and value
        }

        //TODO: close the producer
    }

    public static void main(String[] args) {
        HelloProducer helloProducer = new HelloProducer();
        helloProducer.createProducer();
    }
}
