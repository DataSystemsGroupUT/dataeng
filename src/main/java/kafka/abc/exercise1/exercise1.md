#!/usr/bin/env bash

#Part A
bin/kafka-topics --bootstrap-server localhost:9092 --create --topic test
bin/kafka-console-producer --broker-list localhost:9092 --topic test

# A; B; C; D;

bin/kafka-console-consumer --bootstrap-server localhost:9092 --from-beginning --topic test

#Part B

bin/kafka-console-producer --broker-list localhost:9092 \
                                --property parse.key=true \
                                --property key.separator=, \
                                --topic test

# 1,A; 2,B; 3.C; 4,D

bin/kafka-console-consumer --bootstrap-server localhost:9092 --from-beginning --topic test
bin/kafka-console-consumer --bootstrap-server localhost:9092 \
                          --from-beginning      --property print.key=true \
                                --topic test
