# Redis 

![inline](https://upload.wikimedia.org/wikipedia/en/thumb/6/6b/Redis_Logo.svg/1200px-Redis_Logo.svg.png)

### Redis History
- Written in ANSI C by [Salvatore Sanfilippo](https://twitter.com/antirez) :it: 
- Works in most POSIX systems like Linux, BSD and OS X.
- **Linux is the recommended** [^65]
- Redis is a single-threaded server, not designed to benefit from multiple CPU cores.
- Several Redis instances can be launched to scale out on several cores.
- All operations are atomic (no two commands can run at the same time).
- It executes most commands in O(1) complexity and with minimal lines of code.

[^65]: No official support for Windows, but Microsoft develops and maintains an open source Win-64 port of Redis*

### What Redis is
 
- An advanced [[Key-Value Store]]s, where keys can contain data structures such as strings, hashes, lists, sets, and sorted sets. 
- It supports a set of atomic operations on these data types.
- Redis is a different evolution path in the key-value databases where values are complex data types that are closely related to fundamental data structures and are exposed to the programmer as such, without additional abstraction layers.
- Redis Can be used as **Database** [^ 61], a **Caching layer** [^62] or a **Message broker** [^63]

[^62]: it is fast

[^61]: it is durable

[^63]: is not only a key-value store

### What Redis is NOT 

- Redis is not a replacement for Relational Databases nor Document Stores.
- It might be used complementary to a SQL relational store, and/or NoSQL document store.
- Even when Redis offers configurable mechanisms for persistency, increased persistency will tend to increase latency and decrease throughput.
- Best used for rapidly changing data with a foreseeable database size (should fit mostly in memory).

### Redis Use Cases

- Caching
- Counting things
- Blocking queues
- Pub/Sub (service bus)
- MVC Output Cache provider
- Backplane for SignalR
- ASP.NET Session State provider[^64]
- Online user data (shopping cart,…Any real-time, cross-platform, cross-application communication

[^64]: ASP.NET session state providers comparison: http://www.slideshare.net/devopsguys/best-performing-aspnet-session-state-providers

### When to consider Redis
- Speed is critical
- More than just key-value pairs
- Dataset can fit in memory
- Dataset is not critical

### Advantages

- Performance
- Availability
- Fault-Tolerance
- Scalability (adaptability)
- Portability

[source](https://redislabs.com/blog/the-proven-redis-performance/)

![right fit](https://redislabs.com/wp-content/uploads/2014/04/redis_proven_performance_2.png)


### Data Model

[.column]
- Key
   - Printable ASCII

![inline fit](https://3.bp.blogspot.com/-HSQTjkw0djk/WcpnMonIX9I/AAAAAAAAAXY/oxbpvKw8mXkKvuGJxBpSVxherKEy37pSACLcBGAs/s1600/ASCII-Table.png)

[.column]
- Value
	- Primitives
		- Strings
	- Containers (of strings)
		- Hashes
		- Lists
		- Sets
		- Sorted Sets

### Redis data types

| Collection|Contains | Read/Write Ability|
|----------|--------|-------------------|
|String|Binary-safe strings (up to 512 MB), Integers or Floating point values, Bitmaps.|Operate on the whole string, parts, increment/decrement the integers and floats, get/set bits by position.|
|Hash|Unordered hash table of keys to string values|Add, fetch, or remove individual ítems by key, fetch the whole hash.
|List|Doubly linked list of strings|Push or pop items from both ends, trim based on offsets, read individual or multiple items, find or remove items by value.|
|Set|Unordered collection of unique strings|Add, fetch, or remove individual items, check membership, intersect, union, difference, fetch random items.|
|Sorted Set|Ordered mapping of string members to floating-point scores, ordered by score|Add, fetch, or remove individual items, fetch items based on score ranges or member value.|
|Geospatial Index|Sorted set implementation using geospatial information as the score|Add, fetch or remove individual items, search by coordinates and radius, calculate distance.|
|HyperLogLog|Probabilistic data structure to count unique things using 12Kb of memory|Add individual or multiple items, get the cardinality.

### Redis Commands - Strings [Quiz]

|Command|Abstract Syntax|Complexity|
|----|---|----|
|Get/Set strings|SET [key value] GET [key] | ?
|Increment numbers|INCRBY [key increment] |?
|Get Multiple Keys at once|MGET [Key key ...] | ?
|Set Multiple Keys at once|MSET [Key key ...] | ?
|Get String Length|STRNEL [key]| ?
|Update Value and Get old one| GETSET|?

### Redis Commands - Strings [Answers]

|Command|Abstract Syntax|Complexity|
|----|---|----|
|Get/Set strings|SET [key value] GET [key] | O(1)
|Increment numbers|INCRBY [key increment] | O(1)
|Get Multiple Keys at once|MGET [Key key ...] | O(n) n=#Keys
|Set Multiple Keys at once|MSET [Key key ...] | O(n) n=#Keys
|Get String Length|STRNEL [key]| O(1)|
|Update Value and Get old one| GETSET [Key Value]|O(1)|


### Redis Commands - Keys  [Quiz]

|Command|Abstract Syntax|Complexity|
|----|---|----|
|Key Removal|DEL [key ...] | ?
|Text Existance|EXISTS [key...] | ?
|Get the type of a key| TYPE [Key] | ?
|Rename a key| RENAME [Key NewKey] | ?

### Redis Commands - Keys  [Answers]

|Command|Abstract Syntax|Complexity|
|----|---|----|
|Key Removal|DEL [key ...] | O(1)
|Text Existance|EXISTS [key...] | O(1)
|Get the type of a key| TYPE [Key] | O(1)
|Rename a key| RENAME [Key NewKey] | O(1)

### Redis Commands   [Answers]

[.column]

#### Lists

|Command|Abstract Syntax|Complexity|
|----|-------------------|----|
|Push on either end|RPUSH/LPUSH [key value] | ?
|Pop from either end|RPOP/LPOP [key] | ?
|Blocking Pop| BRPOP/BLPOP [key value] |?
|Pop and Push to other list| RPOPLPUSH [src dst] |?
|Get an element by index|LINDEX [key index] |?
|Get a range of elements|LRANGE [key start stop] | ?

[.column]

#### Hashes
|Command|Abstract Syntax|Complexity|
|----|---|----|
|Set a hashed value|HSET [key field value]| ?
|Set multiple fields| HMSET [key field value ...]| ?
|Get a hashed value| HGET [key vield]|?
|Get all the values in a hash|HGETALL [key] | ?
|Increment a hashed value| HINCRBY [key field incr] |?


### Redis Commands   [Answers]

[.column]

#### Lists

|Command|Abstract Syntax|Complexity|
|----|---|----|
|Push on either end|RPUSH/LPUSH [key value] | O(1)
|Pop from either end|RPOP/LPOP [key] | O(1)
|Blocking Pop| BRPOP/BLPOP [key value] | O(1)
|Pop and Push to other list| RPOPLPUSH [src dst] | O(1)
|Get an element by index|LINDEX [key index] | **O(n)**
|Get a range of elements|LRANGE [key start stop] | **O(n)**

[.column]

#### Hashes
|Command|Abstract Syntax|Complexity|
|----|---|----|
|Set a hashed value|HSET [key field value]| O(1)
|Set multiple fields| HMSET [key field value ...]| O(1)
|Get a hashed value| HGET [key vield]|O(1)
|Get all the values in a hash|HGETALL [key] | O(N) : N=size of hash
|Increment a hashed value| HINCRBY [key field incr] | O(1)

### Redis Commands   [Quiz]

[.column]

#### Sets

|Command|Abstract Syntax|Complexity|
|----|---|----|
|Add member to a set| SADD [key member] | ?
|Pop random element| SPOP [key] | ?
|Get all elements| SMEMEBRS [Key]| ?
|Union multiple sets| SUNION [Key Key ...]|?
|Diff multiple sets| DIFF [Key key ...]| ?

[.column]

#### Sorted Sets

|Command|Abstract Syntax|Complexity|
|----|---|----|
|Add member to a sorted set| ZADD [key member] | ?
|Get rank member| ZRANK [key member] | ?
|Get elements by score range| ZRANGEBYSCORE [key min max] [Key] | ?
|Increment score of member| ZINCRBY [Key incr member]|  ?
|remover range by score| ZREMRANGEBYSCORE [key min max]| ?

### Redis Commands   [Answers]

[.column]

#### Sets

|Command|Abstract Syntax|Complexity|
|----|---|----|
|Add member to a set| SADD [key member] | O(1)
|Pop random element| SPOP [key] | O(1)
|Get all elements| SMEMEBRS [Key]|O(n) : n=size of set
|Union multiple sets| SUNION [Key Key ... ]| O(n)
|Diff multiple sets| DIFF [Key key ...]|O(n)

[.column]

#### Sorted Sets

|Command|Abstract Syntax|Complexity|
|----|---|----|
|Add member to a sorted set| ZADD [key member] | O(log(n))
|Get rank member| ZRANK [key member] | O(log(n))
|Get elements by score range| ZRANGEBYSCORE [key min max] [Key] | O(log(n))
|Increment score of member| ZINCRBY [Key incr member]|  O(log(n))
|remover range by score| ZREMRANGEBYSCORE [key min max]| O(log(n))

### Scaling Redis

- **Replication**
	- A Redis instance, known as the **master** , ensures that one or more instances kwown as the  **slaves** become exact copies of the master
	- Clients can connect *to the master or to the slaves*
	- Slaves are* read-only* by default
- **Partitioning**
	- Breaking up data and distributing it across different hosts in a cluster.
	- Can be implemented in different layers:
		- Client:   Partitioning on client-side code
		- Proxy:   An extra layer that proxies all redis queries and performs partitioning (i.e. Twemproxy )
		- Query Router:   instances will make sure to forward the query to the right node. (i.e  Redis Cluster  )

### Scaling Redis

- **Persistence**
	- Redis provides two mechanisms to deal with persistence: Redis database snapshots (RDB) and append-only files (AOF)
- **Failover** 
	- Manual
	- Automatic with Redis Sentinel (for master-slave topology)
	- Automatic with Redis Cluster (for cluster topology)

## Redis topologies


![right fit](https://blog.octo.com/wp-content/uploads/2017/08/screen-shot-2017-08-11-at-14-34-30.png)

- Standalone

- Sentinel (automatic failover)

- Twemproxy (distribute data)

- Cluster (automatic failover and distribute data)


### Redis topologies - Standalone

The master data is optionally replicated to slaves.

The slaves provides data redundancy, reads offloading and save-to-disk offloading.

Clients can connect to the Master for read/write operations or to the Slaves for read operations.

Slaves can also replicate to its own slaves.

There is no automatic failover.

Master-slave multi-level

![right fit](https://blog.octo.com/wp-content/uploads/2017/08/screen-shot-2017-08-11-at-14-35-11.png)

### Redis topologies - Sentinel

Redis Sentinel provides a reliable   automatic failover   in a master/slave topology, automatically promoting a slave to master if the existing master fails.

Sentinel does not distribute data across nodes.

Master-slave with Sentinel	

![right fit](https://blog.octo.com/wp-content/uploads/2017/08/screen-shot-2017-08-11-at-14-34-42.png)

<!--
### Redis topologies III - Twemproxy

 Twemproxy* works as a proxy between the clients and many Redis instances.

 Is able to   automatically distribute data   among different standalone Redis instances.

 Supports consistent hashing with different strategies and hashing functions.

 Multi-key commands and transactions are not supported.

Twemproxy (single)  Twemproxy (load balanced)

*Twemproxy is a project from Twitter and is not part of redis:   _https://github.com/twitter/twemproxy_ 

**Illustrations from Redis Essentials book by Maxwell Dayvson Da Silva and Hugo Lopes Tavares
-->

### Redis topologies - Cluster

 Redis Cluster distributes data across different Redis instances and   perform automatic failover if any problem happens to any master instance.

All nodes are directly connected with a service channel.

The keyspace is divided into hash slots. Different nodes will hold a subset of hash slots.

Multi-key commands are only allowed for keys in the same hash slot.

![right fit](https://blog.octo.com/wp-content/uploads/2017/08/screen-shot-2017-08-11-at-14-34-48.png)


