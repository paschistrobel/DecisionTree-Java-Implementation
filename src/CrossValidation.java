import models.DecisionTree;
import models.Passenger;
import java.util.HashSet;
import java.util.Set;

public class CrossValidation {

    private Set<Passenger> [] splitData;
    private final int NUMBER_OF_SPLITS;
    private String[] trainAttributes;
    private String targetAttribute;

    public CrossValidation(Set<Passenger> data, int numberOfSplits, String [] trainAttributes, String targetAttribute){
        this.splitData = splitData(data, numberOfSplits);
        this.NUMBER_OF_SPLITS = numberOfSplits;
        this.trainAttributes = trainAttributes;
        this.targetAttribute = targetAttribute;
    }

    private Set<Passenger>[] splitData(Set<Passenger> data, int numberSplits){
        Set<Passenger> [] splitSets = new Set[numberSplits];
        for(int i = 0; i < splitSets.length ; i++){
            splitSets[i] = new HashSet<>();
        }
        Object [] data_arr = data.toArray();
        Passenger p;
        int i = 0;
        for(Object o : data_arr){
            p = (Passenger) o;
            splitSets[i].add(p);
            i++;
            if(i >= numberSplits){
                i = 0;
            }
        }
        return splitSets;
    }

    public double crossValidate(){
        double precisions[] = new double[NUMBER_OF_SPLITS];
        Set<Passenger> trainData, testData;
        for(int i = 0; i < NUMBER_OF_SPLITS; i++){
            int correct = 0, incorrect = 0;
            // get train and test data
            testData = splitData[i];
            trainData = mergeAllExceptIndex(splitData, i);
            // create decision tree based on the train data
            DecisionTree decisionTree = new DecisionTree(targetAttribute, trainAttributes);
            decisionTree.train(trainData);
            decisionTree.print();
            for(Passenger p : testData){
                // decisionTree.classify: 0 = not survived, 1 = survived, 2 = can not classify data
                if(Integer.parseInt(decisionTree.classify(p)) == p.getAttributeValue(targetAttribute)) correct++;
                else incorrect++;
            }
            //System.out.println("Korrekt klassifiziert: " + correct + "\nInkorrekt klassifiziert: " + incorrect);
            precisions[i] = (double) correct / ((double) correct + (double) incorrect);
            //System.out.println("Präzision: " + precisions[i] + "\n\n");
        }
        double avgPrecision = average(precisions);
        System.out.println("\nDURCHSCHNITTLICHE PRÄZISION (CROSS-VALIDATION): " + avgPrecision);
        return avgPrecision;
    }

    private double average(double [] nums){
        double sum = 0.0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        return sum / nums.length;
    }

    // merges all sets from array except for the one at index idx
    private Set<Passenger> mergeAllExceptIndex(Set<Passenger> [] dataSplits, int idx){
        Set<Passenger> trainData = new HashSet<>();
        for(int i = 0; i < dataSplits.length; i++){
            if(i != idx){
                trainData.addAll(dataSplits[i]);
            }
        }
        return trainData;
    }
}
