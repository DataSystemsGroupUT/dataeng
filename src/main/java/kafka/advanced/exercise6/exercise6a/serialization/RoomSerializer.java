package kafka.advanced.exercise6.exercise6a.serialization;

import kafka.advanced.exercise6.exercise6a.model.Room;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class RoomSerializer implements Serializer<Room> {
    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, Room data) {
        return null;
    }

    @Override
    public void close() {

    }
}
