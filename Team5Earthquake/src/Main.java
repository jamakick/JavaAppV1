import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static Scanner input;

	public static void main(String[] args) {
		input = new Scanner(System.in);
		System.out.println("Welcome to the searchquake command prompt. Type exit to leave, or help for commands.");
		ArrayList<Earthquake> earthquakes = EarthquakeCollection.getFileInformation();
		while(true) {
			String userInput = input.next();
			if(userInput.equals("exit")){
				System.out.println("goodbye!");
				System.exit(0);
			}
			if(userInput.equals("print")) {
				
				System.out.println(earthquakes);
			}
			
			if(userInput.equals("help")) {
				
				System.out.println("Commands:");
				System.out.println(" print - Prints out information for every earthquakes");
				System.out.println(" summary - Print out information summarizing all earthquakes");
				System.out.println(" searchByDate - Takes two dates and gives earthquakes between the two dates");
				System.out.println(" exit - Exit console");
			}
			
			if(userInput.equals("summary")) {
				
				System.out.println(EarthquakeCollection.arrayListToString(earthquakes));
			}
			
			if(userInput.equals("searchByDate")) {
				
				System.out.println("Print first date, press enter and enter the second date: (yyyy-mm-dd)");
				String first = input.next();
				String second = input.next();
				
				System.out.println(EarthquakeCollection.SearchByDate(first,second));
			}
			
			if(userInput.equals("searchByLocation")) {
				
				System.out.println("Enter first latitude and longitude, press enter and enter the second latitude and longitude");
				String lat1 = input.next();
				String long1 = input.next();
				String lat2 = input.next();
				String long2 = input.next();
				
			}
			
			if(userInput.equals("printByDepth")) {
				Collections.sort(earthquakes, Earthquake.EquDepthComparator);
				for(Earthquake eq: earthquakes){
					System.out.println(eq);
				   }
			}
			
			if(userInput.equals("printByMag")) {
				Collections.sort(earthquakes, Earthquake.EquMagComparator);
				for(Earthquake eq: earthquakes){
					System.out.println(eq);
				   }
			}
			
			if(userInput.equals("printByPlace")) {
				Collections.sort(earthquakes, Earthquake.EquLocComparator);
				for(Earthquake eq: earthquakes){
					System.out.println(eq);
				   }
			}
			//status checker not functional yet, need to change status from boolean
			if(userInput.equals("printByStatus")) {
				//Collections.sort(earthquakes, Earthquake.EquStatusComparator);
				for(Earthquake eq: earthquakes){
					System.out.println(eq);
				   }
			}
		}
	}
}
