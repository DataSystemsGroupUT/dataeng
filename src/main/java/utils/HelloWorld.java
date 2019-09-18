package utils;

import com.google.gson.Gson;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.ValueMapper;

import java.util.Properties;

public class HelloWorld {

    public static void main(String[] args) {
        StreamsBuilder builder = new StreamsBuilder();

        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "utils-helloworld");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        KStream<String, String> simpleFirstStream = builder.stream("lowercase-topic", Consumed.with(Serdes.String(), Serdes.String()));

        simpleFirstStream.mapValues((ValueMapper<String, String>) String::toUpperCase).to("uppercase-topic", Produced.with(Serdes.String(), Serdes.String()));


//        You don’t always have to provide Serde objects to either the Consumed or Produced objects.
//        If you don’t, the application will use the serializer/deserializer listed in the configuration.
//        Additionally, with the Consumed and Produced classes, you can specify a Serde for either the key or value only.

        Topology topology = builder.build();


        AdminClient client = AdminClient.create(props);

        System.out.println(
                client.describeCluster().clusterId());


        System.out.println(topology.describe().toString());
        System.out.println(new Gson().toJson(topology));

        KafkaStreams ks = new KafkaStreams(topology, props);
        ks.start();
    }


}



