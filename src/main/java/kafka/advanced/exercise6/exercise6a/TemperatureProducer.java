package kafka.advanced.exercise6.exercise6a;

import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;
import java.util.Random;


public class TemperatureProducer {
    public void createProducer() {

        Random random = new Random(1);

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        //TODO key serializer
        //TODO valuse serializer

        // TODO: Create a KafkaProducer

        int ts = 0;
        try {

            while (true) {

                // TODO: Producing code

                Thread.sleep(10000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        TemperatureProducer helloProducer = new TemperatureProducer();
        helloProducer.createProducer();
    }
}
