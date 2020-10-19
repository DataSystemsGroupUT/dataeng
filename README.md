## Introduction:

### Apache Kafka

![](https://upload.wikimedia.org/wikipedia/commons/5/53/Apache_kafka_wordtype.svg)

### Kafka PreLab

#### Windows users:

For running Kafka We recommend a virtual machine or using Docker.

#### Linux/MacOs users

Download Confluent Platform [here](https://packages.confluent.io/archive/5.5/confluent-community-5.5.2-2.12.tar.gz?_ga=2.109919813.1538540511.1603024157-1065440358.1582035676)

enter the bin folder and verify if all the scripts are executable

```bash
#grant permission
chmod +x *.sh
```

Configure your host file to avoid localhost
```bash
#/bin/bash
if [ "$HOSTNAME" = tools ]; then
  echo "We don't need to update hosts in the tools container. Exiting."
  exit 1
fi

if grep "DEV host entries" /etc/hosts >/dev/null; then
  echo "Already done!"
  exit 0
fi

cat << EOF | sudo tee -a /etc/hosts >/dev/null
# DEV host entries
127.0.0.1 kafka1
127.0.0.1 kafka2
127.0.0.1 zookeeper
127.0.0.1 schema-registry
127.0.0.1 connect
127.0.0.1 ksqldb-server
127.0.0.1 postgres
EOF
echo Done!

```

Then start zookeeper. It's address is *localhost:2181*
```bash
bin/zookeeper-server-start etc/kafka/zookeeper.properties
```

Then we start a kafka broker

```bash
bin/kafka-server-start etc/kafka/server.properties

```

If you want to start a second broker you MUST change the ID in the configuration and the port as indicated below. Suggestion, also differentiate the log folder.

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

#### Docker Users

```bash

docker-compose up -d

```

```yaml
version: '2.2'
services:
  zookeeper:
    hostname: zookeeper
    container_name: zookeeper
    image: "confluentinc/cp-zookeeper:5.5.0-1-ubi8"
    restart: always
    networks:
      - kafka-net
    restart: always
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
      ZOOKEEPER_TICK_TIME: 2000

  kafka1:
    image: "confluentinc/cp-enterprise-kafka:5.5.0-1-ubi8"
    restart: always
    hostname: kafka
    container_name: kafka1
    ports:
      - "9092:9092"
    networks:
      - kafka-net
    restart: always
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka1:9092
      KAFKA_AUTO_CREATE_TOPICS_ENABLE: "true"
      KAFKA_DELETE_TOPIC_ENABLE: "true"
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
      KAFKA_METRIC_REPORTERS: "io.confluent.metrics.reporter.ConfluentMetricsReporter"
      CONFLUENT_METRICS_REPORTER_BOOTSTRAP_SERVERS: "kafka1:9092"
      CONFLUENT_METRICS_REPORTER_TOPIC_REPLICAS: 1
      KAFKA_GROUP_INITIAL_REBALANCE_DELAY_MS: 100
      KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 1
      KAFKA_TRANSACTION_STATE_LOG_MIN_ISR: 1
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
  
  kafka2:
    image: "confluentinc/cp-enterprise-kafka:5.5.0-1-ubi8"
    restart: always
    hostname: kafka
    container_name: kafka2
    ports:
      - "9093:9093"
    networks:
      - kafka-net
    restart: always
    environment:
      KAFKA_BROKER_ID: 2
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka2:9093
      KAFKA_AUTO_CREATE_TOPICS_ENABLE: "true"
      KAFKA_DELETE_TOPIC_ENABLE: "true"
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
      KAFKA_METRIC_REPORTERS: "io.confluent.metrics.reporter.ConfluentMetricsReporter"
      CONFLUENT_METRICS_REPORTER_BOOTSTRAP_SERVERS: "kafka2:9093"
      CONFLUENT_METRICS_REPORTER_TOPIC_REPLICAS: 1
      KAFKA_GROUP_INITIAL_REBALANCE_DELAY_MS: 100
      KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 1
      KAFKA_TRANSACTION_STATE_LOG_MIN_ISR: 1
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1

  schema-registry:
    hostname: schema-registry
    container_name: schema-registry
    image: "confluentinc/cp-schema-registry:5.5.0-1-ubi8"
    restart: always
    ports:
      - 8081:8081
    networks:
      - kafka-net
    restart: always
    environment:
      SCHEMA_REGISTRY_HOST_NAME: schema-registry
      SCHEMA_REGISTRY_KAFKASTORE_BOOTSTRAP_SERVERS: kafka1:9092
      SCHEMA_REGISTRY_LISTENERS: http://schema-registry:8081
  
  tools:
    image: cnfltraining/training-tools:5.5
    restart: always
    hostname: tools
    container_name: tools
    restart: always
    networks:
      - kafka-net
    volumes:
      - .:/root/confluent-streams/labs/using-ksql
    working_dir: /root/confluent-streams/labs/using-ksql
    command: /bin/bash
    tty: true

  notebook:
    build: notebook/
    networks:
      - kafka-net
    ports:
      - 8888:8888
    volumes:
       - ./:/home/jovyan/work/data
    environment:
      - GRANT_SUDO=yesd

networks:
  kafka-net:
```
