package ee.ut.cs.dsg.dsg.exercise2.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.ut.cs.dsg.dsg.exercise2.model.Temperature;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class TemperatureSerializer implements Serializer<Temperature> {
    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, Temperature data) {
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
