# 3 Days Kafka Training Course

![img](qrcode.png)
## Prerequisites
    
    - Knowledge of Java
    - Knowledge of Bash
    - Download Confluent Plaform

## Day 1 Kafka

[Slide]()
[Detailed Program]()

### Topics

    - Publish Subscribe
    - Kafka Concepts
        - Topics
        - Producers
        - Consumers
    - Kafka System
        - Brokers
        - Partitioning
        - Serialization
    - Kafka Internals
        - Commit Log
        - Log Compaction
        - Fault Tolerance
            - Broker via Replication
            - Producer via Transactionality

#### List of Exercise in this module

- Basic
    - [Exercise 1](src/main/java/kafka/basics/exercise1/exercise1.md)
    - [Exercise 2](src/main/java/kafka/abasics/exercise1/exercise2.md)

- Advanced
    - [Exercise 3](src/main/java/kafka/advanced/exercise3/Readme.md)
    - [Exercise 4](src/main/java/kafka/advanced/exercise4/Readme.md)
    - [Exercise 5](src/main/java/kafka/advanced/exercise5/Readme.md)

- Expert
    - [Exercise 6](src/main/java/kafka/expert/exercise6/Readme.md)
    - [Exercise 7](src/main/java/kafka/expert/exercise7/Readme.md)
    - [Exercise 8](src/main/java/kafka/expert/exercise8/Readme.md)

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

### CLI Utilities

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
