package ee.ut.cs.dsg.dsg.exercise3.serdes;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.ut.cs.dsg.dsg.exercise3.model.Tuple;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class TupleSerializer implements Serializer<Tuple> {
    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, Tuple data) {
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
