package kstreams.exercise12.serdes;


import kafka.advanced.exercise6.solution.exercise6a.model.Temperature;
import kafka.advanced.exercise6.solution.exercise6a.serialization.TemperatureSerializer;
import kafka.advanced.exercise6.solution.exercise6b.deserialization.TemperatureDeserializer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class TemperatureSerde implements Serde<Temperature> {

    TemperatureSerializer serializer = new TemperatureSerializer();
    TemperatureDeserializer deserializer = new TemperatureDeserializer();

    @Override
    public Serializer<Temperature> serializer() {
        return (topic, data) -> serializer.serialize(topic, data);
    }

    @Override
    public Deserializer<Temperature> deserializer() {
        return (topic, data) -> deserializer.deserialize(topic, data);
    }
}
