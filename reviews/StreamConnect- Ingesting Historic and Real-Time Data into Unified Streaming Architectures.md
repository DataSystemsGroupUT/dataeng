The paper describes StreamConnect, a framework that allows to semantically describe streaming and static source and uses these descriptions to automatically generate the adapter required for the source integration. StreamConnect contribution comprise an extension of StreamPipe[10] vocabulary that contains models of raw physical stream, the provisioning protocols and Raw or Event Data from the sources.

The writing and structure are clear enough but some improvements are possible. In particular, 
I notice redundant paragraph that, if reduced, will leave more space to explain concepts were
currently not deeply described.

The appreciated the idea, but I am not convinced to accept the paper since I found 
some major drawbacks. My biggest concerns are:

**Generality of the approach**
 
The challenges presented in the introduction are not well named.

Variety -> Temporal Aspects?

Adapter Configuration & Semantic Metadata seems the same to me.

I also disagree with this statement “data is often published without any further semantic information”
We lack of vocabulary to describe streaming data, but not to describe streaming data sources, e.g. SSN, SIOC…

(Edge) Pre-Processing -> Not sure what edge mean here, maybe it should be explained before.

What is missing here is also a requirement analysis, which will definitely help the validation afterwards.
On these challenges, the authors could elicit some requirements and check if StreamConnect satisfy them.

StreamConnect seems tightly coupled with StreamPipes rather than a generic approach that can be used for
source discovery and adaption, which is a relevant problem for StreamReasoning IMO.
StreamPipes claims to be extendible, but I don’t think this is enough.
I didn’t understand if StreamConnect can be used with existing StreamReasoners for instance of if it could
be integrated with the recent TripleWave, whose focus is indeed stream provisioning.


**Incomplete State of the art** 
 
StreamPipe is a major base of StreamConnect and, thus, the paper should describe what StreamPipe is and clearly highlight the contribution with regard to it. I already expressed my concern about the generality, clarify the background work might solve this kind of problems.
For instance, the presented vocabulary were, quoting form the paper, “inspired by [10]”. 
It is fine build on what was done for StreamPipe but this statement should be expanded and figures 1,2 and 3 should esplicitely say what is new and what is not.  A not on the references: StreamPipe is cited as a PhD thesis, as well as [14]. When the reference is big, i.e. books or theses is a good practice to indicate the section of interest. Such a pointer to specific
content would simplify the information retrieval.

I think authors should survey the works done on Streaming Linked Data and Semantic Web Service.
The process of automatic adapting is quite related.


** Lacks of detailed technical explanations**

Sections 4 and 5 are those that should contain the technical details about StreamConnect.
However, they lack of specific details and focus too much on the general idea of the approach.

The example presented in Section 2 is not effectively used. The authors in many passages refer
to an inference process that exploits the event definition provided by the domain experts to 
adapt the source.  However, Figures 7 and 8 fail to present the conversion passage.
They only show input and output of a very simple example that makes impossible to understand
what is the process that allows to move from one representation to another?

How are the transformation rules derived?

Another doubt I have is, why didn’t the authors present the data in RDF? Where is the model used?

More example, hopefully related to the use-case of section 2, are required for all the agent listed in Section 5.2
in order to make the process less abstract.
 
 
 **Validation**
 
 I am sorry to say, but the presented validation is not very useful. It sounds like a promise of an evaluation.
 I guess authors wanted to show that they have clue on how to evaluate StreamConnect. If this was the goal,
 I suggest to move this as future work in the conclusions.
 
 As a workshop paper, I do not expect a complete evaluation though.
 

 **Out of Scope Statements**
 
 I found several sentences in the paper whose presence was not justified to me.
 
 At the end of page 7,
 “The key label can later be used by the processing engines to parallelize the execution for scalability reasons.”
 This was not part of the discussion and is quite distracting.
 
 In the conclusion, the authors start a new discussion about using ML to improve the approach. 
 This future works sound intriguing, but too far from the stage of development.
 
 
 —— Minor Details — 
 
 It is not clear to me the difference between Dataset and Iterative Dataset.
 
 Figure 1 2 and 3 could be collapsed into a single one.
 
 Several references are used only once without a clear explanation behind, e.g. [2]
 

