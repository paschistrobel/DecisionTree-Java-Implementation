package models;
import java.util.*;

public class DecisionTree {

    private Node rootNode;
    private final String targetAttribute; // target attribute is the attribute we want to predict when classifying
    private String [] trainAttributes; // attributes by which the tree classifies data
    private Map<String, ArrayList<Integer>> possibleAttributeValues; // values that each attribute can have

    public DecisionTree(String targetAttribute, String [] trainAttributes){
        this.rootNode = new Node();
        // attribute that the model is trained for to predict the value --> SURVIVED in our case
        this.targetAttribute = targetAttribute;
        // List with attributes that the data should be trained on
        this.trainAttributes = trainAttributes;
    }

    /*
     * Method for training the decision tree
     */
    public void train(Set<Passenger> data){
        possibleAttributeValues = getPossibleAttributeValues(data);
        long timeStart = System.currentTimeMillis();
        train(this.rootNode, data, this.trainAttributes);
        long duration = System.currentTimeMillis() - timeStart;
        // System.out.println("Duration of training: " + duration + " ms");
    }

    // Overload method
    public void train(Node node, Set<Passenger> passengers, String[] attributes){
        /**
        if(isHomogeneous(passengers, targetAttribute)){
            //node.setLabel(getValue for targetAttribute);
            node.setLeaf(true);
            return;
        }
         */
        if(allPositive(passengers, this.targetAttribute)){ // if all passengers from the set survived
            node.setLabel("1");
            node.setLeaf(true);
            return;
        }
        else if(allNegative(passengers, this.targetAttribute)){ // if all passengers from the set died
            node.setLabel("0");
            node.setLeaf(true);
            return;
        }
        else if(attributes.length == 0){
            node.setLabel(String.valueOf(mcv(passengers, this.targetAttribute)));
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
                    child.setLabel(String.valueOf(mcv(passengers, this.targetAttribute)));
                    child.setLeaf(true);
                }
                else{
                    String [] remainingAttributes = removeAttribute(bestAttribute, attributes);
                    train(child, subsetPV, remainingAttributes);
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

    // checks if all data is homogeneous regarding in terms of their values for attribute attribute
    private boolean isHomogeneous(Set<Passenger> data, String attribute){
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
        double postEntropy = 0.0; // entropy that will emerge when splitting on attribute attribute
        for(int possValue : possibleAttributeValues.get(attribute)){
            // subset for the data with value v for attribute attribute
            Set<Passenger> subset_v = createSubset(data, attribute, possValue);
            double weighting = (double) subset_v.size() / (double) data.size();
            double subsetEntropy = calcEntropy(subset_v, this.targetAttribute);
            postEntropy += weighting * subsetEntropy;
        }
        double preEntropy = calcEntropy(data, this.targetAttribute); // entropy before splitting on attribute attribute
        return preEntropy - postEntropy;
    }

    private double log2(double to_log){
        return Math.log(to_log)/Math.log(2.0);
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

    private String[] removeAttribute(String attribute, String [] attributes){
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
        for(String attribute : trainAttributes){
            possAttValues.put(attribute, new ArrayList<>());
        }
        Object [] dataArr = data.toArray();
        Passenger p;
        for(Object o : dataArr){
            p = (Passenger) o;
            for(String attribute : trainAttributes){
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

    // classifies a single data point
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
