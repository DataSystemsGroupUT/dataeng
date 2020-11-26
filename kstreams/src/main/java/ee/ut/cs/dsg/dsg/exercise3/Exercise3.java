package ee.ut.cs.dsg.dsg.exercise3;

import ee.ut.cs.dsg.dsg.exercise2.serde.RoomSerde;
import ee.ut.cs.dsg.dsg.exercise2.serde.TemperatureSerde;
import ee.ut.cs.dsg.dsg.exercise3.model.Tuple;
import ee.ut.cs.dsg.dsg.exercise3.serdes.Tuple2Serde;
import ee.ut.cs.dsg.dsg.exercise2.model.Room;
import ee.ut.cs.dsg.dsg.exercise2.model.Temperature;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Printed;

import java.util.Properties;

public class Exercise3 {

    public static void main(String[] args) {

        StreamsBuilder builder = new StreamsBuilder();

        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "rolling-average-kafkastream");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, RoomSerde.class);
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, TemperatureSerde.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KStream<Room, Temperature> measures =
                builder.stream("temperature",
                        Consumed.with(new RoomSerde(), new TemperatureSerde()));

        measures.mapValues((key, value) -> new Tuple(0L, (long) value.getValue()))
                .groupByKey().aggregate(() -> new Tuple(0L, 0L),
                (Room key, Tuple value, Tuple aggregate) -> {
                    long t1 = aggregate.t1 + 1L;
                    long t2 = ((long) aggregate.t2) + value.t2;
                    return new Tuple(t1, t2);
                }, Materialized.with(new RoomSerde(),
                        new Tuple2Serde()))
                .mapValues((readOnlyKey, value) -> value.t2 / value.t1)
                .toStream().print(Printed.toSysOut());


        Topology topology = builder.build();

        System.out.println(topology.describe());
        KafkaStreams ks = new KafkaStreams(topology, props);
        ks.start();
    }
}
