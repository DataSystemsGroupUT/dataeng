# Working with temperature data using avro


## Learning Goals

- create custom value and key object
- create a custom serializer/deserializer
- create a custom partitioner
- create a custom serde

A Kafka consumer reads the topic observations from the topic temperature


## Kafka 101 (Recap)

The temperature observation records is a pair value and the timestamp.
Moreover, we want to carry on the information about the room where the sensor is deployed.

The producer generates the observation every 5 seconds (system time)
and pushes them to a temperature topic.

The partitioning can be customized, for instance per room.

Before starting, recall to create the topic with two partitions.

Either via bash or using the AdminClient


```bash
kafka-topics --bootstrap-server kafka1:9092 --create \
                      --partitions 2 \
                      --replication-factor 1 \
                      --topic temperature
```


```json
{"namespace": "ksql.ee.ut.cs.dsg.dsg.ksql.exercise2",
 "type": "record",
 "name": "Observation",
 "fields": [
     {"name": "id", "type": "long", "doc" : "The observation id"},
     {"name": "prefVal", "type": "double", "doc" : "The actual measurement from the sensor"},
     {"name": "measurement", "type": "string", "doc" : "The measurement type, e.g., temperature"},
     {"name": "timestamp", "type": "long", "doc" : "The measurement timestamp"}
 ]
}
```
