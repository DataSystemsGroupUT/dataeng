
# Cassandra

![inline](https://upload.wikimedia.org/wikipedia/commons/thumb/5/5e/Cassandra_logo.svg/1280px-Cassandra_logo.svg.png)

## History of Cassandra

Originally designed at Facebook

Open-sourced and now within Apache foundation

## What Cassandra is

- A Wide [[Column Oriented Database]]
- *tuneably* consistent (**~~C~~**)
- very fast in writes
- highly avaeng
- ailable (**A**)
- fault tolerant (**P**)
-  linearly scalable, elastic scalability
- Cassandra is very good at writes, okay with reads. 

![right fit](https://www.datastax.com/sites/default/files/content/blog/blog-fix-0514.png)

## What Cassandra is not

- Cassandra is not a replacement for Relational Databases 
- Tables should **not** have multiple access paths
-  Cassandra does not support aggregates, if you need to do a lot of them, think another database.
-  Updates and deletes are implemented as special cases of writes and that has consequences that are not immediately obvious.

## Comparison with RDBMS

![inline](./attachments/cassandra-26.png)

---

![inline](./attachments/cassandra-27.png)

## Use Cases

The use-case leading to the initial design and development of Cassandra was the so entitled Inbox Search problem at Facebook.

- ![inline 30%](https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSu9NTPsdf3vDCAsMuYTH_7jsd69WrDXlCVmw&usqp=CAU)[Purchases, test scores](https://tech.ebayinc.com/engineering/cassandra-data-modeling-best-practices-part-1/)
- Storing time series data (as long as you do your own aggregates).
    - Storing health tracker data.
    - Weather service history.
    - ![inline 30%](https://miro.medium.com/fit/c/96/96/1*BJWRqfSMf9Da9vsXG9EBRQ.jpeg) [User Activity](https://netflixtechblog.com/scaling-time-series-data-storage-part-i-ec2b6d44ba39)
- Internet of things status and event history.
- ![inline 30%](https://farm1.staticflickr.com/781/20772148484_a1932971e7_o.jpg)[IOT for cars and trucks](http://highscalability.com/blog/2016/9/28/how-uber-manages-a-million-writes-per-second-using-mesos-and.html)
- Email envelopes—not the contents.


### When to consider Cassandra

[.column]
- you need really fast writes
- you need durability
- you have lots of data  (> GBs) and (>=) three servers
- your app is evolving
  - startup mode, fluid data structure
- loose domain data
  - “points of interest”

[.column]
- your programmers can handle
  - complexity
  - consistency model
  - change
  - visibility tools
- your operations can deal
  - hardware considerations
  - data transport
  - JMX monitoring

### Advantages

A general-purpose framework for high concurrency & load conditioning

Decomposes applications into stages separated by queues

Adopt a structured approach to event-driven concurrency

## Data Model

**RDBMSs**: domain-based model 
    -> what answers do I have?
    
<br><br>

**Cassandra**: query-based model 
      -> what questions do I have?

^ Start from queries, then design the data model

---

Cassandra does **not** support a full relational data model.

Instead, it provides clients with a simple data model that supports **dynamic control** over data layout and formats.

An instance of Cassandra typically consists of a one distributed multidimensional map indexed by key which contains one or more **column families** that, in turn, **rows**

![right fit](./attachments/cassandra-25.png)

---
- **Rows** are identified by a string-key
- **Column Families** correponds to tables in RDBMS but may be unstructured. A column family consists of 
  - **(Simple) Columns Families** have a name and store a number of values per row which are identified by a timestamp
  - **Super Columns Families** have a name and an arbitrary number of columns associated with them

![inline](https://www.researchgate.net/publication/274174394/figure/fig6/AS:668443506384898@1536380759059/cassandra-data-model.ppm)

###  Keyspace


- Key space is typically one per application
- Keys are similar to those of databases
- Some settings are configurable only per keyspace
- Each Row must have a key

![right fit](./attachments/cassandra-32.png)

### Columns Families

A Column consists of three parts

[.column]
- name
  - byte\[\]
  - determines sort order
  - used in queries
  - indexed
  
[.column]
- value
  - byte\[\]
  - you don’t query on column values
 
[.column]
- timestamp
  - long (clock)
  - last write wins conflict resolution

### Super Column Families

- Super columns group columns under a common name
- sub-column names in a Super Column Family are **not** indexed
  -  top level columns (Super Column Family Name) are **always** indexed
- often used for **denormalizing** data from standard Column Families

![right fit](./attachments/supercolumn.png)

---
#### Example

 ![inline](./attachments/cassandra-37.png)
 
---
#### Example (Json Notation)
 

```json

 PointOfInterest { //Supercolumn Family
  key:85255 { 
     Phoenixzoo { phone: 480-555-5555, //column
          desc: They have animals here //column },
     Spring Training { 
          phone: 623-333-3333,  //column
            desc: Fun for baseball fans. //column
    }
  } //end phoenix,
  
  key: 10019 { 
     Central Park   //super column
        { desc: Walk around. It's pretty. // missing phone column } ,
      Empire State Building { phone: 212-777-7777, 
             desc: Great view from 102nd floor. }
    } //end nyc
}
 ```

<!-- ### Slice Predicate

A data structure describing columns to return
- start  column name
- finish column name (can be empty to stop on count)
- reverse
- count (like LIMIT) -->
  
# Architecture

Cassandra is required to be incrementally scalable. 

Therefore machines can join and leave a cluster (or they may crash).

Data have to be **partitioned** and **distributed** among the nodes of a cluster in a fashion that allows *repartitioning* and *redistribution*.

## Partitioning

- Data of a Cassandra table get partitioned and distributed among the nodes by a consistent **order-preserving** hashing function. 
- The order preservation property of the hash function is important to support **range scans** over the data of a table.
- Cassandra performs a **deterministic** load balancing
  - it measures and analyzes the load information of servers and moves nodes on the consistent hash ring to get the data and processing load balanced. 

## Replication

^ Cassandra is configured such that each row is replicated across multiple data centers. In essence, the preference list of a key is constructed such that the storage nodes are spread across multiple data centers

- Data get replicated to a number of nodes which can be defined as a **replication factor** per Cassandra instance. 
- Replication is managed by a **coordinator node** for the particular **key** being modified. 
- The coordinator node for any key is the **first node on the consistent hash ring** that is visited when walking from the key’s position on the ring in **clockwise** direction.

### ~~Replication Strategies~~ (Used to be)

  - **Rack Unaware**: the non-coordinator replicas are chosen by picking N-1 successors of the coordinator on the ring
  - **Rack Aware** and **Datacenter Aware** rely on Zookeeper for leader election.
    - the elected leader is in charge of maintaining the invariant that no node is responsible for more than N-1 ranges in the ring

### Replica placement strategies Today [^81]

[^81]: [docs](https://cassandra.apache.org/doc/latest/architecture/dynamo.html)

---
#### Simple Strategy
- Allows a single integer *replication_factor* to be defined
- Single datacenter
- Clockwise placement to the next node(s)
- all nodes are treaded equally, ignoring any configured data centers or racks.

![right fit](./attachments/cassandra-30.png)

---
##### Network Topology Strategy
- Multiple datacenters
- Allows a single integer *replication_factor* to be defined per data center
- Attempts to choose replicas within a data center from different racks as specified by the [Snitch](https://cassandra.apache.org/doc/latest/operating/snitch.html#)[^82]
- Supports local (reads) queries
 
![right fit](./attachments/cassandra-31.png)

[^82]: Snitch teaches Cassandra about your network topology to route requests efficiently.

^ NetworkTopologyStrategy should be preferred over SimpleStrategy to make it easier to add new physical or virtual datacenters to the cluster later.

### Partitioner Smack-Down

[.column] 

#### Random Preserving

- system will use MD5(key) to distribute data across nodes
- even distribution of keys from one Column Family across ranges/nodes
 
[.column] 

#### Order Preserving

- key distribution determined by token
- lexicographical ordering
- required for range queries
- can specify the token for this node to use

### Persistence


Cassandra provides **durability guarantees** in the presence of node failures and network partitions by relaxing the quorum requirements

The Cassandra system relies on the **local file system** for data persistence. 

The data is **represented** on disk using a format that lends itself to **efficient** data **retrieval**. 

Typical write  operation involves a write into a **commit** log for durability
and recoverability and an update into an in-memory data structure.

![right fit](https://2.bp.blogspot.com/-MLpiirj6BW8/UzFD57JiPLI/AAAAAAAALBM/CmFOmzYNTyM/s1600/persist.png)

# Operations

![original fit](https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/Basic_arithmetic_operators.svg/1200px-Basic_arithmetic_operators.svg.png)

## Writes

- Need to be lock-free and fast (no reads or disk seeks)
- AClient sends write to one front-end node in Cassandra cluster (Coordinator)
- Coordinator sends it to all replica nodes responsible for that key
- A write is atomic at the partition-level, meaning inserting columns in a row is treated as one write operation.

### Hinted Handoff

If any replica is down, the coordinator writes to all other replicas, and keeps the write until down replica comes back up.

When all replicas are down, the Coordinator (front end) buffers writes (for up to an hour).

### Writing Flow

1.  Cassandra logs it in disk commit log (disk)
2. Adds *values* to appropriate *memtables* [^83]
3. When memtable is full or old, flush to disk using a Sorted String Table

![right fit](https://static.packt-cdn.com/products/9781789131499/graphics/d8cba1d6-07f7-404e-a4e3-d73233474f3e.png)

[source](https://subscription.packtpub.com/book/big_data_and_business_intelligence/9781789131499/2/ch02lvl1sec20/cassandra-s-write-path)

[^83]:  In-memory representation of multiple key-value pairs

### Consistency levels for a write operations[^87]
  - ANY: any node (may not be replica)
  - ONE: at least one replica
  - QUORUM: quorum across all replicas in all datacenters
  - LOCAL-QUORUM: in coordinator’s datacenter
  - EACH-QUORUM: quorum in every datacenter
  - ALL: all replicas all datacenters

[^87]: [detailed discussion](https://medium.com/@foundev/cassandra-how-many-nodes-are-talked-to-with-quorum-also-should-i-use-it-98074e75d7d5)

### Write Consistency

| Level         | Description                                                                                                                                                                                                                                                                                                        | Usage                                                                                                                                                                                                                                                      |
|---------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| ALL           | A write must be written to the commit log and memtable on all replica nodes in the cluster for that partition.                                                                                                                                                                                                     | Provides the highest consistency and the lowest availability of any other level.                                                                                                                                                                           |
| QUORUM        | A write must be written to the commit log and memtable on a quorum of replica nodes across all datacenters.                                                                                                                                                                                                        | Used in either single or multiple datacenter clusters to maintain strong consistency across the cluster. Use if you can tolerate some level of failure.                                                                                                    |
| ONE/TWO/THREE | A write must be written to the commit log and memtable of at least one/two/three replica node.                                                                                                                                                                                                                     | Satisfies the needs of most users because consistency requirements are not stringent.                                                                                                                                                                      |
| ANY           | A write must be written to at least one node. If all replica nodes for the given partition key are down, the write can still succeed after a hinted handoff has been written. If all replica nodes are down at write time, an ANY write is not readable until the replica nodes for that partition have recovered. | Provides low latency and a guarantee that a write never fails. Delivers the lowest consistency and highest availability.                                                                                                                                   |
|...|...|...|

## Reads

- Coordinator can contact closest replica (e.g., in same rack)
- Coordinator also fetches from multiple replicas
    - check consistency in the background, 
    - Makes read slower than writes (but still fast)
    - initiating a **read-repair** if any two values are different using gossip

### Reading Flow

1. Check row cache, if enabled
2. Checks partition key cache, if enabled 
3. Check the memtable
4. Fetches the data from the SSTable on disk
5. If Row cache is enabled the data is added to the row cache

![right fit](https://static.packt-cdn.com/products/9781789131499/graphics/04b08f1b-a6ba-4711-b3f2-5b7532176bcc.jpeg)

[source](https://subscription.packtpub.com/book/big_data_and_business_intelligence/9781789131499/2/ch02lvl1sec20/cassandra-s-write-path)
 
### Read Consistency: Read count

| Level          | Description                                                                                                                                                               | Usage                                                                                                                                                                                                          |
|----------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| ALL            | Returns the record after all replicas have responded. The read operation will fail if a replica does not respond.                                                         | Provides the highest consistency of all levels and the lowest availability of all levels.                                                                                                                      |
| QUORUM         | Returns the record after a quorum of replicas from all datacenters has responded.                                                                                         | Used in either single or multiple datacenter clusters to maintain strong consistency across the cluster. Ensures strong consistency if you can tolerate some level of failure.                                 |
| ONE/TWO/Threee | Returns a response from the closest (1/2/3) replica, as determined by the snitch. By default, a read repair runs in the background to make the other replicas consistent. | Provides the highest availability of all the levels if you can tolerate a comparatively high probability of stale data being read. The replicas contacted for reads may not always have the most recent write. |       |

We are discussing the number of replicas that are contacted when a data object is accessed through a read operation

### Read-Repair[^84]

Read-repair is a **lazy** mechanism in Cassandra that ensures that the data you request from the database is accurate and consistent.

For every read request, the coordinator node requests to all the node having the data requested by the client. All nodes return the data which client requested for.

The most recent data is sent to the client and asynchronously, the coordinator identifies any replicas that return obsolete data and issues a read-repair request to each of these replicas to update their data based on the latest data.

[^84]: [source](https://blog.knoldus.com/the-curious-case-of-cassandra-reads)

### Consistency Level: Quorum

[.column]
- N **is replication factor**: the number of copies of each data item
- R **is read replica count**: the number of replicas that are contacted when a data object is accessed through a read operation
- W **is write replica count**: the number of replicas that need to acknowledge the receipt of the update before the update completes

[.column]
- Quorum Q = N/2 + 1
- If W+R > N and W > N/2, you have *strong* consistency
- Allowed:

|W |R   | Comment
|---|---|---------|
|1   |N  | In a write-intensive application, setting W=1 and R=RF can affect durability, as failures can result in conflicting writes|
|N |1 |In read-intensive applications, setting W=RF and R=1 can affect the probability of the write succeeding.|
|Q|Q| balanced mix of reads and writes|

### Consistency Level: Explained

[.column]
#### Reads
  - Wait for R replicas (R specified by clients)
  - In background check for consistency of remaining N-R replicas

[.column]
#### Writes 
  - **Block** until quorum is reached
  - **Async**: Write to any node

## Deletes

- Delete: don’t delete item right away
  - add a tombstone to the log
  - Compaction will remove tombstone and delete item

# Digression Time

![original](https://media1.tenor.com/images/b190022cc53957ac269ac226a922d745/tenor.gif?itemid=16077304)

## The Data Structure That Power Your Database[^85]

[^85]: Chapter 3 - Designing Data Intensive Applications

### [[Log]]

- A log is an append-only sequence of records. It doesn’t have to be human-readable; 
- Log-structured storage segments are typically a sequence of key-value pairs.
- These pairs appear in the order that they were written, and values later in the log take precedence over values for the same key earlier in the log. 

![right fit](./attachments/commitlog.png)

^ Questions:
- What is the cost of lookup O(n)
- What is the cost of write O(1)
- What is the cost of read from the head O(1).

### [[Sorted String Table]] (SSTable)

Make a simple change to logs: sequence of key-value pairs is sorted by key.

Merging segments is simple and efficient, even if the files are bigger than the available memory (mergesort algorithm).

In order to find a particular key in the file, you no longer just need a spare index of the offsets

### [[Bloom Filter]]

- Compact way of representing a set of items
- Checking for existence in set is cheap
- Some probability of false positives and item not in set may check true as being in set
- Never false negatives

![right fit](./attachments/bloomfilter.png)

### </Digression Time>

## Cluster Membership

- Any server in cluster could be the coordinator
- So every server needs to maintain a list of all the
other servers that are currently in the server
- List needs to be updated automatically as servers join, leave, and fail 

## Gossip Protocol

- Each node picks its discussants (up to 3)

- Having three messages for each round of gossip adds a degree of *anti-entropy* .

- This process allows obtaining "**convergence**" of data shared between the two interacting nodes much faster.

- Always  a constant amount of network traffic (except for gossip storms)


![right fit](./attachments/cassandra-29.png)

### Gossip Protocol in practice
![inline](./attachments/casssandra-38.png)

- regulates cluster membership
- Nodes periodically gossip their membership list
- On receipt, the local membership list is updated


### Cluster Membership, contd.

- Suspicion mechanisms
- Accrual detector: FD outputs a value (PHI)  representing suspicion
- Apps set an appropriate threshold
- PHI = 5 => 10-15 sec detection time
- PHI calculation for a member
  - Inter-arrival times for gossip messages
  - PHI(t) = - log(CDF or Probability(tnow – tlast))/log 10
  - PHI basically determines the detection timeout, but is sensitive to actual inter-arrival time variations for gossiped heartbeats

## Queries

Values in Cassandra are addressed by the triple (row-key, column-key, timestamp) with column- key as 

- column-family:column (for simple columns contained in the column family) 
- column-family: supercolumn:column (for columns subsumed under a supercolumn).


### what about… CQL?

SELECT WHERE
ORDER BY
JOIN ON
GROUP

### SELECT WHERE

Column Family: USER
Key: UserID
Colunms: username, email, birth date, city, state

How to support this query?


```sql
SELECT * FROM User WHERE city = ‘Scottsdale’
```

Create a new columns family called **UserCity**:

Column Family: USERCITY
Key: city
Colunms: IDs of the users in that city.

Also uses the Valueless Column pattern

### SELECT WHERE pt 2

- Use an aggregate key
  - **state:city: { user1, user2}**

Get rows between **AZ:** & **AZ;** for all Arizona users

Get rows between **AZ:Scottsdale** & **AZ:Scottsdale1** for all Scottsdale users

### ORDER BY

[.column]
#### Columns

are sorted according to ```CompareWith```  or ```CompareSubcolumnsWith```

[.column]
#### Rows

- are  *sorted*  by key, regardless of partitioner
- are  *placed*  according to their Partitioner:
  - Random: MD5 of key
  - Order-Preserving: actual key

### References

![left inline](./attachments/cassandra-35.png)

### Extra (10)

Prepare Cassandra Practice

[hints](https://medium.com/@michaeljpr/five-minute-guide-getting-started-with-cassandra-on-docker-4ef69c710d84)
