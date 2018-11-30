package temperature.solutions;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import temperature.solutions.model.TemperatureKey;
import temperature.solutions.model.TemperatureValue;
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
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, TemperatureKeySerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, TemperatureValueSerializer.class.getName());
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, RoomPartitioner.class.getName());

        // TODO: Create a KafkaProducer

        try (KafkaProducer<TemperatureKey, TemperatureValue> termometer = new KafkaProducer<>(props)) {

            while (true) {

                TemperatureKey key = new TemperatureKey("room" + random.nextInt(5));

                int temperature = random.nextInt(40);

                if (random.nextBoolean())
                    temperature = -1 * temperature;

                TemperatureValue value = new TemperatureValue(temperature, System.currentTimeMillis());

                ProducerRecord<TemperatureKey, TemperatureValue> record =
                        new ProducerRecord<>("temp-2p   ", key, value);

                termometer.send(record);

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
