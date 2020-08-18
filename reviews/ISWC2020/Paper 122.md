# Efficient Multi-way Join Processing on Complex Window Definitions over RDF streams

## Summary

The paper presents a technique for window-based multi-way join over RDF Streams. Although the paper presents an interesting idea, i.e., optimizing multi-way join by using specialized windowing techniques, it follows an unusual structure. Moreover, the paper requires some proofreading. It is often hard to follow and the contributions are not easy to distinguish from explanation. The evaluation relies on WatDiv, but not the streaming version, and ignores standard benchmark in the field, e.g., CityBench. The queries used in the evaluation are modified version of WatDiv's ones, but no link is provided to review them, making it hard to assess the producibility of the work..


## Detailed Review

The paper presents a technique for window-based multi-way join over RDF Streams. 
The paper's main contribution are:

- a proposal for a "unified execution semantics for window join that considers various window definitions in one query".
- a strategy for "physical window multi-way join where states are stored in a way that both graph pattern structure and temporal locality are considered".

Although the paper presents an interesting idea, i.e., optimizing multi-way join by using a specialised windowing techniques, it follows an unsusual structure. Moereover, the paper requires an extensive proofread. In the current status, it is hard to follow and some concepts are not clearly explained.
Moreover, the evaluations presents some major issues.
In the following, I will comment section by section.

### Introduction

"RSP-QL provides a flexible and complex window declarations over the same or different streams,..''

- what does make the window definition *flexible* and *complex*?

"...which makes the join processing more complex."

-  more complex than what?

this idea of "complex semantics" appears several time along the paper but is never clarified. 

"Most of the existing RSP engines take the window operators to convert the timestamped data to atemporal snapshots and time annotations are no longer used by the operator logics."

The role of the window operator is to deal with the unboundness of the stream. It is true that *some* window operators remove the temporal annotation, but this is related to the query language semantics and not to the engine operational semantics which remains, in principle, dependent on time.

"Hence, they need to process data in an increasing order of timestamps to achieve semantic correctness."

I do not understand why this sentence logically follow the former one (Hence). After the authors write

"That is, the query processor should never receive a stream element with a lower timestamp than any previously received ones. Since stream sources are distributed in nature and operators can be executed concurrently, synchronizing among distributed streams and operators introduce extra overhead."

All this is true. In practice, none of the existing RSP engine deals with out-of-order arrivals, they are simply discarted or buffered in advance. The overhead is then not observable at the engine endpoints.
Synchronization happens within the window context, and this is why multi-window semantics is needed.


### Preliminaries

This section is supposed to contain the information necessary to understand the content of the paper. The section requires some rewriting. In particular, it is lacking the necessary rigorousness that a paper of this kind require. 

In particular.
- the problem of out-of-order is modelled at the end of section 2.1 but is never considred again.
- no numbered definition is provided. Thus, it is extremely hard to understand when, in the following sections, the authors crossref the background knowledge
- The evaluation semantics of SPARQL BGP does not introduce nor the concept of mapping nor the concept of sulution mappings. Moreover, the title of Section 2.2 says "Continuous RSP Operators", the concept of Continuous Semantics is missing and so it is the RSP-QL extensions for BGP evaluation, which is time-dependent.

#### Section 2.2

"Unlike the schema-free RDF streams, the structure of a stream mapping is predictable since the domain of mapping are explicitly shown in the BGP"

can the authors clarify what they meant this sentence? The "schema" of the mapping are the variables names if we see a mapping as a tabel. 

Let p denotes -> the p denote
p\<S\> denotes the mapping stream M ∈ *M*. *M* is undefined
The two examples are never discussed in this section.
	
The paragraphs that discuss the window definition also lack in rigorousness. In particular, the window operators are defined as in RSP-QL but a reference is missing

"We assume the parameter α and β are measured upon timestamp defined in Section 2.1."

In section 2.1., the authors provide two different time definition from the state-of-the-art. Processing time and event time. The sentence above is ambiguous because it does not explain what notion of time is isued (maybe both, intergchangably).

"Sliding window is context free" -> time based Sliding window operators are context free
WID ->  Window ID (WID)
This paragraph is very hard to read. The message should be rephrasaed with simpler sentences and I suggest to extract the mathmatical definitions from the in-line text.

For a good definition of window assigned (which assigns an element to a window) consider Flink's documentation.

The paragraph on *Past window* lacks a the referecing in the state of the art. I think ti should be SparqlStream \6\.

window function w(..) -> a window function..

the concept of *validity* is undefined. It can be extrapolated from the text but it should be formalised accordingly. 

Figure 1 remains totally unexplained or the supporting text is requires rephrasing. E.g., 

"Comparisons of valid time interval of the same stream item and the current active window at the same timestamp in the context of the two windows are shown."

Finally, the base join operator is never explained in terms of SPARQL semantics. Thus, the paper is not self-contained. New notation, e.g., o for overlap, is introduced without notice.

#### Section 2.3

This section seems to introduce the first contribution of the paper. However, it is still part of Section 2.
Moreover, it contains a long discussion about the query optimisation that (1) does not finds any followup neither in Section 3 nor in the evaluation, (2) hides many assumptions that are not met in RSP-QL. For instance, in RSP-QL the window operator is not part of the algebra, thus BGP push down cannot be done.

"As mentioned in previ- ous sections, window operator and BGP operator are stateless and commutative, which means the order in which the data items are processed is irrelevant"

- BGP are statelees, in RSP, when applied on a single stream item
- commutativity is never discussed and depends on the BGP.

Can the authors elaborate on the difference between Fig 2 and Fig 3. If they indicate different stages of the optimisation plan they should have at least different notation (or a clarifying legend.)


#### Section 2.4

Also this section is central to the paper contribution, why is it still part of the preliminaries?

"A window multi-way join runs a subgraph homomorphism with temporal constraints. Thus, the window join operator checks both the graph structure and temporal constraints."

This intuition should be followed up by a proper formulation of the operators, which is still missing.

"Since the join predicates are known in prior, the structure of the temporal state can be aware of the graph pattern structure and temporal-locality"

" the structure of the temporal state " is undefined
" graph pattern structur" is deducibale but yet undefined
"temporal-locality" is undefined

### Section 3

In general, Section 3 really lacks sufficient rigorousness. it is very hard to assess the soundness of the presented approach. Many terms are just vaguely defined and the examples is mixed with the explanation of the approach.

#### 3.1 Temporal State Management

the problem of out-of-order which seemed to be one of the works motivation is reduced to source-watermarking. In these regards, I suggest to use a different naming as *watermarking* in stream processing indicates the notion of progress metrics which depends on the engine and not on the source.

"How to create watermarks is out of the scope this paper."
I am not sure it is, unless the synchronization problem that characterises multi-stream joins does not concern the kind of joins studied in this work.


### Section 4

Finally, the design of the evaluation is better than the reset of the paper. Nevertheless, there are some shortcoming that suggest it can be improved.

First, the queries should be made available (peharps anonimously) in order to veryfy their validity. Moreover, the authors should justify the choice of a non-RSP benchmark that they modified. What are the limitations of exsting RSP benchmarks like Citybench? Could they be extended instread? WatDiv has also a streaming version, why did they not use that one?

Moreover, the authors admidetly declare theri engine does not have the same execution semantics of CQELS engine. As the work on RSP-QL shows, this makes the results incomparable and, thus, I do not understand the value of such contrast. 



