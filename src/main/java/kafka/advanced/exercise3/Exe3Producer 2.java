package kafka.advanced.exercise3;

import java.util.Properties;


public class Exe3Producer {
    public void createProducer() throws InterruptedException {
        // TODO: Create a KafkaProducer
        Properties props = new Properties();
        //TODO add List of Brokers
        //TODO add Serializers for KEY
        //TODO add Serializers for Message

        // TODO: Create a KafkaProducer

        //TODO try to consume the output stream with the console consumer

        int i = 0;
        while (true) {
            i += 2;

            //TODO create and send a record to the selected topic "evens"

            System.out.printf("key = %d, value = %d\n", i, i);

            Thread.sleep(5000);
        }
        //TODO close the producer gracefully

    }

    public static void main(String[] args) throws InterruptedException {
        Exe3Producer helloProducer = new Exe3Producer();
        helloProducer.createProducer();
    }
}
