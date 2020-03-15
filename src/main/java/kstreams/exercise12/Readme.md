# Exercise 12: Temperature Average using Kafka Streams.

## Before

Re-run [exercise 5](../../kafka/advanced/exercise5/Readme.md).

or

- create a TemperatureKey and Temperature classes
- create a temperature topic

```bash
bin/kafka-topics --bootstrap-server localhost:9092 --create \
                      --partitions 2 \
                      --replication-factor 2 \
                      --topic temperature
```

## TODOs

- define a SerDe, i.e., Serializer and Deserializers

A KafkaStream application typically does both the tasks, so you must implement them both
of them and a wrapper class 

```java
public class TemperatureSerde implements Serde<Temperature>...
```
You can re-use you previously defined serializers and deserializers from exercise 5


Calculating the average


![formula](./formula.png)

This suggests we need to keep a local 
count and then a total sum
finally divide it.

*Clearly a stateful computation*


Can we do this in one-pass?

