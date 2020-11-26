package ee.ut.cs.dsg.dsg.exercise5.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.ut.cs.dsg.dsg.exercise5.model.RichTemperature;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class RichTemperatureSerializer implements Serializer<RichTemperature> {
    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, RichTemperature data) {
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
