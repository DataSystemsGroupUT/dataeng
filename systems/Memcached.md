# Memcached

### About memcached

[[Key-Value Store]]

**Free & open source, high-performance, distributed memory object caching system**

**Generic in nature, intended for use in speeding up dynamic web applications by alleviating database load.**

**key/value dictionary**

**Developed by Brad Fitzpatrick for LiveJournal in 2003**

**Now used by Netlog, Facebook, Flickr, Wikipedia, Twitter, YouTube ...**

### Technically

* **It’s a server**
* **Client access over TCP or UDP**
* **Servers can run in pools**
  * **eg. 3 servers with 64GB mem each give you a single pool of 192GB storage for caching**
  * **Servers are independent, clients manage the pool**

### What to store in memcache?

**high demand**  (used often)

**expensive**  (hard to compute)

**common**  (shared accross users)

**Best: All three**

### What to store in memcache? (cont’d)

* **Typical:**
  * **user sessions**  (often)
  * **user data**  (often, shared)
  * **homepage data**  (eg. often, shared, expensive)

### Memcached principles

**Fast network access**  (memcached servers close to other application servers)

**No persistency**  (if your server goes down, data in memcached is gone)

**No redundancy / fail-over**

**No replication**  **(single item in cache lives on one server only)**

**No authentication**  (not in shared environments)

### Memcached principles (cont’d)

**1 key is maximum 1MB**

**keys are strings of 250 characters**  (in application typically MD5 of user readable string)

**No enumeration of keys**  (thus no list of valid keys in cache at certain moment)

**No active clean-up**  (only clean up when more space needed, LRU: Least Recently Used )

**PHP Client functions**

**Memcached::decrement**  — Decrement numeric item's value

**Memcached::delete**  — Delete an item

**Memcached::flush**  — Invalidate all items in the cache

**Memcached::get**  — Retrieve an item

**Memcached::getMulti**  — Retrieve multiple items

**Memcached::getStats**  — Get server pool statistics

**Memcached::add**  — Add an item under a new key

**Memcached::addServer**  — Add a server to the server pool

**Memcached::increment**  — Increment numeric item's value

**Memcached::set**  — Store an item

...

**=**  'mypage'  **);**

$html  **)**

**{**

**ob*start();**

"<html>"  **;**

**}**

//	all the fancy

"</html>"  **;**

$html  **=**

**ob*get*contents();**

'mypage'  **,**

### Data caching

* **on a lower level**
* **easier to find all dependencies**
* **ideal solution for offloading database queries**
  * **the database is almost always the biggest bottleneck in backend performance problems**

**getUserData(**  $UID  **)**

function

**{**

$key  **=**

**=**  $key  **);**

$userData  **)**

**{**

**= Database::query(**  "SELECT

$UID  **);**

**=**  $queryResult  **->getRow();**

$userData  **);**

**}**

$userData  **;**

**}**

?>

 **“There are only two hard things in Computer Science: cache invalidation and naming things.”** 

 **Phil Karlton** 

### Invalidation

* **Caching for a certain amount of time**
  * **eg. 10	minutes**
  * **don’t delete caches**
  * **thus: You can’t trust that data coming from cache is correct**

### Invalidation (cont’d)

* Use: Great for summaries
  * Overview
  * Pages where it’s not that big a problem if data is a little bit out of date (eg. search results)
* Good for quick and dirty optimizations

* **Store forever, and expire on certain events**
  * **the userdata example**
    * **store userdata for ever**
    * **when user changes any of his preferences, throw cache away**

"SELECT *

FROM USERS

WHERE uid =

'UID'  **,**

$invalidateCache  **)**

**{**

$db  **->invalidateCache();**

**}**

$db  **->getRow();**

**}**

?>

function

**{**

**updateUserData(**  $UID  **,**

$db  **= DB::getInstance();**

"UPDATE USERS

...

WHERE uid = {UID}"  **);**

...

**getUserData(**  $UID  **,**

### Multi-Get Optimisations

* **We reduced database access**
* **Memcached is faster, but access to memcache still has it’s price**
* **Solution: multiget**
  * **fetch multiple keys from memcached in one single call**
  * **result is array of items**

### More tips ...

**Be carefull when security matters.**  (Remember ‘no authentication’?)

Working on authentication for memcached via SASL Auth Protocol

**Caching is not an excuse not to do database tuning.**  (Remember cold cache?)

**Make sure to write unit tests for your caching classes and places where you use it.**  (Debugging problems related to out-of-date cache data is hard and boring. Very boring.)
