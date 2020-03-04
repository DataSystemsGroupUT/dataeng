package kstreams.exercise10;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.*;

import java.util.Properties;
import java.util.UUID;

public class Exe10cStreams1 {

    public static void main(String[] args) {
        StreamsBuilder builder = new StreamsBuilder();

        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, UUID.randomUUID().toString());
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Integer().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Integer().getClass());

        KStream<Integer, Integer> evenstreams = builder
                .stream("module2", Consumed.with(Serdes.Integer(), Serdes.Integer()));

        KTable<Integer, Long> count = evenstreams.groupByKey().count();

        count.toStream().print(Printed.toSysOut());


//        You don’t always have to provide Serde objects to either the Consumed or Produced objects.
//        If you don’t, the application will use the serializer/deserializer listed in the configuration.
//        Additionally, with the Consumed and Produced classes, you can specify a Serde for either the key or value only.


        Topology topology = builder.build();

        System.out.println(topology.describe().toString());

        KafkaStreams ks = new KafkaStreams(topology, props);
        ks.start();
    }


}



