# DecisionTree-Java-Implementation
An intuitive Java implementation (built from the very bottom) for a Decision Tree classifier. The implementation is based on the "Titanic - Machine Learning from Disaster" dataset [1].

The classifier achieves an accuracy of **0.8227 with a 10-fold cross-validation** and an accuracy of **0.7249 with the Kaggle test data set**.

## Algorithm
The implementation is based on the **C4.5 algorithm** developed by Ross Quinlan [2]. Pseudocode and a detailed explanation of it is provided in the following sections.
1. Pseudocode
2. C4.5 Explanation
   * MCV (most common value)
   * Entropy
   * Information Gain
```
train(EXAMPLES, TARGET_ATTRIBUTE, ATTRIBUTES)
   if all EXAMPLES positiv
     return root (label = +)
   else if all EXAMPLES negative
     return root (label = -)
   else if ATTRIBUTES is empty
     return root (label = mcv(TARGET_ATTRIBUTE))
   else
     A <- the attribute of ATTRIBUTES that best classifies EXAMPLES
     A is attribute for ROOT
     For each value v in A
       create new child c_v for ROOT
       create subset EXAMPLES_v for c_v
       if EXAMPLES_v is empty
         create leaf (label = mcv(TARGET_ATTRIBUTE))
       else
         train(EXAMPLES_v, TARGET_ATTRIBUTE, ATTRIBUTES - {A})
```
The key aspect of this algorithm is the step where the attribute that best classifies 
### Explanation of the algorithm

[Go to Real Cool Heading section](#real-cool-heading)

## References
[1] https://www.kaggle.com/c/titanic<br/>
[2] https://www.rulequest.com/Personal/
[]
