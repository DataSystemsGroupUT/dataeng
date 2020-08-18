footer:  [Riccardo Tommasini](http://rictomm.me) - riccardo.tommasini@ut.ee - @rictomm - 
slidenumbers: true
<!-- : #course, #topic, #LTAT.02.007 -->

# Part 1: Data Modelling

### What is Data Modelling?

It is the process of defining the structure of the data for the purpose of communicating[^1] or to develop an information systems[^2].

[^1]: between functional and technical people to show data needed for business processes

[^2]: between components of the information system, how data is stored and accessed.

### Level of Data Modeling (or Models)

**Conceptual**: The data model defines *WHAT* the system contains.

^ Conceptual model is typically created by Business stakeholders and Data Architects. The purpose is to organize, scope and define business concepts and rules. Definitions are most important this level.

**Logical**: Defines *HOW* the system should be implemented regardless of the DBMS. 

^ Logical model is typically created by Data Architects and Business Analysts. The purpose is to developed technical map of rules and data structures. Business rules, relationships, attribute become visible. Conceptual definitions become metadata.

**Physical**: This Data Model describes *HOW* the system will be implemented using a specific DBMS system [^3].

^ Physical model is typically created by DBA and developers. The purpose is actual implementation of the database. Trade-offs are explored by in terms of data structures and algorithms.

[^3]: [physical](https://www.databass.dev/)

### In Big Data, Data Variety is the Driver

![inline](https://upload.wikimedia.org/wikipedia/commons/6/6d/Data_types_-_en.svg) 

### Data Modeling for Big Data[^4]
![oro](https://image.slidesharecdn.com/datamodelingbigdatadataversityaugust2016-160830052651/95/data-modeling-for-big-data-25-1024.jpg?cb=1472534835)

[^4]: [slides](https://www.slideshare.net/Dataversity/data-modeling-for-big-data) & [video](https://www.dataversity.net/ldm-webinar-data-modeling-big-data/) by Donna Burbank

^ The variety of data available today encourages the design and development of dedicated data models and query languages that can imporve both BI as well as the engineering process itself.

###  What is a data model? 

A data model represents the structure and the integrity of the data elements of a (single) applications [2](x-bdsk://DBLP:journals/sigmod/SpynsMJ02) 

Data models provide a framework for data to be used within information systems by providing specific definition and format.

The literatrure of data management is rich of data models that aim at providing increased expressiveness to the modeler and capturing a richer set of semantics.

### The Data Landscape 

![inline](./attachments/m2_structure.png)

^ Structured data are organized and labelled according to a precise model (e.g., relational data)
^ Unstructured data, on the other hand, are not constrained (e.g., text, video, audio)
^ In between, there are many form of semi-structured data, e.g., JSON and XML, whose models do not impose a strict structure but privde means for validtation. 

### History of Data Model[^5]

![inline](./attachments/History of Data Models by Ilya Katsov.png)

[^5]: [by Ilya Katsov](https://highlyscalable.wordpress.com/2012/03/01/nosql-data-modeling-techniques/)

### Querying the Data
[.text: text-scale(2.0)]

![left](./attachments/Processing%20and%20Querying.png)

Generic term that describes data manipulation.


A query is a request for data or information from a database.


### Relational Data [^6]
-  Data Model Definition
-  Modeling Techniques: Entity-Relationship Model/UML
-  Normal Forms
-  Star and Snowflake Schema
-  Reference Systems: MySQL, Postgress
	
[^6]: TODO Add reference book

###  NoSQL Data[^7]
- Key-Value: redis or memcache
- Document
- -Full-Text Search--
- Graph 
	- RDFdata
	- Property Graph
	
[^7]:[Ilya Katsov](https://highlyscalable.wordpress.com/2012/03/01/nosql-data-modeling-techniques/)

### Key-Value:
-  Data Model Definition
-  Modeling Techniques
-  Normal Forms?
-  Reference Systems: redis or memcache

### Document
-  Data Model Definition
-  Modeling Techniques
-  Normal Forms?
-  Reference Systems: mongodb

### Graph: Property Graphs
-  Data Model Definition
-  Modeling Techniques
-  Normal Forms?
-  Reference Systems: neo4j


	-  Current Trends extending Relational	
		-  NewSQL
		-  ModernSQL
		- **IDEA**: Ragab Seminars of Relational meets Graph
    - Related work on data modelling techniques
		 - UML (just mentioned)
		 - Knowledge Representation
- Query Languages and APIs
	- Query Languages
	- Dataflow
	- APIs
