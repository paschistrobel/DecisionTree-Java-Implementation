import models.*;
import java.util.*;

/**
 * @author Pascal Strobel
 * */
public class Program {
    public static void main(String [] args){
        final String DATA_PATH = "src/data/train.csv";
        final int NUMBER_SPLITS = 10;
        // Read CSV data
        Set<Passenger> data = CSV_Helper.readData(DATA_PATH);
        // CSV_Helper.printTrainData(trainData); //show the data

        /**If you just want to classify one item, uncomment the following code and comment out the code (for cross-validation) below*/
        /*
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
        decisionTree.train(data);
        decisionTree.print();
        // 0 = not survived, 1 = survived, 2 = can't classify data
        decisionTree.classify(new Passenger("885","0","3","Sutehall, Mr. Henry Jr","male","25","0","0","7.05","S"));
        */

        /**The following code is for cross-validation. If you just want to classify one item, comment out the following code and uncomment the code above*/
        Set<Passenger> [] splitData = CSV_Helper.splitData(data, NUMBER_SPLITS); // Divides data into NUMBER_SPLITS sub sets for cross validation
        Set<Passenger> trainData;
        Set<Passenger> testData;
        String [] attributes = {
                Attribute.PCLASS,
                Attribute.TITLE,
                Attribute.SEX,
                Attribute.AGEGROUP,
                Attribute.SIBSP,
                Attribute.PARCH,
                Attribute.FARE,
                Attribute.EMBARKED};
        int incorrect = 0;
        int correct = 0;
        double precisions [] = new double[NUMBER_SPLITS];
        for(int i = 0; i < NUMBER_SPLITS; i++){
            testData = splitData[i];
            trainData = CSV_Helper.mergeData(splitData, i);
            DecisionTree decisionTree = new DecisionTree(Attribute.SURVIVED, attributes);
            decisionTree.train(trainData);
            decisionTree.print();
            for(Passenger p : testData){
                // decisionTree.classify: 0 = not survived, 1 = survived, 2 = can not classify data
                if(Integer.parseInt(decisionTree.classify(p)) == p.getSurvived()){
                    correct++;
                }
                else{
                    incorrect++;
                }
            }
            System.out.println("Korrekt klassifiziert: " + correct + "\nInkorrekt klassifiziert: " + incorrect);
            precisions[i] = (double) correct / ((double) correct + (double) incorrect);
            System.out.println("Präzision: " + precisions[i] + "\n\n");
            correct = 0;
            incorrect = 0;
        }
        double avg_precision = 0.0;
        for(int i = 0; i < precisions.length; i++){
            avg_precision += precisions[i];
        }
        avg_precision /= NUMBER_SPLITS;
        System.out.println("\nDURCHSCHNITTLICHE PRÄZISION (CROSS-VALIDATION): " + avg_precision);
    }
}
