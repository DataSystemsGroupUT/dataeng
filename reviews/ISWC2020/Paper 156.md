

# summary
The paper presents a cache-based approach for parallel ABox reasoning using Tableau algorithm.
The paper is well written and technically sound. The authors provide a theoretical explanation of their contribution and an evaluation of a proof-of-concept implementation that uses Konclude reasoner. 
The problem of parallel Abox reasoning is very relevant, and the proposed approach seems technical sound.
Nevertheless, the authors did not discuss how the mechanics of the asynchronous cache update relate the completeness. In practice, a good part of the contribution is not explained. Moreover, the evaluation does not investigate the scalability of the approach concerning different abox partitioning techniques.

# Detailed 
The paper presents a cache-based approach for parallel ABox reasoning using Tableau algorithm.
The paper is well written and organized. Although some sections are very hard to read due to the amount of condensed information, the authors provide an extended technical report that unties some of the most elaborate passages. 

The authors provide a theoretical explanation of their contribution and an evaluation of a proof-of-concept implementation that uses Konclude reasoner. Although the evaluation shows some shortcomings, the paper constitutes a fair contribution to ISWC.

### Minor Issues  
- the examples (fig 1 and fig 2), whose descriptions do not capture very well the expansion progres3s. I suggest to add some numbers or improve the usage of colors to identify the various steps of the expansion. Intuitively, some of them at meant to happen in parallel. However,  the example hardly passes the intuition to the reader.
- the discussion in Section 2 regarding the optimizations techniques is hugely condensed and very hard to follow without examples. Moreover, it does not seem particularly relevant, since none of these optimizations is investigated in the evaluation.

### Major Issues

- A discussion of the asynchronous cache updates is never developed. It appears to be a relation between the influencing conditions. the authors should provide evidence of this possibility, for instance in terms of pseudo-code or empirically measuring the correctness of different implementations
- the discussion on how the ABox should abe partitioned is missing. ABox-partitioning s a research problem itself, and the authors are not expected to provide a solution. Nevertheless, they should investigate the impact on performance. I think at least two scenarios are relevant:
    - different balanced splits of the Abox (random), to understand if the cache strategy is convenient in the average case. I assume this is the current case
    - unbalanced partitions of the ABox, to understand how the approach behaved when one worked as much more work than the other (and thus it hits the cache more frequently).
- The part on query answering suffers from the lack of comparison in the evaluation. Although this is present in the technical report, I am not sure we should consider it as the page limit should be the same for everyone.

A final side note. The paper is very well written, and I found it pleasing to read. However, it sometimes has a very confident tone that drives away from the discussion. In general, the paper should be self-contained, and the discussion should focus on the paper contributions and the most relevant related work. The authors sometimes forget these principles and engage a discussion which is exciting but distracts the reader from the contributions.


