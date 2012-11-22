
import java.sql.*;
import java.util.Scanner;

public class DBConnect {

	private Connection con;
	// user is allowed 3 login attempts
    
	
	
    public DBConnect(){
    	System.out.println("Plz enter username and password:");
    	Scanner sc = new Scanner(System.in);
    	
    	loadDriver();
    	connect(sc.next(), sc.next());    
    }
    
    
    
    /*
     * Variable getters
     */
    public Connection getCon(){
    	return con;
    }
    
     
    /*
     * Load the Oracle JDBC driver
     */
    private void loadDriver(){ 	
        try 
        {
  	// Load the Oracle JDBC driver
  	DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        }
        catch (SQLException ex)
        {
  	System.out.println("Message: " + ex.getMessage());
  	System.exit(-1);
        }
    }
    
 
    /*
     * connects to Oracle database named ug using user supplied username and password
     */ 
    private void connect(String username, String password)
    {
      String connectURL = "jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1522:ug"; 

      try 
      {
	con = DriverManager.getConnection(connectURL,username,password);

	System.out.println("\nConnected to Oracle!");
      }
      catch (SQLException ex)
      {
	System.out.println("Message: " + ex.getMessage());
	System.exit(-1);
      }
    }
    
    
    
    
    
	
}
