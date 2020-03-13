package kafka.advanced.exercise6;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

import java.util.Random;


public class SentenceProducer {

    Lorem lorem = LoremIpsum.getInstance();
    Random random = new Random();


    public void createProducer() throws InterruptedException {

    }

    public static void main(String[] args) throws InterruptedException {
        SentenceProducer helloProducer = new SentenceProducer();
        helloProducer.createProducer();
    }
}
