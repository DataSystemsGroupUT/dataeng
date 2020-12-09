# Streaming Data Pipelines 


This branch contains the the practice and homework on Kafka Streams/KSQL.

The repository contains the following folders

- src/main: entry folder for the project, nothing important here
- ksql: markdown files with the exercises done during the practice using KSQL CLI
- ksql-uds: the java code for the KSQL UDF Function
- kstream: the java code for the exercises done during the practice using KStreams 
- notebook: a jupyter notebook environment
- students: the assignment folder

## Setup

```
mvn clean generate-sources```
```

Start the containers with

```
docker-compose up -d
```

![Deployment](./docker-compose.png)

### KSQL-CLI

SSH to the KSQL-DB CLI container ```docker exec -it ksqldb-cli /bin/bash```

Connect to the KSQL-CLI server ```ksql http://ksqldb-server:8088```

### LOAD UDFs

Write your UDFs in the [KSQL-UDFs Project](./ksql-udfs/).

```mvn clean package```

Copy the jar with dependency in [target](./ksql-udfs/target/*jar-with-dependencies.jar) into the extensions folder

### Tools

SSH to the Tools container

```docker exec -it tools /bin/bash```

Run the following command to create the topic ```example_topic``` to test.

```kafka-topics --bootstrap-server kafka1:9092 --partitions 2 --create --topic example_topic ```

### Cleanup

Stop the containers ```docker-compose down```

Delete all the volumes ```docker volume rm $(docker volume ls -q)```


## ASSIGNMENT !!!!!

Can be done EITHER in Java (KStreams) or using KSQL (command Line)
In the first case submit a zip/rar with the code, in the latter case you can directlu edit the readme and sumbit that one.
Data are generated, so you do not need to send the results.

- [README](./students/README.md)



