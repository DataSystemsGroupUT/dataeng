package ee.ut.cs.dsg.dsg.exercise5.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.ut.cs.dsg.dsg.exercise5.model.Configuration;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class ConfigDeserializer implements Deserializer<Configuration> {
    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public Configuration deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        Configuration user = null;
        try {
            user = mapper.readValue(data, Configuration.class);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void close() {

    }
}
