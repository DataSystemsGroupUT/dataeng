package temperature.partial;

import org.apache.kafka.clients.producer.ProducerConfig;
import temperature.solutions.partitioning.RoomPartitioner;
import temperature.solutions.serialization.TemperatureKeySerializer;
import temperature.solutions.serialization.TemperatureValueSerializer;

import java.util.Properties;
import java.util.Random;


public class TemperatureProducer {
    public void createProducer() {

        Random random = new Random(1);

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        // TODO: Create a KafkaProducer


        while (true) {


            //TODO Key is a wrapper for a String Room+Number
            //TODO Value contains temperature and timestamp

            //TODO create custom serializers

            //TODO Create a Record with Temperature key and value

            //TODO Create a Custom Partitioner

            //TODO write to the temp-2p topic (Two partitions)

        }


    }

    public static void main(String[] args) {
        TemperatureProducer helloProducer = new TemperatureProducer();
        helloProducer.createProducer();
    }
}
