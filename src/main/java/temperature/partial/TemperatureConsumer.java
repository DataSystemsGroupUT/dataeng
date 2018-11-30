package temperature.partial;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import temperature.solutions.serialization.TemperatureKeyDeserializer;
import temperature.solutions.serialization.TemperatureValueDeserializer;

import java.util.Properties;


public class TemperatureConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "tempgroup");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");

        // TODO: Create a new consumer, with the properties we've created above

        // TODO: Create custom deserializers

        // TODO: Set the offest from beginning

        // TODO: Read records using the poll() method

        // TODO: Loop around the records, printing out each record.offset, record.key, and record.value
    }

}
