#!/usr/bin/env bash

#Part A
./bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic test
./bin/kafka-console-producer.sh --broker-list localhost:9092,localhost:9093 --topic test

# A; B; C; D;

./bin/kafka-console-consumer.sh --bootstrap-server localhost:9093 --from-beginning --topic test

#Part B

./bin/kafka-console-producer.sh --broker-list localhost:9092,localhost:9093  \
                                --property parse.key=true \
                                --property key.separator=, \
                                --topic test

# 1,A; 2,B; 3.C; 4,D

./bin/kafka-console-consumer.sh --bootstrap-server localhost:9093 --from-beginning --topic test
./bin/kafka-console-producer.sh --broker-list localhost:9092,localhost:9093 \
                                --property print.key=true \
                                --topic test
