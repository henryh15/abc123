import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BorrowerType {

	private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	private DBConnect db;
	private Connection con;
	
	public BorrowerType (DBConnect connect){
		db = connect;
		con = db.getCon();
	}
	
	/*
     * inserts a borrower type
     * Need to catch exception for not passing in arguments for NOT NULL attributes in the DB
     */ 
    private void insertBorrowerType() throws SQLException, IOException{
	
    int                bookTimeLimit;
	String             borrower_type;
	PreparedStatement  ps;
	  
	  ps = con.prepareStatement("INSERT INTO borrowerType VALUES (?,?)");
	
	  System.out.print("\nBorrower Type: ");
	  borrower_type = in.readLine();
	  ps.setString(1, borrower_type);
	  
	  System.out.print("\nBook Time Limit: ");
	  bookTimeLimit = Integer.parseInt(in.readLine());
	  ps.setInt(2,bookTimeLimit);

	  ps.executeUpdate();

	  // commit work 
	  con.commit();

	  ps.close();
	}


    /*
     * deletes a borrower type
     * Need to catch exception for not passing in arguments for NOT NULL attributes in the DB
     */ 
    private void deleteBorrowerType() throws SQLException, IOException
    {
    	String             borrower_type;
    	PreparedStatement  ps;
	  
	  ps = con.prepareStatement("DELETE FROM borrowerType WHERE borrower_type = ?");
	
	  System.out.print("\nBorrower Type: ");
	  borrower_type = in.readLine();
	  ps.setString(1, borrower_type);

	  int rowCount = ps.executeUpdate();

	  if (rowCount == 0)
	  {
	      System.out.println("\nBorrower Type " + borrower_type + " does not exist!");
	  }

	  con.commit();

	  ps.close();

    }
}
