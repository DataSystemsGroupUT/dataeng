package shakespeare_example;

import java.util.HashMap;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Consumed;

import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import shakespeare_example.model.ShakespeareKey;
import shakespeare_example.model.ShakespeareValue;


public class StreamsVersionOfShakespeareAvroConversion {

    static HashMap<String, Integer> shakespeareWorkToYearWritten = new HashMap<String, Integer>();

    static Pattern pattern = Pattern.compile("^\\s*(\\d*)\\s*(.*)$");

    public static void main(String[] args) throws Exception {

        // Create the list of works to their publication date
        shakespeareWorkToYearWritten.put("Hamlet", 1600);
        shakespeareWorkToYearWritten.put("Julius Caesar", 1599);
        shakespeareWorkToYearWritten.put("Macbeth", 1605);
        shakespeareWorkToYearWritten.put("Merchant of Venice", 1596);
        shakespeareWorkToYearWritten.put("Othello", 1604);
        shakespeareWorkToYearWritten.put("Romeo and Juliet", 1594);

        Properties streamsConfiguration = new Properties();
        // Application name must be unique in the Kafka cluster
        streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, "shakes5");

        streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "broker101:9092");
        streamsConfiguration.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");


        // Specify default (de)serializers for record keys and for record values.
        streamsConfiguration.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, SpecificAvroSerde.class);
        streamsConfiguration.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, SpecificAvroSerde.class);
        streamsConfiguration.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://schemaregistry1:8081");	  



        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, String> textLines = builder.stream("shakespeare_topic", Consumed.with(Serdes.String(), Serdes.String()));
        KStream<ShakespeareKey, ShakespeareValue> converted = textLines

            .map((k, line) -> new KeyValue<ShakespeareKey, ShakespeareValue>(getShakespeareKey(k), getShakespeareLine(line)));


        // Write the `KStream<String, Long>` to the output topic.
        converted.to("streamshakes5");

        // Now that we have finished the definition of the processing topology we can actually run
        // it via `start()`.  The Streams application as a whole can be launched just like any
        // normal Java application that has a `main()` method.
        KafkaStreams streams = new KafkaStreams(builder.build(), streamsConfiguration);
        streams.start();

        // Add shutdown hook to respond to SIGTERM and gracefully close Kafka Streams
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }

    static private ShakespeareKey getShakespeareKey(String key) {
        Integer yearWritten = shakespeareWorkToYearWritten.get(key);

        if (yearWritten == null) {
            throw new RuntimeException(
                    "Could not find year written for \"" + key + "\"");
        }

        return new ShakespeareKey(key, yearWritten);
    }

    static private ShakespeareValue getShakespeareLine(String line) {
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

