import models.Passenger;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
Helper class for reading data from the csv files
*/
public class CSV_Helper {
    public static Set<Passenger> readTrainData(String filePath){
        Set<Passenger> trainData = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // remove first heading line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                try{
                    Passenger p = new Passenger(values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8], values[9]);
                    trainData.add(p);
                }catch(IndexOutOfBoundsException e){
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return trainData;
    }
}
