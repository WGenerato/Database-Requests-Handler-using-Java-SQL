package database;
import java.sql.*;
import java.util.Scanner;
public class Data {
	public static void main(String[] args) {
	Connection c = null; 
	Statement stmt = null;
	System.out.println(
  		   "To see the list of stores that my games were bought from, please type 1\n "
  		  +"To see the list of platforms that my games are on, please type 2\n"
          +"To see the list of my systems, please type 3\n"
          +"To see the list of my prefered genres, please type 4\n"
          +"To see whether I bought more physical or digital games, please type 5\n"
          +"To see the list of my favorite studios, please type 6\n"
          +"To see the my favorite services to purchase games, please type 7\n"
          +"To see if I have more than one copies of each game, please type 8\n"
          +"To see the list of voiced games, please type 9\n"
          +"To see whether I bought more single or multiplayer games, please type 10");
	Scanner myQuery = new Scanner(System.in);  
	String queryNum = myQuery.nextLine();
  
	{
	try {       
	Class.forName("org.sqlite.JDBC");       
	c = DriverManager.getConnection("jdbc:sqlite:gameDatabase");    
	
	
	
   switch(queryNum) {
   case "1":
   	stmt = c.createStatement();
   	String sql1 = "SELECT *from GameStores;";
   	ResultSet rs1 = stmt.executeQuery(sql1);
   	while ( rs1.next() ) {
   		String stores= rs1.getString("NameofStore");
   		System.out.println("Stores:"+stores);
   	}
   	stmt.executeUpdate(sql1);
     break;
   case "2":
   	stmt = c.createStatement();
   	String sql2 = "SELECT *from Platform;";
   	ResultSet rs2 = stmt.executeQuery(sql2);
   	while ( rs2.next() ) {
   		String platforms = rs2.getString("NameofPlatform");
   		System.out.println("Platforms my games are on:"+platforms);
   	}
   	stmt.executeUpdate(sql2);
     break;
   case "3":
   	stmt = c.createStatement();
   	String sql3 = "SELECT DISTINCT NameofPlatform from Platform;";
   	ResultSet rs3 = stmt.executeQuery(sql3);
   	while ( rs3.next() ) {
   		String dplatforms = rs3.getString("NameofPlatform");
   		System.out.println("Systems that I own:"+dplatforms);
   	}
   	stmt.executeUpdate(sql3);
       break;
   case "4":
   	stmt = c.createStatement();
   	String sql4 = "SELECT genreName FROM Genres GROUP BY genreName HAVING COUNT(*)>1;";
   	ResultSet rs4 = stmt.executeQuery(sql4);
   	while ( rs4.next() ) {
   		String dgenres = rs4.getString("genreName");
   		System.out.println("My preffered genres:"+dgenres);
   	}
   	stmt.executeUpdate(sql4);
       break;
   case "5":
   	stmt = c.createStatement();
   	String sql5 = "SELECT mediumType FROM Medium ;";
   	ResultSet rs5 = stmt.executeQuery(sql5);
   	while ( rs5.next() ) {
   		String dmediums = rs5.getString("mediumType");
   		System.out.println("My mediums for physical and digital games:"+dmediums);
   	}
   	stmt.executeUpdate(sql5);
       break;
   case "6":
   	stmt = c.createStatement();
   	String sql6 = "SELECT NameofStudio FROM GameStudios GROUP BY NameofStudio HAVING COUNT(*)>1;";
   	ResultSet rs6 = stmt.executeQuery(sql6);
   	while ( rs6.next() ) {
   		String dstudios = rs6.getString("NameofStudio");
   		System.out.println("My favorite studios:"+dstudios);
   	}
   	stmt.executeUpdate(sql6);
       break;
   case "7":
   	stmt = c.createStatement();
   	String sql7 = "SELECT NameofStore FROM GameStores GROUP BY NameofStore HAVING COUNT(*)>1;";
   	ResultSet rs7 = stmt.executeQuery(sql7);
   	while ( rs7.next() ) {
   		String dstores = rs7.getString("NameofStore");
   		System.out.println("My preferred services:"+dstores);
   	}
   	stmt.executeUpdate(sql7);
       break;
   case "8":
   	stmt = c.createStatement();
   	String sql8 = "SELECT NameofPlatform FROM Platform GROUP BY fk_gameName HAVING COUNT(*)>1;";
   	ResultSet rs8 = stmt.executeQuery(sql8);
   	while ( rs8.next() ) {
   		String dgames = rs8.getString("fk_gameName");
   		
   		System.out.println("Duplicate of my games:"+ dgames);
  
   	}
   	stmt.executeUpdate(sql8);
       break;
   case "9":
   	stmt = c.createStatement();
   	String sql9 = "SELECT Nationality,actorName,fk_gameName FROM VoiceActors WHERE (Nationality='NULL' OR 'NONE' OR 'None') OR (actorName='NULL' OR 'NONE' OR 'None');";
   	ResultSet rs9 = stmt.executeQuery(sql9);
   	while ( rs9.next() ) {
   		String nvoiceactors = rs9.getString("fk_gameName");
   		System.out.println("Games that does not have voiceover:"+nvoiceactors);
   	}
   	stmt.executeUpdate(sql9);
       break;
   case "10":
   	stmt = c.createStatement();
   	String sql10 = "SELECT subgenreName FROM Genres WHERE subgenreName='Singleplayer' OR subgenreName='Multiplayer'; ";
   	ResultSet rs10 = stmt.executeQuery(sql10);
   	while ( rs10.next() ) {
   		String ddplatforms = rs10.getString("subgenreName");
   		System.out.println("Number of single and multiplayer games that I own:"+ddplatforms);
   	}
   	stmt.executeUpdate(sql10);
       break;
   default:
	   System.out.println("No query entered. Exit the program and try again");
   	break;
   	
 }
   stmt.close();
   c.setAutoCommit(false);
   c.commit();
   c.close();
	 } 
	catch ( Exception e )  {      
	 System.err.println("Problem Encountered"); 
	 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	 System.exit(0);
	    }     
	System.out.println("Opened database successfully");
	System.out.println("Query answered successfully. Closed and exited the database");
} 
    
  } 
	}
