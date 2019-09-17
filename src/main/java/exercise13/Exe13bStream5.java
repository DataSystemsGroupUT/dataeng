package exercise13;

import com.google.gson.Gson;
import exercise13.model.Order;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;

import java.time.Duration;
import java.util.Properties;

public class Exe13bStream5 {

    static Gson gson = new Gson();

    public static void main(String[] args) {
        StreamsBuilder builder = new StreamsBuilder();

        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "window-slide");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        KStream<String, String> orders = builder.stream("orderst", Consumed.with(Serdes.String(), Serdes.String()));

        OrderSerde orderSerde = new OrderSerde();
        orders.mapValues(value -> gson.fromJson(value, Order.class))
                .to("orderstb", Produced.with(Serdes.String(), orderSerde));

        KStream<String, Order> ordersj = builder.stream("orderstb", Consumed.with(Serdes.String(), orderSerde));

        TimeWindowedKStream<String, Order> windowedKStream = ordersj
                //group by state
                .groupBy((key, value) -> value.getItemid())
                //tumbling window of 5 seconds
                .windowedBy(TimeWindows.of(Duration.ofSeconds(10)).advanceBy(Duration.ofSeconds(5)));

        windowedKStream.count().toStream().print(Printed.toSysOut());

        Topology topology = builder.build();

        System.out.println(topology.describe().toString());

        KafkaStreams ks = new KafkaStreams(topology, props);
        ks.start();
    }

    //[KSTREAM-SOURCE-0000000000]: 1013, {"ordertime":1509049597997,"orderid":1013,"itemid":"Item_273","orderunits":5.076322735052166,"address":{"city":"City_14","state":"State_31","zipcode":80962}}

}



