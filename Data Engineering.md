footer:  [Riccardo Tommasini](http://rictomm.me) - riccardo.tommasini@ut.ee - @rictomm 
slide-dividers: #, ##, ###
slidenumbers: true
autoscale: true

# Data Engineering: Data Engineer
#### LTAT.02.007
#### Ass Prof. Riccardo Tommasini
#### Assistants: [Fabiano Spiga](mailto:),  [Mohamed Ragab](mailto:mohamed.ragab@ut.ee),  [Hassan Eldeeb](mailto:hassan.eldeeb@ut.ee)
- [https://courses.cs.ut.ee/2020/dataeng](https://courses.cs.ut.ee/2020/dataeng)
- [Forum](https://piazza.com/ut.ee/fall2020/ltat02007/home) 
- [Moodle](https://moodle.ut.ee/course/view.php?id=10457)


---
## Course Overview 
### Intro

![[Data Engineer]]

-  Data Infrastructure 
	 -  Data Warehouse
	 -  Data Lakes
	- Event-Driven Systems
	- OLAP  vs  OLTP:
	 - Kleppmann book pages 90-91
- Scalability 
- 
## Part 1:

#### [[Data Modeling]]
- Data Variety
- brief history of data modeling https://tdan.com/data-vault-series-1-data-vault-overview/5054

#### [[Query Languages]]
- [ ] [[Relational Algebra and SQL]]
- [ ] [[Graph Query Languages]]
- [ ] [[Datalog]]
- [ ] [[Map-Reduce]]

### ![[Data Acquisition]]
[Data Ingestion in a Connected World](https://people.csail.mit.edu/tatbul/publications/sstore_cidr17.pdf)
- Replication
- Partitioning (Sharding)
- CAP Theorem
- Batch vs Streaming Ingestion 
- [[Data Velocity]]

### Part 2: [[Data Transformation ]]
- ETL vs ELT
- [[Data Pipeline]]
- SQL-based vs JVM-based data Transformation
 - Apache Kafka  
- Provenance and Lineage
 - Data Pipeline
 - Streaming ETL
	 - Kafka Streams & KSQL
 - Interoperability

### Part 3:  [[Data Wrangling]]
 - Understanding
 - Cleansing
 - Enrichment or Augmentation

#### Conclusion

![[Data Driven Decision Making]]

### Evaluation

The course will have assignments for each part 
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
- Data Wrangling (3 classes + 3 practices)
	- Cleansing
		- OpenRefine
	- Enrichment
		- Crawling
	- Augmentation
		- [x] Tensorflow
	
	

	[^2]: 
	
	[^c1]: maybe it make sense to do a class on ER
	[^c2]: