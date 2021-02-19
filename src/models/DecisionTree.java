package models;

import java.util.Set;

public class DecisionTree {

    public DecisionTree(){

    }

    public void train(Node root, Set<Passenger> passengers, String targetAttribute, String[] attributes){
        if(allPositive(passengers, targetAttribute)){
            root.setLabel("1");
            root.setLeaf(true);
            return;
        }
        else if(allNegative(passengers, targetAttribute)){
            root.setLabel("0");
            root.setLeaf(true);
            return;
        }
        else if(attributes.length == 0){
            //root.setLabel(mcv(targetAttribute));
            return;
        }
        else{
            String bestAttribute = getBestSplit();
            /*
            * for each possible Value v from bestAttribute:
            *   Node child = new Node();
            *   root
            *   Set<Passenger> = new Hashset<>(); // Teilmenge für cv erstellen
            *   if()
            *
            * */
        }

        // if examples[survived] !contains(false) --> alle Examples positiv --> return root, Label = +
        // else if examples[survived] !contains(true) --> alle Examples negativ --> return root, Label = -
        // else if attributes.length == 0 --> return root, label = mcv(targetAttribute)
        // else
        /*String bestAttribute = getBestSplit(attributes)
        *root.setLabel(bestAttribute)
        *for each possible Value v (z.B. male, female) in bestAttribute-spalte
        *   create new child node für aktuellen Knoten
        *   Teilmenge von examples, das den Wert v hat
        *   if (teilmenge == empty) erzeuge leafnode mit Label mcv(targetAttribute)
        *   else train(teilmenge von examples, targetAttribute, übrige models.Attribute)
        * */
    }

    // checks whether the set is homogeneous with regard to an attribute
    private boolean allPositive(Set<Passenger> set, String attribute){
        Object [] o = set.toArray();
        Passenger p;
        for (int i = 0; i < o.length; i++){
            p = (Passenger) o[i];
            if(p.getAttributeValue(attribute) == 0){
                return false;
            }
        }
        return true;
    }

    private boolean allNegative(Set<Passenger> set, String attribute){
        Object [] o = set.toArray();
        Passenger p;
        for (int i = 0; i < o.length; i++){
            p = (Passenger) o[i];
            if(p.getAttributeValue(attribute) == 1){
                return false;
            }
        }
        return true;
    }

    private int mcv(String targetAttribute){
        return 0;
    }

    private String getBestSplit(){
        return null;
    }

    public String classify(String dataPoint){

        return null;
    }
}
