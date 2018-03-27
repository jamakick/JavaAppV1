package application;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class EarthquakeCollection {
	
	public static void getFileInformation(){
        String strFile = "all_month.csv";
        BufferedReader br = null;
        String strLine = "";
        String cvsSplitBy = ",";
        
        ArrayList<Earthquake> earthquakeList = new ArrayList<Earthquake>();
        
        try {
            br = new BufferedReader(new FileReader(strFile));                
            while ((strLine= br.readLine()) != null) {
            		String[] row = strLine.split(cvsSplitBy);
            		Earthquake quake = new Earthquake(row[0], Double.parseDouble(row[1]), Double.parseDouble(row[2]), Double.parseDouble(row[3]), 
            				Double.parseDouble(row[4]), row[5], Integer.parseInt(row[6]), Double.parseDouble(row[7]),
            				Double.parseDouble(row[8]), Double.parseDouble(row[9]), row[10], row[11], row[12], row[13], row[14], Double.parseDouble(row[15]),
            				Double.parseDouble(row[16]), Double.parseDouble(row[17]), Integer.parseInt(row[18]),
            				Boolean.parseBoolean(row[19]), row[20], row[21]);
            		earthquakeList.add(quake);
            		System.out.println("made quake");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                    System.out.print("Didn't work");
                 } catch (IOException e) {
                    e.printStackTrace();
                 }
            }
        }
        
        for (Earthquake e:earthquakeList)
        	System.out.println(e);
    }
}
