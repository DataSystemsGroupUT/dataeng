package ee.ut.cs.dsg.dsg.exercise2.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.ut.cs.dsg.dsg.exercise2.model.Room;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class RoomDeserializer implements Deserializer<Room> {
    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public Room deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        Room user = null;
        try {
            user = mapper.readValue(data, Room.class);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void close() {

    }
}
