package ee.ut.cs.dsg.dsg.exercise7.model;

import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.Charset;

public class RegionalPageSerde implements Serde<RegionalView> {

    Gson gson = new Gson();

    @Override
    public Serializer<RegionalView> serializer() {
        return (topic, data) -> gson.toJson(data).getBytes(Charset.forName("UTF-8"));
    }

    @Override
    public Deserializer<RegionalView> deserializer() {
        return (topic, data) -> gson.fromJson(new String(data), RegionalView.class);
    }
}
