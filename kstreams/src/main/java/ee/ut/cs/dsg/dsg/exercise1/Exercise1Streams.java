package ee.ut.cs.dsg.dsg.exercise1;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;

import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Pattern;

public class Exercise1Streams {

    final static Pattern pattern = Pattern.compile("\\W+", Pattern.UNICODE_CHARACTER_CLASS);
    public static String INPUT_TOPIC = "paragraphs";
    public static String OUTPUT_TOPIC = "wordcount-stream-out";
    ;

    public static void main(String[] args) {
        StreamsBuilder builder = new StreamsBuilder();

        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-stream" + UUID.randomUUID());
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KStream<String, String> words = builder.stream(INPUT_TOPIC, Consumed.with(Serdes.String(), Serdes.String()));

        KTable<String, Long> table = words
//                .mapValues(value -> value.toLowerCase()
//                        .replace(",", " ")
//                        .replace(";", " ")
//                        .replace(".", " ")
//                        .replace("!", " ")
//                        .replace("\"", " ")
//                        .replace("?", " "))
                .flatMapValues(value -> Arrays.asList(pattern.split(value)))
                .groupBy((title, word) -> word).count();

        KStream<String, Long> wordcounts = table.toStream();

        wordcounts.to(OUTPUT_TOPIC, Produced.with(Serdes.String(), Serdes.Long()));
        wordcounts.print(Printed.toSysOut());

        Topology topology = builder.build();

        System.out.println(topology.describe());
        KafkaStreams ks = new KafkaStreams(topology, props);
        ks.start();
    }


}



