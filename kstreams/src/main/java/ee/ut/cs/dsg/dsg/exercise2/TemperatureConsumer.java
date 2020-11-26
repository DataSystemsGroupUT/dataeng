package ee.ut.cs.dsg.dsg.exercise2;

import ee.ut.cs.dsg.dsg.exercise2.model.Room;
import ee.ut.cs.dsg.dsg.exercise2.model.Temperature;
import ee.ut.cs.dsg.dsg.exercise2.serialization.RoomDeserializer;
import ee.ut.cs.dsg.dsg.exercise2.serialization.TemperatureDeserializer;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;


public class TemperatureConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka1:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "tempgroup");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, RoomDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, TemperatureDeserializer.class);

        // TODO: Create a new consumer, with the properties we've created above

        Consumer<Room, Temperature> consumer = new KafkaConsumer<>(props);

        consumer.subscribe(Arrays.asList("temperature"));

        //todo explain 0 does not work.
        consumer.poll(1);
        Set<TopicPartition> assignment = consumer.assignment();
        consumer.seekToBeginning(assignment);


        while (true) {
            ConsumerRecords<Room, Temperature> records = consumer.poll(Duration.ofMillis(100));

            records.forEach(record -> {

                System.out.println(record.offset());
                System.out.println(record.key());
                System.out.println(record.value());
                System.out.println(record.timestampType() + ": " + record.timestamp());

            });
            // TODO: Read records using the poll() method

            // TODO: Loop around the records, printing out each record.offset, record.key, and record.value
        }
    }

}
