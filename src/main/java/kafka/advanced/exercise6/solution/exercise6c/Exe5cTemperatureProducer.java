package kafka.advanced.exercise6.solution.exercise6c;

import kafka.advanced.exercise6.solution.exercise6a.model.Room;
import kafka.advanced.exercise6.solution.exercise6a.model.Temperature;
import kafka.advanced.exercise6.solution.exercise6a.serialization.RoomSerializer;
import kafka.advanced.exercise6.solution.exercise6a.serialization.TemperatureSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;


public class Exe5cTemperatureProducer {
    public void createProducer() {

        Random random = new Random(1);

        Properties props = new Properties();
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, RoomSerializer.class.getName());
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, TemperatureSerializer.class.getName());
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, RoomPartitioner.class.getName());

        // TODO: Create a KafkaProducer

        try (KafkaProducer<Room, Temperature> termometer = new KafkaProducer<>(props)) {

            while (true) {

                Room key = new Room("room" + random.nextInt(5));

                int temperature = random.nextInt(40);

                if (random.nextBoolean())
                    temperature = -1 * temperature;

                Temperature value = new Temperature(temperature, System.currentTimeMillis());

                ProducerRecord<Room, Temperature> record =
                        new ProducerRecord<>("temperature", key, value);

                termometer.send(record);

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
