import models.*;
import java.util.*;

public class Program {
    public static void main(String [] args){
        final String trainData_path = "src/data/train.csv";
        // Read CSV train data
        Set<Passenger> trainData = CSV_Helper.readTrainData(trainData_path);
        // separateData --> trainData test
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
        System.out.println(decisionTree.classify(new Passenger("697", "0", "1","Kelly, Mrs. James","male","70","0","0","8.05","S")));
    }
}
