# Exercize 5: Working with temperature data

## Part A

## Model

The temperature observation records a prefVal and the timestamp.
Moreover, we want to carry on the information about the room where the sensor is deployed.

## producer

Write a Kafka producer that generates the observation every 5 seconds (system time)
and pushes them to a temperature topic.

Before starting, recall to create the topic, with with two partitions

```bash
bin/kafka-topics --bootstrap-server localhost:9092 --create \
                      --partitions 2 \
                      --replication-factor 2 \
                      --topic temperature
```

## Part B

Write a Kafka consumer that reads the topic observations from the topic temperature

## Part C

Modify the Kafka Producer so that it partition the observation per room.