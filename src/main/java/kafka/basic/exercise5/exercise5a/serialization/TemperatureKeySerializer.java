package kafka.basic.exercise5.exercise5a.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.basic.exercise5.exercise5a.model.TemperatureKey;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class TemperatureKeySerializer implements Serializer<TemperatureKey> {
    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, TemperatureKey data) {
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
