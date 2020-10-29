## Big Data Pipeline - Streaming

![right](https://live.staticflickr.com/3438/3936825397_4625288e73_b.jpg)

### Apache Beam[^2]

[^2]: [link](https://beam.apache.org/documentation/programming-guide/)

### Kafka Streams

### KSQL

## Modern Data pipeline components

![right fit](https://live.staticflickr.com/8007/7157008845_11bf504ee8_b.jpg)

- **Storage and Ingestion Layers**, e.g., HDFS
	- Storage layers nowadays typically support [[polyglot persistence]].
	- Message Buses that help move chunks of data (sometimes at blazing speeds) from one system to another, e.g., Kafka, RabbitMQ, Kinesis
	- Serialization Frameworks, e.g., Protocol Buffers, Avro,
- **(Event Stream) Processing frameworks**, e.g., Kafka Streams, Flink
	- JVM Based
	- SQL Based
- **Workflow Management Tools** that supervise the processes which run inside your data pipelines, e.g., Airflow, Luigi, Dagster
  	-  "orchestrating" systems
  	-  "choreographing" systems
- **Query layer**, e.g., NoSQL Datamarts
- **Analytics layer** (out of scope)

## Architectural Patterns

How to combine the components above?


### [[Lambda Architecture]][^17]

[Source](http://lambda-architecture.net/)

![inline](http://lambda-architecture.net/img/la-overview_small.png)

The lambda architecture is used when approximate results are needed quickly and more accurate results can come later. 

Moreover, it is suitable for cases where pure stream processing is not fault tolerant and more accurate results require to wait for late arrivals.

![left fit](./attachments/Images/lambda-arch.pdf)

[^17]: Courtesy of Emanuele Della Valle/Marco Balduini

### [[Kappa architecture]][^17]

The Kappa architecture was designed to address the limitation of the lambda architecture. 

It leverages only a speed layer but it relies on a fault-tolerant stream storage, e.g., a distributed log. 

The Kappa architecture is simpler to maintain and less costly to operate than the lambda architecture.

### Kappa Architecture
![inline](./attachments/Images/kappa-arch.pdf)


## Data Virtualisation

	
## Anti Patterns