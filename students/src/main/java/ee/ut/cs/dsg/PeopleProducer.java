package ee.ut.cs.dsg;

import com.opencsv.CSVReader;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;


public class PeopleProducer {

    public static String TOPIC = "observations";

    public void createProducer() throws InterruptedException, FileNotFoundException {

        Random random = new Random(1);

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka1:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        props.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://schema-registry:8081/");

        KafkaProducer<String, Person> observer = new KafkaProducer<>(props);
        long ts = 0;

        File file = new File(PeopleProducer.class.getClassLoader().getResource("people.csv").getPath());
        FileReader f = new FileReader(file);
        CSVReader reader = new CSVReader(f);

        Iterator<String[]> iterator = reader.iterator();

        //discard header
        iterator.next();

        while (iterator.hasNext()) {

            String[] next = iterator.next();
            System.out.println(Arrays.toString(next));
            Person.Builder builder = Person.newBuilder()
                    .setName(next[0])
                    .setEmail(next[1])
                    .setCity(next[2])
                    .setId(Long.parseLong(next[3]))
                    .setTimestamp(Long.parseLong(next[4]));

            String room = random.nextInt(5) + "" + random.nextInt(4) + "" + random.nextInt(1);
            observer.send(new ProducerRecord<>(TOPIC, room, builder.build()));

            Thread.sleep(3000);
        }

    }

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        PeopleProducer helloProducer = new PeopleProducer();
        helloProducer.createProducer();
    }
}
