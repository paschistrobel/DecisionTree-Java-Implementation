import models.*;
import java.util.*;

public class Program {
    public static void main(String [] args){
        final String trainData_path = "src/data/train.csv";
        // Read CSV train data
        Set<Passenger> trainData = CSV_Helper.readTrainData(trainData_path);
        // CSV_Helper.printTrainData(trainData);

        String [] attributes = {
                Attribute.PCLASS,
                Attribute.TITLE,
                Attribute.SEX,
                Attribute.AGEGROUP,
                Attribute.SIBSP,
                Attribute.PARCH,
                Attribute.FARE,
                Attribute.EMBARKED};
        DecisionTree decisionTree = new DecisionTree(Attribute.SURVIVED, attributes);
        decisionTree.train(trainData); // train the decision tree with train data
        decisionTree.print();
    }
}
