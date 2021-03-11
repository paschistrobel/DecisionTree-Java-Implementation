package models;
import java.util.*;

/**
 * @author Pascal Strobel (außer classify() Methode)
 * */
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

    /*
     * Method for training the decision tree
     */
    public void train(Set<Passenger> data){
        possibleAttributeValues = getPossibleAttributeValues(data);
        long timeStart = System.currentTimeMillis();
        train(this.rootNode, data, this.targetAttribute, this.attributeList);
        System.out.println("Trainingsdauer: " + (System.currentTimeMillis() - timeStart) + " ms");
    }

    // Overload method
    public void train(Node node, Set<Passenger> passengers, String targetAttribute, String[] attributes){
        /**Abbruch-/ Endbedingungen*/
        if(allPositive(passengers, targetAttribute)){ // if all passengers from the set survived
            node.setLabel("1");
            node.setLeaf(true);
            return;
        }
        else if(allNegative(passengers, targetAttribute)){ // if all passengers from the set died
            node.setLabel("0");
            node.setLeaf(true);
            return;
        }
        else if(attributes.length == 0){
            node.setLabel(String.valueOf(mcv(passengers, targetAttribute)));
            node.setLeaf(true);
            return;
        }
        else{
            String bestAttribute = getBestSplitAttribute(passengers, attributes);
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
                    child.setLeaf(true);
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

    private String getBestSplitAttribute(Set<Passenger> data, String [] attributes){
        double [] gainResults = new double[attributes.length];
        // calculate information gain for every attribute (that is still available)
        for(int i = 0; i < attributes.length; i++){
            gainResults[i] = calcInformationGain(data, attributes[i]);
        }
        // return attribute with max information gain
        return attributes[getMaxPosition(gainResults)];
    }

    // returns the index of the item with max value of an array
    private int getMaxPosition(double[] arr){
        int idx = 0;
        double max = Double.MIN_VALUE;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > max){
                max = arr[i];
                idx = i;
            }
        }
        return idx;
    }

    // calculates the entropy for a set in regard to the target attribute
    private double calcEntropy(Set<Passenger> data, String targetAttribute){
        double entropy = 0.0;
        Set<Passenger> container;
        double p;
        for(int possValue : possibleAttributeValues.get(targetAttribute)){
            container = createSubset(data, targetAttribute, possValue);
            if(container.size() == 0){
                // if no examples for value v, than p(v) = 0 --> entropy = 0 and thus doesn't change
                continue;
            }
            p = (double) container.size() / (double) data.size();
            // entropy = - SUM (p(e) * log2(p(e)))
            entropy -= p * log2(p);
        }
        return entropy;
    }

    // calculates the information gain for a set
    private double calcInformationGain(Set<Passenger> data, String attribute){
        double sum = 0.0;
        for(int possValue : possibleAttributeValues.get(attribute)){
            // example set with value v for attribute attribute
            Set<Passenger> ev = createSubset(data, attribute, possValue);
            //      Gewichtung                                      Entropie von ev
            sum += ((double) ev.size() / (double) data.size()) * calcEntropy(ev, this.targetAttribute);
        }
        return calcEntropy(data, this.targetAttribute) - sum;
    }

    private double log2(double to_log){
        return (double)(Math.log(to_log)/Math.log(2.0));
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
        ArrayList<Integer> survivedValues = new ArrayList<>();
        survivedValues.add(0);
        survivedValues.add(1);
        possAttValues.put(Attribute.SURVIVED, survivedValues);
        return possAttValues;
    }


    /*
     * Print the trained decision tree
     */
    public void print(){
        Queue<Node> q = new LinkedList();
        Queue<Node> q2 = new LinkedList();
        q.add(rootNode);
        Node curr;
        Node br = new Node();
        br.setLabel(" | ");
        List<Condition> branches;
        while(!q.isEmpty() || !q2.isEmpty()){
            while(!q.isEmpty()){
                curr = q.poll();
                branches = curr.getConditions();
                System.out.print(" " + curr.getLabel());
                for(Condition branch : branches){
                    q2.add(branch.getSuccessor());
                    System.out.print(branch.getCompareValue());
                }
                if(branches.size() != 0) q2.add(br);
            }
            System.out.println();
            while(!q2.isEmpty()){
                q.add(q2.poll());
            }

        }
    }

    /**
     * Method for classifying data
     * @author Benedikt Zanker
     * */
    // classifies a passengers (if he/she survives or not)
    public String classify(Passenger passenger){
        Node curr = rootNode;
        List<Condition> branches;
        while(!curr.isLeaf()) {
            branches = curr.getConditions();
            int pass_val = passenger.getAttributeValue(curr.getLabel());
            Node next = null;
            for (Condition branch : branches) {
                if (branch.check(pass_val)) {
                    next = branch.getSuccessor();
                    break;
                }
            }
            if(next == null){
                return "2"; // can not classify data
            }
            else{
                curr = next;
            }
        }
        return curr.getLabel();
    }
}
