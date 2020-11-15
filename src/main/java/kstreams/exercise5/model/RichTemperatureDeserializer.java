package kstreams.exercise5.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class RichTemperatureDeserializer implements Deserializer<RichTemperature> {
    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public RichTemperature deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        RichTemperature t = null;
        try {
            t = mapper.readValue(data, RichTemperature.class);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return t;
    }

    @Override
    public void close() {

    }
}
