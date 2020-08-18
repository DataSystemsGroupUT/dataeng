# Research Article, Storing RDF(S) in object-oriented databases.docx
#JWE #review
The paper describes a possible solution to store RDF data into Object-Oriented Database (OODB).
Although the presented idea is valuable and potentially impactful,  the manuscript lacks the fundamental elements that define a sound paper. 
In particular, 
  1) it does not formulate a problem statement or any research questions that frame the scope of the work and motivate it. 
  2) It does not include a proper background section that introduces the prerequisite to the work, i.e., RDF and OODB.
  3) Moreover, it lacks sufficient scientific rigor. The paper seems more an extended technical report rather than a scientific article.

In the following, I will elaborate on these significant flaws in detail.

1) In the introduction, the authors claim that the abundance of RDF data motivates <..scalable management of large-scale RDF(S) data..>. Also, they name some alternatives to relational RDF storage. Although I agree with their claim, the relation it has with OODB remains unclear. I understood the intuition that motivated the work: the OODB data model resembles the graph structure of RDF data. Moreover, RDFS has some similarities with the hierarchical organization that is typical of OO programming. Nevertheless, the authors never made this intuition explicit in the form of a problem statement and/or research question. 

Consequently, the work assumes an extremely practical tone, which quickly diverges into an anecdotical reporting of the research findings. Moreover, the provided evaluation fails to support the claim mentioned above, as it is extremely limited both in method and scale.

2) The introduction briefly discusses the state-of-the-art of RDF data management and OODB. Although i
presents a good part of the relevant literature, the paper does not report any of the necessary definitions and or formal specification of RDF and RDFS nor OODB. Instead, the authors provide their definitions and use them to sustain the approach they propose (i.e., an alignment between RDF(S) and OODB model).

However, the lack of a formal alignment with the literature on RDF and RDFS specifications makes it hard to verify whether the authors' proposal is correct. Currently, it is impossible to understand the consequence of the proposed alignment in terms of reasoning and query answering semantics. 

Is this alignment lossless in terms of expressivity? What is the impact of data complexity?

Finally,  there is no information about how the query language used by their OODB of choice aligns with SPARQL, which the W3C recommendation for RDF querying. However, a shorter version of the paper can be found online, which reports such content.

3) the paper does not present major language issues, but it lacks the rigor required to disseminate scientific contributions in the current stage. I appreciate engineering-driven research works. However, these works should stress the evaluation to the extent of obtaining generalizable results. Instead, the paper has more the aspect of a technical report. In particular,

- Section 2.1 has not formal at all. It just describes RDF and RDFS vocabularies without providing a concrete explanation on their semantics. 
- RDFS is not a data model; it is instead a knowledge representation language that builds on RDF semantics. In practice, RDFS is a vocabulary that can be used in combination with RDF vocabulary to form sentences that relate to the data schema (logical organization) and not just to instances. 
- impedance mismatch is undefined
- URIs identify resources while OID identifies objects. I agree the two entities have some similarities, but *identity* works differently in the two areas.
- Definition 1 and 2 completely ignore the literature on the formal specification of RDF/RDFS.  Their validity is none unless the authors could show that they somehow align to existing formal definitions (which have served as bases to formal proves). 
- The authors say Definition 2 was inspired by the one in [30]. However, there is no formal OODB definition in [30] but just an informal description of the idea.
- The alignment between def 1 and def 2 (fig 6) is purely syntactic (on a syntax defined by the authors). No mathematical alignment is given on the used terms, e.g., how do the RDFS' Class concept and OODB's one align? Moreover, is not clear what an <<aggregation relationship>> is.
- The authors' ruleset is more like a practical description of how the alignment could work. Moreover, they are coupled with the classes that constitute the storage framework. Perhaps these sections were initially reversed, and the authors explained how the meta-schema was identified as a means to fulfill the rules. 
- <<We use the symbol "£" to represent the storage procedure>> - what does a storage procedure do formally?


### Minors
- Figures's captions are very short and sometimes missing. I suggest to expand them to make more self-contained.
	
- Sentence repetition:

<<Object-oriented databases (OODBs) integrate the powerful modeling capabilities of the object-oriented paradigm into database model [14].>>

<<Compared with relational databases, object-oriented databases provide a powerful object-oriented modeling capability and can solve the problem of impedance mismatch of relational databases in complex applications.>>

<<Object-oriented databases have been applied in many complex domains such as temporal application [3] and industrial application [4]. >>

- what is a temporal application, and what industrial application is [4] referring to?
- What is the role of Fig 9?

There seems to be a similarity with [24]. However, the paper cannot be found. Can the authors position their work wrt to it? XML encoding seems a poor difference as we are discussing to aligning data models, while XML is a markup language and a data format. I suspect that if the author's alignment is correct, the storage format of the OODB does not impact the query correctness.