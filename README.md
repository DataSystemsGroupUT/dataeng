# Kafka Training Middleware Course


Download Kafka suite


## CLI Commands

/bin/zookeeper-server-start.sh config/zookeeper.properties

bin/kafka-server-start.sh config/server.properties

bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test

bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test

bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning

bin/kafka-topics.sh --list --zookeeper localhost:2181

bin/schema-registry-start ./etc/schema-registry/schema-registry.properties

