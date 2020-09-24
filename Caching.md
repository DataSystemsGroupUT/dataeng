### What is caching?

**From Wikipedia: "A cache is a collection of data duplicating original values stored elsewhere or computed earlier, where the original data is expensive to fetch (owing to longer access time) or to compute, compared to the cost of reading the cache."**

**Term introducted by IBM in the 60’s**

### The anatomy

* **simple key/value storage**
* **simple operations**
  * **save**
  * **get**
  * **delete**

### Terminology

**storage cost**

**retrieval cost**  (network load / algorithm load)

**invalidation**  (keeping data up to date / removing irrelevant data)

**replacement policy**  (FIFO/LFU/LRU/MRU/RANDOM vs. Belady’s algorithm)

**cold cache / warm cache**

* **cache hit and cache miss**
* **typical stats:**
  * **hit ratio (hits / hits + misses)**
  * **miss ratio (1 - hit ratio)**
  * **45 cache hits and 10 cache misses**
* **•**  **45/(45+10) = 82% hit ratio**
    * **18% miss ratio**

### When to cache?

* **caches are only efficient when the benefits of faster access outweigh the overhead of checking and keeping your cache up to date**
  * **more cache hits then cache misses**

### Where are caches used?

**at hardware level**  (cpu, hdd)

**operating systems**  (ram)

**web stack**

**applications**

**your own short term vs long term memory**

### Caches in the web stack

* **Browser cache**
* **DNS cache**
* **Content Delivery Networks (CDN)**
* **Proxy servers**
* **Application level**
  * **full output caching**  (eg. Wordpress WP-Cache

### Efficiency of caching?

**the earlier in the process, the closer to the original request(er), the faster**

**browser cache will be faster than cache on a proxy**

**but probably also the harder to**  **get it right**

**the closer to the requester the more parameters the cache depends on**
