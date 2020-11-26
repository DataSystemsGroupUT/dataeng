package ee.ut.cs.dsg.dsg.exercise5;

import ee.ut.cs.dsg.dsg.exercise2.model.Room;
import ee.ut.cs.dsg.dsg.exercise2.serialization.RoomSerializer;
import ee.ut.cs.dsg.dsg.exercise5.serialization.ConfigSerializer;
import ee.ut.cs.dsg.dsg.exercise5.model.Configuration;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;


public class RoomMetadataProducer {
    public void createProducer() {

        Random random = new Random(1);

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, RoomSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ConfigSerializer.class.getName());

        try (KafkaProducer<Room, Configuration> configurator = new KafkaProducer<>(props)) {


            Room key = new Room("room0");
            Configuration value = new Configuration(42, System.currentTimeMillis(), "Riccardo");
            configurator.send(new ProducerRecord<>("configurations", key, value));
            Thread.sleep(5000);

            key = new Room("room1");
            value = new Configuration(21, System.currentTimeMillis(), "John Doe");
            configurator.send(new ProducerRecord<>("configurations", key, value));
            Thread.sleep(5000);

            key = new Room("room2");
            value = new Configuration(33, System.currentTimeMillis(), "Jane Doe");
            configurator.send(new ProducerRecord<>("configurations", key, value));
            Thread.sleep(5000);

            key = new Room("room3");
            value = new Configuration(24, System.currentTimeMillis(), "Marvin");
            configurator.send(new ProducerRecord<>("configurations", key, value));
            Thread.sleep(5000);

            key = new Room("room4");
            value = new Configuration(29, System.currentTimeMillis(), "Thor");
            configurator.send(new ProducerRecord<>("configurations", key, value));
            Thread.sleep(5000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        RoomMetadataProducer helloProducer = new RoomMetadataProducer();
        helloProducer.createProducer();
    }
}
