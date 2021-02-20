package models;
import java.util.*;

public class DecisionTree {

    private final String targetAttribute;
    private Node rootNode;
    private String [] attributeList;
    private Map<String, ArrayList<Integer>> possibleAttributeValues;

    public DecisionTree(String targetAttribute, String [] attributeList){
        this.rootNode = new Node();
        // attribute that the model is trained for to predict the value --> SURVIVED in our case
        this.targetAttribute = targetAttribute;
        // List with attributes that the data should be trained on
        this.attributeList = attributeList;
        // Map that contains all possible Values for each attribute
    }

    /**
     * Methods for training the decision tree
     * */
    public void train(Set<Passenger> data){
        possibleAttributeValues = getPossibleAttributeValues(data);
        long timeStart = System.currentTimeMillis();
        train(this.rootNode, data, this.targetAttribute, this.attributeList);
        System.out.println("Trainingsdauer: " + (System.currentTimeMillis() - timeStart) + " ms");
    }

    // Overload method
    public void train(Node node, Set<Passenger> passengers, String targetAttribute, String[] attributes){
        //System.out.println("durchlauf");
        if(allPositive(passengers, targetAttribute)){ // if all passengers from the set survived
            node.setLabel("1");
            node.setLeaf(true);
        }
        else if(allNegative(passengers, targetAttribute)){ // if all passengers from the set died
            node.setLabel("0");
            node.setLeaf(true);
        }
        else if(attributes.length == 0){
            node.setLabel(String.valueOf(mcv(passengers, targetAttribute)));
            node.setLeaf(true);
        }
        else{
            //String bestAttribute = getBestSplit(); // TODO: Methode implementieren
            String bestAttribute = attributes[0];
            node.setLabel(bestAttribute);
            ArrayList<Integer> possibleValues = possibleAttributeValues.get(bestAttribute);
            for(int pv : possibleValues){
                Condition c = new Condition(pv); // create new condition with condition value for each possible class value
                Node child = new Node();
                c.setSuccessor(child); // create new node that is connected to that condition
                node.addCondition(c);
                Set<Passenger> subsetPV = createSubset(passengers, bestAttribute, pv);
                if(subsetPV.isEmpty()){
                    child.setLabel(String.valueOf(mcv(passengers, targetAttribute)));
                }
                else{
                    String [] remainingAttributes = removeAttributeFrom(bestAttribute, attributes);
                    train(child, subsetPV, targetAttribute, remainingAttributes);
                }
            }
        }
    }

    // checks if all all examples are positive/ survived
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

    // checks if all all examples are negative/ died
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

    // returns the most common value from a the column "attribute" of a data set "data"
    private int mcv(Set<Passenger> data, String attribute){
        Object[] dataArr = data.toArray();
        Map<Integer, Integer> valueCount = new HashMap<>();
        Passenger p;
        for(int i = 0; i < dataArr.length; i++){
            p = (Passenger) dataArr[i];
            valueCount.merge(p.getAttributeValue(attribute), 1, Integer::sum);
        }
        return Collections.max(valueCount.entrySet(), Map.Entry.comparingByValue()).getKey(); // = most common value
    }

    private String getBestSplit(){
        return null;
    }

    private int [] getPossibleValues(){
        return null;
    }

    // creates a subset from a set with all Elements that have value as their attribute value
    private Set<Passenger> createSubset(Set<Passenger> prevSet, String attribute, int value){
        Set<Passenger> subset = new HashSet<>();
        Object[] dataArr = prevSet.toArray();
        Passenger p;
        for(Object o : dataArr){
            p = (Passenger) o;
            if(p.getAttributeValue(attribute) == value){
                subset.add(p);
            }
        }
        return subset;
    }

    private String[] removeAttributeFrom(String attribute, String [] attributes){
        try{
            String [] res = new String[attributes.length-1];
            int j = 0;
            for(int i = 0; i < attributes.length; i++){
                if(attributes[i].equals(attribute)){
                    continue;
                }
                res[j] = attributes[i];
                j++;
            }
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private HashMap<String, ArrayList<Integer>> getPossibleAttributeValues(Set<Passenger> data){
        HashMap<String, ArrayList<Integer>> possAttValues = new HashMap<>();
        for(String attribute : attributeList){
            possAttValues.put(attribute, new ArrayList<>());
        }
        Object [] dataArr = data.toArray();
        Passenger p;
        for(Object o : dataArr){
            p = (Passenger) o;
            for(String attribute : attributeList){
                if(possAttValues.get(attribute).contains(p.getAttributeValue(attribute))){
                    continue;
                }
                possAttValues.get(attribute).add(p.getAttributeValue(attribute));
            }
        }
        return possAttValues;
    }


    /**
     * Print the trained decision tree
     * */
    private void print(){

    }



    /**
     * Methods for classifying data
     * */
    // classifies a passengers (if he/she survives or not)
    public String classify(Passenger passenger){
        return null;
    }
}
