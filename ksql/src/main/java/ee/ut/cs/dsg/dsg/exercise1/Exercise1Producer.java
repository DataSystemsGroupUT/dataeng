package ee.ut.cs.dsg.dsg.exercise1;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Random;


public class Exercise1Producer {

    Lorem lorem = LoremIpsum.getInstance();
    Random random = new Random();
    public static String INPUT_TOPIC = "paragraphs";
    Gson gson = new Gson();

    public void createProducer() throws InterruptedException {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka1:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        try {
            while (true) {

                String paragraph = lorem.getParagraphs(random.nextInt(10), random.nextInt(20));
                String title = lorem.getTitle(10);

                JsonObject element = new JsonObject();
                element.add("title", new JsonPrimitive(title));
                element.add("paragraph", new JsonPrimitive(paragraph));

                ProducerRecord<String, String> record = new ProducerRecord<>(INPUT_TOPIC, title, gson.toJson(element));
                producer.send(record);

                Thread.sleep(5000);
            }

        } finally {
            producer.close();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Exercise1Producer helloProducer = new Exercise1Producer();
        helloProducer.createProducer();
    }
}
