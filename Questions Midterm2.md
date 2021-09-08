
### Map Reduce

4) In the following example, what is the output of Mapper 3 (change sentence)

	![inline](./attachments/WordCountMapper1.png)

5) what is the output of Combiner 3 (change number)

	![inline](./attachments/WordCountMapper2.png)


ANSWER: B


### Kafka

What is Apache Kafka?

A) a distributed and replicated message queue
B) a German-speaking Bohemian novelist
C) a supporting system for microservices
D)  a key-value store

ANSWER: A

What is Kafka Streams?
A) an functional DSL built on top of Kafka Streams
B) a library for complex event processing
C) a way to build data pipeline in python
D) a library for data cleansing

ANSWER: A

What is KSQL?
A) an SQL-like streaming DSL built on top of Kafka Streams
B) a noSQL database
C) a plugin for Postgress
D) a language for complex event recognition

ANSWER: A

In Kafka, what are the alternative to cleanup a topic

A) deletion by compaction
B) deletion by retention T
C) deletion by index
D) deletion by consumption

ANSWER: A

matching with the theoretical framework

CQL - KSQL
Dataflow - Topology Kafka Streams
Actor Model 	- Producer/Consumer
Stream Table Duality - Kafka Streams DSL




34) Provide the correct relationship - 1:1, 1:N, N:1, or N:N -

	-   Broker to Partition - ?
	-   Key to Partition - ?
	-   Producer to Topic - ?
	-   Consumer Group to Topic - ?
	-   Consumer (in a Consumer Group) to Partition - ?

35) Provide the correct relationship - 1:1, 1:N, N:1, or N:N -

	-   Broker to Partition - N:N
	-   Key to Partition - N:1
	-   Producer to Topic - N:N
	-   Consumer Group to Topic - N:N
	-   Consumer (in a Consumer Group) to Partition - 1:N

### Streaming



41) Streaming Operators 

-   Time-based Window Operators - Stream-to-Relation Operator
-   Count based Window operator - Stream-to-Relation Operator
-   Data Driven window operators - Stream-to-Relation Operator
-   Extension of relational algebra - Relation-to-Relation Operator
-   Rstream - Relation-to-Stream operator
-   Istream - Relation-to-Stream operator
-   Dstream - Relation-to-Stream operator

42) steam and table

![inline](https://www.michael-noll.com/assets/uploads/stream-table-animation-latestLocation.gif)

![inline](./streamtable-questions.png)

a. x=Paris,y=Sydney,z=Lima,t=Berlin
b. x=Sydney,y=Sydney,z=Lima,t=Lima
c. x=Paris,y=Berlin,z=Lima,t=Berlin
d. x=Berlin,y=Lima,z=Sydney,t=Paris

```sql
SELECT NAME,PLACE
FROM PEOPLE-PLACES
GROUP BY NAME
```

```java
KTable<String, String> wherenow = 
	.groupByKey()
	.reduce((oldPlace, newPlace) -> newPlace);
```
---
43) ![inline](https://www.michael-noll.com/assets/uploads/stream-table-animation-numVisitedLocations.gif)

![inline](./streamtable-questions.png)

a. x=1,y=1,z=2,t=3
b. x=1,y=1,z=2,t=2
c. x=0,y=1,z=1,t=2
d. x=2,y=2,z=3,t=1

```sql
SELECT COUNT(*), NAME
FROM PEOPLE-PLACES
GROUP BY NAME
```

```java
KTable<String, Long> wherenow = 
	.groupByKey()
	.count() 
```

  
## Data Preprocessing



### Exercise

### Kafka

![[attachments/exercise kafka.png]]

There are two highways, North(N) and South(S) each with four lanes.
The highways is equipped with sensors that detect when a vehicle
changes lane

Message Contains |plate|highway|fromLane|toLane|timestamp|
- fromLane and toLane uniquely identify the lane across highways 
- if the fromlane or the tolane are empty it means the vehicle entered or exited the highway.

 | highway | fromLane | toLane | plate  | timestamp |
 | ------- | -------- | ------ | ------ | --------- |
 | S       | S1       | S2     | GHG424 | 00001     |
 | N       | S4       | S3     | hey454 | 00003     |

Message Key [multichoice]
- highway
- plate
- fromLane
- toLane
- timestamp

Message Value
- highway
- plate
- fromLane
- toLane
- timestamp

We want to solve the following tasks: 

- Task1: calculate the average number of vehicles that are currently going north or south
- Task2: calculate the average number of vehicles that are currently in each lane of each highway

Using Kafka, design the data infrastructure what supports such analysis.

What Topics

- an ingestion topic, a topic for task 1 results, and a topic for task 2's results (my solution)
- a topic for each lane
- a topic for the North highway and a topic for the North highway

What partitions: chose the options (one answer and one X) that works for you according to the answer to the previous question. 

- all the topic have the same partitions: X
- the ingestion topic has X partitions (one for each lane); 
- the task1 and task2 result topics have X partitions
- the lane topics have X partitions each
- the highway topics have X partitions each
-  X=8
-  X=4
-  X=2
-  X=Y (to specify in the justification)

Justify your choice describing how you imagine the data transfer across topics and commenting on the parallelism


### KSQL

![[attachments/exercise kafka.png]]

There are two highways, North(N) and South(S) each with four lanes.
The highways is equipped with sensors that detect when a vehicle
changes lane

Message Contains |plate|fromLane|toLane|timestamp|
if the fromlane or the tolane are empty it means the vehicle entered or exited the highway

Message Contains |plate|highway|fromLane|toLane|timestamp|
- fromLane and toLane uniquely identify the lane across highways 
- if the fromlane or the tolane are empty it means the vehicle entered or exited the highway.

 | plate  | highway | fromLane | toLane | timestamp |
 | ------ | ------- | -------- | ------ | --------- |
 | GHG424 | S       | S1       | S2     | 00001     |
 | hey454 | N       | N4       | N3     | 00003     |

	The highway management company maintains a list of work in progress that may affect the various lanes capacity.

| Highway | Lane | Capacity |
| ------- | ---- | -------- |
| S       | S1   | 4        |
| N       | N3     |    10      |

- you can assume the observations and the wip notification topics to be co-partitioned

KSQL Task1: create streams/table for the observation and the capacity sources

A) CREATE STREAM OBSERVATIONS (HIGHWAY VARCHAR, LANE VARCHAR, PLATE VARCHAR, TIMESTAMP BIGINT ) WITH (kafka_topic='observations', value_format='avro');
B) CREATE STREAM WIPs (LANE VARCHAR KEY, CAPACITY BIGINT, TIMESTAMP BIGINT) WITH (kafka_topic='wips', value_format='avro');
C) CREATE STREAM (HIGHWAY VARCHAR, LANE VARCHAR, PLATE VARCHAR, TIMESTAMP BIGINT ) WITH (kafka_topic='observations', value_format='avro');
D) CREATE STREAM WIPs (LANE VARCHAR KEY, CAPACITY BIGINT, TIMESTAMP BIGINT) WITH (kafka_topic='wips', value_format='avro');

ANSWER: A

KSQL Task2: calculate the current number of cars per lane 

A) CREATE TABLE VEHICLECOUNT AS SELECT COUNT(*), TOLANE FROM OBSERVATION GROUP BY TOLANE EMIT CHANGES
B) CREATE STREAM VEHICLECOUNT AS SELECT COUNT(*), TOLANE FROM OBSERVATION GROUP BY TOLANE EMIT CHANGES
C) CREATE TABLE VEHICLECOUNT AS SELECT AVG(*), fromLANE FROM OBSERVATION GROUP BY fromLane EMIT CHANGES
 CREATE STREAM VEHICLECOUNT AS SELECT COUNT(*) FROM OBSERVATION
D) CREATE TABLE VEHICLECOUNT AS SELECT MAX(*), TOLANE FROM OBSERVATION GROUP BY TOLANE

ANSWER: A

KSQL Task3: calculate the number of per lane every 15minutes, using processing time

A) CREATE TABLE COUNT15MIN SELECT COUNT(*), TOLANE FROM OBSERVATION WINDOW TUMBLING (SIZE 15 MINUTES) GROUP BY TOLANE EMIT CHANGES
B) CREATE TABLE COUNT15MIN SELECT COUNT(*), TOLANE FROM OBSERVATION HOPPING (SIZE 15 MINUTES, ADVANCE BY 5 MINUTES) GROUP BY TOLANE EMIT CHANGES
C) CREATE STREAM COUNT15MIN SELECT COUNT(*), FROMLANE FROM OBSERVATION WINDOW TUMBLING (SIZE 15 MINUTES) GROUP BY FROMLANE EMIT CHANGES
D) CREATE STREAM COUNT15MIN SELECT COUNT(*), FROMLANE FROM OBSERVATION HOPPING (SIZE 15 MINUTES, ADVANCE BY 5 MINUTES) GROUP BY FROMLANE EMIT CHANGES

ANSWER: A 

KSQL Task4: identify the lanes that risk a congestion every 15 minutes 

A) CREATE TABLE CONGESTIONS AS SELECT LANE, COUNT(1) as VEHICLECOUNT from  observations o join WIPS W ON W.lane=O.lane WINDOW TUMBLING (SIZE 15 SECONDS) GROUP BY c.LANE HAVING COUNT(1) > C.CAP;
B) CREATE TABLE CONGESTIONS AS SELECT LANE, COUNT(1) as VEHICLECOUNT from  COUNT15MIN O JOIN WIPS W ON W.lane=O.LANE GROUP BY c.LANE HAVING COUNT(1) > C.CAP;
C) CREATE STREAM CONGESTIONS_TEMP AS SELECT *
   from  observations o LEFT JOIN WIPS W ON W.lane=O.lane ;
   CREATE TABLE CONGESTIONS AS COUNT(*), LANE
   FROM CONGESTIONS_TEMP WINDOW TUMBLING (SIZE 15 MINUTES)
   GROUP BY LANE
   HAVING COUNT(*)>CAP

ANSWER: A





### Scenario:

The following graph represents a data pipeline where:

1. A cluster is spawned
2. A query is made in order to pull some data from a database
3. A decision is made about how much processing this data needs
4. Processing happens
5. The cluster is shut down

![](./exercise_dag.png)

### Exercises about the scenario:

You will have two tasks to solve:

Which operator should the task `make_decision` have in order to make sure that depending on the result of its upstream task, one of its two downstream ones will be skipped?

A) BashOperator
B) PostgresOperator
C) BranchPythonOperator
D) BaseHook

Answer: C

Which trigger rule should `shut_down_cluster` have such that irrespective of its upstream tasks' termination it will always be executed?

A) none_failed
B) none_skipped
C) all_failed
D) all_done

Answer: D