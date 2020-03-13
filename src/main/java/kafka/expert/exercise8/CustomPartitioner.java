package kafka.expert.exercise8;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;

public class CustomPartitioner implements Partitioner {

    @Override
    public void configure(Map<String, ?> configs) {
    }

    @Override
    public void close() {
    }

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {

        Location tk = (Location) key;

        List<PartitionInfo> partitionInfos = cluster.partitionsForTopic(topic);
        int size = partitionInfos.size();

        Integer room = tk.getNumber();

        return partitionInfos.get(room % size).partition();

    }

}