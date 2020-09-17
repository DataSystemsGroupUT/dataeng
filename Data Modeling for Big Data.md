footer:  [Riccardo Tommasini](http://rictomm.me) - riccardo.tommasini@ut.ee - @rictomm 
slide-dividers: #, ##, ###
slidenumbers: true
autoscale: true
theme: Plain Jane

# Data Engineering: Topic
#### LTAT.02.007
#### Ass Prof. Riccardo Tommasini
#### Assistants: [Fabiano Spiga](mailto:),  [Mohamed Ragab](mailto:mohamed.ragab@ut.ee),  [Hassan Eldeeb](mailto:hassan.eldeeb@ut.ee)
- [https://courses.cs.ut.ee/2020/dataeng](https://courses.cs.ut.ee/2020/dataeng)
- [Forum](https://piazza.com/ut.ee/fall2020/ltat02007/home) 
- [Moodle](https://moodle.ut.ee/course/view.php?id=10457)

# Data Modeling for Big Data

![inline](https://upload.wikimedia.org/wikipedia/commons/6/6d/Data_types_-_en.svg) 

^ The Data Landscape: Variety is the Driver

### From data to analysis and execution

![inline](./attachments/bigdatatimeline1.png)


### The appearance of the “Big Data”

![inline](./attachments/bigdatatimeline2.png)


### Big Data Vs [Lanely]

![inline](https://storage.ning.com/topology/rest/1.0/file/get/1994871?profile=RESIZE_1024x1024)


### A Growing Trend

![inline](https://www.elderresearch.com/hs-fs/hubfs/graph_big-data-number%20of%20v's%20over%20time.png?width=767&name=graph_big-data-number%20of%20v%27s%20over%20time.png)


[source](https://www.elderresearch.com/blog/42-v-of-big-data)

### The Data Landscape

![inline](./attachments/m2_structure.png)

^ Structured data are organized and labeled according to a precise model (e.g., relational data)
^ Unstructured data, on the other hand, are not constrained (e.g., text, video, audio)
^ In between, there are many form of semi-structured data, e.g., JSON and XML, whose models do not impose a strict structure but provide means for validation. 

### Traditional Data Modelling Workflow

- Known as Schema on Write
- Focus on the modelling a schema that can accommodate all needs
- Bad impact on those analysis that were not envisioned

![right fit](./attachments/schemaonread.png)

^
 - Extract Transform Load
 - Some analyses may no longer be performed because the data were lost at writing time,

---
## Schema on Read

- Load data first, ask question later
- All data are kept, the minimal schema need for an analysis is applied when needed
- New analyses can be introduced in any point in time

![right fit](./attachments/schemaonwrite.png)

## Data Lakes

![inline](./attachments/datalakewf.png)


### Big Data Storage

- Distributed File Systems, e.g., HDFS
- Distributed Databases, e.g., VoltDB
- Queues, e.g., Pulsar or Kafka
- NoSQL Databases

^ A distributed file system stores files across a large collection of machines while giving a single-file-system view to clients.

## Data Ingestion

- The process of importing, transferring and loading data for storage and later use 
- It involves loading data from a variety of sources
- It can involve altering and modification of individual files to fit into a format that optimizes the storage
- For instance, in Big Data small files are concatenated to form files of 100s of MBs and large files are broken down in files of 100s of MB

![right fit](./attachments/ingestion.png)

### We Will Talk About Distributed File Systems

A distributed file system stores files across a large collection of machines while giving a single-file-system view to clients.

- ![[HDFS]]

![right fit](./attachments/nottoday.png)

### Will Will Talk  About Distributed Message Queues

A distribured message quque stores file in a log an allos sequential reads. 

- ![[Apache Kafka]]


![right fit](./attachments/nottoday.png)

---

##  ~~ETL~~ [[Data Pipelines]]

A data pipeline aggregates, organizes, and moves data to a destination for storage, insights, and analysis. 

Modern data pipeline generalize the notion of ETL (extract, transform, load) to include data ingestion, integration, and movement across any cloud architecture and add additional layers of resiliency against failure.

- [[Apache Airflow]]
- [[Kafka Streams]]
- [[KSQL]]

## [[Data Wrangling]]

The process of creating *reliable*  that can be analysed to generate valid actionable insights.

The central goal is to make data usable: to put data in a form that can be easily manipulated by analysis tools.

It includes understanding, cleansing, augmenting and shaping data.

^ Additional goals:
- ensure that data is responsive to the intended analyses
- ensure that data contain the necessary information, 
- ensure metadata that describe data are available
- ensure that data are sufficiently correct to support successful modeling and decision-making.

The results is data in the best format (e.g., columnar) for the analysis to perform.

![right 150%](./attachments/wranglingsteps4.png)

## The Advent of NoSQL

> Google, Amazon, Facebook, and DARPA all recognized that when you scale systems large enough, you can never put enough iron in one place to get the job done (and you wouldn’t want to, to prevent a single point of failure). 
<br>
> Once you accept that you have a distributed system, you need to give up consistency or availability, which the fundamental transactionality of traditional RDBMSs cannot abide.
--[Cedric Beust](https://beust.com/weblog/2010/02/25/nosql-explained-correctly-finally/)

^  The name “NoSQL” is unfortunate, since it doesn’t actually refer to any particular technology—it was originally intended simply as a catchy Twitter hashtag for a meetup on open source, distributed, nonrelational databases in 2009 Cf Pramod J. Sadalage and Martin Fowler: NoSQL Distilled. Addison-Wesley, August 2012. ISBN: 978-0-321-82662-6

### The Reasons Behind

- Big Data: need for greater scalability than relational databases can easily achieve *in write*
- Open Source: a widespread preference for free and open source software 
- Queryability: need for specialized query operations that are not well supported by the relational model
- Schemaless:  desire for a more dynamic and expressive data model than relational

### Object-Relational Mismatch 

Most application development today is done in object-oriented programming languages

An awkward translation layer is required between the objects in the application code and the database model of tables, rows, and columns

Object-relational mapping (ORM) frameworks like Hibernate try to mild the mismatch, but they can’t completely hide the differences
 
---

![inline](./attachments/timelinenosql.png)

### NoSQL Familty

![inline fit](./attachments/nsqlfamily.png)

### Kinds of NoSQL (2/4)

NoSQL solutions fall into four major areas:

-** Key-Value Store**
	- A key that refers to a payload (actual content / data)
	- Examples: MemcacheDB, Azure Table Storage, Redis, HDFS

- **Column Store** 
	- Column data is saved together, as opposed to row data
	- Super useful for data analytics
	- Examples: Hadoop, Cassandra, Hypertable

### Kinds of NoSQL (4/4)

- **Document / XML / Object Store**
	- Key (and possibly other indexes) point at a serialized object
	- DB can operate against values in document
	- Examples: MongoDB, CouchDB, RavenDB

- **Graph Store**
	- Nodes are stored independently, and the relationship between nodes (edges) are stored with data
	- Examples: AllegroGraph, Neo4j

### You can also distinguish them

[.column]

- **Key/Value or ‘the big hash table’ (remember caching?)**
	- Amazon S3 (Dynamo)
	- Voldemort
	- Scalaris
	- MemcacheDB, 
	- Azure Table Storage, 
	- *Redis* $$\leftarrow$$
	- Riak

[.column]

- **Schema-less**
	- *MongoDB* $$\leftarrow$$
	- Cassandra (column-based)
	- CouchDB (document-based)
	- *Neo4J (graph-based)* $$\leftarrow$$
	- HBase (column-based) 

---

### NOSQL Complexity

![inline](https://slideplayer.com/slide/16139843/95/images/16/NoSQL.jpg)

---


![original fit](https://www.ebayinc.com/assets/Uploads/Blog/2014/10/nosql_evolution.png)

^ a natural evolutionary path exists from simple key-value stores to the highly complicated graph databases, as shown in the following diagram:

### SQL vs (Not only SQL) NoSQL

|SQL databases| NoSQL  databases|
|----|-----|
| Triggered the need of relational databases  | Triggered by the storage needs of Web 2.0 companies such as Facebook,Google and Amazon.com|
| Well structured data| Not necessarily well structured – e.g., pictures, documents, web page description, video clips, etc.|
| Focus on data integrity|focuses on availability of data even in the presence of multiple failures|
| Mostly Centralized|spread data across many storage systems with a high degree of replication.|
| ACID properties should hold|ACID properties may not hold[^2]|

[^2]: no properties at all???

### ACID vs. BASE properties[^61]

[^61]:Do you recall the CAP theorem? 🎩

---

### Rationale

- It’s ok to use stale data (Accounting systems do this all the time. It’s called “closing out the books.”) ; 
- It’s ok to give approximate answers
- Use resource versioning -> say what the data really is about – no more, no less
	- the value of x is 5 at time T

![right fit](https://i.ytimg.com/vi/ZNo6gfCAgWE/maxresdefault.jpg)

---

### CAP Theorem Trade-off Remember?

---

![original fit](https://player.slideplayer.com/95/16139843/slides/slide_30.jpg)

### BASE(Basically Available, Soft-State, Eventually Consistent)

- **Basic Availability**: fulfill request, even in partial consistency.
- **Soft State**: abandon the consistency requirements of the ACID model pretty much completely
-**Eventual Consistency**: at some point in the future, data will converge to a consistent state; delayed consistency, as opposed to immediate consistency of the ACID properties.
  - purely alivenessguarantee (reads eventually return the requested value); but
  - does not makesafetyguarantees, i.e.,
  - an eventually consistent system can return any value before it converges

---
![original fit](./attachments/media_httpfarm5static_mevIk.png)

[.footer: [img](https://blog.nahurst.com/visual-guide-to-nosql-systems)]

### ACID vs. BASE trade-off

No general answer to whether your application needs an ACID versus BASE consistency model.

Given BASE ’s loose consistency, developers need to be more knowledgeable and rigorous about consistent data if they choose a BASE store for their application.

Planning around BASE limitations can sometimes be a major disadvantage when compared to the simplicity of ACID transactions.

A fully ACID database is the perfect fit for use cases where data reliability and consistency are essential.

# History of Data Models[^5]

--- 

![original fit](https://miro.medium.com/max/1225/1*V2zU24JMyIuCKV3wkDN26A.png)

[^5]: [by Ilya Katsov](https://highlyscalable.wordpress.com/2012/03/01/nosql-data-modeling-techniques/)

### Extra Reads

![original fit](https://www.ics.uci.edu/~cs223/papers/cidr07p15.pdf)


## Shall we rethink the three-layered modeling?

![right 95%](https://www.matillion.com/wp-content/uploads/2020/04/DataModels-Diagram-01.png)

###  Data Modeling for Big Data

- Conceptual Level Remains:
	- ER, UML diagram can still be used for no SQL as they output a model that encompasses the whole company.

- Phsyical Level remains: NoSQL solutions often expose internals for obtaining flexibility, e.g., 
	- Key-value stores API
	- Column stores
	- Log structures

- _Logical level no longer make sense. Schema on read focuses on the query side.__

## Domain Driven Design

Domain-Driven Design is a language- and domain-centric approach to software design for complex problem domains.

DDD promotes the reduction of the translation cost between business and technical terminology.

DDD consists of a collection of patterns, principles, and practices that allows teams to focus on the core t business goels while crafting software.

![right fit](https://images-na.ssl-images-amazon.com/images/I/81aA7hEEykL.jpg)

### Domain Driven Design 

![inline](./attachments/domain-driven-design-model-driven-design/main.png)

[source](http://tigerthinks.com/images/books/domain-driven-design-model-driven-design.png)


### Domain Driven Design

![inline](./attachments/domain-driven-design-model-driven-design/stage0.png)

### Domain Driven Design

![inline](./attachments/domain-driven-design-model-driven-design/stage1.png)

### The Layered Architecture

![right fit](https://miro.medium.com/max/1225/1*Ly4z7CXj1znZl8fPIbQ5_w.png)

|Layer|Description|
|------|--------|
| Presentation Layer |Responsible for showing information to the user and interpreting the user’s commands. |
|Application Layer| Defines the jobs the software is supposed to do and directs the expressive domain objects to work out problems|
|Domain Layer |Responsible for representing concepts of the business, information about the business situation, and business rules. |
|Infrastructure Layer |Provide generic technical capabilities that support the higher layers: message sending for the application, persistence for the domain, drawing widgets for the UI, etc. |

### Entities

![right fit](./attachments/domain-driven-design-model-driven-design/stage2.png)

- Are objects defined primarily by their identity
- Their identities must be defined so that they can be effectively tracked. We care about *who* they are rather than *what* information they carry
- They have lifecycles may can radically change their form and content, while a thread of continuity must be maintained. 
- E.g., bank accounts, deposit transaction.


### Value Objects

![right fit](./attachments/domain-driven-design-model-driven-design/stage3.png)

- Value Objects represent a descriptive aspect of the domain that has no conceptual identity. 
	- They are instantiated to represent elements of the design that we care about only for *what* they are, not *who* they are.
	- E.g.,  For example, street, city, and postal code shouldn’t be separate attributes of a Person object.

### Services

![right fit](./attachments/domain-driven-design-model-driven-design/stage6.png)

 - Services are operations offered as an interface that stands alone in the model, without encapsulating state as Entities and Value Objects do. 
 	- They are a common pattern in technical frameworks, but they can also apply in the domain layer.
 	- The name “service” is ment to emphasize the relationship with other objects. 
 
 
### The Lifecycle of a Domain Object

Every object has a lifecycle. It is born, it may go through various states, it eventually dies and is either archived or deleted.

The problems fall into two categories:
- Maintaining integrity throughout the lifecycle
- Preventing the model from getting swamped by the complexity of managing the lifecycle.

The most important concepts for this are Aggregates and Repositories[^3]

[^3]: an Aggregate is always associated with one and only one Repository.


### Aggregates and Repositories

**Aggregates** are a cluster of Entities and Value Objects that make sense domain-wise and are retrieved and persisted together.

E.g. A Car is an aggregate of wheel, engine, and the customer

**Repositories** offer an interface to retrieve and persist aggregates, hiding lower level details from the domain. 


### Spin Off DDD: Event Sourcing (More when dealing with Streaming)

### Event Sourcing[^51]

> The fundamental idea of Event Sourcing is ensuring that every change to the state of an application is captured in an event object, 

> Event objects are immutable and stored in the sequence they were applied for the same lifetime as the application state itself.

[^51]: Martin Fowler, [link](https://martinfowler.com/eaaDev/EventSourcing.html)

### Events

Events are both a fact and a notification. 

They represent something that happened in the real world but include no expectation of any future action. 

They travel in only one direction and expect no response (sometimes called “fire and forget”), but one may be “synthesized” from a subsequent event.

---

![original fit](./attachments/seen.png)

---

![original fit](./attachments/brandnew.png)

---

![original fit](attachments/4doohb.jpg)