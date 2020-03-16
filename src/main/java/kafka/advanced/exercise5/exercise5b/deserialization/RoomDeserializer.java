package kafka.advanced.exercise5.exercise5b.deserialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.advanced.exercise5.exercise5a.model.Room;
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
