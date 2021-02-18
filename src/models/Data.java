package models;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
Helper class for reading data from the csv files
*/
public class Data {

    private Set<Passenger> trainData;
    private Set<Passenger> testData;


    public Data(){
        this.trainData = new HashSet<>();
        this.testData = new HashSet<>();
    }

    public void readTrainData(Path dataPath){
        /*
        for each line in csv file (außer erste Zeile):
            Passenger p = new Passenger(Werte aus der Zeile einlesen);
            trainData.add(p);
         */
    }
}
