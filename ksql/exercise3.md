# Working with temperature data: Rolling Average


### Learning Goals

- Using AVRO and KSQL
- Rolling Average

## Before

```bash
kafka-topics --bootstrap-server kafka1:9092 --partitions 2 --create --topic temperature_avro 
```

Run [Exercise 2](exercise2.md)

## KSQL

```sql
CREATE STREAM TEMPERATURE (ROOM VARCHAR KEY, ID BIGINT, VALUE DOUBLE, MEASUREMENT STRING, TIMESTAMP BIGINT) 
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

Let's calculate the rolling average

```sql
CREATE TABLE TEMP_AVG 
AS SELECT ROOM, AVG(PREFVAL) AS AVG 
FROM TEMPERATURE GROUP BY ROOM EMIT CHANGES;
```

```
 Message
----------------------------------------
 Created query with ID CTAS_TEMP_AVG_59
----------------------------------------
```

```sql 
DESCRIBE EXTENDED TEMP_AVG;
```

```
Name                 : TEMP_AVG
Type                 : TABLE
Timestamp field      : Not set - using <ROWTIME>
Key format           : KAFKA
Value format         : AVRO
Kafka topic          : TEMP_AVG (partitions: 1, replication: 1)
Statement            : CREATE TABLE TEMP_AVG WITH (KAFKA_TOPIC='TEMP_AVG', PARTITIONS=1, REPLICAS=1) AS SELECT
  TEMPERATURE.ROOM ROOM,
  AVG(TEMPERATURE.PREFVAL) AVG
FROM TEMPERATURE TEMPERATURE
GROUP BY TEMPERATURE.ROOM
EMIT CHANGES;

 Field | Type
----------------------------------------
 ROOM  | VARCHAR(STRING)  (primary key)
 AVG   | DOUBLE
----------------------------------------

Queries that write from this TABLE
-----------------------------------
CTAS_TEMP_AVG_59 (RUNNING) : CREATE TABLE TEMP_AVG WITH (KAFKA_TOPIC='TEMP_AVG', PARTITIONS=1, REPLICAS=1) AS SELECT   TEMPERATURE.ROOM ROOM,   AVG(TEMPERATURE.PREFVAL) AVG FROM TEMPERATURE TEMPERATURE GROUP BY TEMPERATURE.ROOM EMIT CHANGES;

For query topology and execution plan please run: EXPLAIN <QueryId>

Local runtime statistics
------------------------
messages-per-sec:      0.09   total-messages:         9     last-message: 2020-11-18T09:11:46.153Z
```
 
```sql
EXPLAIN CTAS_TEMP_AVG_59;
```

```
ID                   : CTAS_TEMP_AVG_59
Query Type           : PERSISTENT
SQL                  : CREATE TABLE TEMP_AVG WITH (KAFKA_TOPIC='TEMP_AVG', PARTITIONS=1, REPLICAS=1) AS SELECT
  TEMPERATURE.ROOM ROOM,
  AVG(TEMPERATURE.PREFVAL) AVG
FROM TEMPERATURE TEMPERATURE
GROUP BY TEMPERATURE.ROOM
EMIT CHANGES;
Host Query Status    : {e121bb17f7a1:8088=RUNNING}

 Field | Type
--------------------------------
 ROOM  | VARCHAR(STRING)  (key)
 AVG   | DOUBLE
--------------------------------

Sources that this query reads from:
-----------------------------------
TEMPERATURE

For source description please run: DESCRIBE [EXTENDED] <SourceId>

Sinks that this query writes to:
-----------------------------------
TEMP_AVG

For sink description please run: DESCRIBE [EXTENDED] <SinkId>
```

### Execution plan
```
 > [ SINK ] | Schema: ROOM STRING KEY, AVG DOUBLE | Logger: CTAS_TEMP_AVG_59.TEMP_AVG
		 > [ PROJECT ] | Schema: ROOM STRING KEY, AVG DOUBLE | Logger: CTAS_TEMP_AVG_59.Aggregate.Project
				 > [ AGGREGATE ] | Schema: ROOM STRING KEY, ROOM STRING, PREFVAL DOUBLE, KSQL_AGG_VARIABLE_0 DOUBLE | Logger: CTAS_TEMP_AVG_59.Aggregate.Aggregate
						 > [ GROUP_BY ] | Schema: ROOM STRING KEY, ROOM STRING, PREFVAL DOUBLE | Logger: CTAS_TEMP_AVG_59.Aggregate.GroupBy
								 > [ PROJECT ] | Schema: ROOM STRING KEY, ROOM STRING, PREFVAL DOUBLE | Logger: CTAS_TEMP_AVG_59.Aggregate.Prepare
										 > [ SOURCE ] | Schema: ROOM STRING KEY, ID BIGINT, PREFVAL DOUBLE, MEASUREMENT STRING, TIMESTAMP BIGINT, ROWTIME BIGINT, ROOM STRING | Logger: CTAS_TEMP_AVG_59.KsqlTopic.Source

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
      --> Aggregate-Project
      <-- KSTREAM-AGGREGATE-0000000003
    Processor: Aggregate-Project (stores: [])
      --> KTABLE-TOSTREAM-0000000006
      <-- Aggregate-Aggregate-ToOutputSchema
    Processor: KTABLE-TOSTREAM-0000000006 (stores: [])
      --> KSTREAM-SINK-0000000007
      <-- Aggregate-Project
    Sink: KSTREAM-SINK-0000000007 (topic: TEMP_AVG)
      <-- KTABLE-TOSTREAM-0000000006
```

```