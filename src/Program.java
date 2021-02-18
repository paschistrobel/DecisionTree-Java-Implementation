import models.Data;
import models.DecisionTree;

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

        final String [] attributes = {"pClass", "title", "sex", "ageGroup", "sibSp", "parch", "fare", "embarked"};
        final String targetAttribute = "Survived";

        Data dataReader = new Data();
        // trainData = dataReader.readTrainData();

        DecisionTree decisionTree = new DecisionTree();
        // decisionTree.train(data, attributes);


    }
}
