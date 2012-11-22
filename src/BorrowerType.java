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
    private String insertBorrowerType( int bookTimeLimit, String borrower_type) throws SQLException, IOException{
	
	PreparedStatement  ps;
	String msg;
	  
	  ps = con.prepareStatement("INSERT INTO borrowerType VALUES (?,?)");
	  ps.setString(1, borrower_type);
	  ps.setInt(2,bookTimeLimit);

	  if(ps.executeUpdate()==0)
		  msg = "insertion failed";
	  else
		  msg = "inserted successfully";

	  // commit work 
	  con.commit();
	  ps.close();
	  return msg;
	}


    /*
     * deletes a borrower type
     * Need to catch exception for not passing in arguments for NOT NULL attributes in the DB
     */ 
    private String deleteBorrowerType(String borrower_type) throws SQLException, IOException
    {
    	PreparedStatement  ps;
    	String msg;
	  
	  ps = con.prepareStatement("DELETE FROM borrowerType WHERE borrower_type = ?");
	  ps.setString(1, borrower_type);

	  int rowCount = ps.executeUpdate();
	  if (rowCount == 0)
	      msg = "\nBorrower Type " + borrower_type + " does not exist!";
	  else
		  msg = "Deleted Borrower Type" + borrower_type + "successfully";

	  con.commit();
	  ps.close();
	  return msg;
    }
}
