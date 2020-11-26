package ee.ut.cs.dsg;

import com.opencsv.CSVWriter;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;


public class CapacityGen {

    private static Random random = new Random();

    public static void main(String[] args) throws IOException {
        Random random = new Random(1);

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka1:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
//        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomPartitioner.class.getName());
//        props.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "mock://" + scope);
        props.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://schema-registry:8081/");

        long ts = 0;
        File file = new File("capacities.csv");

        String[] authors = new String[]{"Riccardo", "Fabiano", "Ragab", "Hassan", "Radwa"};
        FileWriter outputfile = new FileWriter(file);
        CSVWriter writer = new CSVWriter(outputfile);
        // adding header to csv
        String[] header = {"Room", "Floor", "Building", "Capacity", "Author"};
        writer.writeNext(header);
        for (int building = 0; building < 1; building++) {
            for (int floor = 0; floor < 8; floor++) {
                for (int room = 0; room < 20; room++) {
                    String[] data1 = {room + "" + floor + "" + building, floor + "" + building, building + "", String.valueOf(random.nextInt(20)), authors[random.nextInt(authors.length - 1)]};
                    writer.writeNext(data1);
                }
            }
        }

        writer.close();
    }
}
