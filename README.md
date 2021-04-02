# DecisionTree-Java-Implementation
An intuitive Java implementation (built from the very bottom) for a Decision Tree classifier. The implementation is based on the "Titanic - Machine Learning from Disaster" dataset [[1]](#references).

The classifier achieves an accuracy of **0.8227 with a 10-fold cross-validation** and an accuracy of **0.7249 with the Kaggle test data set**.

## Algorithm
The implementation is based on the **C4.5 algorithm** developed by Ross Quinlan [[2]](#references). Pseudocode and a detailed explanation of it is provided in the following sections.
1. [Pseudocode](#pseudocode)
2. [C4.5 Explanation](#detailled-explanation)  
  2.1 [MCV](#mcv)  
  2.2 [Entropy](#entropy)  
  2.3 [Information gain](#information-gain)  

### Pseudocode
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
The recursive algorithm consists of two main steps. The first three if/else-if blocks form the termination conditions for the algorithm. The else block first determines the best attribute to split the remaining data on and then recursively calls the train method.

### Detailled explanation
The algorithm is best explained when it's visualized with an example. Therefore let's assume we have the following data: 
| ID | Sex | PriceClass | Embarked | Survived | > fasdtes
|:--:|:---:|:----------:|:--------:|:--------:| > fdsjfaf
|1   |m    |1           |C         |1         |
|2   |m    |2           |Q         |0         |
|3   |f    |1           |Q         |1         |
|4   |f    |1           |S         |1         |
|5   |m    |3           |S         |0         |
|6   |m    |2           |Q         |1         |
|7   |f    |3           |C         |1         |
|8   |m    |1           |Q         |1         |
|9   |m    |2           |Q         |0         |
|10  |m    |2           |C         |1         |
#### MCV
#### Entropy
#### Information gain

## References
[1] https://www.kaggle.com/c/titanic<br/>
[2] https://www.rulequest.com/Personal/
