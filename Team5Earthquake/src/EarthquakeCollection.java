import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeCollection {
	
//	You cannot override tostring on an array list, as shown below, so we create a new method called arrayListToString
//	@Override
//	public String toString() {
//		ArrayList<Earthquake> earthquakes = getFileInformation();
//		return "Number of Earthquakes: " + earthquakes.size();
//	}
	
	//This takes the earthquake arraylist as an argument and returns a string, our toString method for the earthquake collection
	public static String arrayListToString(ArrayList<Earthquake> earthquakes) {
		//create our date format for the date portion
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			//define earliest and latest date as a new date
			Date earliestDate = new Date();
			Date latestDate = new Date();
			
			//try statement that parses a very old date for early, and very young date for late.
			try {
				earliestDate = sdf.parse("2200-01-01");
				latestDate = sdf.parse("1900-01-01");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//assigning variables for summary
			//extreme values for these so ours replace it absolutely
			double largestLat = -2000f;
			double smallestLat = 2000f;
			double largestLong = -2000f;
			double smallestLong = 2000f;
			double totalDepth = 0f;
			double totalMag = 0f;
			//make an arrayList for our magtypes to sit in
			ArrayList<String> magTypes = new ArrayList<String>();
			int totalNst = 0;
			double totalGap = 0f;
			int earthquakeCount = 0;
			int miningCount = 0;
			int reviewCount = 0;
			int autoCount = 0;
			//For loop that loops through every earthquake in our collection
			for (int i = 0; i < earthquakes.size(); i++) {
				
				//establishes the earthquake object
				Earthquake earthquake = earthquakes.get(i);
				
				//get the substring of place that is the date 
				String timeString = earthquake.getTime().substring(0, 10);
				//establish a date object to parse into
				Date timeDate = new Date();
				
				//try statement that parses our string from place into a date, formatted with our sdf
				try {
					timeDate = sdf.parse(timeString);;
				} catch (ParseException e) {
					e.printStackTrace();
				}
				//checks if the date is earlier than earliest date or later than latest date
				if(timeDate.before(earliestDate))
					earliestDate = timeDate;
				if(timeDate.after(latestDate))
					latestDate = timeDate;
				
				//checks if the earthquakes latitude is smaller or larger than our smallest and largest lats
				if((earthquake.getLatitude()) <= smallestLat)
					smallestLat = earthquake.getLatitude();
				if((earthquake.getLatitude()) >= largestLat)
					largestLat = earthquake.getLatitude();
				
				//checks the same for longitude
				if((earthquake.getLongitude()) <= smallestLong)
					smallestLong = earthquake.getLongitude();
				if((earthquake.getLongitude()) >= largestLong)
					largestLong = earthquake.getLongitude();
				
				//add to our totaldepth and totalmag variables the depth and mag of the earthquake
				totalDepth += earthquake.getDepth();
				totalMag += earthquake.getMag();
				
				//obtains the string of magtype from the earthquake
				String magType = earthquake.getMagType();
				
				//checks if the magtype is already in our arrayList
				if(!magTypes.contains(magType))
					//if it is not in there, it then adds it
					magTypes.add(magType);
				
				//adds our nst and gap from the earthquake to their total
				totalNst += earthquake.getNst();
				totalGap += earthquake.getGap();
				
				//get our type and status from earthquake
				String type = earthquake.getType();
				String status = earthquake.getStatus();
				
				//check if the type is earthquake
				if(type.equals("earthquake"))
					//if it is earthquake, we add one to the count for it
					earthquakeCount++;
				//otherwise we add one to the mining blast count instead
				else
					miningCount++;
				
				//check if status is reviewed
				if(status.equals("reviewed"))
					//if it is, we add it to our reviewed count
					reviewCount++;
				//otherwise, it is automatic and we add to that count
				else
					autoCount++;
			//this is the end of our loop of running through the earthquake objects
			}
			
			//we check to see if null was included in our magType list since it is not a valid type
			if(magTypes.contains("null"))
				//if it exists, we remove it
				magTypes.remove("null");
			
			//calculate the averages for our total variables, dviding by the earthquake list's size
			double avgDepth = totalDepth / earthquakes.size();
			double avgMag = totalMag / earthquakes.size();
			double avgNst = totalNst / earthquakes.size();
			double avgGap = totalGap / earthquakes.size();
			
			//build our summary string using our variables defined above
			String returnString = "Summary of Earthquake Collection: ";
			returnString += "\n---------------------------------------------------------------------------------------------------------------------";
			returnString += "\n Number of Earthquakes: " + earthquakes.size();
			returnString += "\n Earliest Date: " + sdf.format(earliestDate) + " Latest Date: " + sdf.format(latestDate);
			returnString += "\n Latitudes range from " + smallestLat + " to " + largestLat;
			returnString += "\n Longitudes range from " + smallestLong + " to " + largestLong;
			//we use string.format here in order to limit the decimal places on our doubles
			returnString += "\n Average Depth: " + String.format("%.3f", avgDepth);
			returnString += "\n Average Magnitude: " + String.format("%.3f", avgMag) + "\n Magnitude Types: ";
			//for loop adds each magtype from the magtype arraylist to our returnString
			for (String s:magTypes)
				returnString += s + " ";
			returnString += "\n Average Number of Stations Contributing: " + avgNst;
			returnString += "\n Average Gap Between Stations: " + String.format("%.3f", avgGap);
			returnString += "\n Amount of Earthquakes: " + earthquakeCount + " Amount of Mining Blasts: " + miningCount;
			returnString += "\n Amount of Earthquakes reviewed: " + reviewCount + " Amount of Earthquakes automatically entered: " + autoCount;
			returnString += "\n---------------------------------------------------------------------------------------------------------------------";
			
			//return this string as our output
			return returnString;
		}
		
	//takes the string status as an argument, searches for it in the earthquakes, and outputs a list of all the ones that match
	public static ArrayList<Earthquake> SearchByStatus(String status) {
		
		//get our earthquake collection 
		ArrayList<Earthquake> earthquakes = getFileInformation();
		//create new list for our objects that match the status
		ArrayList<Earthquake> statusMatch = new ArrayList<Earthquake>();
		
			//loop through every earthquake in our collection
			for (int i = 0; i < earthquakes.size(); i++) {
				//get the status from the earthquake
				String quakePlace = earthquakes.get(i).getStatus();
				//checks if the earthquakes status matches the argument
				if(quakePlace.equals(status)) {
						//if it does, it adds that earthquake to our status list
						statusMatch.add(earthquakes.get(i));
				}
			}
		//return the list of earthquakes that match our status
		return statusMatch;
	}
	//takes string place as argument, matches the place in earthquakes and outputs a list of matching earthquakes
	public static ArrayList<Earthquake> SearchByPlace(String place) {
		
		//define our arrayLists
		ArrayList<Earthquake> earthquakes = getFileInformation();
		ArrayList<Earthquake> placeMatch = new ArrayList<Earthquake>();

		//loop earthquakes
		for (int i = 0; i < earthquakes.size(); i++) {
			//get place
			String quakePlace = earthquakes.get(i).getPlace();
			//if earthquake place matches our arg
			if(quakePlace.equals(place)) {
					//adds to our list
					placeMatch.add(earthquakes.get(i));
			}
		}
		//return our list of earthquake objects that match the place
		return placeMatch;
	}
	//string magType arg, match the magtypes and output list of matching quakes
	public static ArrayList<Earthquake> SearchByMagType(String magType) {
		
		//define our arraylists
		ArrayList<Earthquake> earthquakes = getFileInformation();
		ArrayList<Earthquake> magTypeMatch = new ArrayList<Earthquake>();

		//loop earthquakes
		for (int i = 0; i < earthquakes.size(); i++) {
			//get our magtype
			String quakeMagType = earthquakes.get(i).getMagType();
			//if our quake magtype matches our arg
			if(quakeMagType.equals(magType)) {
					//we add it to our matched list
					magTypeMatch.add(earthquakes.get(i));
			}
		}
		//return list of all earthquakes that match the magtype given
		return magTypeMatch;
	}
	//takes two magnitudes as arguments, finds any that are in between them, and returns an array list of matching quakes
	public static ArrayList<Earthquake> SearchByMag(String mag1, String mag2) {
		
		//define our arraylists
		ArrayList<Earthquake> earthquakes = getFileInformation();
		ArrayList<Earthquake> magBetween = new ArrayList<Earthquake>();
		
		//define our two mag numbers so we can parse them
		double magNum1 = 0f;
		double magNum2 = 0f;
		
		//try statement that parses from a string to a double
		try {
			magNum1 = Double.parseDouble(mag1);
			magNum2 = Double.parseDouble(mag2);
		} catch(Exception e) {
			//if there is an error, i.e. it cannot be a double, we return a statement that it is invalid
			System.out.println("Those are not valid magnitudes");
		}

		//loop earthquakes
		for (int i = 0; i < earthquakes.size(); i++) {
			//get our earthquake's magnitude
			double quakeMag = earthquakes.get(i).getMag();
			//check if the quakemag is greater than the first one and less than the second, meaning its in the middle
			if(quakeMag >= magNum1 && quakeMag <= magNum2) {
					//add this quake to our matched list
					magBetween.add(earthquakes.get(i));
			}
		}
		//return our earthquake list that matches our magnitude range
		return magBetween;
	}
	//takes two depths as a range, matches them, and gives back an arraylist of matching quakes
	public static ArrayList<Earthquake> SearchByDepth(String depth1, String depth2) {
		
		//define our arraylists
		ArrayList<Earthquake> earthquakes = getFileInformation();
		ArrayList<Earthquake> depthBetween = new ArrayList<Earthquake>();
		
		//define our depths
		double depthNum1 = 0f;
		double depthNum2 = 0f;
		
		//parse our depths
		try {
			depthNum1 = Double.parseDouble(depth1);
			depthNum2 = Double.parseDouble(depth2);
		} catch(Exception e) {
			//if the depths give an error, we tell them they aren't valid
			System.out.println("Those are not valid depths");
		}

		//loop earthquakes
		for (int i = 0; i < earthquakes.size(); i++) {
			//get our earthquake depth
			double quakeDepth = earthquakes.get(i).getDepth();
			//if the depth is in between our two args
			if(quakeDepth >= depthNum1 && quakeDepth <= depthNum2) {
					//it gets added to our list
					depthBetween.add(earthquakes.get(i));
			}
		}
		//return our list of depths in between our range
		return depthBetween;
	}
	//takes 4 coordinates, two lats and two longs, searches in between them all and gives back a list of the quakes in between them
	public static ArrayList<Earthquake> SearchByLoc(String lat1, String long1, String lat2, String long2) {
		
		//define our arraylists
		ArrayList<Earthquake> earthquakes = getFileInformation();
		ArrayList<Earthquake> locBetween = new ArrayList<Earthquake>();
		
		//define our lats and longs
		double latNum1 = 0f;
		double longNum1 = 0f;
		double latNum2 = 0f;
		double longNum2 = 0f;
		
		//parse our lats and longs
		try {
			latNum1 = Double.parseDouble(lat1);
			longNum1 = Double.parseDouble(long1);
			latNum2 = Double.parseDouble(lat2);
			longNum2 = Double.parseDouble(long2);
		} catch(Exception e) {
			//catch our errors and print that they are not valid
			System.out.println("Those aren't valid coordinates.");
		}
		
		//loop earthquakes
		for (int i = 0; i < earthquakes.size(); i++) {
			
			//get our lat and long from our quake
			double quakeLat = earthquakes.get(i).getLatitude();
			double quakeLong = earthquakes.get(i).getLongitude();
			
			//nested if statement
			//first we check if our earthquakes lat is in between our two latitude args
			if(quakeLat >= latNum1 && quakeLat <= latNum2) {
				//we then also check if the earthquake long is in between our two longitude args
				if(quakeLong <= longNum1 && quakeLong >= longNum2) {
					//if both of these are true, we add it to our list
					locBetween.add(earthquakes.get(i));
				}
			}
		}
		//return the earthquakes that are inbetween our coords
		return locBetween;
	}
	//takes two strings formatted as dates, searches for quakes in between them, and returns an arraylist of those quakes
	public static ArrayList<Earthquake> SearchByDate(String string1, String string2) {
		
		//define our arraylists
		ArrayList<Earthquake> earthquakes = getFileInformation();
		ArrayList<Earthquake> datesBetween = new ArrayList<Earthquake>();
		
		//define our simpledateformat that we can use to format and parse our dates and strings back and forth
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		//define our two dates
		Date date1 = new Date();
		Date date2 = new Date();
		
		//parse our dates
		try {
			date1 = sdf.parse(string1);
			date2 = sdf.parse(string2);
		} catch (ParseException e) {
			//if our dates are incorrect, we tell them they aren't valid dates
			System.out.println("Those are not valid dates");
		}
		
		//loop earthquakes
		for (int i = 0; i < earthquakes.size(); i++) {
			//get the substring of our Time from earthquake, this matches our simpledateformat
			String timeString = earthquakes.get(i).getTime().substring(0, 10);
			
			//define our earthquake date
			Date timeDate = new Date();
			
			//parse our earthquake date
			try {
				timeDate = sdf.parse(timeString);;
			} catch (ParseException e) {
				//if we get an error here, we print the stacktrace
				e.printStackTrace();
			}
			//we check to see if the earthquakes date is after the first date, but before the second date
			if (timeDate.after(date1) && timeDate.before(date2))
				//if it is, we add it to our list
				datesBetween.add(earthquakes.get(i));	
		}
		//return our list of earthquakes in between our two dates
		return datesBetween;
	}
	
	//function that takes our csv file, and creates an arraylist of earthquake objects
	public static ArrayList<Earthquake> getFileInformation(){
		
		//define our variables for the earthquake object
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
		
		//file where the earthquake data is stored
        String strFile = "all_month.csv";

        //using bufferedreader to read in the lines
        BufferedReader br = null;

        //define our line that we are reading
        String strLine = "";
        
        //define our earthquake list that will hold all of our earthquakes
        ArrayList<Earthquake> earthquakeList = new ArrayList<Earthquake>();

        //try statement that reads our file
        try {
        	//opens the buffer reader on our csv file
            br = new BufferedReader(new FileReader(strFile));

            //pull the headers out since they are the first row
            @SuppressWarnings("unused")
			String headers = br.readLine();    		

            //while there are still lines in our reader, a.k.a still rows of data
            while ((strLine= br.readLine()) != null) {
            		//split by a quotation, this is the only way to identify every place
            		String[] row = strLine.split("\"");
            		
            		//take the other variables aside from place and put them into a string "variables"
            		String variables = row[0] + row[2];
            		//split our variables up by commas, aside from place
            		String[] vars = variables.split(",");
            		
            		//place variable stored into placeString
            		String placeString = row[1];
            		
            		//below handles all the null fields in the csv file for our variables
            		
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
            		
            		//once we have all the variables, with defaults for those that were null
            	    
            		//create an earthquake with all the variable
            		Earthquake quake = new Earthquake(time, latitude, longitude, depth,  mag, magType,
            				nst, gap, dMin, rms, net, id, updated, place,
            				type, horError, depthError, magError, 
            				magNst, status, locSource, magSource);
            		earthquakeList.add(quake);
            }
          //catch our errors
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                	//close our reader
                    br.close();
                    //test code that was used to prove the code stopped running
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
        	
        //return our arraylist of earthquake objects that we call in other methods
        return earthquakeList;
    }
}
