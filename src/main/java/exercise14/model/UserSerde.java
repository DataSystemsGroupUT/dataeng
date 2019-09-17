package exercise14.model;

import com.google.gson.Gson;
import exercise13.model.Order;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.Charset;

public class UserSerde implements Serde<Order> {

    Gson gson = new Gson();

    @Override
    public Serializer<Order> serializer() {
        return (topic, data) -> gson.toJson(data).getBytes(Charset.forName("UTF-8"));
    }

    @Override
    public Deserializer<Order> deserializer() {
        return (topic, data) -> gson.fromJson(new String(data), Order.class);
    }
}
