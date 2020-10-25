package kstreams.exercise13;

import kafka.advanced.exercise6.solution.exercise6a.model.Temperature;
import kafka.advanced.exercise6.solution.exercise6a.model.Room;
import kstreams.exercise12.model.Tuple;
import kstreams.exercise12.serdes.RoomSerde;
import kstreams.exercise12.serdes.TemperatureSerde;
import kstreams.exercise12.serdes.Tuple2Serde;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;

import java.time.Duration;
import java.util.Properties;

public class Exe13bWindowedAVG {

    public static void main(String[] args) {

        StreamsBuilder builder = new StreamsBuilder();

        Properties props = new Properties();
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "winwoed-ext-average-kafkastream");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, RoomSerde.class);
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, TemperatureSerde.class);
        props.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, TemperatureTimestampExtractor.class);

        KStream<Room, Temperature> measures =
                builder.stream("temperature",
                        Consumed.with(new RoomSerde(), new TemperatureSerde()));

        KTable<Windowed<Room>, Tuple> aggregate1 = measures.mapValues((key, value) -> new Tuple(0L, (long) value.getValue()))
                .groupByKey()

                .windowedBy(TimeWindows.of(Duration.ofSeconds(10)))
                        // SLIDING WINDOW .advanceBy(Duration.ofSeconds(5)))

                .aggregate(
                        () -> new Tuple(0L, 0L),
                        (Room key, Tuple value, Tuple aggregate) -> {
                            long t2 = ((long) aggregate.t2) + value.t2;
                            long t1 = aggregate.t1 + 1L;
                            return new Tuple(t1, t2);
                        },
                        Materialized.with(new RoomSerde(), new Tuple2Serde()));

        aggregate1.mapValues((readOnlyKey, value) -> value.t2 / value.t1)
                .toStream().print(Printed.toSysOut());


        Topology topology = builder.build();

        System.out.println(topology.describe());
        KafkaStreams ks = new KafkaStreams(topology, props);
        ks.start();
    }
}
