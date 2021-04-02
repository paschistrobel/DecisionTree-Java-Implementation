# DecisionTree-Java-Implementation
An intuitive Java implementation (built from the very bottom) for a Decision Tree classifier. The implementation is based on the "Titanic - Machine Learning from Disaster" dataset [1].

The classifier achieves an accuracy of 0.8227 with a 10-fold cross-validation and an accuracy of 0.7249 with the Kaggle test data set.

## Algorithm
The implementation is based on the C4.5 algorithm developed by Ross Quinlan [2]. The following pseudocode provides an overview of the algorithm:
> train(EXAMPLES, TARGET_ATTRIBUTE, ATTRIBUTES)
>   if all EXAMPLES positiv
>     return root (label = +)
>   else if all EXAMPLES negative
>     return root (label = -)
>   else if ATTRIBUTES empty
>     return root (label = mcv(TARGET_ATTRIBUTE))
>   else
>     _A_ <- Element of REMAINING_ATTRIBUTES that best classifies EXAMPLES
>     _A_ is attribute for ROOT
>     For each value _v_ in _A_
>       create new child _c_v_ for _ROOT_
>       create subset _EXAMPLES_v_ for _c_v_
>       if _EXAMPLES_v_ empty
>         create leaf (label = mcv(TARGET_ATTRIBUTE))
>       else
>         train(_EXAMPLES_v_, TARGET_ATTRIBUTE, ATTRIBUTES - {A})


## References
[1](https://www.kaggle.com/c/titanic)
[2]
