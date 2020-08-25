### Systems Overview: [[Kafka Stream]]/KSQL-DB ![inline](./attachments/Images/Kafka.png)

Kafka Stream is an open-source stream processing engine that enables
scalable data processing on top of Apache Kafka.

Kafka Stream programs make use of topics, on which they build two
further abstractions: Streams and Tables. Kafka Stream Programs consume
and produce data using a functional API. It is also possible to write
directly Dataflow topologies using the so called Processor API.
References:

Sax, Matthias J., et al. "Streams and tables: Two sides of the same
coin." Proceedings of the International Workshop on Real-Time Business
Intelligence and Analytics. 2018.

### Systems Overview: Kafka Stream/KSQL-DB ![inline](./attachments/Images/KSQL.png)

KSQL-DB is a SQL engine built on top of Kafka Streams. SQL queries are
rewritten into Operator topologies and executed over the Kafka Cluster.

References:

Johan Desai. "KSQL: Streaming SQL Engine for Apache Kafka." EDBT.
2019.