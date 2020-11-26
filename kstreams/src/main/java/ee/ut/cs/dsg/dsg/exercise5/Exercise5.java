package ee.ut.cs.dsg.dsg.exercise5;

import ee.ut.cs.dsg.dsg.exercise2.model.Room;
import ee.ut.cs.dsg.dsg.exercise2.model.Temperature;
import ee.ut.cs.dsg.dsg.exercise2.serde.RoomSerde;
import ee.ut.cs.dsg.dsg.exercise2.serde.TemperatureSerde;
import ee.ut.cs.dsg.dsg.exercise5.model.Configuration;
import ee.ut.cs.dsg.dsg.exercise5.model.RichTemperature;
import ee.ut.cs.dsg.dsg.exercise5.serialization.ConfigSerde;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Printed;

import java.util.Properties;
import java.util.UUID;

public class Exercise5 {

    public static String CONFIG_TOPIC = "configuration2";
    public static String TEMPERATURE_TOPIC = "temperature2";

    public static void main(String[] args) {

        StreamsBuilder builder = new StreamsBuilder();

        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "join-kafkastream" + UUID.randomUUID().toString());
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka1:9092");
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, TemperatureSerde.class);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, RoomSerde.class);
        props.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, 2);

        KStream<Room, Temperature> measures =
                builder.stream(TEMPERATURE_TOPIC,
                        Consumed.with(new RoomSerde(), new TemperatureSerde()));

        KTable<Room, Configuration> configurations = builder.table(CONFIG_TOPIC,

                Consumed.with(new RoomSerde(), new ConfigSerde()));

        KStream<Room, RichTemperature> join = measures.join(configurations, (value1, value2) -> {

            Temperature t = value1;
            Configuration c = value2;

            return new RichTemperature(t, c);

        });

        join
//                .filter((key, value) -> value.getConfiguration().getPrefVal()!=value.getTemp().getValue())
                .print(Printed.toSysOut());


        Topology topology = builder.build();

        System.out.println(topology.describe());
        KafkaStreams ks = new KafkaStreams(topology, props);
        ks.start();
    }
}
