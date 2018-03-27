//package application;
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class dataIO {
//	public static void getFileInformation(){
//        String strFile = "all_month.csv";
//        BufferedReader br = null;
//        String strLine = "";
//        String cvsSplitBy = ",";
//        ArrayList<Earthquake> earthquakes = new ArrayList<Earthquake>();
//        try {
//            br = new BufferedReader(new FileReader(strFile));                
//            while ((strLine= br.readLine()) != null) {
//            		String[] row = strLine.split(cvsSplitBy);
//            		Earthquake quake = new Earthquake();
//            		quake.setTime(row[0]);
//            		System.out.println(row[0]);
//            		quake.setLatitude(Double.parseDouble(row[1]));
//            		quake.setLongitude(Double.parseDouble(row[2]));
//            		quake.setDepth(Double.parseDouble(row[3]));
//            		quake.setMag(Double.parseDouble(row[4]));
//            		quake.setMagType(row[5]);
//            		quake.setNst(Integer.parseInt(row[6]));
//            		quake.setGap(Double.parseDouble(row[7]));
//            		quake.setdMin(Double.parseDouble(row[8]));
//            		quake.setRms(Double.parseDouble(row[9]));
//            		quake.setNet(row[10]);
//            		earthquakes.add(quake);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                 } catch (IOException e) {
//                    e.printStackTrace();
//                 }
//            }
//        }
//    }
//}
