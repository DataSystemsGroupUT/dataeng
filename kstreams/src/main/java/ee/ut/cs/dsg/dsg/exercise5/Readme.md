# Exercise 5: Stream Enrichment


Stream enrichment is a common task in stream processing. It is useful when you have to attach some slowly-evolving metadata to 
an incoming stream. Metadata usually represents contextual information that gives the streaming data some meaning.

During this exercise, we enrich the stream of temperature observation with some metadata about the rooms. We call this metadata
```Configuration``` and it corresponds to the stream preferred temperature.


One we enriched the stream, we filter out all the anomalous rooms where the current temperature is not the requested one.

Finally, we aggregate such cases by key to observe how many room are currently not in the required state.

## Dependencies

- [Exercise 2](../ee.ut.cs.dsg.dsg.ksql.exercise2/Readme.md).
- [exercise 3](../exercise3/Readme.md).

```bash
bin/kafka-topics --bootstrap-server kafka1:9092 --create \
                      --partitions 2 \
                      --replication-factor 1 \
                      --topic temperature
```

## Learning Goals

-  Recap on custom serializers and partitioning
-  KTable
-  KStream and KTable Join


```bash
bin/kafka-topics --bootstrap-server localhost:9092 --create \
                      --partitions 2 \
                      --replication-factor 1 \
                      --topic configuration
```

Using the kafka stream builder we create a KTable from the configuration topic

We simply join the temperature stream and the Ktable to create an enriched stream

The topology shows how the join relies on an external state-storage.

![topology](topology.png)
```
Topologies:
   Sub-topology: 0
    Source: KSTREAM-SOURCE-0000000000 (topics: [temperature])
      --> KSTREAM-LEFTJOIN-0000000004
    Processor: KSTREAM-LEFTJOIN-0000000004 (stores: [configurations-STATE-STORE-0000000001])
      --> KSTREAM-PRINTER-0000000005
      <-- KSTREAM-SOURCE-0000000000
    Source: KSTREAM-SOURCE-0000000002 (topics: [configurations])
      --> KTABLE-SOURCE-0000000003
    Processor: KSTREAM-PRINTER-0000000005 (stores: [])
      --> none
      <-- KSTREAM-LEFTJOIN-0000000004
    Processor: KTABLE-SOURCE-0000000003 (stores: [configurations-STATE-STORE-0000000001])
      --> none
      <-- KSTREAM-SOURCE-0000000002
```

Question: What would happen if we update a configuration?

```java
{"location":"room0"}	{"prefVal":42,"timestamp":1584318440065,"author":"Riccardo"}
{"location":"room1"}	{"prefVal":21,"timestamp":1584318445800,"author":"John Doe"}
{"location":"room2"}	{"prefVal":33,"timestamp":1584318450806,"author":"Jane Doe"}
{"location":"room3"}	{"prefVal":24,"timestamp":1584318455811,"author":"Marvin"}
{"location":"room4"}	{"prefVal":29,"timestamp":1584318460818,"author":"Thor"}
{"location":"room1"}	{"prefVal":42,"timestamp":1584318741707,"author":"Riccardo"}
{"location":"room4"}	{"prefVal":100,"timestamp":1584318747114,"author":"Thor"}
{"location":"room2"}	{"prefVal":42,"timestamp":1584318827423,"author":"Riccardo"}
{"location":"room3"}	{"prefVal":100,"timestamp":1584318832841,"author":"Thor"}
{"location":"room2"}	{"prefVal":42,"timestamp":1584318868259,"author":"Riccardo"}
{"location":"room3"}	{"prefVal":100,"timestamp":1584318873674,"author":"Thor"}
```

```java
[KSTREAM-FILTER-0000000005]: TemperatureKey 'location' room3, RichTemperature{value=(10000,28), configuration=(1584318455811,24,Marvin)}
[KSTREAM-FILTER-0000000005]: TemperatureKey 'location' room4, RichTemperature{value=(12000,33), configuration=(1584318460818,29,Thor)}
[KSTREAM-FILTER-0000000005]: TemperatureKey 'location' room2, RichTemperature{value=(16000,34), configuration=(1584318450806,33,Jane Doe)}
[KSTREAM-FILTER-0000000005]: TemperatureKey 'location' room1, RichTemperature{value=(20000,29), configuration=(1584318445800,21,John Doe)}
[KSTREAM-FILTER-0000000005]: TemperatureKey 'location' room4, RichTemperature{value=(26000,39), configuration=(1584318460818,29,Thor)}
[KSTREAM-FILTER-0000000005]: TemperatureKey 'location' room3, RichTemperature{value=(28000,33), configuration=(1584318455811,24,Marvin)}
[KSTREAM-FILTER-0000000005]: TemperatureKey 'location' room3, RichTemperature{value=(36000,35), configuration=(1584318455811,24,Marvin)}
[KSTREAM-FILTER-0000000005]: TemperatureKey 'location' room1, RichTemperature{value=(56000,25), configuration=(1584318445800,21,John Doe)}
[KSTREAM-FILTER-0000000005]: TemperatureKey 'location' room3, RichTemperature{value=(58000,29), configuration=(1584318455811,24,Marvin)}
[KSTREAM-FILTER-0000000005]: TemperatureKey 'location' room3, RichTemperature{value=(66000,36), configuration=(1584318455811,24,Marvin)}
[KSTREAM-FILTER-0000000005]: TemperatureKey 'location' room3, RichTemperature{value=(68000,34), configuration=(1584318455811,24,Marvin)}
```