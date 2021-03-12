import models.*;
import java.util.*;

/**
 * @author paschistrobel
 * */
public class Program {
    public static void main(String [] args){
        /**1. read in data*/
        final String TRAIN_DATA_PATH = "src/data/train.csv";
        Set<Passenger> data = CSV_Helper.readTrainData(TRAIN_DATA_PATH); // read in data from csv file
        /**2. define attributes for training and the target Attribute (the one our classifier is going to predict)*/
        String [] trainAttributes = {
                Attribute.PCLASS,
                Attribute.TITLE,
                Attribute.SEX,
                Attribute.AGEGROUP,
                Attribute.SIBSP,
                Attribute.PARCH,
                Attribute.FARE,
                Attribute.EMBARKED
        };
        String targetAttribute = Attribute.SURVIVED;
        /**3. Create and train your decision tree*/
        DecisionTree decisionTree = new DecisionTree(targetAttribute, trainAttributes);
        decisionTree.train(data);
        //decisionTree.print(); // print resulting decision tree
        /**4. Classify new unseen data*/
        System.out.println(
                "Classified as: " +
                decisionTree.classify(new Passenger("885","0","3","Strobel, Mrs. Pascal","male","66","0","0","7.05","S"))
        );

        /**Cross validation*/
        final int NUMBER_OF_SPLITS = 10;
        Validation validator = new Validation(data, NUMBER_OF_SPLITS, trainAttributes, targetAttribute);
        //validator.crossValidate();

        /**Validation on Kaggle test data*/
        final String TEST_DATA_PATH = "src/data/test.csv";
        Set<Passenger> testData = CSV_Helper.readTestData(TEST_DATA_PATH);
        validator.evaluateKaggleTestData(decisionTree, testData);
    }
}
