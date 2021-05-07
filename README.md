# DecisionTree-Java-Implementation
An intuitive Java implementation (built from the very bottom) for a Decision Tree classifier, based on the "Titanic - Machine Learning from Disaster" dataset [[1]](#references).

The classifier achieves an accuracy of **0.8227 with a 10-fold cross-validation** and an accuracy of **0.7249 with the Kaggle test data set**.

## Algorithm
The implementation is based on the **C4.5 algorithm** developed by Ross Quinlan [[2]](#references). Pseudocode and a detailed explanation of its main concepts is provided in the following sections.
1. [Pseudocode](#pseudocode)
2. [C4.5 Explanation](#detailled-explanation)   
  2.1 [Entropy](#entropy)  
  2.2 [Information gain](#information-gain)  

### Pseudocode
```
train(EXAMPLES, TARGET_ATTRIBUTE, ATTRIBUTES)
   if all EXAMPLES positiv
     return root (label = +)
   else if all EXAMPLES negative
     return root (label = -)
   else if ATTRIBUTES is empty
     return root (label = mcv(TARGET_ATTRIBUTE)) //mcv = most common value
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
The recursive algorithm generates a decision tree from a set of training data using the concept of (information) entropy. At each node of the tree, C4.5 chooses the attribute of the data that most effectively splits the training data into subsets. To find that "best attribute", the information gain (related to entropy but not the same) for each attribute is calculated and the one with the highest gain is chosen. The algorithm then recurses on the partitioned data subsets. 

### Detailled explanation
#### Entropy

#### Information gain

## References
[1] https://www.kaggle.com/c/titanic<br/>
[2] https://www.rulequest.com/Personal/
