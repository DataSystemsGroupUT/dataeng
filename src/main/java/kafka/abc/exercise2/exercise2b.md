#!/usr/bin/env bash

/bin/kafka-console-consumer --bootstrap-server localhost:9092 \
                               --from-beginning \
                               --topic evens


#Weird results because by default console consumer uses string deserialization

/bin/kafka-console-consumer --bootstrap-server localhost:9092 \
                               --from-beginning \
                               --property key.deserializer=org.apache.kafka.common.serialization.IntegerDeserializer \
                               --property prefVal.deserializer=org.apache.kafka.common.serialization.IntegerDeserializer
                               --topic evens
