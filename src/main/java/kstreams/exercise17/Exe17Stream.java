package kstreams.exercise17;

import kstreams.exercise17.model.*;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.*;

import java.time.Duration;
import java.util.Properties;

public class Exe17Stream {

    public static void main(String[] args) {
        StreamsBuilder builder = new StreamsBuilder();

        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "window-join");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, PageviewTimestampExtractor.class);


        KStream<String, PageView> pageviews = builder.stream("pageviews", Consumed.with(Serdes.String(), new PageSerde()));


        //TODO intermediate topics must be created outside
        KStream<String, PageView> pageviewsbyuser = pageviews.selectKey((key, value) -> value.getUserid()).through("pageviewsbyuser");

        KTable<String, User> users = builder.table("users", Consumed.with(Serdes.String(), new UserSerde()));


        KStream<String, RegionalView> joined = pageviewsbyuser
                .leftJoin(users, (pageView, user) -> new RegionalView(user.getRegionid(), pageView));

        KStream<String, RegionalView> pageviewsbyregion = joined.selectKey((key, value) -> value.getUser_region())
                .through("pageviewsbyregion", Produced.with(Serdes.String(), new RegionalPageSerde()));
        //joined.print(Printed.toSysOut());

        KTable<Windowed<String>, Long> counttable = pageviewsbyregion
                .groupByKey()
                .windowedBy(TimeWindows.of(Duration.ofSeconds(30))
                        .advanceBy(Duration.ofSeconds(10))).count();


        counttable.toStream().print(Printed.toSysOut());

        Topology topology = builder.build();

        System.out.println(topology.describe().toString());

        KafkaStreams ks = new KafkaStreams(topology, props);
        ks.start();
    }

    //[KSTREAM-SOURCE-0000000000]: 1013, {"ordertime":1509049597997,"orderid":1013,"itemid":"Item_273","orderunits":5.076322735052166,"address":{"city":"City_14","state":"State_31","zipcode":80962}}

}



