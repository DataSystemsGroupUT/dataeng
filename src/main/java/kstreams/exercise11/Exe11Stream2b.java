package kstreams.exercise11;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;

import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Pattern;

public class Exe11Stream2b {

    final static Pattern pattern = Pattern.compile("\\W+", Pattern.UNICODE_CHARACTER_CLASS);

    public static void main(String[] args) {
        StreamsBuilder builder = new StreamsBuilder();

        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount4");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        KStream<String, String> words = builder.stream("shakespeare_topic", Consumed.with(Serdes.String(), Serdes.String()));

        // words.print(Printed.toSysOut());
        // words.mapValues(String::length).print(Printed.toSysOut());
        // words.flatMapValues(value -> Arrays.asList(pattern.split(value.toLowerCase()))).print(Printed.toSysOut());
        KTable<String, Long> table = words
                .mapValues(value -> value.toLowerCase()
                        .replace(",", " ")
                        .replace(";", " ")
                        .replace(".", " ")
                        .replace("!", " ")
                        .replace("\"", " ")
                        .replace("?", " "))
                .flatMapValues(value -> Arrays.asList(pattern.split(value)))
                .groupBy((poem, word) -> poem + "-" + word).count();

        KStream<String, Long> wordcounts = table.toStream();

        wordcounts.to("wordcount-by-poem-t", Produced.with(Serdes.String(), Serdes.Long()));
        wordcounts.print(Printed.toSysOut());

        Topology topology = builder.build();
        KafkaStreams ks = new KafkaStreams(topology, props);
        ks.start();
    }


}



