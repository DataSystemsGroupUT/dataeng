package ee.ut.cs.dsg;

import com.opencsv.CSVReader;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;


public class CapacityProducer {

    public static String TOPIC = "capacities";
    private static Random random = new Random();

    public void createProducer() throws InterruptedException, IOException {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka1:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
//        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomPartitioner.class.getName());
//        props.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "mock://" + scope);
        props.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://schema-registry:8081/");

        KafkaProducer<String, Capacity> producer = new KafkaProducer<>(props);
        long ts = 0;
        File file = new File(PeopleProducer.class.getClassLoader().getResource("capacities.csv").getPath());
        FileReader f = new FileReader(file);
        CSVReader reader = new CSVReader(f);

        Iterator<String[]> iterator = reader.iterator();

        //discard header
        iterator.next();
        while (iterator.hasNext()) {

            String[] next = iterator.next();

            Capacity.Builder builder = Capacity.newBuilder()
                    .setRoom(next[0])
                    .setFloor(next[1])
                    .setBuilding(next[2])
                    .setCap(Integer.parseInt(next[3]))
                    .setAuthor(next[4])
                    .setTimestamp(System.currentTimeMillis());


            producer.send(new ProducerRecord<>(TOPIC, next[0], builder.build()));

            Thread.sleep(3000);

        }

    }

    public static void main(String[] args) throws InterruptedException, IOException {
        CapacityProducer helloProducer = new CapacityProducer();
        helloProducer.createProducer();
    }
}
