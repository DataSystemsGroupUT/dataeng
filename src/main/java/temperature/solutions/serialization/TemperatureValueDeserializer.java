package temperature.solutions.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import temperature.solutions.model.TemperatureValue;

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
