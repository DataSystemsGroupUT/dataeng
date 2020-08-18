#BDR-D-20-00191

The manuscript, which extends the one presented in [6]*, revise existing semantics for Ontology-Mediated SPARQL query execution and propose a new one, called epistemic certain answers.
The paper is well written and well structured. One minor regards the title, which is a bit too general w.r.t. the content of the manuscript.

I appreciated very much the requirement analysis and its formalisation as a problem statement. The contribution to the state-of-the-art is clear. Not only the authors discuss formally pros and cons
of existing SPARQL semantics, but they revise the existing work systematically. Moreover, they proposals for alternative semantics are sound. 
To the best of my knowledge, the proofs add up and can be considered correct. There are some minor issues, related to notation and phrasing. 
However, I think the paper can be considered for acceptance.

In the following, I will list some minor comments that can be fixed in the camera ready. 
They are mostly aesthetics, but I believe they might reduce the reader's work when it comes to understand and extend the work.

Section 2.1 Page 4
- ω|X is the only function compatible with ω that verifies...
ω||X is the only function compatible with ω that verifies...

I wonder what are the implication of uniqueness and how they impact definitions 5 and 6, respectively.

the explanation of notation w(q) can be improve, perhaps with an example.

Section 2.2
Although the concept is clear, aDom (active domain) should be made explicit and referenced accordingly when used in alongside the paper definition and proofs.


Section 3

In this section, several functions are explained in-line and not numbered yet referenced later one. 

- eAns
- eCertAns

Page 7
<<But as illustrated by the authors, this proposal does not comply with the standard semantics for sparql over plain graphs. Example 3 below reproduces the one given in [1, Example 4]:>>

It is not clear what proposal does not comply to the standard semantics. we know eCertAns does not from [1], but *this* leaves some minor doubts.

I think the final paragraph at page 7 should be moved in the introduction or preliminaries.
 

page 9

I wonder if Requirement 5 is really necessary and couldn't be relaxed by just relying on SPARQL operator precedence. Perhaps the authors can comment on this?

Section 5

On Definition 4 

The authors explain that the formalisation given in [9] is too low-level and propose a more abstract one.
However, they do not explain neither what "low-level" means nor how their proposal is compliant to the one given in [9]

According to [9] and entailment regime (ER) specifies 
- A subset of RDF graphs called well-formed for the regime
- An entailment relation between subsets of well-formed graphs and well-formed graphs.

I understood that the provided specification is independent from the ER of choice, but can the authors elaborate on why Definition 4 incorporate such information?
Or can they argument on why this specification is not needed?

Also, I believe eRans(t,K)=certAns(q,K) should be eRans(t,K)=certAns(t,K).

page 12-13

the following equations should be numbered and crosse-refenced in the proofs. (I couldn't replicate the symbols here)

- Ω ◃ B = {ω ∈ Ω | range(ω) ⊆ B}
- Ω  􏰄 B = {ω∥B | ω ∈ Ω}.
- Ω⊗X= {ω|X |ω∈Ω,X∈max⊆(X∩2dom(ω))}

the following notations, sparqlAns(q,K,q') and mCanAns(q,K,q') can be easily confused with the 2-parameters function. Please consider changing them. 

Appendix A

Lemma 3, is the B1-B2 passage really necessary?

*using the same reference numbering of the paper