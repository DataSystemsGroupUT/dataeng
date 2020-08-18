# An Unsupervised Aspect Extraction Strategy For Real-Time Reviews Monitoring 
#review 
Strong Points

- The paper addresses an important problem, i.e. domain-independent opinion mining;
- the paper proposes an innovative approach that combines different lexicons.

Weak Points

- The approach is outperformed by the terms of comparison in half of the situation;

- authors attributed the performance loss to linguistic differences between the benchmark datasets. Nevertheless, they should have characterized such differences and, if possible,
generalize the results accordingly. 

- Authors claim the approach better fits real-time context since it is not based on supervised learning. However, they neither elaborate nor evaluate the real-time aspect of their work.

General Comments

The paper proposes an innovative approach for domain-independent opinion mining. This approach combines different lexicons for opinion mining, uniforming how the polarity measure among them.

The paper presents an architecture that explains how the system works and a Web UI that makes it accessible to users.

The paper is reasonably written, although readability of some parts could be improved. 

Although I understand the value of the work, the evaluation results still do not fully convinced.

Regarding Aspect Extraction,
the approach is outperformed by the terms of comparison in half of the experiments (either precision/recall/F-measure). However, authors argue that deltas are always less of the 2%, which is an acceptable trade-off to gain domain independence.

This would be fine, but regarding Polarity Computation, the final results show that the approach performs better on the "Laptop" (and "Hotel") domains than in the "Restaurant" one.
This is true also for the terms of comparison, but they are domain dependent.  Authors argue that this difference is due to the language complexity of the two domains, but they did not characterize this linguistic aspect. 
For a proper generalization of the results, it is necessary to correlate the obtained results with a quantification of this linguistic difference.

Finally, in the abstract and the introduction, authors claim the approach better fits real-time context than traditional opinion mining methods, because it is not based on supervised learning. 
However, they neither elaborate nor evaluate the real-time aspects of their work. 

Unless supported by responsiveness requirements and coherent performance measure,  "real-time" is a catching jargoon that is not related to the approach. 

I do not understand why a rule-based approach should be better (and in particular faster) than a supervised one, in a context where the model was trained offline.

Moreover, online ML is evolving and this claim should be supported by proper performance measure.