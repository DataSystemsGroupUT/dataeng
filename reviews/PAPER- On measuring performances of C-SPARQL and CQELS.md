!(Paper)[file:///Users/Riccardo/Dropbox/_Reviewed/SR16/SR2016_paper_1.pdf]
    
    
    The paper proposes a deep benchmarking of two well known RSP engines, C-SPARQL engine and CQELS. The systems are evaluated  by the means of:
        - a data generator that allows to control the generation rate and the content, so that is possible to assess query results correctness.
        - six queries expressed in natural languages that, according to the authors, have different complexities. Such complexities are defined w.r.t the presence of some operators, e.g. FILTER or UNION or the integration with static data.
        - a set of *test criteria* that defines how the evaluation should be done, i.e. stream rate, number of triples, window size, number of streams, evaluation time and memory consumption.
        
    The paper is overall well written and authors' goal are clearly stated, i.e., they claim that RSP benchmarking requires more formal evaluation plans and they provide two examples. 
    Despite this, I think the paper does not deserve a full acceptance (+3). RSP benchmarking received a lot of attention lately and, thus, IMHO it is required  to provide more impactful contributions w.r.t the state of the art and not just
    yet another benchmark. This work contains many contributions, but none of them sounds to me innovative enough to motivate a full acceptance.
    
    In the following I will comment section by section:
    
    Section 1
    
    " [...] some performance criteria have not been considered in their (other RSP benchmarks ) evaluation plans. Thus, we conduct a redesigned experiment to have a comprehensive view on current RSP systems. "
    
    The authors well motivated their work but then it seems they are proposing Yet another RSP benchmark, rather than redesigning existing one according to a study that they did on performance criteria.
    
    Section 2
    
    The state-of-the-art for RSP benchmarking is laking of some works. [1] is very recent recents so its absence is understandable, but  [2] or [3] are particularly relevant w.r.t this work and, thus, require a citation. 
    
    [2] provides some criteria, namely commandments, to evaluate RSP engines. Since I still believe that the main contribution of this work is the reformulation of these criteria, I suggest the author to look at this paper.
    
    The author refers to concepts like "time-driven", "data-driven", "on content change", that, for the best of my knowledges, belong to [3]. I suggest to the author to include this work too or provide specific description about
    what they may alternatively mean with these terms.
    
    Finally, a description on *how* other benchmarks approached the evaluation, i.e., their evaluation plan,  would be interesting to understand the novelty of the proposed one.
    
    Section 3
    
    This section first describes the proposed "benchmark". I understand the meaning of controlling the testing rate, ( I did the same choice in my last work [4]), but the authors could exploit some existing work instead of providing a new
    ontology and queries. This would let them focus on the evaluation and the criteria. Moreover, I think deeper definition of "complex" query is worth w.r.t the  following section.
    
    "Note that C-SPARQL supports only one window per query, whereas CQELS defines a window operator on each stream."
    
    This is wrong. C-SPARQL allows the definition of one window per stream and the evaluation of the query happens when *any* window slides.
    
    Section 4
    
    I agree and appreciate the insights provided as well as the discussion in Section 5, especially the idea of the MCR sounds really appropriate. 
    However, I have some doubts about  the definitive distinction between the two systems. Does not C-SPARQL engine supports physical windows as well?
    
    Moreover, 
    
    "Let us denote by Rate_max_ the maximum stream rate that can be accepted by C-SPARQL for a given query. Theoretically, execution time equals to STEP for an incoming stream at Rate = Rate_max_"
    
    Who does define this theoretically?
    
    
I still think that the work is valuable and the quality is enough for this workshop. I encourage the authors to improve the work for an extended version.

Riccardo Tommasini  

[1]     Maxim Kolchin, Peter Wetz, Elmar Kiesling, A Min Tjoa:
    YABench: A Comprehensive Framework for RDF Stream Processor Correctness and Performance Assessment. ICWE 2016: 280-298

[2]  Thomas Scharrenbach, Jacopo Urbani, Alessandro Margara, Emanuele Della Valle, Abraham Bernstein:
    Seven Commandments for Benchmarking Semantic Flow Processing Systems. ESWC 2013: 305-319
[3]  Irina Botan, Roozbeh Derakhshan, Nihal Dindar, Laura M. Haas, Renée J. Miller, Nesime Tatbul:
    SECRET: A Model for Analysis of the Execution Semantics of Stream Processing Systems. PVLDB 3(1):232-243 (2010)

[4]  Riccardo Tommasini, Emanuele Della Valle, Marco Balduini, Daniele Dell'Aglio:
    Heaven: A Framework for Systematic Comparative Research Approach for RSP Engines. ESWC 2016:250-265