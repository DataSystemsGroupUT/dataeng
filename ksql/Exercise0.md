# Exercise 0: Piping (|) one topic into another

In this ice breaker exercise we just read from an input topic and write from an ouput topic.

## Learning Goals

- break the ice with KSQL 
- KSQL DB Lang
- KSQL DDL (Streams)
- Streaming Queries (EMIT Changes)

## Before Starting (Assuming a Docker Setup)



```bash
kafka-topics --bootstrap-server kafka1:9092 --create --topic input 

kafka-topics --bootstrap-server kafka1:9092 --create --topic output
```

```bash
kafka-console-producer --broker-list kafka1:9092 \
                                --property parse.key=true \
                                --property key.separator=, \
                                --topic input
                                
```
WRITING

{"key":1,"val":"A"}
{"key":2,"val":"B"}
{"key":3,"val":"C"}
{"key":4,"val":"D"}

```bash
kafka-console-consumer --bootstrap-server kafka1:9092 \
    --from-beginning --property print.key=true \
    --topic output
                                
```

## KSQL

```SHOW TOPICS``` lists the available topics

```SHOW STREAMS```

```SHOW TABLES```


```PRINT 'input' FROM BEGINNING;```

```
Key format: JSON or KAFKA_STRING
Value format: KAFKA_STRING
rowtime: 2020/11/17 20:11:54.898 Z, key: <null>, value: {"key":1,"val":"A"}
rowtime: 2020/11/17 20:12:00.949 Z, key: <null>, value: {"key":2,"val":"B"}
rowtime: 2020/11/17 20:12:04.432 Z, key: <null>, value: {"key":3,"val":"C"}
rowtime: 2020/11/17 20:12:07.085 Z, key: <null>, value: {"key":4,"val":"D"}
```

### DDL STREAMS

```sql
CREATE STREAM input (key INTEGER, val VARCHAR)
  WITH (kafka_topic='input', value_format='json');
```

```DESCRIBE input;```

| Field | Type
|-------|-----------------
| KEY   | INTEGER
| VAL   | VARCHAR(STRING)
 
```DESCRIBE EXTENDED input;```

```
Name                 : INPUT
Type                 : STREAM
Timestamp field      : Not set - using <ROWTIME>
Key format           : KAFKA
Value format         : JSON
Kafka topic          : input (partitions: 1, replication: 1)
Statement            : CREATE STREAM INPUT (KEY INTEGER, VAL STRING) WITH (KAFKA_TOPIC='input', KEY_FORMAT='KAFKA', VALUE_FORMAT='JSON');
```
 Field | Type
-------|-----------------
 KEY   | INTEGER
 VAL   | VARCHAR(STRING)
-------------------------
```
Local runtime statistics
------------------------
```



### Insertion
```sql
INSERT INTO INPUT  (key,val) VALUES (5,'E');
```

```PRINT 'input' FROM BEGINNING;```

```
Key format: JSON or KAFKA_STRING
Value format: KAFKA_STRING
rowtime: 2020/11/17 20:11:54.898 Z, key: <null>, value: {"key":1,"val":"A"}
rowtime: 2020/11/17 20:12:00.949 Z, key: <null>, value: {"key":2,"val":"B"}
rowtime: 2020/11/17 20:12:04.432 Z, key: <null>, value: {"key":3,"val":"C"}
rowtime: 2020/11/17 20:12:07.085 Z, key: <null>, value: {"key":4,"val":"D"}
rowtime: 2020/11/17 20:15:58.686 Z, key: <null>, value: {"KEY":5,"VAL":"E"} <-
```

inserted data follow are serialized as in the schema.

```SQL
SELECT * FROM INPUT EMIT CHANGES; 
```
returns

```
+--------------------------------------------------------------------------------------------------------------------+
|KEY                                                       |VAL                                                       |
+--------------------------------------------------------------------------------------------------------------------+
|1                                                         |A                                                         |
|2                                                         |B                                                         |
|3                                                         |C                                                         |
|4                                                         |D                                                         |
|5                                                         |E                                                         |
```


In order to pipe the input stream into the output steam we simply do:
```sql
CREATE STREAM output AS
SELECT * FROM input EMIT CHANGES;
```

```BASH
 Message
-------------------------------------
 Created query with ID CSAS_OUTPUT_1
-------------------------------------
```

- EMIT CHANGES is a result modifier that informs ksqlDB that the caller wants to receive incremental changes to the query result as it runs perpetually.
- the new stream is backuped automatically by a topic which is co-partitioned with the input topic

```SHOW topics;```

 Kafka Topic | Partitions | Partition Replicas
-------------|------------|-------------------
 OUTPUT      | 1          | 1
 input       | 1          | 1
-----------------------------------------------

```
SHOW QUERIES;
```

```
 Query ID         | Query Type | Status    | Sink Name | Sink Kafka Topic | Query String
------------------------------------------------------------------------------------------------------------------------
 CSAS_OUTPUT_1   | PERSISTENT | RUNNING:1 | OUTPUT    | OUTPUT           | CREATE STREAM OUTPUT WITH (KAFKA_TOPIC='OUTPUT', PARTITIONS=1, REPLICAS=1) AS SELECT * FROM INPUT INPUT EMIT CHANGES;
------------------------------------------------------------------------------------------------------------------------
```

Cleanup

```TERMINATE CSAS_OUTPUT_1;```

```DROP STREAM INPUT;```

Unfortunately, you cannot delete topics from KSQL.
