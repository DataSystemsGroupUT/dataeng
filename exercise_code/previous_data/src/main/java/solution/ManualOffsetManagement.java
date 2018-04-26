package solution;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;


public class ManualOffsetManagement {
    public static void main(String[] args) throws IOException {
        final String OFFSET_FILE_PREFIX = "offset_";

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "broker101:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "mygroup");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "10");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Arrays.asList("hello_world_topic"));
            consumer.poll(0);
            for (TopicPartition partition : consumer.assignment()) {
                if (Files.exists(Paths.get(OFFSET_FILE_PREFIX + partition.partition()))) {
                    long offset = Long
                        .parseLong(Files.readAllLines(Paths.get(OFFSET_FILE_PREFIX + partition.partition()),
                                    Charset.defaultCharset()).get(0));
                    consumer.seek(partition, offset);
                }
            }

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.format("Offset = %s, Key = %s, Value = %s\n", record.offset(), record.key(),
                            record.value());
                    Files.write(Paths.get(OFFSET_FILE_PREFIX + record.partition()),
                            Long.valueOf(record.offset() + 1).toString().getBytes());
                }

            }
        }
    }
}
