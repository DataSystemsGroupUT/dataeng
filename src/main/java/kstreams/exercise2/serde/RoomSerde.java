package kstreams.exercise2.serde;


import kstreams.exercise2.model.Room;
import kstreams.exercise2.serialization.RoomSerializer;
import kstreams.exercise2.serialization.RoomDeserializer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class RoomSerde implements Serde<Room> {

    RoomSerializer serializer = new RoomSerializer();
    RoomDeserializer deserializer = new RoomDeserializer();

    @Override
    public Serializer<Room> serializer() {
        return (topic, data) -> serializer.serialize(topic, data);
    }

    @Override
    public Deserializer<Room> deserializer() {
        return (topic, data) -> deserializer.deserialize(topic, data);
    }
}
