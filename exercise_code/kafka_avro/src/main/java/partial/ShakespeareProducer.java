package partial;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import io.confluent.kafka.serializers.KafkaAvroSerializer;

public class ShakespeareProducer {
	/**
	 * Reads in every line of the input file and sends it with a KafkaProducer
	 */
	private final static String INPUT_PATH_NAME = "/home/training/developer/datasets/shakespeare";

	public void runProducer() throws IOException {
		KafkaProducer<String, String> producer = createProducer();

		File inputFile = new File(INPUT_PATH_NAME);
		if (inputFile.isDirectory()) {
			// If a directory, iterate through all files
			for (File fileInDirectory : inputFile.listFiles()) {
				sendFile(fileInDirectory, producer);
			}
		} else {
			// If a single file, send it
			sendFile(inputFile, producer);
		}
		producer.close();
	}

	private void sendFile(File inputFile, KafkaProducer<String, String> producer)
			throws FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));

		// Use the file name as the key
		String key = inputFile.getName().split("\\.")[0];

		String line = null;

		// Read in the file line by line and send it
		while ((line = reader.readLine()) != null) {

			// Create a ProducerRecord with the key and line, and send it
			producer.send(new ProducerRecord<String, String>("shakespeare_topic3", key, line));
			
		}

		reader.close();

		System.out.println("Finished producing file:" + inputFile.getName());
	}

	/**
	 * Creates the KafkaProducer and configures it
	 * 
	 * @return The configured KafkaProducer
	 */
	private KafkaProducer<String, String> createProducer() {
		Properties props = new Properties();
		// Configure brokers to connect to
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "broker101:9092");
		// Configure key serializer
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

		// props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
		// KafkaAvroSerializer.class);

		// Configure the value serializer
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

		//Create a KafkaProducer object and return it

		return new KafkaProducer<String, String>(props);

	}

	public static void main(String[] args) {

		try {
			ShakespeareProducer helloProducer = new ShakespeareProducer();
			helloProducer.runProducer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
