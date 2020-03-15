package kstreams.exercise12.serdes;

import kstreams.exercise12.model.Tuple;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class Tuple2Serde implements Serde<Tuple> {
    @Override
    public Serializer<Tuple> serializer() {
        return null;
    }

    @Override
    public Deserializer<Tuple> deserializer() {
        return null;
    }
}
