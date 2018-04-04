import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		//create scanner
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to the searchquake command prompt. Type exit to leave, or help for commands.");
		//create our earthquake arraylist from our collection class
		ArrayList<Earthquake> earthquakes = EarthquakeCollection.getFileInformation();
		//while loop that runs 
		while(true) {
			String userInput = input.next();
			if(userInput.equals("exit")){
				System.out.println("goodbye!");
				System.exit(0);
			}
			else if(userInput.equals("print")) {
				
				System.out.println(earthquakes);
			}
			
			else if(userInput.equals("help")) {
				
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
				System.out.println(" exit - Exit console.");
			}
			
			else if(userInput.equals("summary")) {
				

				System.out.println(EarthquakeCollection.arrayListToString(earthquakes));
			}
			
			else if(userInput.equals("searchByDate")) {
				
				System.out.println("Print first date, press enter and enter the second date: (yyyy-mm-dd) format");
				String first = input.next();
				String second = input.next();
				
				if(!EarthquakeCollection.SearchByDate(first,second).isEmpty())
					System.out.println(EarthquakeCollection.SearchByDate(first,second));
				else
					System.out.println("Format should be yyyy-mm-dd, yyyy-mm-dd");
			}
			
			else if(userInput.equals("searchByLocation")) {
				
				System.out.println("Enter first latitude and longitude, press enter and enter the second latitude and longitude (Lat1 Long1 Lat2 Long2) format");
				String lat1 = input.next();
				String long1 = input.next();
				String lat2 = input.next();
				String long2 = input.next();
				
				if(!EarthquakeCollection.SearchByLoc(lat1, long1, lat2, long2).isEmpty())
					System.out.println(EarthquakeCollection.SearchByLoc(lat1, long1, lat2, long2));
				else
					System.out.println("Format should be Lat1 Long1 Lat2 Long2");
			}
			
			else if(userInput.equals("searchByDepth")) {
				
				System.out.println("Print first depth, press enter and enter the second depth: (double)");
				String depth1 = input.next();
				String depth2 = input.next();
				
				if(!EarthquakeCollection.SearchByDepth(depth1,depth2).isEmpty())
					System.out.println(EarthquakeCollection.SearchByDepth(depth1, depth2));
				else
					System.out.println("Format should be a double, ex 14.23");
			}
			
			else if(userInput.equals("searchByMag")) {
				
				System.out.println("Print first mag, press enter and enter the second mag: (double)");
				String mag1 = input.next();
				String mag2 = input.next();
				
				if(!EarthquakeCollection.SearchByMag(mag1,mag2).isEmpty())
					System.out.println(EarthquakeCollection.SearchByMag(mag1, mag2));
				else
					System.out.println("Format should be a double, ex: 0.83");
				
			}
			
			else if(userInput.equals("searchByMagType")) {
				
				System.out.println("Print what magnitude type you would like to search for. (String)");
				String magType = input.next();
				
				if(!EarthquakeCollection.SearchByMagType(magType).isEmpty())
					System.out.println(EarthquakeCollection.SearchByMagType(magType));
				else
					System.out.println("Format should be a string, ex. ml");
			}
			
			else if(userInput.equals("searchByPlace")) {
				
				System.out.println("Print what place you would like to search for. (String)");
				String blank = input.nextLine();
				String place = input.nextLine();
				
				if(!EarthquakeCollection.SearchByPlace(place).isEmpty())
					System.out.println(EarthquakeCollection.SearchByPlace(place));
				else
					System.out.println("Format should be a string, ex. South of the Fiji Islands");
			}
			
			else if(userInput.equals("searchByStatus")) {
				
				System.out.println("Print what status type you would like to search for. (String)");
				String status = input.next();
				
				if(!EarthquakeCollection.SearchByStatus(status).isEmpty())
					System.out.println(EarthquakeCollection.SearchByStatus(status));
				else
					System.out.println("Format should be a string, ex. reviewed");
			}
			
			else
				System.out.println(userInput + " is not a valid command. Type help for commands.");
			
			
		}
	}
}
