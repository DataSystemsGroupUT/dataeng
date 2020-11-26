package ee.ut.cs.dsg.dsg.exercise5.serialization;

import ee.ut.cs.dsg.dsg.exercise5.model.Configuration;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class ConfigSerde implements Serde<Configuration> {

    ConfigSerializer serializer = new ConfigSerializer();
    ConfigDeserializer deserializer = new ConfigDeserializer();

    @Override
    public Serializer<Configuration> serializer() {
        return serializer;
    }

    @Override
    public Deserializer<Configuration> deserializer() {
        return deserializer;
    }
}
