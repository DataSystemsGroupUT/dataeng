package kstreams.exercise12.serdes;

import kafka.exercise5.deserialization.TemperatureKeyDeserializer;
import kafka.exercise5.model.TemperatureKey;
import kafka.exercise5.serialization.TemperatureKeySerializer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class TemperatureKeySerde implements Serde<TemperatureKey> {

    TemperatureKeySerializer serializer = new TemperatureKeySerializer();
    TemperatureKeyDeserializer deserializer = new TemperatureKeyDeserializer();

    @Override
    public Serializer<TemperatureKey> serializer() {
        return (topic, data) -> serializer.serialize(topic, data);
    }

    @Override
    public Deserializer<TemperatureKey> deserializer() {
        return (topic, data) -> deserializer.deserialize(topic, data);
    }
}
