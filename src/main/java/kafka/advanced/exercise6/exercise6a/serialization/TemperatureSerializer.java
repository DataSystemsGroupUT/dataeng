package kafka.advanced.exercise6.exercise6a.serialization;

import kafka.advanced.exercise6.exercise6a.model.Temperature;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class TemperatureSerializer implements Serializer<Temperature> {
    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, Temperature data) {
        return null;
    }

    @Override
    public void close() {

    }
}
