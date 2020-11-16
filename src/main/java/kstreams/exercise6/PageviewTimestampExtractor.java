package kstreams.exercise6;

import kstreams.exercise6.model.PageView;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;

public class PageviewTimestampExtractor implements TimestampExtractor {

    @Override
    public long extract(ConsumerRecord<Object, Object> record, long partitionTime) {
        if (record.value() instanceof PageView) {
            return ((PageView) record.value()).getViewtime();
        }

        throw new IllegalArgumentException("TimestampExtractor cannot recognize the record value " + record.value());

    }
}
