import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Borrower {
	
	private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	private DBConnect db;
	private Connection con;
	
	public Borrower (DBConnect connect){
		db = connect;
		con = db.getCon();
	}
	
	/*
     * inserts a borrower
     * Need to catch exception for not passing in arguments for NOT NULL attributes in the DB
     * Pass in 0 for phone if phone number is not provided by borrower
     */ 
    public String insertBorrower(int bid, String password, String name, String addr, int phone, String emailAddr, int sinOrStNo, Date expiryDate,
	String borrower_type) throws SQLException, IOException{
	
	PreparedStatement  ps;
	String msg;
	  
	  ps = con.prepareStatement("INSERT INTO borrower VALUES (?,?,?,?,?,?,?,?,?)");
	
	  ps.setInt(1,bid);
	  ps.setString(2, password);
	  ps.setString(3, name);
	  ps.setString(4, addr);

	  if (phone == 0)
	      ps.setNull(5, java.sql.Types.INTEGER);
	  else
	      ps.setInt(5, phone);
	  
	  ps.setString(6, emailAddr);
	  
	  if (sinOrStNo == 0)
	      ps.setNull(7, java.sql.Types.INTEGER);
	  else
	      ps.setInt(7, sinOrStNo);
	 
	  if (expiryDate == null)
	      ps.setNull(8, java.sql.Types.DATE);
	  else  
	      ps.setDate(8, expiryDate);
	      ps.setString(9, null);	  
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
     * deletes a borrower
     * Need to catch exception for not passing in arguments for NOT NULL attributes in the DB
     * 
     */ 
    public String deleteBorrower(int bid) throws SQLException, IOException
    {
	
	PreparedStatement  ps;
	String msg;
	  
	  ps = con.prepareStatement("DELETE FROM borrower WHERE bid = ?");
	  ps.setInt(1, bid);

	  int rowCount = ps.executeUpdate();

	  if (rowCount == 0)
	      msg = "\nBorrower " + bid + " does not exist!";
	  else 
		  msg =  "deletion completed";

	  con.commit();
	  ps.close();
	  return msg;

    }
    
  }





















