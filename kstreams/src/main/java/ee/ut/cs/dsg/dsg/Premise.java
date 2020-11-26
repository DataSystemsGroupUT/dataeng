package ee.ut.cs.dsg.dsg;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.config.ConfigResource;
import org.apache.kafka.common.config.TopicConfig;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class Premise {

    public static String TOPIC1p = "topic1p";
    public static String TOPIC2p = "topic2p";
    public static List<String> TOPIC_LIST = Arrays.asList(TOPIC1p, TOPIC2p);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //First we need to initialize Kafka properties
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "kafka1:9092,kafka2:9093");
        properties.put("client.id", "java-admin-client");

        AdminClient client = AdminClient.create(properties);

        ListTopicsResult topics = client.listTopics();
        topics.names().get().forEach(System.out::println);

        //TOPIC 1P

        NewTopic topic1p = new NewTopic(TOPIC1p, 1, (short) 1); //new NewTopic(topicName, numPartitions, replicationFactor)

        client.createTopics(Arrays.asList(topic1p));

        DescribeTopicsResult topicList = client.describeTopics(Arrays.asList(TOPIC1p));

        TopicDescription topicDescription = topicList.values().get(TOPIC1p).get();

        System.out.println("Name=" + topicDescription.name());
        System.out.println("Partitions=" + topicDescription.partitions().size());

        //TOPIC 2P


        NewTopic topic2p = new NewTopic(TOPIC2p, 1, (short) 1); //new NewTopic(topicName, numPartitions, replicationFactor)

        client.createTopics(Arrays.asList(topic2p));

        topicList = client.describeTopics(Arrays.asList(TOPIC2p));

        topicDescription = topicList.values().get(TOPIC2p).get();

        System.out.println("Name=" + topicDescription.name());
        System.out.println("Partitions=" + topicDescription.partitions().size());

        //TODO Alter topic

        //TODO Add partition to topic 2p

        Map<String, NewPartitions> newPartitions = new HashMap<>();
        newPartitions.put(TOPIC2p     //numbers 1P
                , NewPartitions.increaseTo(2));
        client.createPartitions(newPartitions);

        topicList = client.describeTopics(Arrays.asList(TOPIC2p));

        topicDescription = topicList.values().get(TOPIC2p).get();

        System.out.println("Name=" + topicDescription.name());
        System.out.println("Partitions=" + topicDescription.partitions().size());


        //TODO Compaction

        ConfigResource configResource = new ConfigResource(ConfigResource.Type.TOPIC, TOPIC1p);
        DescribeConfigsResult configsResult = client.describeConfigs(Collections.singleton(configResource));
        Config configs = configsResult.all().get().get(configResource);

        // print non-default configs
        configs.entries().stream().filter(
                entry -> !entry.isDefault()).forEach(System.out::println);

        // Check if topic is compacted
        ConfigEntry compaction = new ConfigEntry(TopicConfig.CLEANUP_POLICY_CONFIG,
                TopicConfig.CLEANUP_POLICY_COMPACT);

        Collection<AlterConfigOp> configOp = new ArrayList<AlterConfigOp>();

        configOp.add(new AlterConfigOp(compaction, AlterConfigOp.OpType.SET));

        Map<ConfigResource, Collection<AlterConfigOp>> alterConfigs = new HashMap<>();

        alterConfigs.put(configResource, configOp);

        client.incrementalAlterConfigs(alterConfigs).all().get();

        //TODO TOPIC Deletion

        // finish things off by deleting our topic

        client.deleteTopics(TOPIC_LIST).all().get();

        // Check that it is gone. Note that due to the async nature of deletes,
        // it is possible that at this point the topic still exists

        DescribeTopicsResult topics2 = client.describeTopics(TOPIC_LIST);

        topics2.values().forEach((s, descr) -> System.out.println(s)); // this is just to get the exception when topic doesn't exist


    }

}

