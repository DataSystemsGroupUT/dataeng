package kafka.basic.exercise5.deserialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.basic.exercise5.model.TemperatureKey;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class TemperatureKeyDeserializer implements Deserializer<TemperatureKey> {
    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public TemperatureKey deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        TemperatureKey user = null;
        try {
            user = mapper.readValue(data, TemperatureKey.class);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void close() {

    }
}
