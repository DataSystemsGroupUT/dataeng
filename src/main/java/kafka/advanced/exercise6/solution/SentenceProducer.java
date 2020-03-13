package kafka.advanced.exercise6.solution;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Random;


public class SentenceProducer {

    Lorem lorem = LoremIpsum.getInstance();
    Random random = new Random();


    public void createProducer() throws InterruptedException {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        try {
            while (true) {

                String paragraphs = lorem.getParagraphs(random.nextInt(10), random.nextInt(100));

                ProducerRecord<String, String> record = new ProducerRecord<>("paragraphs", lorem.getTitle(10), paragraphs);
                producer.send(record);

                Thread.sleep(5000);
            }

        } finally {
            producer.close();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        SentenceProducer helloProducer = new SentenceProducer();
        helloProducer.createProducer();
    }
}
