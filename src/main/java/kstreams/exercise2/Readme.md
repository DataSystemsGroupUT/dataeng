# Working with temperature data

## Kafka 101 (Recap)

The temperature observation records is a pair value and the timestamp.
Moreover, we want to carry on the information about the room where the sensor is deployed.

The producer generates the observation every 5 seconds (system time)
and pushes them to a temperature topic.

The partitioning can be customized, for instance per room.

Before starting, recall to create the topic with two partitions.

Either via bash or using the AdminClient


```bash
bin/kafka-topics --bootstrap-server localhost:9092 --create \
                      --partitions 2 \
                      --replication-factor 1 \
                      --topic temperature
```

## Learning Goals

- create custom value and key object
- create a custom serializer/deserializer
- create a custom partitioner
- create a custom serde

A Kafka consumer reads the topic observations from the topic temperature

## Kafka Streams 

Let's try to consumer in streaming now like we did with the first example.

### Dependencies: None

### Learning Goal

- From Serializers to Deserializers to Serdes
- Consuming a topic in streaming