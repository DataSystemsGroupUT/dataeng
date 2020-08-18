The paper describes an approach ontology mediated query answering on spatial and streaming data. It is a follow up of the authors’ previous work [16,17,18].
The authors formulate a set of challenges that relate to an real use-case and elicit a requirement analysis then they use to evaluate their approach in the end.
Although the approach is ontology-agnostic, this work includes  a detailed description of he ontology used for the mediation, which is based on LSD idea.
Finally, the authors summarise the problem of Spatial-Stream QA that was deeply investigated in [18] and provide a description of the system architecture
and the current stage of development.

Although the work seems a reasonable but small development of the previous ones I do not have strong argument to reject it.
It is very well written and I appreciated the previous works.
Therefore, I believe that this one could also start interesting discussions.

The paper approaches the problem from a more technical perspective wrt [16,17,18], 
which  is reasonable since the theoretical foundations were previously discussed.

Among the contributions, I think that the requirement analysis is very interesting. It generalise the engineering effort.
In this direction I encourage the authors to expand it in the camera ready, maybe considering complex event processing.

I do not have a strong comment on the ontology, since it is clear it comes from a real use-case, although would be interesting to evaluate this part itself.

Finally, I have some clarification/suggestion that I would like to ask:

1) the authors mentions that their approach for query answering is pull-based. This is common in OBDA (and its recent applications to stream reasoning)
but less common for traditional stream reasoning application. Can the authors discuss if a switch to a push-based/reactive processing paradigm would be 
feasible and or feasible?

2) In the recent survey on Stream Reasoning [36], it was presented the stream reasoning stack as a reference for describing different possible entailments levels.
Since the approach and the presented architecture are quite complex and several “local” choice were made, it would be very interesting to position the different tasks
accordingly to the stream reasoning stack.

3) I already mentioned that I would like to know if complex event processing is also a interesting scenario and how it would impact the requirement analysis.

4) Since the paper is focus on the technical aspects, and in [18] authors said that their query language could be lifted to SPARQL, I personally think
that a SPARQL-like syntax would make the approach more user friendly and might encourage the adoption.

5) One of my biggest doubt regards one of the future works. The authors claim that the integration of Allen’s interval algebra is possible, but I do not get how alternative
time representation could co-exists with a clear semantics.

6) On of the aspect that remains obscure is the query rewriting. The implemented systems used pipelineDB which seems to have a sort of SQL-like declarative language
to define continuous queries (views) on top of Postgres. Are the authors rewriting query into this QL? I would add a full example (even if the system is not fully implemented).
This is quite interesting also in relation to 1), since it seems to me that pipelineDB does not push results but similar systems could be used in its behalf. 

My last remark regards the evaluation. Since in [18] the authors provided a initial evaluation, I have the expectation of at least a replication of that one that would discuss it
also w.r.t. the presented architecture. The validation using the requirements is fine, but the absence of an empirical study saves me to give a strong acceptance.

[36] Stream reasoning: A survey and outlook

    