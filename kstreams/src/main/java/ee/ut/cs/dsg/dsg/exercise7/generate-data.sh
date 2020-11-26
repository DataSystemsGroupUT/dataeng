#!/usr/bin/env bash

docker exec -it tools ksql-datagen quickstart=pageviews topic=pageviews  format=json bootstrap-server=kafka1:9092

docker exec -it tools ksql-datagen  topic=users  format=json iterations=100 format=json bootstrap-server=kafka1:9092
