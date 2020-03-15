package kstreams.exercise12;

import kafka.advanced.exercise5.exercise5a.model.Temperature;
import kafka.advanced.exercise5.exercise5a.model.TemperatureKey;
import kstreams.exercise12.model.Tuple;
import kstreams.exercise12.serdes.TemperatureKeySerde;
import kstreams.exercise12.serdes.TemperatureSerde;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;

import java.util.Properties;

public class Exe12Stream3 {

    public static void main(String[] args) {

        StreamsBuilder builder = new StreamsBuilder();

        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "rolling-average-kafkastream");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, TemperatureKeySerde.class);
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, TemperatureKeySerde.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KStream<TemperatureKey, Temperature> measures =
                builder.stream("temperature",
                        Consumed.with(new TemperatureKeySerde(), new TemperatureSerde()));


        measures.mapValues(value -> new Tuple<>(0L, (long) value.getValue()))
                .groupByKey().count().toStream()
                .print(Printed.toSysOut());


        Topology topology = builder.build();
        KafkaStreams ks = new KafkaStreams(topology, props);
        ks.start();
    }
}
