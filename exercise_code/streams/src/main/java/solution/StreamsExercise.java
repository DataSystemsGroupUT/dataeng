package solution;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.StreamsBuilder;

public class StreamsExercise {

    public static void main(String[] args) throws Exception {

        Properties streamsConfiguration = new Properties();

        // Give the Streams application a unique name.  The name must be unique in the Kafka cluster
        streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-exercise1");

        streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "broker101:9092");
        streamsConfiguration.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // Specify default (de)serializers for record keys and for record values.
        streamsConfiguration.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        streamsConfiguration.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

        StreamsBuilder builder = new StreamsBuilder();

        // Read the input Kafka Topic into a KStream instance.
        KStream<String, String> shakesLines = builder.stream("shakespeare_topic");

        // Convert the values to upper case
        KStream<String, String> uppercasedBill = shakesLines.mapValues(String::toUpperCase);

        // Write the uppercased results to a new Kafka Topic called "UppercasedShakespeare".
        uppercasedBill.to("UppercasedShakespeare");

        // Use filter() to get just the messages from Macbeth
        KStream<String, String> justTheScottishPlay = shakesLines.filter((k, v) -> k.equals("Macbeth"));

        // Write the Macbeth messages to a Topic called "ScottishPlay"
        justTheScottishPlay.to("ScottishPlay");

        KafkaStreams streams = new KafkaStreams(builder.build(), streamsConfiguration);
        streams.start();

        // Add shutdown hook to respond to SIGTERM and gracefully close Kafka Streams
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }

}

