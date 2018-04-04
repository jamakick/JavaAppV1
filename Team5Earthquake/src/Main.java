import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static Scanner input;

	public static void main(String[] args) {
		//create scanner
		input = new Scanner(System.in);

		System.out.println("Welcome to the searchquake command prompt. Type exit to leave, or help for commands.");
		//create our earthquake arraylist from our collection class
		ArrayList<Earthquake> earthquakes = EarthquakeCollection.getFileInformation();
		//while loop that runs our users commands until they break it
		while(true) {
			String userInput = input.next();
			//if the user types exit, the loop will end
			//we use equalsIgnoreCase so that the user can enter the commands in any capitalization
			if(userInput.equalsIgnoreCase("exit")){
				System.out.println("goodbye!");
				System.exit(0);
			}
			//if the user types print, it will print out the toString for every earthquake
			else if(userInput.equalsIgnoreCase("print")) {
				System.out.println(earthquakes);
			}
			//if the user types help, it will print out all of the available commands and what they do
			else if(userInput.equalsIgnoreCase("help")) {
				System.out.println("Commands:");
				System.out.println(" print - Prints out information for every earthquakes.");
				System.out.println(" summary - Print out information summarizing all earthquakes.");
				System.out.println(" searchByDate - Takes two dates and gives earthquakes between the two dates.");
				System.out.println(" searchByLocation - Takes two latitudes and longitudes and gives earthquakes between the two coordinates.");
				System.out.println(" searchByDepth - Takes two depths and gives earthquakes between them.");
				System.out.println(" searchByMag - Takes two magnitudes and gives earthquakes between them.");
				System.out.println(" searchByMagType - Takes a magnitude type and gives back earthquakes that match.");
				System.out.println(" searchByPlace - Takes a place and gives back earthquakes that match the location.");
				System.out.println(" searchByStatus - Takes a status and gives back earthquakes that match.");
				System.out.println(" printByDate");
				System.out.println(" printByDepth");
				System.out.println(" printByMag");
				System.out.println(" printByPlace");
				System.out.println(" printByStatus");
				System.out.println(" exit - Exit console.");
			}
			//if the user enters summary, it will print the arrayList's toString that summarizes some of the data
			else if(userInput.equalsIgnoreCase("summary")) {
				System.out.println(EarthquakeCollection.arrayListToString(earthquakes));
			}

			//searchByDate function that has them enter two dates
			else if(userInput.equalsIgnoreCase("searchByDate")) {
				System.out.println("Print first date, press enter and enter the second date: (yyyy-mm-dd) format");
				String first = input.next();
				String second = input.next();
				
				//if the dates don't make it null, it gives back the list
				if(!EarthquakeCollection.SearchByDate(first,second).isEmpty())
					System.out.println(EarthquakeCollection.SearchByDate(first,second));
				//otherwise it says what the correct format is
				else {
					System.out.println(EarthquakeCollection.SearchByDate(first,second));
					System.out.println("Format should be yyyy-mm-dd, yyyy-mm-dd");
				}
			}

			//searchByLocation, takes 4 coordinates, two lats and two longs
			else if(userInput.equalsIgnoreCase("searchByLocation")) {
				System.out.println("Enter first latitude and longitude, press enter and enter the second latitude and longitude (Lat1 Long1 Lat2 Long2) format");
				
				String lat1 = input.next();
				String long1 = input.next();
				String lat2 = input.next();
				String long2 = input.next();
				
				//input handling
				if(!EarthquakeCollection.SearchByLoc(lat1, long1, lat2, long2).isEmpty())
					System.out.println(EarthquakeCollection.SearchByLoc(lat1, long1, lat2, long2));
				else
					System.out.println("Format should be Lat1 Long1 Lat2 Long2");
			}

			//searchByDepth, takes two depths 
			else if(userInput.equalsIgnoreCase("searchByDepth")) {
				System.out.println("Print first depth, press enter and enter the second depth: (double)");
				String depth1 = input.next();
				String depth2 = input.next();
				
				//input handling
				if(!EarthquakeCollection.SearchByDepth(depth1,depth2).isEmpty())
					System.out.println(EarthquakeCollection.SearchByDepth(depth1, depth2));
				else
					System.out.println("Format should be a double, ex 14.23");
			}
			//searchByMag, takes two magnitudes
			else if(userInput.equalsIgnoreCase("searchByMag")) {
				System.out.println("Print first mag, press enter and enter the second mag: (double)");
				String mag1 = input.next();
				String mag2 = input.next();
				
				//input handling
				if(!EarthquakeCollection.SearchByMag(mag1,mag2).isEmpty())
					System.out.println(EarthquakeCollection.SearchByMag(mag1, mag2));
				else
					System.out.println("Format should be a double, ex: 0.83");
			}
			//searchByMagType, takes a magType and tries to match it
			else if(userInput.equalsIgnoreCase("searchByMagType")) {
				System.out.println("Print what magnitude type you would like to search for. (String)");
				String magType = input.next();
				
				//input handling
				if(!EarthquakeCollection.SearchByMagType(magType).isEmpty())
					System.out.println(EarthquakeCollection.SearchByMagType(magType));
				else
					System.out.println("Format should be a string, ex. ml");
			}
			//searchByPlace, takes a place/location and matches it
			else if(userInput.equalsIgnoreCase("searchByPlace")) {
				System.out.println("Print what place you would like to search for. (String)");
				//because we are using nextLine instead of next here, we need to do a blank nextLine in order to clear the current line.
				String blank = input.nextLine();
				String place = input.nextLine();
				
				//input handling
				if(!EarthquakeCollection.SearchByPlace(place).isEmpty())
					System.out.println(EarthquakeCollection.SearchByPlace(place));
				else
					System.out.println("Format should be a string, ex. South of the Fiji Islands");
			}
			//searchByStatus, takes a string status and matches it, reviewed or automatic
			else if(userInput.equalsIgnoreCase("searchByStatus")) {
				System.out.println("Print what status type you would like to search for. (String)");
				String status = input.next();
				
				//input handling
				if(!EarthquakeCollection.SearchByStatus(status).isEmpty())
					System.out.println(EarthquakeCollection.SearchByStatus(status));
				else
					System.out.println("Format should be a string, ex. reviewed");
			}
			
			else if(userInput.equalsIgnoreCase("printByDepth")) {
				Collections.sort(earthquakes, Earthquake.EquDepthComparator);
				for(Earthquake eq: earthquakes){
					System.out.println(eq);
				   }
			}
			
			else if(userInput.equalsIgnoreCase("printByMag")) {
				Collections.sort(earthquakes, Earthquake.EquMagComparator);
				for(Earthquake eq: earthquakes){
					System.out.println(eq);
				   }
			}
			
			else if(userInput.equalsIgnoreCase("printByPlace")) {
				Collections.sort(earthquakes, Earthquake.EquLocComparator);
				for(Earthquake eq: earthquakes){
					System.out.println(eq);
				   }
			}
			//status checker not functional yet, need to change status from boolean
			else if(userInput.equalsIgnoreCase("printByStatus")) {
//				Collections.sort(earthquakes, Earthquake.EquStatusComparator);
				for(Earthquake eq: earthquakes){
					System.out.println(eq.getStatus());
				   }
			}
			
			else if(userInput.equalsIgnoreCase("printByDate")) {
				Collections.sort(earthquakes, Earthquake.EquDateComparator);
				for(Earthquake eq: earthquakes){
					System.out.println(eq);
				   }
			}
			
			//if they enter anything that isn't one of these commands, it will tell them it is not a valid command
			else
				System.out.println(userInput + " is not a valid command. Type help for commands.");
		}
	}
}
