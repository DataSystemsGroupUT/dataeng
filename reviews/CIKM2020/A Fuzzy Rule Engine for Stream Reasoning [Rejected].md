The paper presents a framework for Stream Reasoning based on Fuzzy Logic and Event-Condition Action Rules named FECA. The framework uses a modified version of the Rete Algorithm to efficiently evaluate event detection rules.

Complex Event Processing is definitely an interesting topic both for the knowledge management community. Rule-based solutions find their applicability in data-intensive domains like the internet of things. In particular, the combination with fuzzy logic is particularly appealing due to the growing need to deal with imprecise data.

Although I think the idea is interesting, the paper contribution is limited and I
t presents some major issues that I believe could not be fixed in a short time.
First of all, in its current stage, the paper lacks a background section that makes it self-contained and a proper discussion that comments upon the results presented in the evaluation.

Moreover, figures have many flows (e.g. figures 4 and 6 the texts cover the symbols), several terms are used without being introduced (membership function in  2.1), and sometimes misused( e.g., "Continuous Query" has a quite specific meaning in the Stream Reasoning domain). Last but not least, I must recommend the usage of Listings when presenting code snippets such as those in Section 2.2.


As mentioned before, I suggest the authors convert Section 2 to a background section that introduces the necessary knowledge to understand the content of the paper:
- a short primer on fuzzy logic
- an introduction to event-condition-action rules
- the traditional rate algorithm.

I believe most of the content is already in the paper, just scattered across the sections.

Regarding the paper's major contributions, they are the "Fuzzy Time Constrains" presented in Section 2.1 and the "Fuzzy Event-Condition Rete Network" in Section 3.1. Both the contributions are presented at extremely high-level, without using any formalisms. Although many examples are provided, their presentation suffers from a lack of adequate carefulness.

Section 2

This section mixes background knowledge (Fuzzy Logic) and paper contributions. I would prefer to separate the two.

Section 2.1 describes Fuzzy Time Constrains, and it should be separated from the rest of the section and given more centrality.
It is the actual contribution of the paper as it extends Allen's algebra with fuzzy-based rules.

Table 2 should include the original Allen's definition and perhaps comment on the difference. Moreover, I would expect this part to include some reasoning about the overall complexity of the fuzzy extension. In the introduction, the authors indeed mention the rising computational cost of employing fuzzy-based solutions. I would also expect to find empirical evidence of such complexity study in the evaluation section.

Section 2.2 lacks is not presented accurately; listings require a proper presentation and lines numbers to be referred to in the underlying text.
Also, the background on ECA is missing.

Section 3

Also, this section starts with background information about the Rete algorithm. I believe this should be included as part of the paper preliminaries.

- Fuzzy Event-Condition Rete Network

The various definitions should be numbered and referenced in the text. The current presentation makes it very hard to distinguish them from the surrounding content.  Moreover, it looks more like a single definition for "Trigger Node" which is a n-tuple with the indicated fields. 
I also did not find any references to the trigger nodes field. I couldn't understand from the paper why SI, SS,  they are a relevant part of the TN definition.

Section 3.2.1 starts with a definition (not numbered).  "Node Cluster" is not actually defined, as the definition reuses the wording itself. Additionally, in the example "Node Classes" is mentioned and no proper definition for 'Filter' is provided. The remainder of this section proceeds in the description of an example that is quite hard to understand. 

Section 2.3.1 should describe the C-net part of the extension while "Condition Queries" are undefined. (Maybe the authors meant "Continuous" Queries?). Nevertheless, the authors focus on describing possible evaluation strategies that I believe should be part of a processing model. I also do think they are all useful, it seems that only Strategy 3 is actually used from the paper content. Anyways, the text needs major proofreading. It contains many repetitions, and it is very hard to follow. The remainder of the section also digs into an algorithm for constructing the C-network.
The algorithm is not presented correctly, lacks accuracy ,and it is hard to distinguish the pseudo-code from the text commenting it. In this stage, I cannot confirm is appropriateness. An example, which includes the Figures 7 and 8, is used to support the explanation but actually makes it more complex, as it is very hard to understand where does it start the example and the formal explanation ends.

Section 4

This section presents the processing model. However, it presents several shortcomings.

- the processing steps are only listed and poorly described.
- the authors describe three different timestamps. It is not clear from the text if each event uses all of them of they are listing the alternatives. Anyways, a reference to related work is necessary. I suggest "The Dataflow Model" Akidau et al.
- the assumption of using processing time is very strong. It makes the approach synchronous, eliminating the problem of out-of-order, which is one of the biggest issues for parallel and distributed stream processing solutions. I would have expected that fuzzy-based temporal reasoning would have helped to deal with out of order.

In the remainder of the section, the authors describe three different processing models. I did not quite appreciate why did the authors decide to present all of them once the trade-offs are evident from their explanations. Perhaps the authors should explain why each processing model constitutes a contribution.
The conditions that subsume the computation changes, but the impact on the approach expressiveness is left unsaid. We only can read Figure 13, which also is not commented on by the authors.

Finally, Section 5 and 6 are left incomplete. I believe they were written in a hurry, and some content is missing. 


