### HBase

HBase is a [[Column Oriented Database]]

* Google’s BigTable was first “blob-based” storage system
* Yahoo! Open-sourced it -> HBase
* Major Apache project today, part of Hadoop
* Facebook uses HBase internally
* API
  * Get/Put(row)
  * Scan(row range, filter) – range queries
  * MultiPut

### Hbase API

* Supported operations
  * Get(row)
  * Put(row)
  * Scan(row range, filter) – range queries
  * MultiPut(rows)

### HBase Architecture

![[attachments/hbase-36.png]]

### HBase Storage hierarchy

* HBase Table
  * Split it into multiple *regions* : replicated across servers
    * One *Store* per ColumnFamily (subset of columns with similar query patterns) per region
      * Memstore for each Store: in-memory updates to Store; flushed to disk when full
        * *StoreFiles* for each store for each region: where the data lives
        * - Blocks
* HFile
  * SSTable from Google’s BigTable

### HFile

![[attachments/hbase-37.png]]

**(For a census table example)**

![[attachments/hbase-38.png]]

### Strong Consistency: HBase Write-Ahead Log

*Write to HLog*  *before*  *writing to MemStore*

*Can recover from failure*


![[attachments/hbase-39.png]]

### Log Replay

* After recovery from failure, or upon bootup (HRegionServer/HMaster)
  * Replay any stale logs (use timestamps to find out where the database is w.r.t. the logs)
  * Replay: add edits to the MemStore
* Why one HLog per HRegionServer rather than per region?
  * Avoids many concurrent writes, which on the local file system may involve many disk seeks

### Cross-data center replication

![[attachments/hbase-40.png]]

*Zookeeper actually a file*

*system for control information*

*1. /hbase/replication/state*

*2. /hbase/replication/peers*

*/<peer cluster number>*

*3. /hbase/replication/rs/<hlog>*