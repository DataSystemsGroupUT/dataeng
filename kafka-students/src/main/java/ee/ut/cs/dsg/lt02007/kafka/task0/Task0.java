package ee.ut.cs.dsg.lt02007.kafka.task0;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class Task0 {

    public static List<String> TOPIC_LIST;//TODO topics here

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //First we need to initialize Kafka properties
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "kafka1:9092;kafka2:9093");
        properties.put("client.id", "java-admin-client");


        //TODO CREATE TOPICS


        //TODO List Topics
    }

}

