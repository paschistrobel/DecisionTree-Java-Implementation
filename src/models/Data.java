package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
Helper class for reading data from the csv files
*/
public class Data {

    private Set<Passenger> trainData;
    private Set<Passenger> testData;

    private String pathToTrainData = "src\\data\\train.csv";
    private String pathToTestData = "src\\data\\test.csv";


    public Data(){
        this.trainData = new HashSet<>();
        this.testData = new HashSet<>();
    }

    public void readTrainData(/*Path dataPath*/) throws IOException {
        /*
        for each line in csv file (außer erste Zeile):
            Passenger p = new Passenger(Werte aus der Zeile einlesen);
            trainData.add(p);
         */

        BufferedReader csvReader = new BufferedReader(new FileReader(pathToTrainData));
        csvReader.readLine(); // skip first line
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(";");
            System.out.print(data[0]);
            Passenger p = new Passenger(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9]);
            trainData.add(p);
        }
        csvReader.close();
        System.out.print(trainData);
    }
}
