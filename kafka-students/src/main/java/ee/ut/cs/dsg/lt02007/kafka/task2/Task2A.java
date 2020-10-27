package ee.ut.cs.dsg.lt02007.kafka.task2;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.io.IOException;
import java.util.Properties;


public class Task2A {
    private String OBS = "obs.csv";

    public void createProducer() throws InterruptedException, IOException {
        Properties pprops = new Properties();
        pprops.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka1:9092,kafka2:9093");
        //TODO Your Producer Configuration Code Here


        Properties cprops = new Properties();
        pprops.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka1:9092,kafka2:9093");
        //TODO Your consumer Configuration Code Here

        //TODO  Your producer Code Here
        //TODO  Your consumer Code Here

        try {
            for (int i = 0; i < 1000; i++) {
                //TODO consuming and producing code here
            }
        } finally {
            //TODO close the consumer
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        Task2A helloProducer = new Task2A();
        helloProducer.createProducer();
    }
}
