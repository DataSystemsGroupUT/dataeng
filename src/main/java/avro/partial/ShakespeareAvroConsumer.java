package avro.partial;

import java.util.Arrays;
import java.util.Properties;

import avro.solution.model.ShakespeareKey;
import avro.solution.model.ShakespeareValue;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;


public class ShakespeareAvroConsumer {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "newgroup");

		// Set the key and value deserializers
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);

		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		props.put("specific.avro.reader", "true");
        props.put("schema.registry.url", "http://localhost:8081");

		try (KafkaConsumer<ShakespeareKey, ShakespeareValue> consumer = new KafkaConsumer<>(props)) {

			// TODO Subscribe to shakespeare_avro_topic

			while (true) {
				ConsumerRecords<ShakespeareKey, ShakespeareValue> records = consumer.poll(100);
				for (ConsumerRecord<ShakespeareKey, ShakespeareValue> record : records) {

					//TODO Extract the key and value into ShakespeareKeyPartial and
					// ShakespeareValue objects

					ShakespeareKey key = record.key();
					ShakespeareValue value = record.value();

					//TODO Output the information with the SpecificRecords

					// Write out the play name, year, linenumber, and line

					System.out.println("Play Name [" + key.work + "], " + "Year [" + key.year + "], " + "Line Number ["
							+ value.line_number + "], " + "Line [" + value.line + "]");

				}
			}
		}
	}
}
