package ee.ut.cs.dsg.dsg.exercise5;

import ee.ut.cs.dsg.dsg.exercise2.model.Room;
import ee.ut.cs.dsg.dsg.exercise2.serialization.RoomSerializer;
import ee.ut.cs.dsg.dsg.exercise5.serialization.ConfigSerializer;
import ee.ut.cs.dsg.dsg.exercise5.model.Configuration;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;


public class RoomMetadataProducerUpdate {
    public void createProducer() {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, RoomSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ConfigSerializer.class.getName());

        try (KafkaProducer<Room, Configuration> configurator = new KafkaProducer<>(props)) {


            Room key = new Room("room1");
            Configuration value = new Configuration(42, System.currentTimeMillis(), "Gigi");
            configurator.send(new ProducerRecord<>("configurations", key, value));
            Thread.sleep(5000);

            key = new Room("room0");
            value = new Configuration(100, System.currentTimeMillis(), "Murf");
            configurator.send(new ProducerRecord<>("configurations", key, value));
            Thread.sleep(5000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        RoomMetadataProducerUpdate helloProducer = new RoomMetadataProducerUpdate();
        helloProducer.createProducer();
    }
}
