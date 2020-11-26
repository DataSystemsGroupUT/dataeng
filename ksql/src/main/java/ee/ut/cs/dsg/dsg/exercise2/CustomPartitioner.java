package ee.ut.cs.dsg.dsg.exercise2;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;

public class CustomPartitioner implements Partitioner {

    @Override
    public int partition(String topic, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        String tk = (String) o;
        List<PartitionInfo> partitionInfos = cluster.partitionsForTopic(topic);
        Integer room = Integer.valueOf(tk.split("room")[1]);

        return room % partitionInfos.size();
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
