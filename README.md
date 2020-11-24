

# Data Engineering:
## Repository for the Data Engineering Course (LTAT.02.007)

<img src="https://upload.wikimedia.org/wikipedia/en/3/39/Tartu_%C3%9Clikool_logo.svg" width="250"><img src="./attachments/logo_dsg_vettoriale.png" width="250">


## Graph View

![inline](./attachments/latest.png)

## Lecturer: [Riccardo Tommasini, PhD](https://riccardotommasini.com)

### Teaching Assistants: 
- [Fabiano Spiga](mailto:),  
- [Mohamed Ragab](https://bigdata.cs.ut.ee/mohamed-ragab), 
- [Hassan Eldeeb](mailto:hassan.eldeeb@ut.ee)

### [Home Page](https://courses.cs.ut.ee/2020/dataeng)

### [Forum](https://piazza.com/ut.ee/fall2020/ltat02007/home) 

### [Moodle](https://moodle.ut.ee/course/view.php?id=10457)

### [Twitter](https://twitter.com/hashtag/DataEngUT?src=hashtag_click)

### Acknowledgments

Special Thanks to Emanuele Della Valle and Marco Brambilla from Politecnico di Milano to letting me "steal" some of their great slides.

# Lectures

| Date  | Title              | Material | Mandatory Reads | Extras |
|-------|--------------------|----------|-----------------|--------|
| 01/09 | Course Intro       | [Slides](./Data%20Engineer.md) - [pdf](./pdfs/Data%20Engineer.pdf) slide 45-109) | ||
| 03/09 | Data Modeling      | [Slides](Data%20Modeling.md) - [pdf](./pdfs/Data%20Modeling.pdf) slide 1-44 | Chp 4 p111-127, Chp 5 p151-156, Chp 6 p199-205 of [3]
| 10/09 |  DM for Relational Databases |   [Slides](Data%20Modeling.md) - [pdf](./pdfs/Data%20Modeling.pdf) slide 45-109 | Chp 2, 6, and 7 (Normal Forms) of [1] | [Relational Model](https://course.ccs.neu.edu/cs3200sp18s3/ssl/readings/codd.pdf) | 
|10/09  |  DM for Data Warehouse         |  [Slides](Data%20Modeling.md)  - [pdf](./pdfs/Data%20Modeling.pdf)slide 109-118|  [pdf](http://www.kimballgroup.com/wp-content/uploads/2013/08/2013.09-Kimball-Dimensional-Modeling-Techniques11.pdf) [video](http://slideshot.epfl.ch/play/suri_stonebraker)|  Chp 2 of [2] | 
| 17/09 |  DM for Big Data   | [Slides](Data%20Modeling%20for%20Big%20Data.md) - [pdf](./pdfs/Data%20Modeling%20Big%20Data.pdf)| Chp 2 of [3], [video](https://www.youtube.com/watch?v=LDW0QWie21s)|[paper](https://www.ics.uci.edu/~cs223/papers/cidr07p15.pdf)| 
| 17/09 |  Key Value Stores |[Slides 1](https://github.com/DataSystemsGroupUT/dataeng/blob/dataeng/Key-Value%20Store.md),[Slides 2](https://github.com/DataSystemsGroupUT/dataeng/blob/dataeng/Redis.md)[pdf](https://github.com/DataSystemsGroupUT/dataeng/blob/dataeng/pdfs/Key-Value%20Stores%20-%20Redis.pdf)||[nosql](https://www.christof-strauch.de/nosqldbs.pdf)|
|24/10| Column Oriented Databases |[Slides 1](https://github.com/DataSystemsGroupUT/dataeng/blob/dataeng/Column%20Oriented%20Database.md) [Slides 2](https://github.com/DataSystemsGroupUT/dataeng/blob/dataeng/Cassandra.md) [pdf](https://github.com/DataSystemsGroupUT/dataeng/blob/dataeng/pdfs/Column%20Stores%20-%20Cassandra.pdf)||[nosql](https://www.christof-strauch.de/nosqldbs.pdf)|
|24/10| Document Databases  |[Slides 1](https://github.com/DataSystemsGroupUT/dataeng/blob/dataeng/Document%20Databases.md) [Slides 2](https://github.com/DataSystemsGroupUT/dataeng/blob/dataeng/MongoDB.md) [pdf](https://github.com/DataSystemsGroupUT/dataeng/blob/dataeng/pdfs/Document%20Stores%20-%20MongoDB.pdf)||[nosql](https://www.christof-strauch.de/nosqldbs.pdf)|
|01/10| Graph Databases |[Slides 1](https://github.com/DataSystemsGroupUT/dataeng/blob/dataeng/Graph%20Theory.md) [Slides 2](https://github.com/DataSystemsGroupUT/dataeng/blob/dataeng/Graph%20Databases.md) [pdf1](https://github.com/DataSystemsGroupUT/dataeng/blob/dataeng/pdfs/Graph%20Theory.pdf) [pdf2](https://github.com/DataSystemsGroupUT/dataeng/blob/dataeng/pdfs/Graph%20Databases.pdf)|Chp 3 and 5 of [5]|[book](https://neo4j.com/graph-databases-book/)|
|08/10| Data Ingestion |[Slides 1](https://github.com/DataSystemsGroupUT/dataeng/blob/dataeng/Data%20Acquisition.md) [Slide 2](https://github.com/DataSystemsGroupUT/dataeng/blob/dataeng/REST%20API.md) [Slide 3](https://github.com/DataSystemsGroupUT/dataeng/blob/dataeng/HDFS.md) [Slide 4](https://github.com/DataSystemsGroupUT/dataeng/blob/dataeng/Apache%20Kafka.md)|||
|15/10| Part 1 Recap |[Slides 1](https://github.com/DataSystemsGroupUT/dataeng/blob/dataeng/Part%201%20-%20Recap.md)  [pdf](https://github.com/DataSystemsGroupUT/dataeng/blob/dataeng/pdfs/Part%201%20-%20Recap.pdf)|||
|22/10| Midterm |||||
|29/10| Data Engineering Pipelines (Part1) |[Slides 1](https://github.com/DataSystemsGroupUT/dataeng/blob/dataeng/Data%20Pipeline%20(Intro).md) [slide 2](https://github.com/DataSystemsGroupUT/dataeng/blob/dataeng/Data%20Pipeline.md) [pdf](https://github.com/DataSystemsGroupUT/dataeng/blob/dataeng/pdfs/pipelines.pdf)|||
|05/11| Data Engineering Pipelines (Part2) |[Slides 1](https://github.com/DataSystemsGroupUT/dataeng/blob/dataeng/MapReduce.md) [Slides 2](https://github.com/DataSystemsGroupUT/dataeng/blob/dataeng/FlumeJava.md) [Slides 3](https://github.com/DataSystemsGroupUT/dataeng/blob/dataeng/Apache%20Airflow.md)|Chp 10 of 3 [R. Chang Pt 2](https://medium.com/@rchang/a-beginners-guide-to-data-engineering-part-ii-47c4e7cbda71) [R. Chang Pt 3](https://medium.com/@rchang/a-beginners-guide-to-data-engineering-the-series-finale-2cc92ff14b0)||
|12/11| Streaming Data (Part 1) |[Slide 1](https://github.com/DataSystemsGroupUT/dataeng/blob/dataeng/Streaming%20Data%20Engineering.md) [Slide 2](https://github.com/DataSystemsGroupUT/dataeng/blob/dataeng/Streaming%20Pipelines.md)|Chp 11 of 3 [Streaming 101](https://www.oreilly.com/radar/the-world-beyond-batch-streaming-101/) [Streaming 102](https://www.oreilly.com/radar/the-world-beyond-batch-streaming-102)||
|19/11| Data Journey|[Slides](https://github.com/DataSystemsGroupUT/dataeng/blob/dataeng/pdfs/DataJourney_UoT.pdf)|||
|26/11| Streaming Data (Part 2) ||||
|03/12| Data Wrangling (Part 1) ||||
|10/12| Data Wrangling (Part 2) ||||


# Practices (Videos Will be Available after Group 2 issue)

| Date     | Title              | Material | Reads | Videos | Branch | Notes |
|----------|-------------|----------|-------|-------|-------|----|
| 07-8/09  | Docker | [Slides](./docker/README.md) - | |[Video GP1](https://panopto.ut.ee/Panopto/Pages/Viewer.aspx?id=31e77abe-b51e-4a39-8c33-ac30009b7ba6) [Video GP2](https://panopto.ut.ee/Panopto/Pages/Viewer.aspx?id=31e77abe-b51e-4a39-8c33-ac30009b7ba6) |[Lab Branch](https://github.com/DataSystemsGroupUT/dataeng/tree/docker) |  [QA GP2 only](https://docs.google.com/document/d/134YKfqp49-rtAXa0FJO30LJonHVO-PeYLqqeo8DQY9I/) 
| 14-15 /09  |Modeling and Querying Relational Data with Postgres|[Slides](https://github.com/DataSystemsGroupUT/dataeng/blob/Homework1/PostgreSQL.pdf)|[Chp 32 of [1]§](https://www.db-book.com/db7/online-chapters-dir/32.pdf) |[Video](https://panopto.ut.ee/Panopto/Pages/Viewer.aspx?id=b3221179-fd3a-4b4e-9a67-ac38008f7fbe)|[Homework 1](https://github.com/DataSystemsGroupUT/dataeng/tree/Homework1)||
| 21-22 /09  |Modeling and Querying Key Value Data with Redis|[Slides](https://github.com/DataSystemsGroupUT/dataeng/blob/Homework2/REDIS.pdf)||[Video](https://panopto.ut.ee/Panopto/Pages/Viewer.aspx?id=0e659b03-7d3e-4b4c-a0f9-ac3d00f462a7)|[Homework 2](https://github.com/DataSystemsGroupUT/dataeng/tree/Homework2)||
|28-29/09   |Modeling and Querying Document Data with MongoDB|[Slides](https://github.com/DataSystemsGroupUT/dataeng/blob/Homework3/slides/MongoDB.pdf)||[Video](https://panopto.ut.ee/Panopto/Pages/Viewer.aspx?id=9ffba354-a2a1-4131-967e-ac4400c8f226)|[Homework 3](https://github.com/DataSystemsGroupUT/dataeng/tree/Homework3)||
|5-6/10        | Modeling and Querying Graph Data with Neo4J|[Slides](https://github.com/DataSystemsGroupUT/dataeng/blob/Homework4/Neo4j_lab_Slides.pptx.pdf)|[CypherManual](https://s3.amazonaws.com/artifacts.opencypher.org/openCypher9.pdf)|[Video](https://panopto.ut.ee/Panopto/Pages/Viewer.aspx?id=1c9e44d6-d3a0-44ed-a012-ac4b00bb831b)|[Homework 4](https://github.com/DataSystemsGroupUT/dataeng/tree/Homework4)||
|19-20-26-27/10| Data Ingestion with Apache Kafka|[Slides](https://github.com/DataSystemsGroupUT/dataeng/blob/dataeng/pdfs/Apache%20Kafka.pdf)||[Video 1](https://panopto.ut.ee/Panopto/Pages/Viewer.aspx?id=eb980aea-b01b-4144-b4b0-ac5900b38aaa) [Video 2](https://panopto.ut.ee/Panopto/Pages/Viewer.aspx?id=fab6a6f0-9893-49d8-89ec-ac5a009998d4) [Video 3](https://panopto.ut.ee/Panopto/Pages/Viewer.aspx?id=b7073df1-19cf-4a24-a4d4-ac6100a85a1e) [Video 4](https://panopto.ut.ee/Panopto/Pages/Viewer.aspx?id=fce37959-4d0b-4330-90ec-ac6000dbb5f9)|[Homework 5](https://github.com/DataSystemsGroupUT/dataeng/tree/Homework5)||
|10-11/11| Apache Airflow Data Pipelines|[Slides](https://github.com/DataSystemsGroupUT/dataeng/blob/dataeng/Apache%20Airflow.md)||[Video 1](https://panopto.ut.ee/Panopto/Pages/Viewer.aspx?id=0d863345-9ad5-4053-926f-ac6e0118015f) [Video 2](https://panopto.ut.ee/Panopto/Pages/Viewer.aspx?id=eb9bd9de-2e9b-4569-b3c3-ac7000834a8d)|[Homework 6](https://github.com/DataSystemsGroupUT/dataeng/tree/Homework6)||
|16-17/11| Stream Processing with  Kafka Streams|[Slides](https://github.com/DataSystemsGroupUT/dataeng/blob/dataeng/Kafka%20Stream.md)||[Video 1](https://panopto.ut.ee/Panopto/Pages/Viewer.aspx?id=1ceb30eb-56a3-4c3c-ade0-ac75010f2ef0) [Video 2](https://panopto.ut.ee/Panopto/Pages/Viewer.aspx?id=ac3a65e4-3df3-4040-bb1a-ac7600e8946a)|[Homework 7](https://github.com/DataSystemsGroupUT/dataeng/tree/Homework7)||
|23-24/11| Stream Processing with  KSQL|[Slides](https://github.com/DataSystemsGroupUT/dataeng/blob/dataeng/KSQL.md)||[Video 1](https://panopto.ut.ee/Panopto/Pages/Viewer.aspx?id=1a396e84-84d2-4071-b3a4-ac7c00fe41ab) [Video 2](https://panopto.ut.ee/Panopto/Pages/Viewer.aspx?id=92631b24-c3f7-4e1b-9035-ac7d00ab6f0d)|[Homework 7](https://github.com/DataSystemsGroupUT/dataeng/tree/Homework7)||
||Data Cleansing ||||||
||Augmentation||||||

# Extras

[Contributing](./CONTRIBUTING.md)

- [ ] Modeling and Querying RDF data: SPARQL
- [ ] Domain Driven Design: a summary
- [ ] Event Sourcing: a summary
- [ ] Data Pipelines with Luigi
- [ ] Data Pipelines with Apachi Nifi 
- [ ] Data Processing with Apache Flink

# Syllabus

- What is (Big) Data?
- The Role of Data Engineer
- Data Modeling
  	- Data Replication
	- Data Partitioning
	- Transactions
- Relational Data
- NoSQL
  - Document
  - Graph
- Data Warehousing
  - Star and Snowflake schemas
- Data Vault 
- (Big) Data Pipelines
	- Big Data Systems Architectures
	- ETL and Data Pipelines
	  - Best Practices and Anti-Patterns
	- Batch vs Streaming Processing
- Data Cleansing
- Data Augumentation

# Books

- [1] Database System Concepts 7th Edition Avi Silberschatz Henry F. Korth S. Sudarshan McGraw-Hill ISBN 9780078022159
  - [Table of contents](https://www.db-book.com/db7/toc-dir/toc.pdf)
  - [slides](https://www.db-book.com/db7/slides-dir/index.html)
- [2] The Data Warehouse Toolkit - The Definitive Guide to Dimensional Modeling Third Edition  Ralph Kimball Margy Ross
- [3] [Designing Data-Intensive Applications - Martin Kleppmann ](https://dataintensive.net/)
- [4] [Designing Event-Driven Systems](https://www.oreilly.com/library/view/designing-event-driven-systems/9781492038252/)
- [5] [Graph Databases](https://neo4j.com/graph-databases-book/)
[[slides/Slides]]
