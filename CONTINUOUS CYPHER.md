
# CONTINUOUS CYPHER (AKA Seraph)

Emanuele Falzone, Riccardo Tommasini, Emanuele Della Valle


---
### Running Example: Smart Mall
	
The *Smart Mall* monitors customers that approach or enter shops (proximity sensors) and the products they buy (smart cash-registry). The *shop owners* are interested to
access the advanced analytics for businnes reasons. Additionally, the analytics
can be enrinched with data about the customers that liked the page on a social network.

---
### Background

- *Data Stream* is an unbounded sequence of pairs (o,t) where 
	- o is a data item
	- t is a timestamp

- e.g., the stream of recepit from people that buy products with smart cash-registry.

---
### Background
- *Continuous Semantics* our of an infinite output we obtain an infinite input

- e.g., the count of sold product  grows indefinitely.

---

### How does this impact Graph Processing?

---

- *Graph Streams* extend the notion of Data Stream to graph by considering only graphs as data items.

- Seller sells something to someone.  
- E.g. [seller:Person] -[:sells]->[:Product] <- [:buys]- [buyer:Person] at time t.

---

### Not to be confused with...

- *Dynamic Graphs*: graphs whose content, i.e., vertices, edges, or their properties are unpredictably updated.
-  The computing system is assumed to be able to store at least the current state of the graph, if not its entire change history.  
-  A dynamic graph extends the standard notion of graphs to include updates, i.e., insertion and deletion.

---

### Not to be confused with...
- *Streaming Graphs* are graphs that grow indefinitely. 
- They can be seen as a special case of Dynamic Graphs where the updates are limited to insertions.
-  Streaming graphs are unbounded, i.e., they keep expanding indefinitely and the system is assumed to be unable to store the whole graph state. 

---

### Notions of Time (t)

- Event Time, i.e., the time at which something happens in the real-world.
- Ingestion Time, i.e., the time at which the data are recevied by the data system.
- Processing Time, i.e., the time at which data are processed by the data system.

---

### [Enabling Continuous Querying](https://link.springer.com/article/10.1007/s00778-004-0147-z)

![inline CQL](https://raw.githubusercontent.com/riccardotommasini/stuffs/master/cql.png)

---
### [Designing Stream Processing Engines](https://journalofbigdata.springeropen.com/articles/10.1186/s40537-017-0072-9)  (SPE)

![inline dag.pdf](https://raw.githubusercontent.com/riccardotommasini/stuffs/master/dag.png)

---

Introducing time as a first-class citizen in the data system, impact the query answering semantics. In particular, it requires some design-decision that are collectively called *Execution Semantics*

---

### Explaining the execution semantics of SPE with [SECRET](https://dl.acm.org/doi/abs/10.14778/1920841.1920874)

![inline 150% secret.png](https://raw.githubusercontent.com/riccardotommasini/stuffs/master/secret.png)

---

### Explaining the execution semantics of SPE with [SECRET](https://dl.acm.org/doi/abs/10.14778/1920841.1920874)

- SECRET includes the following four primitives:
	- Tick: determines what triggers the computation in a SPEs.
	- Scope: determines what is subject of the computation in the stream
	- Content: determines what is visible to the query engine.
	- *Report*: determines when the user observes the results.

---

### Proposal for Continuous Cypher (AKA Seraph) 

---
### Use Case 1: NEED FOR CONTINUOUS REPORTING 

- The duration of the phenomenon is known.
- There is a requirement for being reactive in the reporting (Aletering).
- The underlying data system minimizes latency as a side effect.

---

### Example 

- List all the users that are near a shop for in the last 10 minutes.

--- 

### Syntax Proposal

```sql
FROM STREAM kafka://topicname WITH WINDOW RANGE 10m 
MATCH (b:Person)-[:NEAR]->(s:Shop)
RETURN *
```

---

#### Equivalent to

```sql
FROM STREAM kafka://topicname WITH WINDOW RANGE 10m 
MATCH (b:Person)-[:NEAR]->(s:Shop)
RETURN *
OUTPUT EVERY 1 EVENT
```

- Output clause controls the report explicitly.
- NOTE:  is *Event* a good keyword?

---
### Use-Case 2: NEED FOR PERIODIC REPORTING

- The duration of observed phenomenon is known
- There is need of regular reporting (Monitoring)
- The underlying system optimizes for throughput

---
### Example

- Count how many *clicks* are in near the shop for 30 minutes every 15 minutes. 
- A *Click* is a set of customers that are firends on our SocialNetwork, with at most 2 hops. 

---
### Syntax Proposal

```sql
FROM STREAM kafka://topicname WITH WINDOW RANGE 30m 
MATCH 
(a:Person)-[:FRIEND*..2]->(b:Person),
(a:Person)-[:NEAR]->(s:Shop)
(b:Person)-[:NEAR]->(s)
RETURN b.name, count(*)
OUTPUT EVERY 15m
```
---
### Use Case 3: NEED FOR CUSTOM REPORTING

- The duration of the phenomenon is unknown.
- We are able to recognize the starting condition.
- We are interested to detect when the phenomenon ends.
- The underlying system mediates between throughput and latency.

---
### Example

- Count the activities of the customers in the mall, if they do not do anything for 15 minutes assume they left.

--- 
### Syntax Proposal

```sql
FROM STREAM kafka://topicname WITH WINDOW SESSION 15m 
MATCH (a:Person)-[:enters]->(:Shop)
UNION
MATCH (a:Person)-[:buys]->(:Product)
RETURN a.name, count(*) as activities
```
---

### An Example of A Complex Query (Negation)

- List all the users who are eligible for a discunt published by a shop owner who were around the shop in the last 30 minutes. 
- *Eligible* means they did not use the discount yet. 30 minutes is the duration of the discount published by the shop owner.

--- 

### Syntax Proposal

```sql
FROM STREAM kafka://topicname WITH WINDOW RANGE 30m 
MATCH 
(a:Person)-[:OWNS]->(s:Shop),
(a)-[:OFFERS]->(c:Coupon),
(b:Person)-[:NEAR]->(s)
WHERE NOT (b)-[:USED]->(c)
RETURN b.name,s.name,c.description
```
---
### Comments From the Discussion

- Input stream of bindings.
- Use GQL as a target language.
- Show the alignment with cypher and Streaming GQL. 

--- 

[[Notes on Seraph]]