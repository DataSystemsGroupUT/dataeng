
### Systems Overview: [[Apache Spark]] 

![[attachments/Images/sparklogo.png]]

Spark's extensions for stream processing include

-  Spark Streaming is an extension of the core Spark API that enables
  scalable, high-throughput, fault-tolerant and real-time processing
  of data. Its key abstraction behind is the Discretized Stream, a
  potentially infinite flow of small batches.

-  Spark Structured Streaming is a new declarative streaming API
  available starting from Apache Spark 2.0 to support continuous
  applications. It is a higher-level API than the one offered by Spark
  Streaming and it is integrated into Dataset and DataFrame API.

References:

Zaharia, Matei, et al. "Discretized streams: Fault-tolerant streaming
computation at scale." Proceedings of the twenty-fourth ACM symposium
on operating systems principles. 2013.

Armbrust, Michael, et al. "Structured streaming: A declarative api for
real-time applications in apache spark." Proceedings of the 2018
International Conference on Management of Data. 2018.