package models;

public class DecisionTree {

    public DecisionTree(){

    }

    public void train(String [] examples, String targetAttribute, String[] attributes){
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
        *   else train(teilmenge von examples, targetAttribute, übrige Attribute)
        * */
    }

    private String getBestSplit(){
        return null;
    }

    public String classify(String dataPoint){

        return null;
    }
}
