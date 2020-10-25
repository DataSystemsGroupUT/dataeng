package kafka.admin.exercise1;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.config.ConfigResource;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class Exercise1 {

    public static String TOPIC1p = "topic1p";
    public static String TOPIC2p = "topic2p";
    public static List<String> TOPIC_LIST = Arrays.asList(TOPIC1p, TOPIC2p);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //First we need to initialize Kafka properties
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092;localhost:9093");
        properties.put("client.id", "java-admin-client");

        //TODO Create Admin Client

        //TODO List Topics


        //TODO CREATE TOPICS


        //TODO: TOPIC 1P

        NewTopic topic1p;


        TopicDescription topicDescription = null;

        System.out.println("Name=" + topicDescription.name());
        System.out.println("Partitions=" + topicDescription.partitions().size());

        //TODO: TOPIC 2P


        NewTopic topic2p;

        System.out.println("Name=" + topicDescription.name());
        System.out.println("Partitions=" + topicDescription.partitions().size());

        //TODO Alter topic

        //TODO Add partition to topic 2p

        Map<String, NewPartitions> newPartitions = new HashMap<>();

        //TODO Add partition newPartitions

        System.out.println("Name=" + topicDescription.name());
        System.out.println("Partitions=" + topicDescription.partitions().size());


        //TODO Make topic 2p compacted

        ConfigResource configResource;
        DescribeConfigsResult configsResult;
        Config configs = null;

        // print non-default configs
        configs.entries().stream().filter(
                entry -> !entry.isDefault()).forEach(System.out::println);

        //TODO create config entry
        ConfigEntry compaction;

        //TODO TOPIC Deletion

        // finish things off by deleting our topic

        // Check that it is gone. Note that due to the async nature of deletes,
        // it is possible that at this point the topic still exists

        DescribeTopicsResult topics2 = null;

        topics2.values().forEach((s, descr) -> System.out.println(s)); // this is just to get the exception when topic doesn't exist

    }
}

