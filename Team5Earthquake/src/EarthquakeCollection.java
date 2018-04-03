import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeCollection {
	
	//you can't override the to string of an array list apparently, I will just make a new function
//	@Override
//	public String toString() {
//		return "Number of Earthquakes: " + earthquakes.size();
//	}
	
	public static String arrayListToString(ArrayList<Earthquake> earthquakes) {
		return "Number of Earthquakes: " + earthquakes.size();
	}
	
	
	
	
	
//	public static ArrayList<Earthquake> SearchByLoc(String lat1, String long1, String lat2, String long2) {
//		
//		ArrayList<Earthquake> earthquakes = getFileInformation();
//		
//		ArrayList<Earthquake> locBetween = new ArrayList<Earthquake>();
//		
//		double latNum1 = Double.parseDouble(lat1);
//		double longNum1 = Double.parseDouble(long1);
//		double latNum2 = Double.parseDouble(lat2);
//		double longNum2 = Double.parseDouble(long2);
//		
//	}
	
	
	public static ArrayList<Earthquake> SearchByDate(String string1, String string2) {
		
		ArrayList<Earthquake> earthquakes = getFileInformation();
		
		ArrayList<Earthquake> datesBetween = new ArrayList<Earthquake>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date1 = new Date();
		Date date2 = new Date();
		
		try {
			date1 = sdf.parse(string1);
			date2 = sdf.parse(string2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < earthquakes.size(); i++) {
			
			String timeString = earthquakes.get(i).getTime().substring(0, 10);
			
			Date timeDate = new Date();
			
			try {
				timeDate = sdf.parse(timeString);;
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			if (timeDate.after(date1) && timeDate.before(date2))
				//System.out.println(timeDate + " is between " + date1 + " and " + date2);
				datesBetween.add(earthquakes.get(i));
			
		}
		
		return datesBetween;
	}
	
	
	public static ArrayList<Earthquake> getFileInformation(){
		
		String time;
		double latitude;
		double longitude;
		double depth;
		double mag;
		String magType;
		int nst;
		double gap;
		double dMin;
		double rms;
		String net;
		String id;
		String updated;
		String place;
		String type;
		double horError;
		double depthError;
		double magError;
		int magNst;
		boolean status;
		String locSource;
		String magSource;
		
        String strFile = "all_month.csv";

        BufferedReader br = null;

        String strLine = "";
        
        ArrayList<Earthquake> earthquakeList = new ArrayList<Earthquake>();

        try {
            br = new BufferedReader(new FileReader(strFile));

            //pull the headers out since they are the first row
            String headers = br.readLine();    		

            while ((strLine= br.readLine()) != null) {
            		//split by a quotation, this is the only way to identify every place
            		String[] row = strLine.split("\"");
            		
            		//take the other variables aside from place and put them into a string "variables"
            		String variables = row[0] + row[2];
            		//split our variables up by commas, aside from place
            		String[] vars = variables.split(",");
            		
            		//place variable stored into placeString
            		String placeString = row[1];
            		
            		//2018-02-27T14:40:10.010Z
            		if (vars[0].equals(""))
            			time = "No time given";
            		else
            			time = vars[0];
            		//38.7888333
            		if (vars[1].equals(""))
            			latitude = 0f;
            		else
            			latitude = Double.parseDouble(vars[1]);
            		//-122.8001667
            		if (vars[2].equals(""))
            			longitude = 0f;
            		else
            			longitude = Double.parseDouble(vars[2]);
            		//4.22
            		if (vars[3].equals(""))
            			depth = 0f;
            		else
            			depth = Double.parseDouble(vars[3]);
            		//0.3
            		if (vars[4].equals(""))
            			mag = 0f;
            		else
            			mag = Double.parseDouble(vars[4]);
            		//md
            		if (vars[5].equals(""))
            			magType = "null";
            		else
            			magType = vars[5];
            		//22	
            		if (vars[6].equals(""))
            			nst = 0;
            		else
            			nst = Integer.parseInt(vars[6]);
            		//132.0
            		if (vars[7].equals(""))
            			gap = 0f;
            		else
            			gap = Double.parseDouble(vars[7]);
            		//0.01173
            		if (vars[8].equals(""))
            			dMin = 0f;
            		else
            			dMin = Double.parseDouble(vars[8]);
            		//.02
            		if (vars[9].equals(""))
            			rms = 0f;
            		else
            			rms = Double.parseDouble(vars[9]);
            		//nc
            		if (vars[10].equals(""))
            			net = "null";
            		else
            			net = vars[10];
            		//nc72991180
            		if (vars[11].equals(""))
            			id = "null";
            		else
            			id = vars[11];
            		//2018-03-29T14:31:02.753Z
            		if (vars[12].equals(""))
            			updated = "null";
            		else
            			updated = vars[12];
            		//"4km WNW of The Geysers, CA"
            		if (placeString.equals(""))
            			place = "null";
            		else
            			place = placeString;
            		//earthquake
            		if (vars[14].equals(""))
            			type = "null";
            		else
            			type = vars[14];
            		//0.26
            		if (vars[15].equals(""))
            			horError = 0f;
            		else
            			horError = Double.parseDouble(vars[15]);
            		//0.52
            		if (vars[16].equals(""))
            			depthError = 0f;
            		else
            			depthError = Double.parseDouble(vars[16]);
            		//0.15
            		if (vars[17].equals(""))
            			magError = 0f;
            		else
            			magError = Double.parseDouble(vars[17]);
            		//3
            		if (vars[18].equals(""))
            			magNst = 0;
            		else
            			magNst = Integer.parseInt(vars[18]);
            		//false
            		if (vars[19].equals(""))
            			status = false;
            		else if (vars[19].equals("automatic"))
            			status = false;
            		else
            			status = true;
            		//nc
            		if (vars[20].equals(""))
            			locSource = "null";
            		else
            			locSource = vars[20];
            		//nc
            		if (vars[21].equals(""))
            			magSource = "null";
            		else
            			magSource = vars[21];
            	    
            		//create an earthquake with all the variable
            		Earthquake quake = new Earthquake(time, latitude, longitude, depth,  mag, magType,
            				nst, gap, dMin, rms, net, id, updated, place,
            				type, horError, depthError, magError, 
            				magNst, status, locSource, magSource);
            		earthquakeList.add(quake);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                    //System.out.println("Finished Parsing");
                 } catch (IOException e) {
                    e.printStackTrace();
                 }
            }
        }
        
        //prints all earthquake objects
//        for (Earthquake e:earthquakeList)
//        	System.out.println(e);
//        	System.out.println();
        	
        return earthquakeList;
    }
}
