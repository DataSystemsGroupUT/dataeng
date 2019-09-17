# Exercize 5: Custom serialization

Create a topic temperature with two partitions

```bash
./bin/kafka-topics.sh --bootstrap-server localhost:9092 --create \
                      --partitions 2 \
                      --replication-factor 2 \
                      --topic temperature
```