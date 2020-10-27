package ee.ut.cs.dsg.lt02007.kafka.task1;


import org.apache.kafka.clients.producer.ProducerConfig;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class Task1A {
    private String OBS = "obs.csv";

    public void createProducer() throws InterruptedException, IOException {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka1:9092,kafka2:9093");
        //TODO Your Producer Configuration Code Here

        String file = Task1A.class.getClassLoader().getResource(OBS).getPath();

        System.out.println(file);

        //TODO Your Producer Here

        BufferedReader br = new BufferedReader(new FileReader(file));

        String line = "";

        while ((line = br.readLine()) != null) {

            System.out.println(line);
            //TODO production code here
        }

    }

    public static void main(String[] args) throws InterruptedException, IOException {
        Task1A helloProducer = new Task1A();
        helloProducer.createProducer();
    }
}
