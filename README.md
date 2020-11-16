# Kafka Streams/KSQL Practice and Homework

## Prerequisites
    
    - Knowledge of Java
    - Knowledge of SQL
    - Knowledge of Bash
    - Download Confluent Plaform

### Topics

- Kafka Recap
- Serdes
- Continuous Computations
- Windowing
- Streaming Aggregations

## List of Exercises

 - [Exercise 1](src/main/java/kstreams/Readme.md)
 - [Exercise 11: Word Count](src/main/java/kstreams/exercise1/Readme.md)
    - Processor
    - Kafka Stream
 - [Exercise 12: Rolling Average](src/main/java/kstreams/exercise3/Readme.md)
 - [Exercise 13: Time-Based Window](src/main/java/kstreams/exercise4/Readme.md)
    - Processing Time
    - Event Time
 - [Exercise 14: Stream Enrichment](src/main/java/kstreams/exercise5/Readme.md)
 - [Exercise 15: TBD](src/main/java/kstreams/exercise15/Readme.md)
 - [Exercise 16: Homework](src/main/java/kstreams/exercise6/Readme.md)
 - [Exercise 17: Pageviews](src/main/java/kstreams/exercise6/Readme.md)

## Day 3 KSQL: List of Exercises

TBD

###  Running Kafka (Requires Linux)

Download Confluent Platform [here](https://www.confluent.io/download/)

enter the bin folder and verify if all the scrits are executable

```bash
#grant permission
chmod +x *.sh
```

ssh to the virtual machine with port forwarding

```bash
ssh -p 3022 -L 9092:localhost:9092 -L 2081:localhost:2081 tartu@localhost
```


Then start zookeeper. It's address is *localhost:2181*
```bash
bin/zookeeper-server-start etc/kafka/zookeeper.properties
```


Then we start a kafka broker

```bash
bin/kafka-server-start etc/kafka/server.properties

```

If you want to start a second broker you MUST change the ID in the configuration and the port
as indicated below. Suggestion, also differentiate the log folder.
```lombok.config


etc/kafka//server-1.properties:
    broker.id=1
    listeners=PLAINTEXT://:9092
    log.dirs=/tmp/kafka-logs-1
    

etc/kafka//server-2.properties:
    broker.id=2
    listeners=PLAINTEXT://:9093
    log.dirs=/tmp/kafka-logs-2
```

## Utilities

[Topology-Viz](https://zz85.github.io/kafka-streams-viz/)



### CLI 

#### Create topics from the CLI

```bash
bin/kafka-topics --create --zookeeper localhost:2181 --replication-factor X --partitions Y --topic <name>
```

#### List existing topics 
bin/kafka-topics --list --zookeeper localhost:2181 

#### Describe a certain topic

```bash
bin/kafka-topics --describe --zookeeper localhost:2181 test
```
Output 

Topic: test	PartitionCount: 1	ReplicationFactor: 1	Configs:
Topic: test	Partition: 0	Leader: 0	Replicas: 0	Isr: 0	Offline:


## Other Utilities

bin/kafka-console-producer --broker-list localhost:9092 --topic test

bin/kafka-console-consumer --bootstrap-server localhost:9092 --topic test --from-beginning

bin/kafka-topics --list --zookeeper localhost:2181

bin/schema-registry-start ./etc/schema-registry/schema-registry.properties
