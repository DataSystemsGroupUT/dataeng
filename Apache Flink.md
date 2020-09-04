### Systems Overview: [[Apache Flink]] 

![inline](./attachments/Images/Flink.png)

Apache Flink is a distributed platform for streaming data (DataStream
API) and batch data (DataSet API). The dataflow engine, the core of the
platform, guarantees the fault tolerance during the distributed
computations. Apache Flink is based on the parallelization contract or
PACT, i.e., a programming model that generalizes MapReduce.

-  Flink programs are translated into Direct-Acyclic Graphs that
  describe the operations.

-  Such DAGs are further translated into low-level job graphs

-  Job graphs are generic streaming programs

References:

Carbone, Paris, et al. "Apache flink: Stream and batch processing in a
single engine." Bulletin of the IEEE Computer Society Technical
Committee on Data Engineering 36.4 (2015).

Carbone, Paris, et al. "State management in Apache Flink®: consistent
stateful distributed stream processing." Proceedings of the VLDB
Endowment 10.12 (2017): 1718-1729.