# Exercise 6: Analyising User Activities


## Generate Data

```bash
docker ps
```

```bash
CONTAINER ID        IMAGE                                    COMMAND                  CREATED             STATUS              PORTS                              NAMES
57f3a31e1864        confluentinc/cp-ksqldb-cli:6.0.0         "/bin/sh"                3 hours ago         Up 3 hours                                             ksqldb-cli
903cd7c1e6c4        dataeng_notebook                         "jupyter notebook --…"   3 hours ago         Up 3 hours          0.0.0.0:8888->8888/tcp             dataeng_notebook_1
eb012fc679a4        cnfltraining/training-tools:6.0          "/bin/bash"              3 hours ago         Up 3 hours                                             tools
d8fc64d6074c        confluentinc/cp-schema-registry:6.0.0    "/etc/confluent/dock…"   3 hours ago         Up 3 hours          0.0.0.0:8081->8081/tcp             schema-registry
0069e1700ba9        confluentinc/cp-enterprise-kafka:6.0.0   "/etc/confluent/dock…"   3 hours ago         Up 3 hours          9092/tcp, 0.0.0.0:9093->9093/tcp   kafka2
5f1863d287f8        confluentinc/cp-enterprise-kafka:6.0.0   "/etc/confluent/dock…"   3 hours ago         Up 3 hours          0.0.0.0:9092->9092/tcp             kafka1
dca43f1b3468        confluentinc/cp-zookeeper:6.0.0          "/etc/confluent/dock…"   3 hours ago         Up 3 hours          2181/tcp, 2888/tcp, 3888/tcp       zookeeper
```

Tools is a container that allows us to do nice things, including generate fake data.


```bash
docker exec -it tools ksql-datagen quickstart=pageviews topic=pageviews  format=json bootstrap-server=kafka1:9092
```

```bash
docker exec -it tools ksql-datagen  topic=users  format=json iterations=100 format=json bootstrap-server=kafka1:9092
```

## Write a 