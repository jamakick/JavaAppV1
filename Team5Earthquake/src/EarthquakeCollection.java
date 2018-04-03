import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeCollection {
	
//	You cannot override tostring on an array list
//	@Override
//	public String toString() {
//		ArrayList<Earthquake> earthquakes = getFileInformation();
//		return "Number of Earthquakes: " + earthquakes.size();
//	}
	
	public static String arrayListToString(ArrayList<Earthquake> earthquakes) {
		return "Number of Earthquakes: " + earthquakes.size();
	}
	
	public static ArrayList<Earthquake> SearchByStatus(String status) {
		
		ArrayList<Earthquake> earthquakes = getFileInformation();
		
		ArrayList<Earthquake> statusMatch = new ArrayList<Earthquake>();
		
			for (int i = 0; i < earthquakes.size(); i++) {
				
				String quakePlace = earthquakes.get(i).getStatus();
				
				if(quakePlace.equals(status)) {
						statusMatch.add(earthquakes.get(i));
				}
			}
		return statusMatch;
	}
	
	public static ArrayList<Earthquake> SearchByPlace(String place) {
		
		ArrayList<Earthquake> earthquakes = getFileInformation();
		
		ArrayList<Earthquake> placeMatch = new ArrayList<Earthquake>();

		for (int i = 0; i < earthquakes.size(); i++) {
			
			String quakePlace = earthquakes.get(i).getPlace();
			
			if(quakePlace.equals(place)) {
					placeMatch.add(earthquakes.get(i));
			}
		}
		return placeMatch;
	}
	
	public static ArrayList<Earthquake> SearchByMagType(String magType) {
		
		ArrayList<Earthquake> earthquakes = getFileInformation();
		
		ArrayList<Earthquake> magTypeMatch = new ArrayList<Earthquake>();

		for (int i = 0; i < earthquakes.size(); i++) {
			
			String quakeMagType = earthquakes.get(i).getMagType();
			
			if(quakeMagType.equals(magType)) {
					magTypeMatch.add(earthquakes.get(i));
			}
		}
		return magTypeMatch;
	}
	
	public static ArrayList<Earthquake> SearchByMag(String mag1, String mag2) {
		
		ArrayList<Earthquake> earthquakes = getFileInformation();
		
		ArrayList<Earthquake> magBetween = new ArrayList<Earthquake>();
		
		double magNum1 = Double.parseDouble(mag1);
		double magNum2 = Double.parseDouble(mag2);

		for (int i = 0; i < earthquakes.size(); i++) {
			
			double quakeMag = earthquakes.get(i).getMag();
			
			if(quakeMag >= magNum1 && quakeMag <= magNum2) {
					magBetween.add(earthquakes.get(i));
			}
		}
		return magBetween;
	}
	
	public static ArrayList<Earthquake> SearchByDepth(String depth1, String depth2) {
		
		ArrayList<Earthquake> earthquakes = getFileInformation();
		
		ArrayList<Earthquake> depthBetween = new ArrayList<Earthquake>();
		
		double depthNum1 = Double.parseDouble(depth1);
		double depthNum2 = Double.parseDouble(depth2);

		for (int i = 0; i < earthquakes.size(); i++) {
			
			double quakeDepth = earthquakes.get(i).getDepth();
			
			if(quakeDepth >= depthNum1 && quakeDepth <= depthNum2) {
					depthBetween.add(earthquakes.get(i));
			}
		}
		return depthBetween;
	}
		
	public static ArrayList<Earthquake> SearchByLoc(String lat1, String long1, String lat2, String long2) {
		
		ArrayList<Earthquake> earthquakes = getFileInformation();
		
		ArrayList<Earthquake> locBetween = new ArrayList<Earthquake>();
		
		double latNum1 = Double.parseDouble(lat1);
		double longNum1 = Double.parseDouble(long1);
		double latNum2 = Double.parseDouble(lat2);
		double longNum2 = Double.parseDouble(long2);
		
		for (int i = 0; i < earthquakes.size(); i++) {
			
			double quakeLat = earthquakes.get(i).getLatitude();
			double quakeLong = earthquakes.get(i).getLongitude();
			
			if(quakeLat >= latNum1 && quakeLat <= latNum2) {
				if(quakeLong <= longNum1 && quakeLong >= longNum2) {
					locBetween.add(earthquakes.get(i));
				}
			}
		}
		return locBetween;
	}
	
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
		String status;
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
            		//automatic
            		if (vars[19].equals(""))
            			status = "null";
            		else
            			status = vars[19];
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
