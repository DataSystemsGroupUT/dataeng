package kafka.admin.exercise2;

import org.apache.kafka.clients.admin.*;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class Exercise2Solution {

    public static String TOPIC_NAME = "numbers";
    public static List<String> TOPIC_LIST = Arrays.asList(TOPIC_NAME);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //First we need to initialize Kafka properties
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092;localhost:9093");
        properties.put("client.id", "java-admin-client");
        System.out.println("***** Topics *****");
        System.out.println("***** Topics Description *****");

        AdminClient client = AdminClient.create(properties);

        //TODO List Topics

        ListTopicsResult topics = client.listTopics();
        topics.names().get().forEach(System.out::println);

        //TODO CREATE TOPICS

        //TOPIC 1P

        NewTopic topic1p = new NewTopic(TOPIC_NAME, 2, (short) 1); //new NewTopic(topicName, numPartitions, replicationFactor)

        client.createTopics(Arrays.asList(topic1p));

        DescribeTopicsResult topicList = client.describeTopics(Arrays.asList(TOPIC_NAME));

        TopicDescription topicDescription = topicList.values().get(TOPIC_NAME).get();

        System.out.println("Name=" + topicDescription.name());
        System.out.println("Partitions=" + topicDescription.partitions().size());

    }

}

