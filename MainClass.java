import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainClass {
	  
	public static void main(String[] args) {
		JBDC connect = new JBDC();
		
		System.out.println("Hello! Choose Query or Report");
		
		Scanner scanner = new Scanner(System.in);
		String answer = scanner.nextLine();
		
		if(answer.equals("Query")){
			System.out.println("Please type in country namebelow: ");
		
			String country = scanner.nextLine();
			connect.searchCountry(country);
			//connect.printRunways(country);
			 
			
		} else if (answer.equals("Report")){
			System.out.println("Please write your airport: ");
			//String airport = scanner.nextLine(); // for runways type (comment if dont use it)
			
			 connect.maxAirports();
			 connect.minAirports();
			 //connect.RunwayType(airport);
			 connect.TopRunway();
		}
		else{
			System.out.println("Wrong input ");
		}
	
	}
	
	}

