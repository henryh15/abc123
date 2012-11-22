import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;



public class Book {
	private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	private DBConnect db;
	private Connection con;
	
	public Book (DBConnect connect){
		db = connect;
		con = db.getCon();
	}
	
	/*
     * inserts a book and return feedback about the insertion in string 
     * Need to catch exception for not passing in arguments for NOT NULL attributes in the DB
     */ 
    public String insertBook(int isbn, String callNumber, String title, String mainAuthor, String	publisher, int year) throws SQLException, IOException{
	
	PreparedStatement  ps;
	String msg;
	  
	  ps = con.prepareStatement("INSERT INTO book VALUES (?,?,?,?,?,?)");
	  ps.setString(1, callNumber);
	  ps.setInt(2,isbn);
	  ps.setString(3, title);
	  ps.setString(4, mainAuthor);	  
	  ps.setString(5, null);
	  if (year == 0)
	      ps.setNull(6, java.sql.Types.INTEGER);
	  else
	      ps.setInt(6, year);
	  
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
     * deletes a book and return feedback about the deletion in string
     * Need to catch exception for not passing in arguments for NOT NULL attributes in the DB
     */ 
    public String deleteBook(String callNumber) throws SQLException, IOException
    {
    	PreparedStatement  ps;
    	String msg;
	  
	  ps = con.prepareStatement("DELETE FROM book WHERE callNumber = ?");
	  ps.setString(1, callNumber);
	  
	  int rowCount = ps.executeUpdate();
	  if (rowCount == 0)
	      msg = "Book " + callNumber + " does not exist!";
	  else
		  msg = "Deleted Book:" + callNumber;
	  con.commit();
	  ps.close();
	  return msg;

    }
}
