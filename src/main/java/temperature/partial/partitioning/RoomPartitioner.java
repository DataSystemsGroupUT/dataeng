package temperature.partial.partitioning;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class RoomPartitioner implements Partitioner {

    @Override
    public void configure(Map<String, ?> configs) {
    }

    @Override
    public void close() {
    }

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {


        //TODO implement a naive partitioning mechanism
        return 0;

    }

}