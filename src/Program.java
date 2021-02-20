import models.*;
import org.w3c.dom.Attr;

import java.io.File;
import java.util.*;

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


        /*Eingelesene Daten ausgeben lassen
        System.out.println("Anzahl Passagiere: " + trainData.size());
        Object [] d = trainData.toArray();
        Passenger p;
        for(int i = 0; i < trainData.size(); i++){
            p = (Passenger) d[i];
            System.out.println("id: " + p. getID() +
                    "\tsurv: " + p.getSurvived() +
                    "\tpclass: " + p.getpClass()+
                    "\ttitle: " + p.getTitle() +
                    "\tsex: " + p.getSex()+
                    "\tage: " + p.getAgeGroup() +
                    "\tSibsp: " + p.getSibSp()+
                    "\tParch: " + p.getParch() +
                    "\tFare: " + p.getFare()+
                    "\tEmba: " + p.getEmbarked());
        }*/
        //System.out.println(mcv(trainData, Attribute.EMBARKED));

        DecisionTree decisionTree = new DecisionTree(Attribute.SURVIVED, attributes);
        decisionTree.train(trainData);
        //decisionTree.train(trainData);
    }
}
