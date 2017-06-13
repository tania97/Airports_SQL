import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.*;


public class JBDC {
	private static final String HOSTNAME="localhost";
    private static final String PORTNUMBER = "3306";
    private static final String USERNAME="root";
    private static final String PASSWORD="student";    
    private static final String DBNAME = "database";
    
    private Connection myConn;
    private Statement stmt;
    private ResultSet rs;
    
    public JBDC() {
    	try{
			Class.forName("com.mysql.jdbc.Driver");
			//1. create a connection
			myConn = DriverManager.getConnection("jdbc:mysql://" + HOSTNAME + ":" + PORTNUMBER + "/" + DBNAME , USERNAME, PASSWORD); 
			//2. create statement
			stmt = myConn.createStatement();
			System.out.println("Connected database successfully...");
			
		}
		catch(Exception e){
			System.out.println("Unable to load driver: " + e);
			e.printStackTrace();
		}
    	
    }

   public void searchCountry(String country){
	   try{
		 String query = "SELECT airports.name from airports inner join countries on countries.code=airports.iso_country where countries.name like '" +country + "'" ;
	       
	       ResultSet rs = stmt.executeQuery(query);
		      //STEP 5: Extract data from result set
		      while(rs.next()){
		    	  String name =rs.getString("name"); //get string
		         System.out.println("Available airports in "+ country + " are: " + name);
		         
		      }
		      rs.close();
	   
	   }catch(Exception e){
		   e.printStackTrace();
	   }
   }
   
   public void printRunways(String name){//DOESNT WORK
	   try{
			 String query = "SELECT airports.name, runways.le_ident FROM airports JOIN runways ON airports.id = runways.airport_ref JOIN countries ON airports.iso_country = countries.code WHERE countries.name LIKE 'Ukraine'" ;
		       
		     ResultSet rs = stmt.executeQuery(query);
			      //STEP 5: Extract data from result set
			      while(rs.next()){
			    	  String runway =rs.getString("le_ident");
			    	  //String airport_name =rs.getString("name"); //get string
			         
			         System.out.println("The runways are: " + runway); //TO DO
			      }
			      rs.close();
		   
		   }catch(Exception e){
			   e.printStackTrace();
		   }
   }
   
   public void maxAirports(){
	   try{
		   String query="SELECT countries.name, COUNT(airports.name) AS total FROM airports INNER JOIN countries ON countries.code = airports.iso_country group by countries.name order by 2 DESC LIMIT 10 ";
		   ResultSet rs = stmt.executeQuery(query);
		   System.out.println("Top 10 Countries With The Highest Number of Airports" +"\n");
		   while(rs.next()){
			   String name =rs.getString("name");
		    	 int count =rs.getInt("total");
		    	 System.out.println(name + " has/have " + count + " airports");
		   }
		   
		   }catch(Exception e){
			   e.printStackTrace();
		   }
   }
   
   public void minAirports(){
	   try{
		   String query="SELECT countries.name, COUNT(airports.name) AS total FROM airports INNER JOIN countries ON countries.code = airports.iso_country group by countries.name order by 2 ASC LIMIT 10 ";
		   ResultSet rs = stmt.executeQuery(query);
		   System.out.println("Top 10 Countries With The Lowest Number of Airports" +"\n");
		   while(rs.next()){
			   String name =rs.getString("name");
		    	 int count =rs.getInt("total");
		    	 System.out.println(name + " has/have only " + count + " airport(s)");
		   }
		   
		   }catch(Exception e){
			   e.printStackTrace();
		   }
   }
   
   public void RunwayType(String airport_name){ //works with airport name (not country name)
	   try{
		  
		   String query1="SELECT runways.surface FROM runways INNER JOIN airports ON runways.airport_ident = airports.ident group by airports.name WHERE airports.name LIKE '" +airport_name +"'";
		   ResultSet rs = stmt.executeQuery(query1);
		   
		   while(rs.next()){
			   String name =rs.getString("surface");
		    	
		    	 System.out.println("The runways type of ...  are: " +name);
		   }
		   
		   }catch(Exception e){
			   e.printStackTrace();
		   }
   }
   
   public void TopRunway(){
	   try{
		   String query="SELECT COUNT(runways.le_ident) AS total from runways group by runways.le_ident order by 1 DESC LIMIT 10";
		  
		   ResultSet rs = stmt.executeQuery(query );
		   
		   System.out.println("Top 10 most occuring runways " +"\n");
		   while(rs.next()){
			 // String name =rs.getString("le_ident");
			   int count =rs.getInt("total");
		    	
			   System.out.println( "Runway repeats " + count + " times");
		   }
		   
		   }catch(Exception e){
			   e.printStackTrace();
		   }
   }
    
}