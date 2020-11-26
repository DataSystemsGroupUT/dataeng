package ee.ut.cs.dsg.dsg.exercise4;

import ee.ut.cs.dsg.dsg.exercise2.model.Temperature;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;

public class TemperatureTimestampExtractor implements TimestampExtractor {

    @Override
    public long extract(ConsumerRecord<Object, Object> record, long partitionTime) {
        if (record.value() instanceof Temperature) {
            return ((Temperature) record.value()).getTimestamp();
        }

        throw new IllegalArgumentException("TimestampExtractor cannot recognize the record value " + record.value());

    }
}
