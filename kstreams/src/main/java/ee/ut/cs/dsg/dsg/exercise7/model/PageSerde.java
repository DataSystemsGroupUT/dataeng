package ee.ut.cs.dsg.dsg.exercise7.model;

import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.Charset;

public class PageSerde implements Serde<PageView> {

    Gson gson = new Gson();

    @Override
    public Serializer<PageView> serializer() {
        return (topic, data) -> gson.toJson(data).getBytes(Charset.forName("UTF-8"));
    }

    @Override
    public Deserializer<PageView> deserializer() {
        return (topic, data) -> gson.fromJson(new String(data), PageView.class);
    }
}
