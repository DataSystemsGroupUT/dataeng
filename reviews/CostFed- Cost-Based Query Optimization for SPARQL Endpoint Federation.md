#Paper Summary 

The paper proposes an approach to improve the performance of SPARQL endpoint federation, i.e. CostFed.
CostFed provides a “TRIE-based source selection” approach which is JOIN-AWARE based on common URI prefixes. 
Indeed, the authors claim that the   “generation of an optimised query plan” and the  “efficient selection of sources” are where existing approaches lack of efficiency. Especially the latter, because it uses a triple-pattern-wise criterium that overestimate the number of sources or a join-aware source selection under the strong, and practically uncommon, assumption that the data are evenly distributed.

The paper is overall well-written and well-structured but sections include to many information that require a simpler representation. 

SECTION 2
The state of the art section seems comprehensive to me that I am not an expert to the field. A fast research on the major academic engines retrieve mostly all the cited paper. 
To simplify the comprehension however I will introduce the preliminaries section before the state of the art, extending it with more basic informations.
SECTION 3

The preliminaries section provides only the minimal information required by the reader to understand the paper, assuming a deep competence about
the work. Unfortunately, due to the depth of the work, this might be hard. I would ask to clarify Definition 2, because is quite hard
to read and required me to be rewritten to fully understand. A more schematic representation would lead an easier understanding to the occasional reader. 

However, almost each theoretical contribution in the paper has a companion example that ease the comprehension. 

SECTION 4

This section lacks of a simple structure. Instead of describe the approach analytically why didn’t the authors include the system of equation 
that captures the approach. If I understood correctly the want to estimate b0 and b1 so they just have to write the equation f(x)=a+b cx +d and its derivates.
It was quite hard to me to relate the text to the figure; I understand the approach is sound and clear to the authors, but please simplify.

Listing 1 has a very simple caption, while the text below contains the whole information. The example of the data summary is very welcome, but I suggest to highlight the syntax of 
or the properties then explain their semantics in the caption while, in the text below, you can discuss the relevance of this summary for the approach, which is, if I understood correctly,  “The innovation behind CostFed’s data summaries is to use common prefixes to characterise resources in data set”.

SECTION 4.2 
this section is quite important, and maybe an example would clarify it further. Noice that, in the appendice to the paper, the algorithm has the same Label of the one in the paper.
Figure 4a should be separated and move closer to the point where it is actually named.
Same for 4b, whose description is far after in the paper.

SECTION 6
The paper evaluation is sound and extensive. The authors compare the work agains the relevant state-of-the-art solutions and their previous, now improved, approach.
They performed two experiment sets, the first one with FedBench and the second one with LargeRDFBench, selecting only subsets of the proposed queries of the two benchmarks.
As I claimed at the beginning, I am not confident enough to argue about the technical soundness of the work, however the results presented by the authors convinced me that their approach improve the state-of-the-art by performing better than the others. 

I however comment again on the way the content is presented. Table 1 could be easily split in two for a better comprehension and highlight the winner or the winners per line (with different colors) will make easier to identify in the table what the authors describe in the paper text.
