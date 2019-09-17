package exercise12;

import exercise5.deserialization.TemperatureValueDeserializer;
import exercise5.model.Temperature;
import exercise5.serialization.TemperatureValueSerializer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class TemperatureSerde implements Serde<Temperature> {

    TemperatureValueSerializer serializer = new TemperatureValueSerializer();
    TemperatureValueDeserializer deserializer = new TemperatureValueDeserializer();

    @Override
    public Serializer<Temperature> serializer() {
        return (topic, data) -> serializer.serialize(topic, data);
    }

    @Override
    public Deserializer<Temperature> deserializer() {
        return (topic, data) -> deserializer.deserialize(topic, data);
    }
}
