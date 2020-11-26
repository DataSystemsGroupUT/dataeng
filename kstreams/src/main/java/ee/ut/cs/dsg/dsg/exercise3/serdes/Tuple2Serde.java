package ee.ut.cs.dsg.dsg.exercise3.serdes;

import ee.ut.cs.dsg.dsg.exercise3.model.Tuple;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class Tuple2Serde implements Serde<Tuple> {

    TupleSerializer serializer = new TupleSerializer();
    TupleDeserializer deserializer = new TupleDeserializer();

    @Override
    public Serializer<Tuple> serializer() {
        return serializer;
    }

    @Override
    public Deserializer<Tuple> deserializer() {
        return deserializer;
    }
}
