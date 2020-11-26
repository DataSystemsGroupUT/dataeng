# Temperature Analysis: Windowing Average using Kafka Streams.

## Dependencies

- [Exercise 2](../ee.ut.cs.dsg.dsg.ksql.exercise2/Readme.md).
- [exercise 3](../exercise3/Readme.md).

```bash
bin/kafka-topics --bootstrap-server kafka1:9092 --create \
                      --partitions 2 \
                      --replication-factor 1 \
                      --topic temperature
```

Compute the moving average of the last 15 seconds
using processing time or event time.


## Learning Goals

- window-based continuous computation
- reporting
- KTABLE


### Using Processing Time

- What results are produced?
- When are produced?
- How are reported?

### Using Event Time

To use Event-Time we specify a timestamp extractor using 
the appropriate interface

```java
public class TemperatureTimestampExtractor implements TimestampExtractor {
       
           @Override
           public long extract(ConsumerRecord<Object, Object> record, long partitionTime) {
               if (record.prefVal() instanceof Temperature) {
                   return ((Temperature) record.prefVal()).getTimestamp();
               }
       
               throw new IllegalArgumentException("TimestampExtractor cannot recognize the record prefVal " + record.prefVal());
       
           }
       }
```

and we configure the application accordingly

```java

props.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, TemperatureTimestampExtractor.class);

```



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

