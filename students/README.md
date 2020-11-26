# Working with temperature data: Stream Enrichment


We now extend the temperature example, assuming to keep a configuration for each room.
The configuration contains the preferred temperature value in that room, and it is signed by it's author
via authentication.

Our goal is identifying those rooms whose current temperature is not the preferred one (Anomalies).

### Learning Goals

- Using AVRO and KSQL
- KTable 
- KTable-KStream JOINs

## Before

```bash
kafka-topics --bootstrap-server kafka1:9092 --partitions 2 --create --topic observations 
kafka-topics --bootstrap-server kafka1:9092 --partitions 2 --create --topic capacities 

```

- Run [Observation Producer](./src/main/java/ee/ut/cs/dsg/exercise6/PersonProducer.java)
- Run [Configuration Producer](../ksql/src/main/java/ee/ut/cs/dsg/exercise6/CapacityProducer.java)

## KSQL/STREAMS

## Task 0

- Create the STREAM/TABLE ```OBSERVATIONS``` from the observations topic 
- Create the STREAM/TABLE ```CAPACITIES``` from the capacities topic

```sql
your KSQL statements here
```

## Task 1

Count the number of people for each room
- CREATE A STREAM/TABLE called ```NUMPEPROOM``` that contains the count
- Provide the topology for the KSQL/KStreams program using [https://zz85.github.io/kafka-streams-viz/](https://zz85.github.io/kafka-streams-viz/)

```sql
your KSQL statements here
```

```
your topology code here
```


![Your topology image her](./todo.png)

## Task 2

Count the number of people for each room every 15 seconds
- tumbling in processing time every 15 seconds; 
    - create a stream/table called ```NUMPEPROOM15S``` 
    - Provide the topology for the KSQL/KStreams program

```sql
your KSQL statements here
```

```
your topology code here
```

![Your topology image her](./todo.png)

- tumbling in event time every 15 seconds 
    -  provide evidence of event time: in KSQL using the ```DESCRIBE``` function, 
    in Kafka streams using the timestamp extractor
    -  create a stream/table called ```NUMPEPROOM15S_ET``` 
    -  Provide the topology for the KSQL/KStreams program


```sql
your KSQL statements here
```

```
your topology code here
```

![Your topology image her](./todo.png)

- [OPTIONAL]: redo with hopping window

## Task 3

Identify the rooms that contains more people than the allowed ones (capacity)

- Perform the calculation rolling count (no window)
  + Create a stream/table called ```ANOMALIESROLL``` 
  + Provide the topology for the KSQL/KStreams program


```sql
your KSQL statements here
```

```
your topology code here
```

![Your topology image her](./todo.png)

- perform the calculation using a window of 15 seconds tumbling in processing time
  + Create a stream/table called ```ANOMALIES15S``` 
  + Provide the topology for the KSQL/KStreams program


```sql
your KSQL statements here
```

```
your topology code here
```


![Your topology image her](./todo.png)

HINT: You **can** reuse the tables ```NUMPEPROOM``` and ```NUMPEPROOM15S```  if needed.
HINT: Stream-Table Join vs Table-Table Join

Question: can you join NUMPEPROOM with CAPACITIES? 
Question: can you join NUMPEPROOM15S with CAPACITIES?

