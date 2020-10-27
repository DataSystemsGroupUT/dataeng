package ee.ut.cs.dsg.lt02007.kafka.task2;

import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Properties;

// Total number of people Per Bulding
public class Task2D {
    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka1:9092,kafka2:9093");
        //TODO Your Configuration Code Here

        // TODO: Consumer code here

        // TODO: Subscribe to the topic

        try {
            for (int i = 0; i < 1000; i++) {
                //TODO consuming code here
            }
        } finally {
            //TODO close the consumer
        }
    }

}
