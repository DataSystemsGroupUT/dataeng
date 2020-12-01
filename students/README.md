# KSQL/Kafka Streams Assignment


## Before

# LOOK AT ALL THE TASKS BEFORE STARTING!

```bash
kafka-topics --bootstrap-server kafka1:9092 --partitions 2 --create --topic observations 
kafka-topics --bootstrap-server kafka1:9092 --partitions 2 --create --topic capacities 

```

- Run [People Producer](./src/main/java/ee/ut/cs/dsg/PeopleProducer.java)
- Run [Capacity Producer](./src/main/java/ee/ut/cs/dsg/CapacityProducer.java)

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

- Perform the calculation in a rolling manner (no window)
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

