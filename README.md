# Kafka Streams/KSQL Practice and Homework


## Setup

Start the containers ```docker-compose up -d```

![Deployment](./docker-compose.png)

## Tools

SSH to the Tools container

```docker exec -it tools /bin/bash```

Run the following command to create the topic ```example_topic```.

```kafka-topics --bootstrap-server kafka1:9092 --partitions 2 --create --topic example_topic ```

## Cleanup

Stop the containers ```docker-compose down```

Delete all the volumes ```docker volume rm $(docker volume ls -q)```

## Kafka Streams

## KSQL

### KSQL-CLI

SSH to the KSQL-DB CLI container ```docker exec -it ksqldb-cli /bin/bash```

Connect to the KSQL-CLI server ```ksql http://ksqldb-server:8088```






