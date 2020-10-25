package kafka.advanced.exercise6.exercise6b.deserialization;

import kafka.advanced.exercise6.solution.exercise6a.model.Temperature;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class TemperatureDeserializer implements Deserializer<Temperature> {
    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public Temperature deserialize(String topic, byte[] data) {
        return null;

    }

    @Override
    public void close() {

    }
}
