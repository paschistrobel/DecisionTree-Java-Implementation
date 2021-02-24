import com.sun.media.sound.SF2GlobalRegion;
import models.Passenger;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Adrian Sterr
 * Helper class for reading data from the csv files
 * */
public class CSV_Helper {
    public static Set<Passenger> readData(String filePath){
        Set<Passenger> data = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // remove first heading line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                try{
                    Passenger p = new Passenger(values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8], values[9]);
                    data.add(p);
                }catch(IndexOutOfBoundsException e){
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    // splits is the number of data sets we get as a result
    public static Set<Passenger> [] splitData(Set<Passenger> data, int splits){
        int splitSize = data.size() / splits;
        Set<Passenger> [] splitSets = new Set[splits];
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
            if(i >= splits){
                i = 0;
            }
        }
        return splitSets;
    }

    public static Set<Passenger> mergeData(Set<Passenger> [] splitData, int testDataPosition){
        Set<Passenger> trainData = new HashSet<>();
        for(int i = 0; i < splitData.length; i++){
            if(i != testDataPosition){
                trainData.addAll(splitData[i]);
            }
        }
        return trainData;
    }

    public static void printData(Set<Passenger> trainData){
        System.out.println("Anzahl Passagiere gesamt: " + trainData.size());
        Object [] d = trainData.toArray();
        Passenger p;
        for(int i = 0; i < trainData.size(); i++){
            p = (Passenger) d[i];
            System.out.println("id: " + p. getID() +
                    "\tsurvived: " + p.getSurvived() +
                    "\tpclass: " + p.getpClass()+
                    "\ttitle: " + p.getTitle() +
                    "\tsex: " + p.getSex()+
                    "\tageGroup: " + p.getAgeGroup() +
                    "\tSibsp: " + p.getSibSp()+
                    "\tParch: " + p.getParch() +
                    "\tFare: " + p.getFare()+
                    "\tEmbarked: " + p.getEmbarked());
        }
    }
}
