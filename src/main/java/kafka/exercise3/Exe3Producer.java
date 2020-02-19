package kafka.exercise3;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;

import java.util.Properties;


public class Exe3Producer {
    public void createProducer() throws InterruptedException {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);

        // TODO: Create a KafkaProducer

        KafkaProducer<Integer, Integer> producer = new KafkaProducer<>(props);

        //TODO try to consume the output stream with the console consumer

        int i = 0;
        while (true) {
            i += 2;

            ProducerRecord<Integer, Integer> record = new ProducerRecord<>("evens", i, i);
            producer.send(record);
            System.out.printf("key = %d, value = %d\n", i, i);

            Thread.sleep(5000);
        }
        //producer.close();
        //TODO try to consume the output stream with the console consumer

    }

    public static void main(String[] args) throws InterruptedException {
        Exe3Producer helloProducer = new Exe3Producer();
        helloProducer.createProducer();
    }
}
