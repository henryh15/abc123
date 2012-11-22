import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class BookCopy {

	private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	private DBConnect db;
	private Connection con;
	
	public BookCopy (DBConnect connect){
		db = connect;
		con = db.getCon();
	}
	
	/*
     * inserts a book copy and return feedback about the insertion in string 
     * Need to catch exception for not passing in arguments for NOT NULL attributes in the DB
     */ 
    private String insertBookCopy(String callNumber, String copyNo, char status) throws SQLException, IOException{
	
    String msg;
	PreparedStatement  ps;
	  
	  ps = con.prepareStatement("INSERT INTO bookCopy VALUES (?,?,?)");
	
	  ps.setString(1, callNumber);
	  ps.setString(2, copyNo);
	  ps.setString(0, String.valueOf(status));

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
     * deletes a book copy
     * Need to catch exception for not passing in arguments for NOT NULL attributes in the DB
     */ 
    private String deleteBookCopy(String callNumber, String copyNo) throws SQLException, IOException
    {
    	String msg;
    	PreparedStatement  ps;
	  
	  ps = con.prepareStatement("DELETE FROM book WHERE callNumber = ? and copyNo = ?");
	
	  ps.setString(1, callNumber);
	  ps.setString(2, copyNo);

	  int rowCount = ps.executeUpdate();

	  if (rowCount == 0)
	      msg = "\nBook " + callNumber + copyNo + " does not exist!";
	  else
		  msg = "Deleted Book " + callNumber + copyNo + " successfully!";
	  con.commit();
	  ps.close();
	  return msg;

    }
	
	
}
