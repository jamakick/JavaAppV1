package application;

import java.util.ArrayList;
import java.util.Scanner;

public class commandLine {
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to the searchquake command prompt. Type exit to leave.");
		ArrayList<Earthquake> quakes = EarthquakeCollection.getList();
		while(true) {
			String userInput = input.next();
			if(userInput.equals("exit")){
				System.out.println("goodbye!");
				System.exit(0);
			}
			if(userInput.equals("printAll")) {
				
				System.out.println("printing all...");
				int count = 0;
				int length = quakes.size();
				for (int i=0;i<length;i++) {
					System.out.println("Earthquake #"+Integer.toString(count)+":");
					System.out.println(quakes.get(i)+"\n");
					count++;
				}
				if(count==length) {
					System.out.println("--- "+Integer.toString(count)+" entries printed ---");
				}
			}
			
		}
	}

}
