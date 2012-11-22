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
     * inserts a book
     * Need to catch exception for not passing in arguments for NOT NULL attributes in the DB
     */ 
    public void insertBook() throws SQLException, IOException{
	
    int                isbn;
	String             callNumber;
	String			   title;
	String 			   mainAuthor;
	String			   publisher;
	int 			   year;
	PreparedStatement  ps;
	  
	  ps = con.prepareStatement("INSERT INTO book VALUES (?,?,?,?,?,?)");
	


	  System.out.print("\nBook Call Number: ");
	  callNumber = in.readLine();
	  ps.setString(1, callNumber);
	  
	  System.out.print("\nBook ISBN: ");
	  isbn = Integer.parseInt(in.readLine());
	  ps.setInt(2,isbn);
	  
	  System.out.print("\nBook Title: ");
	  title = in.readLine();
	  ps.setString(3, title);

	  System.out.print("\nBook Main Author: ");
	  mainAuthor = in.readLine();
	  
	  if (mainAuthor.length() == 0)
          {
	      ps.setString(4, null);
	  }
	  else
	  {
	      ps.setString(4, mainAuthor);
	  }
	  
	  System.out.print("\nBook Publisher: ");
	  publisher = in.readLine();
	  
	  if (publisher.length() == 0)
          {
	      ps.setString(5, null);
	  }
	  else
	  {
	      ps.setString(5, publisher);
	  }
	 
	  System.out.print("\nBook Year: ");
	  String yearTemp = in.readLine();
	  if (yearTemp.length() == 0)
	  {
	      ps.setNull(6, java.sql.Types.INTEGER);
	  }
	  else
	  {
	      year = Integer.parseInt(yearTemp);
	      ps.setInt(6, year);
	  }

	  ps.executeUpdate();

	  // commit work 
	  con.commit();

	  ps.close();
	}


    /*
     * deletes a book
     * Need to catch exception for not passing in arguments for NOT NULL attributes in the DB
     */ 
    public void deleteBook() throws SQLException, IOException
    {
    	String             callNumber;
    	PreparedStatement  ps;
	  
	  ps = con.prepareStatement("DELETE FROM book WHERE callNumber = ?");
	
	  System.out.print("\nBook Call Number: ");
	  callNumber = in.readLine();
	  ps.setString(1, callNumber);

	  int rowCount = ps.executeUpdate();

	  if (rowCount == 0)
	  {
	      System.out.println("\nBook " + callNumber + " does not exist!");
	  }
	  else
		  System.out.println("Deleted Book:" + callNumber);
	  con.commit();
	  ps.close();

    }
}
