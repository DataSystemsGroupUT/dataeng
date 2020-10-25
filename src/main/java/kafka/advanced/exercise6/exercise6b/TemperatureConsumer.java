package kafka.advanced.exercise6.exercise6b;

import kafka.advanced.exercise6.exercise6b.deserialization.RoomDeserializer;
import kafka.advanced.exercise6.exercise6b.deserialization.TemperatureDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Properties;


public class TemperatureConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "tempgroup");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, RoomDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, TemperatureDeserializer.class);

        // TODO: Create a new consumer, with the properties we've created above

        //TODO subscribe to the topic

        //TODO consume from the beginnings

        while (true) {

            // TODO: Read records using the poll() method

            // TODO: Loop around the records, printing out each record.offset, record.key, and record.value
        }
    }

}
