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
kafka-topics --bootstrap-server kafka1:9092 --partitions 2 --create --topic temperature_avro 
kafka-topics --bootstrap-server kafka1:9092 --partitions 2 --create --topic configuration_avro 

```

- Run [Observation Producer](src/main/java/ee/ut/cs/dsg/dsg/exercise2/ObservationProducer.java)
- Run [Configuration Producer](src/main/java/ee/ut/cs/dsg/dsg/exercise5/ConfigurationProducer.java)

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
Kafka topic          : temperature_avro (partitions: 2, replication: 1)
Statement            : CREATE STREAM TEMPERATURE (ROOM STRING KEY, ID BIGINT, PREFVAL DOUBLE, MEASUREMENT STRING, TIMESTAMP BIGINT) WITH (KAFKA_TOPIC='temperature_avro', KEY_FORMAT='KAFKA', VALUE_FORMAT='AVRO');

 Field       | Type
--------------------------------------
 ROOM        | VARCHAR(STRING)  (key)
 ID          | BIGINT
 PREFVAL     | DOUBLE
 MEASUREMENT | VARCHAR(STRING)
 TIMESTAMP   | BIGINT
--------------------------------------
```

```sql
CREATE TABLE CONFIGURATION (ROOM VARCHAR PRIMARY KEY, PREFVAL DOUBLE, MEASUREMENT STRING, TIMESTAMP BIGINT, AUTHOR VARCHAR)
WITH (kafka_topic='configuration_avro', value_format='avro');
```

```sql
Name                 : CONFIGURATION
Type                 : TABLE
Timestamp field      : Not set - using <ROWTIME>
Key format           : KAFKA
Value format         : AVRO
Kafka topic          : configuration_avro (partitions: 2, replication: 1)
Statement            : CREATE TABLE CONFIGURATION (ROOM STRING PRIMARY KEY, PREFVAL DOUBLE, MEASUREMENT STRING, TIMESTAMP BIGINT, AUTHOR STRING) WITH (KAFKA_TOPIC='configuration_avro', KEY_FORMAT='KAFKA', VALUE_FORMAT='AVRO');

 Field       | Type
----------------------------------------------
 ROOM        | VARCHAR(STRING)  (primary key)
 PREFVAL     | DOUBLE
 MEASUREMENT | VARCHAR(STRING)
 TIMESTAMP   | BIGINT
 AUTHOR      | VARCHAR(STRING)
----------------------------------------------
```

```sql
DESCRIBE EXTENDED CONFIGURATION;
```

How do we decide between Stream and Table for a topic?


```
SELECT *
>  FROM TEMPERATURE T JOIN CONFIGURATION C ON T.ROOM = C.ROOM
>  EMIT CHANGES;
+----------------+----------------+----------------+----------------+----------------+----------------+----------------+----------------+----------------+----------------+
|T_ROOM          |T_ID            |T_PREFVAL       |T_MEASUREMENT   |T_TIMESTAMP     |C_ROOM          |C_PREFVAL       |C_MEASUREMENT   |C_TIMESTAMP     |C_AUTHOR        |
+----------------+----------------+----------------+----------------+----------------+----------------+----------------+----------------+----------------+----------------+
|room2           |1               |-0.6304712233889|temperature_avro|124000          |room2           |24.0            |temperature     |1606120514206   |Jane Doe        |
|                |                |196             |                |                |                |                |                |                |                |
|room4           |1               |-0.9973999596608|temperature_avro|128000          |room4           |29.0            |temperature     |1606120524214   |Thor            |
|                |                |523             |                |                |                |                |                |                |                |
|room1           |1               |-0.5966868549463|temperature_avro|122000          |room1           |24.0            |temperature     |1606120509202   |John Doe        |
|                |                |663             |                |                |                |                |                |                |                |
|room1           |1               |0.54341910275855|temperature_avro|126000          |room1           |24.0            |temperature     |1606120509202   |John Doe        |
|                |                |61              |                |                |                |                |                |                |                |
|room1           |1               |-0.8759901479908|temperature_avro|130000          |room1           |24.0            |temperature     |1606120509202   |John Doe        |
|                |                |574             |                |                |                |                |                |                |                |
|room0           |1               |0.77771168362103|temperature_avro|132000          |room0           |42.0            |temperature     |1606120503816   |Riccardo        |
|                |                |53              |                |                |                |                |                |                |                |
```

```sql
CREATE STREAM anomalies AS
  SELECT *
  FROM TEMPERATURE T LEFT JOIN CONFIGURATION C ON T.ROOM = C.ROOM
  WHERE T.PREFVAL !=  C.PREFVAL
  EMIT CHANGES;
```

``` 
Message
-----------------------------------------
 Created query with ID CSAS_ANOMALIES_11
-----------------------------------------
```

```sql
EXPLAIN CSAS_ANOMALIES_11;
```


### Sources that this query reads from:
TEMPERATURE
CONFIGURATION

For source description please run: DESCRIBE [EXTENDED] <SourceId>

### Sinks that this query writes to:
ANOMALIES

For sink description please run: DESCRIBE [EXTENDED] <SinkId>

### Execution plan
```
 > [ SINK ] | Schema: T_ROOM STRING KEY, T_ID BIGINT, T_PREFVAL DOUBLE, T_MEASUREMENT STRING, T_TIMESTAMP BIGINT, C_ROOM STRING, C_PREFVAL DOUBLE, C_MEASUREMENT STRING, C_TIMESTAMP BIGINT, C_AUTHOR STRING | Logger: CSAS_ANOMALIES_11.ANOMALIES
		 > [ PROJECT ] | Schema: T_ROOM STRING KEY, T_ID BIGINT, T_PREFVAL DOUBLE, T_MEASUREMENT STRING, T_TIMESTAMP BIGINT, C_ROOM STRING, C_PREFVAL DOUBLE, C_MEASUREMENT STRING, C_TIMESTAMP BIGINT, C_AUTHOR STRING | Logger: CSAS_ANOMALIES_11.Project
				 > [ FILTER ] | Schema: T_ROOM STRING KEY, T_ID BIGINT, T_PREFVAL DOUBLE, T_MEASUREMENT STRING, T_TIMESTAMP BIGINT, T_ROWTIME BIGINT, T_ROOM STRING, C_PREFVAL DOUBLE, C_MEASUREMENT STRING, C_TIMESTAMP BIGINT, C_AUTHOR STRING, C_ROWTIME BIGINT, C_ROOM STRING | Logger: CSAS_ANOMALIES_11.WhereFilter
						 > [ JOIN ] | Schema: T_ROOM STRING KEY, T_ID BIGINT, T_PREFVAL DOUBLE, T_MEASUREMENT STRING, T_TIMESTAMP BIGINT, T_ROWTIME BIGINT, T_ROOM STRING, C_PREFVAL DOUBLE, C_MEASUREMENT STRING, C_TIMESTAMP BIGINT, C_AUTHOR STRING, C_ROWTIME BIGINT, C_ROOM STRING | Logger: CSAS_ANOMALIES_11.Join
								 > [ PROJECT ] | Schema: T_ROOM STRING KEY, T_ID BIGINT, T_PREFVAL DOUBLE, T_MEASUREMENT STRING, T_TIMESTAMP BIGINT, T_ROWTIME BIGINT, T_ROOM STRING | Logger: CSAS_ANOMALIES_11.PrependAliasLeft
										 > [ SOURCE ] | Schema: ROOM STRING KEY, ID BIGINT, PREFVAL DOUBLE, MEASUREMENT STRING, TIMESTAMP BIGINT, ROWTIME BIGINT, ROOM STRING | Logger: CSAS_ANOMALIES_11.KafkaTopic_Left.Source
								 > [ PROJECT ] | Schema: C_ROOM STRING KEY, C_PREFVAL DOUBLE, C_MEASUREMENT STRING, C_TIMESTAMP BIGINT, C_AUTHOR STRING, C_ROWTIME BIGINT, C_ROOM STRING | Logger: CSAS_ANOMALIES_11.PrependAliasRight
										 > [ SOURCE ] | Schema: ROOM STRING KEY, PREFVAL DOUBLE, MEASUREMENT STRING, TIMESTAMP BIGINT, AUTHOR STRING, ROWTIME BIGINT, ROOM STRING | Logger: CSAS_ANOMALIES_11.KafkaTopic_Right.Source
```

### Processing topology

```
Topologies:
   Sub-topology: 0
    Source: KSTREAM-SOURCE-0000000006 (topics: [temperature_avro])
      --> KSTREAM-TRANSFORMVALUES-0000000007
    Processor: KSTREAM-TRANSFORMVALUES-0000000007 (stores: [])
      --> PrependAliasLeft
      <-- KSTREAM-SOURCE-0000000006
    Source: KSTREAM-SOURCE-0000000001 (topics: [configuration_avro])
      --> KTABLE-SOURCE-0000000002
    Processor: PrependAliasLeft (stores: [])
      --> Join
      <-- KSTREAM-TRANSFORMVALUES-0000000007
    Processor: Join (stores: [KafkaTopic_Right-Reduce])
      --> WhereFilter
      <-- PrependAliasLeft
    Processor: KTABLE-SOURCE-0000000002 (stores: [])
      --> KTABLE-MAPVALUES-0000000003
      <-- KSTREAM-SOURCE-0000000001
    Processor: KTABLE-MAPVALUES-0000000003 (stores: [KafkaTopic_Right-Reduce])
      --> KTABLE-TRANSFORMVALUES-0000000004
      <-- KTABLE-SOURCE-0000000002
    Processor: WhereFilter (stores: [])
      --> Project
      <-- Join
    Processor: KTABLE-TRANSFORMVALUES-0000000004 (stores: [])
      --> PrependAliasRight
      <-- KTABLE-MAPVALUES-0000000003
    Processor: Project (stores: [])
      --> KSTREAM-SINK-0000000012
      <-- WhereFilter
    Sink: KSTREAM-SINK-0000000012 (topic: ANOMALIES)
      <-- Project
    Processor: PrependAliasRight (stores: [])
      --> none
      <-- KTABLE-TRANSFORMVALUES-0000000004
``

