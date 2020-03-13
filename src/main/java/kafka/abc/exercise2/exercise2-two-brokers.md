#!/usr/bin/env bash

#Part A
bin/kafka-topics --bootstrap-server localhost:9092 --create \
                      --partitions 2 \
                      --replication-factor 2 \
                      --topic test2p


bin/kafka-console-producer --broker-list localhost:9092,localhost:9093  \
                             --property parse.key=true \
                             --property key.separator=, \
                             --topic test2p

# 1,A; 2,B; 3,C; 4,D

bin/kafka-console-consumer --bootstrap-server localhost:9093 --from-beginning --topic test

bin/kafka-console-producer --broker-list localhost:9092,localhost:9093 \
                               --property print.key=true \
                                --topic test
1,A, 2,B; 3,C; 4,D;

bin/kafka-console-consumer --bootstrap-server localhost:9092 \
                              --from-beginning \
                              --property print.key=true \
                              --topic test2p

#1	A
#3	C
#4	D
#2	B

bin/kafka-console-consumer --bootstrap-server localhost:9092 --from-beginning --property print.key=true --partition 1 --topic test2p
#1	A
#3	C
#4	D

bin/kafka-console-consumer --bootstrap-server localhost:9092 --from-beginning --property print.key=true --partition 0 --topic test2p
#2	B