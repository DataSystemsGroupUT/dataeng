package ee.ut.cs.dsg.dsg.exercise3.serdes;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.ut.cs.dsg.dsg.exercise3.model.Tuple;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class TupleDeserializer implements Deserializer<Tuple> {
    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public Tuple deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        Tuple t = null;
        try {
            t = mapper.readValue(data, Tuple.class);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return t;
    }

    @Override
    public void close() {

    }
}
