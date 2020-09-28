footer:  [Riccardo Tommasini](http://rictomm.me) - riccardo.tommasini@ut.ee - @rictomm 
slide-dividers: #, ##, ###
slidenumbers: true
autoscale: true
theme: Plain Jane

# Graph Databases

### Graph Emerging Technologies

![inline](./attachments/graphemergingtechnologies.png)

### Graph Databases

-   Graph DB: is an online database management system with CRUD capabilities.

-   The power of Graph Databases appear when you have OLTP fashioned queries.

-   Where you can select subset of your graph based on a condition and then operate on that subset data.

-   But, global OLAP queries over the entire graph will not be so efficient in graph databases.

-   For these kind of online global queries, we need other graph processing frameworks or Graph Computation engines either centralized or distributed.

-   These systems will operate on your whole graph/data, applying computation (algorithms), and finally get final results.

### Graph Storage

   -   Underlying Storage : (Native Graph Storage vs other Non-native approaches )
   -   Some graph databases use native graph storage that is optimized and designed for storing and managing graphs.
   - Some serialize the graph data into a relational database, an object-oriented database, or some other general-purpose data store.
  
### Which one to choose?!

-   Native graph storage and native graph processing are neither good nor bad.

-   They are simply classic engineering trade-offs:

    -   Native graph processing (index-free adjacency) benefits traversal performance, but at the expense of making some queries that don't use traversals difficult or memory intensive.

    -   Native graph storage is that its purpose-built stack is engineered for performance and scalability. The benefit of nonnative graph storage, in contrast, is that it typically depends on a mature non graph backend (such as MySQL).

![inline](./attachments/tradeoff.png)

### Processing Engine

- Native Index-Free Adjacency 
-  Non-native processing
- Some definitions require that a graph database use index-free adjacency, meaning that connected nodes physically "point" to each other in the database.


### Examples of Graph DBs[^1]

![inline](./attachments/graphdatabases.jpg)

### Neo4J Graph DB[^2]

-   One of the most popular graph database designed as an open-source NoSQL database.
-   It supports ACID transactions
-   It implements a Property Graph Model efficiently down to the storage level.
-   It is useful for single server deployments to query over medium sized graphs due to using memory caching and compact storage for the graph.

![inline](./attachments/neo4j_logo_globe.png)

### Neo4J Graph DB

- Its implementation in Java also makes it widely usable.
- It provides master-worker clustering with cache sharding for enterprise deployment.
-   It uses Cypher as a declarative query language.

![inline](./attachments/neo4j_logo_globe.png)

### AllegroGraph Semantic Graph DB[^4]

-   AllegroGraph is a graph database and application framework for building Semantic Web applications.

-   It can store data and meta-data as triples.

-   It can query these triples through various query APIs like SPARQL (the standard W3C query language).

-   It supports RDFS++ as well as Prolog reasoning with its built-in reasoner.

-   AllegroGraph includes support for Federation, Social Network Analysis, Geospatial capabilities and Temporal reasoning.

![inline](./attachments/allegrograph_logo2.png)
![inline](./attachments/blog-spparql.png)

### Graph DBs VS. RDBs

![inline](./attachments/graph-databases-10-638o.jpg)

### Graph DBs VS. Relational DBs and NOSQL DBs

-   **Performance:**

    -   In contrast to relational databases, where join-intensive query performance deteriorates as the dataset gets bigger, with a graph database performance tends to remain relatively constant, even as the dataset grows.

-   **Flexibility:**

    -   Unlike various Relational Databases, Graphs are naturally additive, meaning we can add new kinds of relationships, new nodes, new labels, and new subgraphs to an existing structure without disturbing existing queries and application functionality.

-   **Agility:**

    -   Schema-Free Graph DBs, coupled with the testable nature of a graph database application programming interface (API) and query language, empower us to evolve an application in a controlled manner.

![right fit](./attachments/performance.png)

### RDB Lack Relationships!!

Despite their names though, relational databases are less suited for exploring relationships !!

![inline](./attachments/cropped-relationships-matter-text-logo-2018-b.png)

###  In RDBMSs

![right fit](./attachments/rdbsjoinpains.jpg)

-   RDBs are well fitted to find All-like queries, thanks to the internal structure of the tables. It's all the more adequate when aggregations over a complete dataset must be performed.

-   However, Relational databases struggle with highly connected domains.

### Graph DBs VS. NoSQL

![inline](./attachments/RDBMS_NoSQL-1-1024x785.png)

### NOSQL also lacks Relationships

![inline](./attachments/nosqldbs.jpg)

-   Most NOSQL databases whether key-value, document, or column oriented store sets of disconnected documents/values/columns.

-   This makes it difficult to use them for connected data and graphs.

### NOSQL also lacks Relationships

-   One well-known strategy for adding relationships to such stores is to embed an aggregate's identifier inside the field belonging to another aggregate.

-   But this requires joining aggregates at the application level, which quickly becomes prohibitively expensive.

    -   Seeing a reference to order: 1234 in the record beginning user: Alice, we infer a connection between user: Alice and order: 1234.

-   Because there are no identifiers that "point" backward (the foreign aggregate "links" are not reflexive.

-   *Who customers that bought a particular product?* Alternatively, we can retrospectively insert backward-pointing foreign aggregate references, and then query for the result. Either way, the results will be latent.

### Graph DBs embrace Relationships

![inline](./attachments/graphdbsRelations.jpg)

### Popularity of Graph DBs

![inline](./attachments/graphdbspopular.jpg)

## Graph Query Languages

### SQL VS. Graph Querying Languages

-   SQL is the standard query language for relational databases.
-   However, there are several graph query languages, none of them is standardized till now.
-   SPARQL is the only standard query language, but it is only that for RDF graphs.
-   SPARQL cannot be directly reused for general attributed graphs due to the differences in the data model and the specifications of the query requirements.
-   A standard query language is really needed. A lot of works have been exerted to be standardized query languages such as `Cypher` and `G-Core`, but not yet standard.

![right fit](./attachments/sqlstandard.jpg)

### How To Query Graph Databases!

-   Although graphs can still be (and sometimes still are) stored in relational databases, the choice to use a graph database for certain domains has significant benefits in terms of querying.

-   Where the emphasis shifts from joining various tables to specifying graph patterns and navigational patterns between nodes that may span arbitrary-length paths.

-   A variety of graph database engines, graph data models, and graph query languages have been released over the past few years.

    -   Examples of Graph DBs: Neo4j, OrientDB, AllegroGraph.

    -   Graph data models: Property graphs, and edge labelled graphs and many other variations of them.

    -   Different modern query languages also come to the scene such as Cypher, SPARQL, Gremlin and many more.

![inlineco](./attachments/Creating-Custom-Graph-Views-Over-your-RDF-Data_without-taaext.jpg)

### Graph Data Models

-   Two Popular Graph Data Models:

    -   Edge-Labelled Graphs - Example: RDF

    -   Property Attributed Graphs

![inline](./attachments/graphDatamodels.jpg)

### Property Graphs Vs. Edge-Labelled Graphs

-   Although it is very simple, Edge-Labelled Graphs are widely adopted in practice. E.g. Resource Description Framework (RDF) (Figure in the previous slide).

-   However, it is often cumbersome to add information about the edges to an edge-labelled graph.

-   For example, if we wished to add the source of information, for example, that the acts-in relations were sourced from the web-site IMDb.

-   Adding new types of information to edges in an edge-labelled graph may thus require a major change to the graph's structure, entailing a significant cost.

### Variations of the Property Graph Data Model (PGM)

-   **Direction.** A property graph is a directed graph; the PGM defines edges as ordered pairs of vertices.

-   **Multi-graph.** A property graph is a multi-graph; the PGM allows multiple edges between a given pair of vertices.

    -   **Simple graphs** (in contrast to multi-graphs) additionally require to be injective (one-to-one).

-   **Labels.** A property graph is a multi-labeled graph; the PGM allows vertices and edges to be tagged with zero or more labels.

-   **Properties.** A property graph is a key-value-attributed graph; the PGM allows vertices and edges to be enriched with data in the form of key-value pairs.

### Property Graph Example

![inline](./attachments/propertygraph.png)

### Graph Query Languages Core Features

-   Features:

    -   Graph Patterns.

    -   Navigational "Path" expressions.

    -   Aggregation

    -   Graph-to-Graph queries.

    -   Path unwinding.

-   Standardization:

    -   (SPARQL/SPARQL 1.1) --- Yes

    -   (Gremlin,G-Core,Gremlin,GraphQl,Cypher)--- No

![right fit](./attachments/graphquerylangsexamples.jpg)

### Graph Query Languages In Action

-   **Cypher** --- Property Graphs

-   Gremlin--- Property Graphs

-   GraphQL --- Edge-Labelled multi Graphs

-   **SPARQL** --- Edge-Labelled Graphs RDF

-   G-Core --- Property Graphs

![right fit](./attachments/graphquerylangsexamples.jpg)

## Pattern Matching and Graph Navigation

### Graph Pattern Matching VS. Graph Navigational

-   Although these query languages vary significantly in terms of style, purpose, expressivity, implementation.
-   They share a common conceptual core, which consists of two natural operations of querying graphs:
    -   **Graph pattern matching**
        -   The graph pattern is a graph-structured query that should be matched against the graph database.
        -   Two main categories: **Basic Graph Patterns**, and **Complex Graph Patterns**.
    -   Graph navigation.
        -   More flexible querying mechanisms that allow to navigate the topology of the data.
        -   e.g find all friends-of-a-friend of some person in a social network.

![right fit](./attachments/pattern.jpg)

### Graph Patterns and Pattern Matching

**Basic** VS. **Complex** Graph patterns

-   Graph Pattern Matching is the matching of graph patterns over the graph DB.

    -   Basic Graph Patterns (BGPs): the core of any graph query language.

    -   Extending BGPs with some additional query features: such as UNION, Difference, Projection, Optional (aka left-outer-join), and Filter (Which Covers Selection) giving rise to Complex Graph Patterns (CGPs).

![inline](./attachments/pattern.jpg)

### Graph Pattern Matching VS. Graph Navigational

-   For matching basic graph patterns we classified the main proposals for the semantics into two categories:

    -   **Homomorphism-base**d: matching the pattern onto a graph with no restrictions.
    -   **Isomorphism-based**: one of the following restrictions is imposed on a match:
        -   **No-repeated-anything**: no part of a graph is mapped to two different variables.
        -   **No-repeated-node**: no node in the graph is mapped to two different variables.
        -   **No-repeated-edge**: no edges in the graph is mapped to two different variables.
 
![right fit](./attachments/pgpsemantics.png)

### Complex Graph Patterns(CGPs)

-   Basic Graph Patterns (BGPs) should arguably form the core of any graph query language, and are indeed present in all of the current practical systems.

-   Basic Graph Patterns(BGPs) can be further extended with operators.

-   These operators are such as:
    -   Projection,
    -   Union,
    -   Optional,
    -   And many more operators.

-   This gives the rise to Complex Graph Patterns (CGPs).

### CGPs Operators

-   **Projection:**

    -   Like `SELECT` in SQL, `RETURN` in Cypher, `SELECT` in SPARQL and Gremlin are used also to select project on specific outputs.

    -   Example: To retrieve only the names of actors who starred together in Unforgiven-for example, for a user who is uninterested in node or edge ids.

-   **Union:**

    -   Let $Q1$ and $Q2$ be two graph patterns. The union of $Q1$ and $Q2$ is a complex graph pattern whose evaluation is defined as the union of the evaluations.

    -   Example: *one could use union to find the movies in which Clint Eastwood acted or which he directed*.

-   **Difference:**

    -   The difference of $Q1$ and $Q2$ is also a complex graph pattern whose evaluation is defined as the set of matches in the evaluation of $Q1$ that do not belong to the evaluation of $Q2$.

    -   Example: *one could use difference to find the movies in which Clint Eastwood acted but did not direct*.

### CGPs Operators

-   **Optional:**

    -   This feature is particularly useful when dealing with incomplete information, or in cases where the user may not know what information is available.

    -   Example: *Find the information relating to the gender of users is incomplete but may still be interesting to the client, where available*.

-   **Filter:**

    -   Users may wish to restrict the matches of a cgp over a graph database G based on some of the intermediate values returned using, for example, inequalities, or other types of expressions.

    -   Example: *Find a client may be interested in finding things that mutual friends both liked during October 2015, the client could apply a filter on the CGP of the following form*: 01-10-15 $leq$ x5 $leq$ 31-10-15 and 01-10-15 $leq$ x6 $leq$ 31-10-15.

### **SPARQL** - The RDF Query Language

-   SPARQL is the standard query language of RDF and become official W3C recommendation since 2003.

-   SPARQL is a pattern matching query language over the RDF graph. SPARQL queries contain a set of triple patterns (TPs), also known as Basic Graph Patterns (BGPs).

-   Triple patterns are similar to RDF triple patterns, but each of the subject, predicate or object may be unbounded variable preceded by ("?") prefix.

-   SPARQL mission is to bind those variable by matching the query patterns to triples in the RDF dataset.

![inline](./attachments/blog-spparql.png)

### Graph Patterns in Action: SPARQL

Let us take a closer look at how graph patterns are applied in three practical query languages: SPARQL, Cypher, and Gremlin.

-   SPARQL: Projection

    -   The following SPARQL query represents a complex graph pattern that combines the basic graph pattern with a projection that asks to only return the co-stars and not the movie identifier.

![right fit](./attachments/sparql projecttion.jpg)

### SPARQL over RDF Graphs

![inline](./attachments/rdfgraph.jpg)

-   An RDF graph is simply a set of triples analogous to the edges in a graph database.

-   In the case of RDF, the set V can be partitioned into disjoint sets of IRIs, literals, and blank nodes, and the set Lab is a subset of IRIs (not necessarily disjoint from V ).

-   RDF graph as simply a special type of edge-labelled graph.

-   SPARQL is a declarative language recommended by the W3C for querying RDF graphs.

-   The basic building blocks of SPARQL queries are triple patterns, which are RDF triples where the subject, object, or predicate.

### SPARQL over RDF Graphs

![inline](./attachments/rdfgraph.jpg)

-   RDF graph as simply a special type of edge-labelled graph.

-   SPARQL is a declarative language recommended by the W3C for querying RDF graphs.

-   The basic building blocks of SPARQL queries are triple patterns, which are RDF triples where the subject, object, or predicate.

### Graph Patterns in Action

Union, Difference and Optional in SPARQL

![inline](./attachments/sparqlexamples.png)

-   This example of a union to find movies that Clint Eastwood has acted or directed in. This returns :UNFORGIVEN

-   SPARQL Difference We could use difference to ask for people who acted in the movie Unforgiven but who did not (also) direct it this query will return:Anna-Levine

-   SPARQL: Optional Using optional, we could ask for movies that actors have appeared in, and any other participation they had with the movie besides acting in it

### Cypher - The Neo4J DB Query Language

-   Cypher is Neo4j's open graph query language.

-   Cypher's syntax provides a familiar way to match patterns of nodes and relationships in the graph.

-   Cypher is declarative query language.

-   Cypher is too easy to understand and learn.

-   It is backed by several companies in the database space and allows implementors of databases and clients to freely benefit, use from and contribute to the development of the openCypher language.

![inline](./attachments/cypherneo4j.jpg)

### Graph Patterns in Action: Cypher

-   Cypher is a declarative language for querying property graphs that uses "patterns" as its main building blocks.

-   Patterns are expressed syntactically following a "pictorial" intuition to encode nodes and edges with arrows between them.

-   Cypher: **Projection**

    -   This query asks for Co-Stars of the *"Unforgiven"* movie.

    -   In this case, we would also get the matches that send both x1 and x2 to the node of Clint Eastwood (and likewise to the node of Anna Levine).

![inline](./attachments/cypherexamples.jpg)

### Graph Patterns in Action

**Union**, **Difference** and **Optional-Filer** in Cypher

![inline](./attachments/cypherexamples2.jpg)

### Navigational (Path) Queries in Graphs

![inline](./attachments/navigationalqueries.png)

### Path under Set Semantics

-   **Arbitrary paths**: All paths are considered. More specifically, all paths in G that satisfy the constraints of P are included in P (G).

-   **Shortest paths**: In this case, P (G) is defined in terms of shortest paths only, that is, paths of minimal length that satisfy the constraint specified by P.

-   **No-repeated-node paths**: In this case, P (G) contains all matching paths where each node appears once in the path; such paths are commonly known as simple paths. This interpretation makes sense in some practical scenarios; for example, when finding a route of travel, it is often not desired to have routes that come to the same place more than once.

-   **No-repeated-edge paths**: Under this semantics, P (G) contains all matching paths where each edge appears only once in the path. The Cypher query language of the Neo4j engine currently uses this semantics.

### Navigational Path Queries

-   While graph patterns allow for querying graph databases in a bounded manner, it is often useful to provide more flexible querying mechanisms that allow to navigate the topology of the data.

-   One example of such a query is to find all friends-of-a-friend of some person in a social network.


![right fit](./attachments/navigationalqueries.jpg)

### Output of Navigational Queries

-   As hinted at previously, a user may have different types of questions with respect to the paths contained in the evaluation P(G), such as:

    -   *Does there exist any such path*

    -   *Is a particular path contained in P (G* )

    -   *What are the pairs of nodes connected by a path in P (G)*

    -   *What are (some of) the paths in P (G)*

-   We can Categorize such questions by what they return as results:

    -   Boolean --- (True / False) values.

    -   Nodes --- are interested in the nodes connected by specific paths.

    -   Paths --- some or all of the full paths are returned from P (G). Example:Some of the Shortest Paths.

    -   Graphs --- is to offer a compact representation of the output as a graph

### Navigational Graph Patterns (NGPs)

-   Combining path queries with basic graph patterns (BGPs) gives rise to navigational graph patterns (NGPs).

-   In particular, this language allows to express that some edges in a graph pattern should be replaced by a path (satisfying certain conditions) instead of a single edge.

-   Example: Persons and movies are connected , while a person can also have an author edge connecting it to an article.

-   In such a database we might be interested in finding people with finite Erdos-Bacon number, that is, people who are connected to Kevin Bacon through co-stars relations and are connected to Paul Erdos through co-authorship relations.

![inline](./attachments/ngbs.png)

### Navigational Graph Patterns (NGPs)

-   Coming back to the social network, we might be interested in finding all friends of friends of Julie that liked a post with a tag that Julie follows. The navigational graph pattern in this Figure expresses this query over our social graph.

![inline](./attachments/ngpexample2.jpg)

-   Extending Navigational Graph patterns with the complex operators of "Projection", "Optional", "Filter", "Union" and "Difference" give the rise to another new type of them: (cngps).

-   Example: Let's call these results the "recommended posts" for Julie. Now consider a copy of the same pattern to find the recommended posts for John.

### Navigational Queries in Action: SPARQL

-   Since Version 1.1 , SPARQL permits the use of property paths.

-   SPARQL Property Paths are an extended form of regular expression.

-   As a consequence, we can express any path query using SPARQL 1.1.

---
[.column]
#### Example 1
-  Consider the following SPARQL query to find all pairs of actors who have finite collaboration distance, we can use 

```SQL
SELECT ?x ?y
WHERE { ?x (:acts_in/acts_in*) ?y }

```

[.column]

#### Example 2
-   Consider the following SPARQL query with a negated property-set. 
-   This query will match :Unforgiven (the IRI) and "Unforgiven" (the title string) for ?y.

```SQL
SELECT ?y
WHERE { :Clint_Eastwood (!{rdf:type,:directs})* ?y }

```


### Navigational Queries in Action: SPARQL

-   Similarly, SPARQL can also express navigational graph patterns (ngps).

-   **Example 1**: The ngp query find all people with a finite Erdos-Bacon number can be expressed in SPARQL as:

    ![inline](./attachments/sparqlngbsexample3.png)

-   This query is a conjunction of two RPQs, where the symbol "." denotes conjunction.

-   Likewise, SPARQL can express complex navigational graph patterns (cngps).

-   **Example 2**. We can express an RDF version of the query for the posts recommended to Julie but not to John as follows:

    ![inline](./attachments/sparqlngbsexample4.png)

### Navigational Queries in Action: Cypher

-   While not supporting full regular expressions, Cypher still allows transitive closure over a single edge label in a property graph.

-   Since it is designed to run over property graphs, Cypher also allows the star to be applied to an edge property/value pair.

-   **Example 1**, To compute the friend-of-a-friend relation in Cypher over the graph, we can use the following expression.

    This expression selects pairs of nodes that are linked by a path completely labelled by knows. To do this, it applies the star operator * over the label knows .

    ![inline](./attachments/cypherngpsexample1.png)

### Navigational Queries in Action: Cypher

-   Example 2. If we wanted to find friends of friends of Julie and return only the shortest witnessing path. This will return a single shortest witnessing path. If we wanted to return all shortest paths, then we could replace "shortestPath" with "allShortestPaths".

    ![inline](./attachments/cypherngpsexample2.png)

-   Example 3. Coming back to the social network, if we want to find all friends of-friends of Julie that liked a post with a tag that Julie follows, we can use the following Cypher query:

    ![inline](./attachments/cypherngpsexample3.png)

### Navigational Queries in Action: Cypher

-   Another interesting feature available in Cypher is the ability to return paths.

-   Example 4. If we wanted to return all friends of friends of Julie in the graph, together with a path witnessing the friendship, then we can use:

    ![inline](./attachments/cypherngpsexample4.png)

-   Result will be:

    ![inline](./attachments/cypherngpsexample5.png)

### Other Popular Query Languages.

-   G-Core[^7]

    -   Community effort between industry and academia to shape and standardize the future of graph query languages.

    -   G-Core Features:

        -   Composability: Graphs are inputs and outputs of the queries. Queries can be composed. The fact that G-CORE is closed on the PPG data model means that subqueries and views are possible.

        -   Paths are First Class-Citizens: Paths can increase the expressivity of the language. G-Core extends graphs models with paths (PPG). Can have labels and prosperities.

        -   Capture a core: Standards are difficult and politics, Take the successful functionalities with tractable evaluation of current languages as a base to develop


### Other Popular Query Languages.

-   GraphQl also removes redundancy, Another restriction is type restrictions.

-   The following Figure (left) shows an example GraphQL query over the domain (F, A, T) and the response is in the right.

![inline](./attachments/graphql.png)

### Graph Query Languages Features Comparison

![inline](./attachments/graphqlscompar.png)


[^1]: Ian Robinson, Jim Webber, and Emil Eifrem. 2013. Graph Databases. O'Reilly Media, Inc.

[^2]: [url](https://neo4j.com)

[^3]: <http://titan.thinkaurelius.com/>

[^4]: <https://franz.com/agraph/allegrograph/>

[^5]: <https://docs.janusgraph.org/latest/gremlin.html>

[^6]: Sherif Sakr, Sameh Elnikety, and Yuxiong He. 2012. G-SPARQL: a hybrid engine for querying large attributed graphs. In Proceedings of the 21st ACM international conference on Information and knowledge management (CIKM '12)

[^7]: Angles, Renzo, et al. *G-CORE: A core for future graph query languages*. Proceedings of the 2018 International Conference on Management of Data. ACM, 2018.

[^8]: http://www.cs.cmu.edu/ pegasus/

[^9]: https://github.com/twitter/cassovary

[^10]: http://uzh.github.io/signal-collect/

[^11]: Kyrola, Aapo, Guy E. Blelloch, and Carlos Guestrin. *Graphchi: Large-scale graph computation on just a pc. -USENIX, 2012.

[^12]: A. Roy, I. Mihailovic, and W. Zwaenepoel. 2013. X-Stream: Edge-centric graph processing using streaming partitions. In Proceedings of the 24th ACM Symposium on Operating Systems Principles (SOSP'13)

[^13]: Nilakant, K., Dalibard, V., Roy, A., & Yoneki, E. (2014, June). *PrefEdge: SSD prefetcher for large-scale graph traversal*. In Proceedings of International Conference on Systems and Storage

[^14]: Roy, A., Mihailovic, I., & Zwaenepoel, W. (2013, November). *X-stream: Edge-centric graph processing using streaming partitions*. In Proceedings of the Twenty-Fourth ACM Symposium on Operating Systems Principles

[^15]: Roy, A., Bindschaedler, L., Malicevic, J., & Zwaenepoel, W. (2015, October). *Chaos: Scale-out graph processing from secondary storage*. In Proceedings of the 25th Symposium on Operating Systems Principles.

[^16]: Y.Tian, A. Balmin, S. Andreas Corsten, S. Tatikond, and J. McPherson. 2013. *From \"Think Like a Vertex\" to \"Think Like a Graph.\" -Proc. VLDB Endow.

[^17]: Y. Simmhan, A. Kumbhare, C. Wickramaarachchi, S. Nagarkar, S. Ravi, C. Raghavendra, and V. Prasanna. 2014. GoFFish: *A sub-graph centric framework for large-scale graph analytics*. In Proceedings of the Euro-Par 2014 Parallel Processing Conference.

[^18]: Z. Khayyat, K. Awara, A. Alonazi, H. Jamjoom, D. Williams, and P. Kalnis. Mizan: a system for dynamic load balancing in large-scale graph processing. EuroSys, 2013.

[^19]: http://infolab.stanford.edu/gps/

[^20]: http://www.cse.cuhk.edu.hk/pregelplus/

[^21]: http://dbs.uni-leipzig.de/en/research/projects/gradoop

[^22]: Y. Low, J. Gonzalez, A. Kyrola, D. Bickson, C. Guestrin, and J. M. Hellerstein. *Distributed GraphLab: A Framework for Machine Learning in the Cloud*. PVLDB, 2012

[^23]: https://giraph.apache.org/

[^24]: <https://spark.apache.org/graphx/>

[^25]: <https://www.w3.org/TR/rdf-sparql-query/>

[^26]: Eugene Inseok Chong, Souripriya Das, George Eadon, and Jagannathan Srinivasan. *An efficient sql-based rdf querying scheme*. In Proceedings of the 31st international conference on Very large data bases, 2005

[^27]: Thomas Neumann and Gerhard Weikum. *The rdf-3x engine for scalable management of rdf data*. The VLDB Journal, 2010

[^28]: Weiss, Cathrin, Panagiotis Karras, and Abraham Bernstein. *Hexastore: sextuple indexing for semantic web data management*. Proceedings of the VLDB Endowment, 2008

[^29]: Li Ma,et.al. *Rstar: An rdf storage and query system for enterprise resource management*. In Proceedings of the thirteenth ACM international conference on Information and knowledge management, 2004

[^30]: Luis Galarraga. *Partout: a distributed engine for efficient RDF processing*. In Proceedings of the 23rd International Conference on World Wide Web,2014

[^31]: M.Hammoud, D. A.Rabbou, R.Nouri, S.Beheshti, and S.Sakr. *Dream: Distributed Rdf Engine with Adaptive query planner and Minimal communication*. Proceedings of the VLDB 2015

[^32]: Rohloff, Kurt, and Richard E. Schantz. *High-performance, massively scalable distributed systems using the MapReduce software framework: the SHARD triple-store*. Programming support innovations for emerging distributed applications. ACM, 2010.

[^33]: Nikolaos Papailiou, Dimitrios Tsoumakos, Ioannis Konstantinou, Panagiotis Karras, and Nectarios Koziris. *H2rdf+: an efficient data management system for big RDF graphs*. In Proceedings of the 2014 ACM SIGMOD international conference on Management of data.

[^34]: Olivier Cure, Hubert Naacke, Mohamed Amine Baazizi, and Bernd Amann. *HAQWA: a hash-based and query workload aware distributed RDF store*. In International Semantic Web Conference (Posters & Demos), 2015

[^35]: Damien Graux, Louis Jachiet, Pierre Geneves, and Nabil Layaida. *Sparqlgx: Efficient distributed evaluation of Sparql with Apache Spark*. In International Semantic Web Conference, 2016.

[^36]: Alexander Schatzle, Martin Przyjaciel-Zablocki, Simon Skilevic, and Georg Lausen. *S2rdf: Rdf querying with Sparql on Spark*. VLDB 2016

[^37]: Alexander Schatzle, Martin Przyjaciel-Zablocki, Thorsten Berberich, and Georg Lausen. *S2x: graph-parallel querying of RDF with Graphx*. In Biomedical Data Management and Graph Online Querying, 2015

[^38]: Gergo Gombos, Gabor Racz, and Attila Kiss. *Spar (k) ql: Sparql evaluation method on Spark Graphx*. In 2016 IEEE 4th International Conference on Future Internet of Things and Cloud Workshops (FiCloudW), 2016

[^39]: Ramazan Ali Bahrami, Jayati Gulati, and Muhammad Abulaish. *Efficient processing of Sparql queries over GraphFrames*. In Proceedings of the International Conference on Web Intelligence, 2017


