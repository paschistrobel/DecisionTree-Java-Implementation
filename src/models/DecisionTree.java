package models;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DecisionTree {

    private String targetAttribute;
    private Node rootNode;
    private String [] attributeList;

    public DecisionTree(String targetAttribute, String [] attributeList){
        this.rootNode = new Node();
        this.targetAttribute = targetAttribute;
        this.attributeList = attributeList;
    }

    public void train(Set<Passenger> data){
        train(this.rootNode, data, this.targetAttribute, this.attributeList);
    }

    // Overload method
    public Node train(Node node, Set<Passenger> passengers, String targetAttribute, String[] attributes){
        if(allPositive(passengers, targetAttribute)){
            node.setLabel("survived");
            node.setLeaf(true);
            return node;
        }
        else if(allNegative(passengers, targetAttribute)){
            node.setLabel("not survived");
            node.setLeaf(true);
            return node;
        }
        else if(attributes.length == 0){
            node.setLabel(mcv(passengers));
            node.setLeaf(true);
            return node;
        }
        else{
            /*String bestAttribute = getBestSplit();
            int [] possibleValues = getPossibleValues();
            for(int pv : possibleValues){
                Node childNode = new Node();
                Condition condition = new Condition(pv);
                condition.setSuccessor(childNode);
                node.addCondition(condition);
                Set<Passenger> subset = createSubset();
                if(subset.isEmpty()){
                    childNode.setLeaf(true);
                    childNode.setLabel(mcv(passengers));
                    return childNode;
                }
                else{
                    String [] attr = removeAttribute
                    return train(childNode, subset, this.targetAttribute, );
                }
            }*/return null;
        }
    }

    private boolean allPositive(Set<Passenger> data, String attribute){
        Object [] dataArr = data.toArray();
        Passenger p;
        for (int i = 0; i < dataArr.length; i++){
            p = (Passenger) dataArr[i];
            if(p.getAttributeValue(attribute) == 0){
                return false;
            }
        }
        return true;
    }

    private boolean allNegative(Set<Passenger> data, String attribute){
        Object [] dataArr = data.toArray();
        Passenger p;
        for (int i = 0; i < dataArr.length; i++){
            p = (Passenger) dataArr[i];
            if(p.getAttributeValue(attribute) == 1){
                return false;
            }
        }
        return true;
    }

    // Mehrheitsentscheidung für denjenigen Attributwert des targetAttributes mit der Mehrzahl an Beispielen im Datensatz data
    private String mcv(Set<Passenger> data){
        Object[] dataArr = data.toArray();
        Map<Integer, Integer> valueCount = new HashMap<>();
        Passenger p;
        for(int i = 0; i < dataArr.length; i++){
            p = (Passenger) dataArr[i];
            valueCount.merge(p.getAttributeValue(this.targetAttribute), 1, Integer::sum);
        }
        int maxValue = Collections.max(valueCount.entrySet(), Map.Entry.comparingByValue()).getKey();
        if(maxValue == 0){
            return "not survived";
        }
        return "Survived";
    }

    private String getBestSplit(){
        return null;
    }

    public String classify(String dataPoint){

        return null;
    }
}
