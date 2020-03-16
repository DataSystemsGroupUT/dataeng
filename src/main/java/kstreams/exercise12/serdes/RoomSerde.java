package kstreams.exercise12.serdes;


import kafka.advanced.exercise5.exercise5a.model.Room;
import kafka.advanced.exercise5.exercise5a.serialization.RoomSerializer;
import kafka.advanced.exercise5.exercise5b.deserialization.RoomDeserializer;
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
