package solution;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;



import solution.model.ShakespeareKey;
import solution.model.ShakespeareValue;

public class ShakespeareConverterConsumerProducer {

    /** Regular expression for parsing the line number and line */
    Pattern pattern = Pattern.compile("^\\s*(\\d*)\\s*(.*)$");

    static HashMap<String, Integer> shakespeareWorkToYearWritten = new HashMap<String, Integer>();


    /**
     * Creates a ConsumerConnector that reads a stream, converts to Avro and
     * publishes to a KafkaProducer
     * 
     * @throws InterruptedException
     */
    public void createConsumer() throws InterruptedException {

        // Create the list of works to their publication date
        shakespeareWorkToYearWritten.put("Hamlet", 1600);
        shakespeareWorkToYearWritten.put("Julius Caesar", 1599);
        shakespeareWorkToYearWritten.put("Macbeth", 1605);
        shakespeareWorkToYearWritten.put("Merchant of Venice", 1596);
        shakespeareWorkToYearWritten.put("Othello", 1604);
        shakespeareWorkToYearWritten.put("Romeo and Juliet", 1594);


        Properties prodProps = new Properties();
        prodProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "broker101:9092");
        prodProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        prodProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);

        // Configure schema repository server
        prodProps.put("schema.registry.url", "http://schemaregistry1:8081");

        try (KafkaProducer<Object, Object> producer = new KafkaProducer<Object, Object>(prodProps)) {

            // Properties for the Consumer
            Properties props = new Properties();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "broker101:9092");
            props.put(ConsumerConfig.GROUP_ID_CONFIG, "testgroup");
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

            try (KafkaConsumer<String,String> consumer = new KafkaConsumer<>(props)) {
                consumer.subscribe(Arrays.asList("shakespeare_topic"));

                while(true) {
                    ConsumerRecords<String, String> records = consumer.poll(100);
                    for (ConsumerRecord<String, String> record : records) {

                        // Get original strings from message and convert to Avro
                        ShakespeareKey shakespeareKey = getShakespeareKey(record.key());
                        ShakespeareValue shakespeareLine = getShakespeareLine(record.value());

                        // Create the ProducerRecord with the Avro objects and send them
                        ProducerRecord<Object, Object> avroRecord = new ProducerRecord<Object, Object>("shakespeare_avro_topic", shakespeareKey, shakespeareLine);

                        producer.send(avroRecord);
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        ShakespeareConverterConsumerProducer consumer = new ShakespeareConverterConsumerProducer();

        try {
            consumer.createConsumer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * Creates the ShakespeareKey object with the work of Shakespeare
     * 
     * @param key
     *            The name of the work of Shakespeare
     * @return The ShakespeareKey object with the work of Shakespeare
     */
    private ShakespeareKey getShakespeareKey(String key) {
        Integer yearWritten = shakespeareWorkToYearWritten.get(key);

        if (yearWritten == null) {
            throw new RuntimeException(
                    "Could not find year written for \"" + key + "\"");
        }

        return new ShakespeareKey(key, yearWritten);
    }

    /**
     * Creates the ShakespeareLine object with the line from Shakespeare
     * 
     * @param line
     *            The line of Shakespeare to parse
     * @return The ShakespeareLine object with the line from Shakespeare
     */
    private ShakespeareValue getShakespeareLine(String line) {
        Matcher matcher = pattern.matcher(line);

        // Use a regex to parse out the line number from the rest of the line
        if (matcher.matches()) {
            // Get the line number and line and create the ShakespeareLine
            int lineNumber = Integer.parseInt(matcher.group(1));
            String lineOfWork = matcher.group(2);

            return new ShakespeareValue(lineNumber, lineOfWork);
        } else {
            // Line didn't match the regex
            System.out.println("Did not match Regex:" + line);

            return null;
        }
    }
}
