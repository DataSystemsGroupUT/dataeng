package exercise6;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import exercise5.model.TemperatureKey;

import java.util.List;
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

        TemperatureKey tk = (TemperatureKey) key;

        List<PartitionInfo> partitionInfos = cluster.partitionsForTopic(topic);
        int size = partitionInfos.size();

        Integer room = Integer.valueOf(tk.getLocation().split("room")[1]);

        return partitionInfos.get(room % size).partition();

    }

}