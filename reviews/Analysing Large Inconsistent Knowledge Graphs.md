# paper summary

The paper presents an approach for identifying inconsistencies in RDF knowledge graphs based on justification. The approach aims at addressing three major issues of justification-based approaches:
- poor scalability
- high frequency (redundancy)
- domain dependency

# overall comment

The problem of identifying/explaining inconsistencies in RDF knowledge graph is certainly relevant and appropriate for the ESWC conference.
Nevertheless, the presented approach is not convincing in soundness and novelty.

I fail to understand how the approach differs from standard justification algorithms, especially because the proposed implementation reuses OpenPellet which does exactly that.

Moreover, the evaluation presents some major problems that save me to agree with the presented discussion.

In the following, I will comment (almost) section-by-section discussing the presented problems in more details.

# Introduction

The authors present three research questions that are all very interesting. Nevertheless, this is too ambitious for a single research paper. I appreciate that the presented work is part of a long term research plan, but the current introduction raises the reader expectation to a level that is hard to satisfy in 16 pages.

Especially Q3 is only formulated and not investigated in the manuscript.

# Related work and background

The related work section has a weird organization with the italics paragraph titles. I do not think this is necessary.

The background section instead should use a more formal language and improve the structure. For instance, I suggest the authors to highlight and number definitions.

Also related to definitions, the concept of "justification" is defined five times in slightly different ways. These definition do not hurt the message, but may confuse an reader with less experience on the topic.

    - Section 1, Paragraph 2, page 2: "A justification for a contradiction (entailment) in the KG is a minimal subset of the ontology that is sufficient for the contradiction (entailment) to hold."
    - Section 1 , paragraph 3, page 2: "justifications serve to explain such contradictions, by showing the minimal set of axioms from the ontology that causes the contradiction to hold."
    - Section 3, paragraph 2, page 4: "A justification is a set of axioms that acts as an explanation for an entailment."
    - Section 3, paragraph 2, page 4: "Formally, a justification is a minimal subset of the original axioms which are sufficient to prove the entailed formula" 
    - Section 4.1, paragraph 1, page 5: "A justification is a description of a single contradiction, which can be represented as an instantiated BGP."


The background part should contextualize the reported notion with appropriate citations and references. In particular, triple patterns, entailments, satisfiability and consistency are mentioned and never defined or referenced.

The following definition of "inconsistent KG" does not comply with Paulheim definition, which does not make use of the notion of "model". Instead, this related to description logics knowledge base. I think that Knowledge Graph are a great use-case for semantic technologies, but we should be careful in substituting the term right away.

"an inconsistent KG is a graph for which no model exists, i.e. a formal interpretation that satisfies all the triples in the graph given the semantics of the used vocabularies."

Moreover, Paulheim's definition of knowledge graph requires to provide a proper definition of "subgraphs" used later in section 4. Indeed, as the KG includes the notion of schema, the authors should clarify if the schema is also a subschema (and this may break some entailments) or not.

# Section 4

First of all, the anti-pattern definition should be highlighted. Moreover, its formalization can be improved.

" An anti-pattern of a KG is a minimal set of non-instantiated triple patterns that match an in- consistent subgraph of this KG. "

This definition presents the following shortcomings

- minimality is not defined, I suppose it means "minimal number of triples/axioms". Although, it is not clear if the authors are operating the context of RDF triples of DL axioms.
- "non-instantiated triple patterns" is not defined. From the text I understood that "instantiated" means that the triple pattern variables have been binded to individuals. Would be easier to use a logic notation then?
Indeed, the relation between anti-pattern and justification is the relation occurring between an RDF graph and a SPARQL (BGP) query, which is similar to the relation between a DL assertional axiom and a DL positive query. Both these relations were properly formalized in the literature, but the paper lacks to report that nor introduces any novel alignment. 
- the transformation from justification to anti-patter is only described. Also the terms "variable" are not defined nor referenced. I understand this might be obvious, but the paper should be self contained
and include the necessary formalizations.

The paper continuous describing the execution steps for the anti-pattern retrieval. The proposed approach reuses existing work on justification (OpenPellet) and then it apply the generalization techniques, i.e., transforming the the justification into a anti-pattern (query).
Then the anti-patterns are compared (isomorphic) and reduced in number.

To speed up the computation of justification, the graph is split in star-shaped subgraph, that are also expanded in the object. It is not clear
if across subgraphs there is some data replication.

The proposed execution steps reminds the map-reduce approach, without the reshuffling of the data. This makes me wonder about the splitting techniques. I suppose that the authors do not partition the data, but split them into subgraph admitting replication.
Especially because, the subgraph without inconsistencies are not considered anymore in the process. 

As the authors state: "Depending on the splitting strategy, this step can impact the number of retrieved justifi- cations, which in its turn can potentially impact the number of the retrieved anti-patterns."

Therefore, the following statement should be proved formally. 

"these heuristics emphasizes the scalability of the approach over guaranteeing its completeness regarding the detection of all anti-patterns." 

The proposed approach succeeds in reducing the user work by proposing less anti-pattern. I wonder if wound not be possible to cluster the justification directly before transforming them into anti-pattern. Indeed, they are also represented as an RDF graph.

# Evaluation and Analysis

The evaluation presents some major issues. First of all, the authors attempt to empirically prove that splitting the graph does not impact on the retrieved justification.

Although their result are positive upon a certain size, this does not generalize the solution. Especially with a specific splitting strategy as the one used. At least would be necessary to divide the graph randomly.

Moreover, I fail to understand how the approach improves the problem of finding justification. What the authors demonstrated is that justification algorithms scale badly, and splitting the graph obviously reduce the timing.

Finally, I totally disagree on the statement in section 6.3.

"It (FIgure 7) shows that the detected anti-patterns in the LOD-a-lot, DBpedia, and YAGO make the billions of available contradictions more interpretable by generalising them into 222, 13, and 135 anti-patterns, respectively."

Such a statement at least requires a user-study to see that the approach is effective and users interpret better anti-patterns than justification.


