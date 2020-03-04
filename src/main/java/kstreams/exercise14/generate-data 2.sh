#!/usr/bin/env bash

bin/ksql-datagen quickstart=pageviews toc=pageviews  format=json

bin/ksql-datagen quickstart=users topic=users  format=json iterations=100

bin/kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic pageviewsbyuser

bin/kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic pageviewsbyregion