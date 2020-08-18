open footer:  [Riccardo Tommasini](http://rictomm.me) - riccardo.tommasini@ut.ee - @rictomm - 
slidenumbers: true
<!-- : #course, #topic -->

# Data Engineering
#### LTAT.02.007
#### Ass Prof. Riccardo Tommasini
#### Assistants: Mohamed Ragab, Samuele Langhi, Hassan Elsaeeb
- [https://courses.cs.ut.ee/2020/dataeng](https://courses.cs.ut.ee/2020/dataeng)
- [Forum]() 

---
## Course Overview 
### Part 1: 
#### ![[DataEng Introduction]]
#### ![[Data Driven Decision Making]]
###  [[Docker]]
## Part 2: 

#### [[Data Modeling]]
#### [[Query Languages]]
- Declarative vs Imperative
	-  Extra on [[Functional Programming]]
- Relational Algebra and SQL
- Graph Query Languages
- Datalog
- Map-Reduce
### ![[Data Ingestion]]

### Distributed Data
![inline](./attachments/Partitioning _I_ Replication.png)

### Replication

| Keeping a copy of the same data on multiple machine connected via network.

### Reason to replicate:
- fault tolerance
- reduce latency (by increasing data locality)
- enable concurrent access

### Challenges
- handle changes in your data (velocity)
### Methods for Replication: 
- Replication Logs
[[TODOs on Data Eng]]

## Leader-Follower 
### Partitioning (Sharding)
### CAP Theorem

Data Ingestion
- DOcker Setup
- Accessing Databases
- Crowling the Web
- Batch vs Streaming Ingestion
- OLAP  vs  OLTP:
	 - Kleppmann book pages 90-91
-  Data Infrastructure 
	 -  Data Warehouse
			 -  ETL
		 -  Data Lakes
			 -  ELT
		 -  Data Vault: brief history of data modeling https://tdan.com/data-vault-series-1-data-vault-overview/5054
		 - Apache Kafka  
		 - https://people.csail.mit.edu/tatbul/publications/sstore_cidr17.pdf
  
### Part 3: Data Transformation and Integration (3 classes + 3 Practices)
 - SQL-based vs JVM-bsed data Transformation
 - ETL vs ELT
 - Provenance and Lineage
 - Data Pipeline
 - Streaming ETL
	 - Kafka Streams & KSQL
 - Interoperability

### Part 4:  [Data Wrangling](http://cidrdb.org/cidr2015/Papers/CIDR15_Paper2.pdf)
 - Understanding
 - Cleansing
 - Enrichment or Augmentation

### Evaluation

The course will have assignements for each part 
An ass

### Tooling

- Overview of schemas
		- Key-Value, Columnar, Document, Graph
		- Schemas for Analytics 
			- Star Schema and SnowFlakes
- Data Collection (1 class + 1 practice)
	- Various DBs
		- Postgres
		- Neo4J
		- MongoDB
- ETL and Streaming ETL (2 classes + 2 Practices)
	- Hadoop
	- Apache Kafka
- Data Pipeline (1 class + 1 practice)
	- Apache Airflow
- Data Wrangling (3 classes + 3 pracrices)
	- Cleansing
		- OpenRefine
	- Enrichment
		- Crawling
	- Augmentation
		- [x] Tensorflow
	
	

	[^2]: 
	
	[^c1]: maybe it make sense to do a class on ER
	[^c2]: