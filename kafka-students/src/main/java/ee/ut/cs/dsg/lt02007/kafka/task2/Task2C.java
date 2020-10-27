package ee.ut.cs.dsg.lt02007.kafka.task2;


import org.apache.kafka.clients.producer.ProducerConfig;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

//Let's save the aggregated result in a topic and progress from there.

public class Task2C {

    public void createProducer() throws InterruptedException, IOException {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka1:9092,kafka2:9093");
        //TODO Your Producer Configuration Code Here

        //TODO Your Producer Here

        try {
            for (int i = 0; i < 1000; i++) {
                //TODO consuming code here
            }
        } finally {
            //TODO close the consumer
        }

    }

    public static void main(String[] args) throws InterruptedException, IOException {
        Task2C helloProducer = new Task2C();
        helloProducer.createProducer();
    }
}
