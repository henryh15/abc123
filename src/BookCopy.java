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
     * inserts a book copy
     * Need to catch exception for not passing in arguments for NOT NULL attributes in the DB
     */ 
    private void insertBookCopy() throws SQLException, IOException{
	
	String             callNumber;
	String			   copyNo;
	char			   status;
	PreparedStatement  ps;
	  
	  ps = con.prepareStatement("INSERT INTO bookCopy VALUES (?,?,?)");
	
	  System.out.print("\nBook Call Number: ");
	  callNumber = in.readLine();
	  ps.setString(1, callNumber);
	  
	  System.out.print("\nBook Copy Number: ");
	  copyNo = in.readLine();
	  ps.setString(2, copyNo);
	  
	  System.out.print("\nBook Status: ");
	  String copyNoTemp = in.readLine();
	  ps.setString(0, String.valueOf(copyNoTemp));

	  ps.executeUpdate();

	  // commit work 
	  con.commit();

	  ps.close();
	}


    /*
     * deletes a book copy
     * Need to catch exception for not passing in arguments for NOT NULL attributes in the DB
     */ 
    private void deleteBookCopy() throws SQLException, IOException
    {
    	String             callNumber;
    	String             copyNo;
    	PreparedStatement  ps;
	  
	  ps = con.prepareStatement("DELETE FROM book WHERE callNumber = ? and copyNo = ?");
	
	  System.out.print("\nBook Call Number: ");
	  callNumber = in.readLine();
	  ps.setString(1, callNumber);
	  
	  System.out.print("\nBook Copy Number: ");
	  copyNo = in.readLine();
	  ps.setString(2, copyNo);

	  int rowCount = ps.executeUpdate();

	  if (rowCount == 0)
	  {
	      System.out.println("\nBook " + callNumber + copyNo + " does not exist!");
	  }
	  con.commit();
	  ps.close();

    }
	
	
}
