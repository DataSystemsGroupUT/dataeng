package exercise5;

import com.fasterxml.jackson.databind.ObjectMapper;
import exercise5.model.TemperatureValue;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class TemperatureValueDeserializer implements Deserializer<TemperatureValue> {
    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public TemperatureValue deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        TemperatureValue user = null;
        try {
            user = mapper.readValue(data, TemperatureValue.class);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void close() {

    }
}
