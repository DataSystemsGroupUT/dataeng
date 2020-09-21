

# Data Engineering:
## Repository for the Data Engineering Course (LTAT.02.007)

![logout](https://upload.wikimedia.org/wikipedia/en/3/39/Tartu_%C3%9Clikool_logo.svg)

![logodsg](./attachments/logo_dsg_vettoriale.png)


## Graph View

![inline](./attachments/Thursday-September-17-2020.png)

## Lecturer: [Riccardo Tommasini, PhD](https://riccardotommasini.com)

### Teaching Assistants: 
- [Fabiano Spiga](mailto:),  
- [Mohamed Ragab](mailto:mohamed.ragab@ut.ee), 
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
| 17/09 |  Key Value Stores ||||
|| Document Databases  ||||
|| Graph Databases ||||
|| Data Engineering Pipelines ||Chp 1 of [3]||
|| Keynote TBA||||
|| Streaming Data ||Chp 11 of [3]||
|| Data Wrangling ||||

# Practices (Videos Will be Available after Group 2 issue)

| Date     | Title              | Material | Reads | Videos | Assignment | Notes |
|----------|-------------|----------|-------|-------|-------|----|
| 07-8/09  | Docker |  [Slides](./docker/README.md) - [Lab Branch](https://github.com/DataSystemsGroupUT/dataeng/tree/docker) | |[Video GP1](https://panopto.ut.ee/Panopto/Pages/Viewer.aspx?id=31e77abe-b51e-4a39-8c33-ac30009b7ba6) [Video GP2](https://panopto.ut.ee/Panopto/Pages/Viewer.aspx?id=31e77abe-b51e-4a39-8c33-ac30009b7ba6) ||  [QA GP2 only](https://docs.google.com/document/d/134YKfqp49-rtAXa0FJO30LJonHVO-PeYLqqeo8DQY9I/) 
| 14-15 /09  |Modeling and Querying Relational Data with Postgres|[Slides](https://github.com/DataSystemsGroupUT/dataeng/blob/Homework1/PostgreSQL.pdf)|[Chp 32 of [1]§](https://www.db-book.com/db7/online-chapters-dir/32.pdf) |[Video](https://panopto.ut.ee/Panopto/Pages/Viewer.aspx?id=b3221179-fd3a-4b4e-9a67-ac38008f7fbe)|||
| 21-22 /09  |Modeling and Querying Key Value Data with Redis|[Slides](https://github.com/DataSystemsGroupUT/dataeng/blob/Homework2/REDIS.pdf)|||||
|28-29/09   |Modeling and Querying Document Data with MongoDB||||||
|5-6/10        | Modeling and Querying Graph Data with Neo4J||||||
|| Data Ingestion with Apache Kafka||||||
|| Apache Airflow Data Pipelines||||||
|| Stream Processing with  Kafka Streams||||||
|| Stream Processing with  KSQL||||||
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

[[slides/Slides]]
