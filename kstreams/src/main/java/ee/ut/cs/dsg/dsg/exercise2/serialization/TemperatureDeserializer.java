package ee.ut.cs.dsg.dsg.exercise2.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.ut.cs.dsg.dsg.exercise2.model.Temperature;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class TemperatureDeserializer implements Deserializer<Temperature> {
    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public Temperature deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        Temperature user = null;
        try {
            user = mapper.readValue(data, Temperature.class);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void close() {

    }
}
