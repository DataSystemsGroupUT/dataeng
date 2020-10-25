package kafka.advanced.exercise6.exercise6c;

import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;
import java.util.Random;


public class Exe5cTemperatureProducer {
    public void createProducer() {

        Random random = new Random(1);

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        //TODO key serializer
        //TODO valus serializer
        //TODO partitioner

        // TODO: Create a KafkaProducer

        try {

            while (true) {

                // TODO: Producing code
                Thread.sleep(10001);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Exe5cTemperatureProducer helloProducer = new Exe5cTemperatureProducer();
        helloProducer.createProducer();
    }
}
