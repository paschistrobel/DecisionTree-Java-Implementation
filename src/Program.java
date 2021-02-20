import models.Attribute;
import models.DecisionTree;
import models.Passenger;
import org.w3c.dom.Attr;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/** So sollte das Programm aussehen:
 * DecisionTree tree = new DecisionTree();
 *
 * //Trainieren des decisionTrees
 * tree.train(new File("resources/vertebrate.psv"));
 *
 * //Klassifizieren des DecisionTrees
 * System.out.println(tree.classify("gila mrs. Zanker, 3, "));
 */
public class Program {
    public static void main(String [] args){

        String [] attributes = {
                Attribute.PCLASS,
                Attribute.TITLE,
                Attribute.SEX,
                Attribute.AGEGROUP,
                Attribute.SIBSP,
                Attribute.PARCH,
                Attribute.FARE,
                Attribute.EMBARKED};


        // Read CSV train data
        String trainData_path = "src/data/train.csv";
        Set<Passenger> trainData = CSV_Helper.readTrainData(trainData_path);
        //System.out.println(trainData.size());

        DecisionTree decisionTree = new DecisionTree(Attribute.SURVIVED, attributes);
        //decisionTree.train(trainData);
    }

}
