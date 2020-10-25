package kafka.advanced.exercise6.exercise6b.deserialization;

import kafka.advanced.exercise6.solution.exercise6a.model.Room;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class RoomDeserializer implements Deserializer<Room> {
    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public Room deserialize(String topic, byte[] data) {
        return null;
    }

    @Override
    public void close() {

    }
}
