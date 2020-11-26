package ee.ut.cs.dsg.dsg.exercise2;

import ee.ut.cs.dsg.dsg.exercise2.model.Room;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

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

        Room tk = (Room) key;

        List<PartitionInfo> partitionInfos = cluster.partitionsForTopic(topic);
        int size = partitionInfos.size();

        Integer room = Integer.valueOf(tk.getLocation().split("room")[1]);

        return partitionInfos.get(room % size).partition();

    }

}