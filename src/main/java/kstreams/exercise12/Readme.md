# Exercise 12: Temperature Average using Kafka Streams.

## Before

Re-run [exercise 5](../../kafka/advanced/exercise6/Readme.md).

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


Calculating the average temperature per room.


![formula](./formula.png)

This suggests we need to keep a local 
count and then a total sum
finally divide it.

*Clearly a stateful computation*

Can we do this in one-pass?

- Let's introduce an helper structure Tuple (Long,Long)
- We can group across the stream per key

![topology](topology.png)
```
Topologies:
   Sub-topology: 0
    Source: KSTREAM-SOURCE-0000000000 (topics: [temperature])
      --> KSTREAM-MAPVALUES-0000000001
    Processor: KSTREAM-MAPVALUES-0000000001 (stores: [])
      --> KSTREAM-AGGREGATE-0000000003
      <-- KSTREAM-SOURCE-0000000000
    Processor: KSTREAM-AGGREGATE-0000000003 (stores: [KSTREAM-AGGREGATE-STATE-STORE-0000000002])
      --> KTABLE-MAPVALUES-0000000004
      <-- KSTREAM-MAPVALUES-0000000001
    Processor: KTABLE-MAPVALUES-0000000004 (stores: [])
      --> KTABLE-TOSTREAM-0000000005
      <-- KSTREAM-AGGREGATE-0000000003
    Processor: KTABLE-TOSTREAM-0000000005 (stores: [])
      --> KSTREAM-PRINTER-0000000006
      <-- KTABLE-MAPVALUES-0000000004
    Processor: KSTREAM-PRINTER-0000000006 (stores: [])
      --> none
      <-- KTABLE-TOSTREAM-0000000005
```