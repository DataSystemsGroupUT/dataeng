package ee.ut.cs.dsg.dsg.exercise5.serialization;

import ee.ut.cs.dsg.dsg.exercise5.model.RichTemperature;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class RichTemperatureSerde implements Serde<RichTemperature> {

    RichTemperatureSerializer serializer = new RichTemperatureSerializer();
    RichTemperatureDeserializer deserializer = new RichTemperatureDeserializer();

    @Override
    public Serializer<RichTemperature> serializer() {
        return serializer;
    }

    @Override
    public Deserializer<RichTemperature> deserializer() {
        return deserializer;
    }
}
