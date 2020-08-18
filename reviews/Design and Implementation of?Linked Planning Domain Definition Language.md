## Summary of the Paper

The authors, who remain anonymous, propose an extension of the Planning Domain Definition Language 
for the Semantic Web called PDDLS. 
The paper contributions are listed, i.e., 
PDDLS syntax and semantics; the language design and implementation, and a demonstration.

Although there are many PPDL extensions and applications related to the Semantic Web, I found the idea interesting as it seemed
to take a modern approach that involved new standards and technologies, i.e., JSON-LD and SHACLE.
Nevertheless, the paper several major issues and structural 
problems that convinced me it does not meet the quality standard of ESWC.

In the following, I first list the major issues and the structural problems, 
than I comment (almost) section by section for minor things.

- Several Figures/Listings are unreadable. Moreover, 
the paper uses often too complex sentences that makes hard the overall understanding.
Finally, the section structure seems over-engineered.

- The Implementation is not provided. What the authors give are a EBNF grammar, a system schema and two examples.
This is definitely not enough to constitute a language design. 

- The paper lacks a background section that contains Description Logics, and PPDL syntax and semantics. 
To the best of my knowledge [1] is the reference work. This section is mandatory as the authors claim to 
extend the language semantics. 

- The paper presents an imprecise and insufficient formalization of the language. What is presented in Section 4 is
just a sketch of a formalization. Moreover it presents several issues, e.g., 
"q" assumes several meaning in the same sentence: a graph pattern, an individual, a query function (also undefined).


Section 1

The very first paragraph uses an unexpected language for a research paper. I understand the challenge of writing
an appealing introduction, and I like to be innovative too. However,  I suggest the authors to opt for something more standard.

Section 2

"While the actual system is complex as depicted in Figure 2, the focus of this paper can be depicted as simple in Figure 3."

This sentence makes me wonder why is Figure 2 in the paper. Also, the figure is very small and not commented.

Moreover, Figure 3 actually contains 2 figures, which are also very small.

The "object database" is mentioned and never explained.

The acronyms NLU is redundantly defined.


Section 3

This section does not present what it promises. A sketch of the semantics is presented in Section 4.
What this section contains is an explanation of the "context" clause that PPDLS inherits from its alignment
with JSON-LD. Nevertheless, this clauses does not add any semantics itself. 

PPDLS semantics must be defined somehow, operation by operation or in a composable way.

Moreover, the authors seem to see JSON-LD as an ontological language rather than an RDF format.
Indeed, they refer to JSON-LD expressive power. I am not arguing against this observation, 
but it requires more explanation. 


Section 4

This section gives some background on OWL and robotics. Then introduces three subsections
related on Conditional predicates and Implementation.

The formalization of conditional predicate is not rigorous. 
Moreover, the implementation section actually presents an explanation of what "decidability" is.


Section 5

The main Figure is too small and the overall text is very hard to follow.


[1] McDermott, Drew. "The formal semantics of processes in PDDL." Proc. ICAPS Workshop on PDDL. 2003. 
http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.8.3437&rep=rep1&type=pdf