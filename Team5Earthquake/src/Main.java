import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to the searchquake command prompt. Type exit to leave.");
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
				
				System.out.println(earthquakes);
			}
			
			if(userInput.equals("summary")) {
				
				System.out.println(EarthquakeCollection.arrayListToString(earthquakes));
			}
			
			if(userInput.equals("printByDate")) {
				
				System.out.println("Print dates one line each");
				String first = input.next();
				String second = input.next();
				
				System.out.println(EarthquakeCollection.SearchByDate(first,second));
			}
			
			
		}
	}
}
