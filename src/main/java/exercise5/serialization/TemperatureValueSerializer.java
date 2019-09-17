package exercise5;

import com.fasterxml.jackson.databind.ObjectMapper;
import exercise5.model.TemperatureValue;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class TemperatureValueSerializer implements Serializer<TemperatureValue> {
    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, TemperatureValue data) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsString(data).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }

    @Override
    public void close() {

    }
}
