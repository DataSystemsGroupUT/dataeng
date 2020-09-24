# Key-Value Stores

### Why Key-value Store?

(Business) Key -> Value

(twitter.com) tweet id -> information about tweet

(kayak.com) Flight number -> information about flight

(yourbank.com) Account number -> information about it

(amazon.com) item number -> information about it

Search by ID is usually built on top of a key-value store

### Isn’t that just a database?

- Queried using SQL
- Key-based
- Foreign keys
- Indexes 
- Joins

```sql 
SELECT user_id from users WHERE 
	username = “jbellis”
```

![right fit](./attachments/reldbex.png)

### Systems

- Project Voldemort 
- [[Redis]]
- Memcached 
- ETCD
- Amazon's Dynamo
- RocksDB

