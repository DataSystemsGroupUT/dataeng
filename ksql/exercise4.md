# Working with temperature data: Processing Time Windowing

### Learning Goals

- Using AVRO and KSQL
- Moving Average
- Processing Time Windowing

## Before

```bash
kafka-topics --bootstrap-server kafka1:9092 --partitions 2 --create --topic temperature_avro 

```

Run [Observation Producer](src/main/java/ee/ut/cs/dsg/dsg/exercise2/ObservationProducer.java)

## KSQL

```sql
CREATE STREAM TEMPERATURE (ROOM VARCHAR KEY, ID BIGINT, PREFVAL DOUBLE, MEASUREMENT STRING, TIMESTAMP BIGINT) 
WITH (kafka_topic='temperature_avro', value_format='avro');
```

```sql
DESCRIBE EXTENDED TEMPERATURE;
```

```
Name                 : TEMPERATURE
Type                 : STREAM
Timestamp field      : Not set - using <ROWTIME>
Key format           : KAFKA
Value format         : AVRO
Kafka topic          : temperature_avro2 (partitions: 1, replication: 1)
Statement            : CREATE STREAM TEMPERATURE (ROOM STRING KEY, ID BIGINT, PREFVAL DOUBLE, MEASUREMENT STRING, TIMESTAMP BIGINT) WITH (KAFKA_TOPIC='temperature_avro2', KEY_FORMAT='KAFKA', VALUE_FORMAT='AVRO');

 Field       | Type
--------------------------------------
 ROOM        | VARCHAR(STRING)  (key)
 ID          | BIGINT
 PREFVAL     | DOUBLE
 MEASUREMENT | VARCHAR(STRING)
 TIMESTAMP   | BIGINT
--------------------------------------
```

## Controlling reporting using Tumbling Windows

```sql
CREATE TABLE TEMP_AVG AS 
SELECT ROOM AS ROOM, AVG(PREFVAL) AS AVG 
FROM TEMPERATURE WINDOW TUMBLING (SIZE 15 SECONDS)
GROUP BY ROOM EMIT CHANGES;
```__

```sql
EXPLAIN CTAS_TEMP_AVG_9;
```

### Execution plan

```
 > [ SINK ] | Schema: ROOM STRING KEY, AVG DOUBLE | Logger: CTAS_TEMP_AVG_9.TEMP_AVG_PT
     > [ PROJECT ] | Schema: ROOM STRING KEY, AVG DOUBLE | Logger: CTAS_TEMP_AVG_9.Aggregate.Project
         > [ AGGREGATE ] | Schema: ROOM STRING KEY, ROOM STRING, PREFVAL DOUBLE, KSQL_AGG_VARIABLE_0 DOUBLE, WINDOWSTART BIGINT, WINDOWEND BIGINT | Logger: CTAS_TEMP_AVG_9.Aggregate.Aggregate
             > [ GROUP_BY ] | Schema: ROOM STRING KEY, ROOM STRING, PREFVAL DOUBLE | Logger: CTAS_TEMP_AVG_9.Aggregate.GroupBy
                 > [ PROJECT ] | Schema: ROOM STRING KEY, ROOM STRING, PREFVAL DOUBLE | Logger: CTAS_TEMP_AVG_9.Aggregate.Prepare
                     > [ SOURCE ] | Schema: ROOM STRING KEY, ID BIGINT, PREFVAL DOUBLE, MEASUREMENT STRING, TIMESTAMP BIGINT, ROWTIME BIGINT, ROOM STRING | Logger: CTAS_TEMP_AVG_9.KsqlTopic.Source
```

### Processing topology
```
Topologies:
   Sub-topology: 0
    Source: KSTREAM-SOURCE-0000000000 (topics: [temperature_avro])
      --> KSTREAM-TRANSFORMVALUES-0000000001
    Processor: KSTREAM-TRANSFORMVALUES-0000000001 (stores: [])
      --> Aggregate-Prepare
      <-- KSTREAM-SOURCE-0000000000
    Processor: Aggregate-Prepare (stores: [])
      --> KSTREAM-AGGREGATE-0000000003
      <-- KSTREAM-TRANSFORMVALUES-0000000001
    Processor: KSTREAM-AGGREGATE-0000000003 (stores: [Aggregate-Aggregate-Materialize])
      --> Aggregate-Aggregate-ToOutputSchema
      <-- Aggregate-Prepare
    Processor: Aggregate-Aggregate-ToOutputSchema (stores: [])
      --> Aggregate-Aggregate-WindowSelect
      <-- KSTREAM-AGGREGATE-0000000003
    Processor: Aggregate-Aggregate-WindowSelect (stores: [])
      --> Aggregate-Project
      <-- Aggregate-Aggregate-ToOutputSchema
    Processor: Aggregate-Project (stores: [])
      --> KTABLE-TOSTREAM-0000000007
      <-- Aggregate-Aggregate-WindowSelect
    Processor: KTABLE-TOSTREAM-0000000007 (stores: [])
      --> KSTREAM-SINK-0000000008
      <-- Aggregate-Project
    Sink: KSTREAM-SINK-0000000008 (topic: TEMP_AVG)
      <-- KTABLE-TOSTREAM-0000000007
```

# Working with temperature data: Event Time Windowing

### Learning Goals

- Using AVRO and KSQL
- Moving Average
- Event Time Windowing
- HOPPING windows


## BEFORE

Let's delete the former stream

```sql
DROP STREAM TEMPERATURE;
```

## KSQL

```sql
CREATE STREAM TEMPERATURE (ROOM VARCHAR KEY, ID BIGINT, PREFVAL DOUBLE, MEASUREMENT STRING, TIMESTAMP BIGINT) 
WITH (kafka_topic='temperature_avro', value_format='avro', timestamp='timestamp');
```


```sql
DESCRIBE EXTENDED TEMPERATURE;
```

```
Name                 : TEMPERATURE
Type                 : STREAM
Timestamp field      : TIMESTAMP <-
Key format           : KAFKA
Value format         : AVRO
Kafka topic          : temperature_avro (partitions: 1, replication: 1)
Statement            : CREATE STREAM TEMPERATURE (ROOM STRING KEY, ID BIGINT, PREFVAL DOUBLE, MEASUREMENT STRING, TIMESTAMP BIGINT) WITH (KAFKA_TOPIC='temperature_avro2', KEY_FORMAT='KAFKA', VALUE_FORMAT='AVRO');

 Field       | Type
--------------------------------------
 ROOM        | VARCHAR(STRING)  (key)
 ID          | BIGINT
 PREFVAL     | DOUBLE
 MEASUREMENT | VARCHAR(STRING)
 TIMESTAMP   | BIGINT
--------------------------------------
```

Comparing with [Exercise 4](./exercise.md), the timestamp metadata changed from ```Timestamp field      : Not set - using <ROWTIME>
``` to ```Timestamp field      : TIMESTAMP```.

### Controlling reporting using Hopping Windows

```sql
CREATE TABLE TEMP_AVG_HOP AS 
SELECT ROOM AS ROOM, AVG(PREFVAL) AS AVG 
FROM TEMPERATURE WINDOW HOPPING (SIZE 30 SECONDS, ADVANCE BY 10 SECONDS)
GROUP BY ROOM EMIT CHANGES;
```

```
Message
--------------------------------------------
 Created query with ID CTAS_TEMP_AVG_HOP_11
--------------------------------------------
```


```sql
EXPLAIN CTAS_TEMP_AVG_HOP_11;
```

### Execution plan

```
> [ SINK ] | Schema: ROOM STRING KEY, AVG DOUBLE | Logger: CTAS_TEMP_AVG_HOP_11.TEMP_AVG_HOP
     > [ PROJECT ] | Schema: ROOM STRING KEY, AVG DOUBLE | Logger: CTAS_TEMP_AVG_HOP_11.Aggregate.Project
         > [ AGGREGATE ] | Schema: ROOM STRING KEY, ROOM STRING, PREFVAL DOUBLE, KSQL_AGG_VARIABLE_0 DOUBLE, WINDOWSTART BIGINT, WINDOWEND BIGINT | Logger: CTAS_TEMP_AVG_HOP_11.Aggregate.Aggregate
             > [ GROUP_BY ] | Schema: ROOM STRING KEY, ROOM STRING, PREFVAL DOUBLE | Logger: CTAS_TEMP_AVG_HOP_11.Aggregate.GroupBy
                 > [ PROJECT ] | Schema: ROOM STRING KEY, ROOM STRING, PREFVAL DOUBLE | Logger: CTAS_TEMP_AVG_HOP_11.Aggregate.Prepare
                     > [ SOURCE ] | Schema: ROOM STRING KEY, ID BIGINT, PREFVAL DOUBLE, MEASUREMENT STRING, TIMESTAMP BIGINT, ROWTIME BIGINT, ROOM STRING | Logger: CTAS_TEMP_AVG_HOP_11.KsqlTopic.Source
```

### Processing topology
```
Topologies:
   Sub-topology: 0
    Source: KSTREAM-SOURCE-0000000000 (topics: [temperature_avro])
      --> KSTREAM-TRANSFORMVALUES-0000000001
    Processor: KSTREAM-TRANSFORMVALUES-0000000001 (stores: [])
      --> Aggregate-Prepare
      <-- KSTREAM-SOURCE-0000000000
    Processor: Aggregate-Prepare (stores: [])
      --> KSTREAM-AGGREGATE-0000000003
      <-- KSTREAM-TRANSFORMVALUES-0000000001
    Processor: KSTREAM-AGGREGATE-0000000003 (stores: [Aggregate-Aggregate-Materialize])
      --> Aggregate-Aggregate-ToOutputSchema
      <-- Aggregate-Prepare
    Processor: Aggregate-Aggregate-ToOutputSchema (stores: [])
      --> Aggregate-Aggregate-WindowSelect
      <-- KSTREAM-AGGREGATE-0000000003
    Processor: Aggregate-Aggregate-WindowSelect (stores: [])
      --> Aggregate-Project
      <-- Aggregate-Aggregate-ToOutputSchema
    Processor: Aggregate-Project (stores: [])
      --> KTABLE-TOSTREAM-0000000007
      <-- Aggregate-Aggregate-WindowSelect
    Processor: KTABLE-TOSTREAM-0000000007 (stores: [])
      --> KSTREAM-SINK-0000000008
      <-- Aggregate-Project
    Sink: KSTREAM-SINK-0000000008 (topic:  TEMP_AVG_HOP)
      <-- KTABLE-TOSTREAM-0000000007
```

