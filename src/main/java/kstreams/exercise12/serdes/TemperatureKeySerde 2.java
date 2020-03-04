package kstreams.exercise12.serdes;

import kafka.advanced.exercise5.exercise5b.deserialization.TemperatureKeyDeserializer;
import kafka.advanced.exercise5.exercise5a.model.TemperatureKey;
import kafka.advanced.exercise5.exercise5a.serialization.TemperatureKeySerializer;
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
