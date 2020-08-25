Synonsymns: [[Data ingestion]] [[Data Collection ]]

It is the process of collecting raw data from various silo databases or files and integrating it into a data lake on the data processing platform, e.g., Hadoop data lake.

Data collection corresponds the extract (E) in an ETL/ELT pipeline.

Two forms of data collection: Collection and Ingestion

- Batch vs Streaming
- Pull vs Push
- Data flow vs Query Languages

Additionally, data can be Structured or Unstructured

### [[Data Collection]] Two Schools

[.column]

- Batch
- Pull
- Query-Based

[.column]

- Streaming
- Push
- Dataflow based

### Data Collection vs  Ingestion examples 

- Accessing Databases
- Crowling the Web
- Log processing

### Distributed Data

![inline](./attachments/Partitioning _I_ Replication.png)

### Replication

> Keeping a copy of the same data on multiple machine connected via network.

### Reasons to replicate:
- fault tolerance
- reduce latency (by increasing data locality)
- enable concurrent access

### Challenges

- handle changes in your data (velocity)

### Methods for Replication: 
- Replication Logs

[[TODOs on Data Eng]]

### Leader-Follower 

### Partitioning (Sharding)

### CAP Theorem

