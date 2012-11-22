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
     */ 
    private void insertBorrower() throws SQLException, IOException{
	
    int                bid;
	String             password;
	String             name;
	String             addr;
	int                phone;
	String 			   emailAddr;
	int				   sinOrStNo;
	Date 			   expiryDate;
	String			   borrower_type;
	PreparedStatement  ps;
	  
	  ps = con.prepareStatement("INSERT INTO borrower VALUES (?,?,?,?,?,?,?,?,?)");
	
	  System.out.print("\nBorrower ID: ");
	  bid = Integer.parseInt(in.readLine());
	  ps.setInt(1,bid);

	  System.out.print("\nBorrower's Password: ");
	  password = in.readLine();
	  ps.setString(2, password);
	  
	  System.out.print("\nBorrower Name: ");
	  name = in.readLine();
	  ps.setString(3, name);

	  System.out.print("\nBorrower Address: ");
	  addr = in.readLine();
	  
	  if (addr.length() == 0)
          {
	      ps.setString(4, null);
	  }
	  else
	  {
	      ps.setString(4, addr);
	  }
	 
	  System.out.print("\nBorrower Phone: ");
	  String phoneTemp = in.readLine();
	  if (phoneTemp.length() == 0)
	  {
	      ps.setNull(5, java.sql.Types.INTEGER);
	  }
	  else
	  {
	      phone = Integer.parseInt(phoneTemp);
	      ps.setInt(5, phone);
	  }
	  
	  System.out.print("\nBorrower Email Address: ");
	  emailAddr = in.readLine();
	  
	  if (emailAddr.length() == 0)
          {
	      ps.setString(6, null);
	  }
	  else
	  {
	      ps.setString(6, emailAddr);
	  }
	  
	  
	  System.out.print("\nBorrower Sin No. or Student No.: ");
	  String sinOrStNoTemp = in.readLine();
	  if (sinOrStNoTemp.length() == 0)
	  {
	      ps.setNull(7, java.sql.Types.INTEGER);
	  }
	  else
	  {
		  sinOrStNo = Integer.parseInt(sinOrStNoTemp);
	      ps.setInt(7, sinOrStNo);
	  }
	  
	  System.out.print("\nBorrower Expiry Date: ");
	  String expiryDateTemp = in.readLine();
	  if (expiryDateTemp.length() == 0)
	  {
	      ps.setNull(8, java.sql.Types.DATE);
	  }
	  else
	  {
		  expiryDate = Date.valueOf(expiryDateTemp);   
	      ps.setDate(8, expiryDate);
	  }
	  
	  System.out.print("\nBorrower Type: ");
	  borrower_type = in.readLine();
	  
	  if (borrower_type.length() == 0)
      {
	      ps.setString(9, null);
	  }
	  else
	  {
	      ps.setString(9, borrower_type);
	  }
	  
	  ps.executeUpdate();

	  // commit work 
	  con.commit();

	  ps.close();
	}


    /*
     * deletes a borrower
     * Need to catch exception for not passing in arguments for NOT NULL attributes in the DB
     */ 
    private void deleteBorrower() throws SQLException, IOException
    {
	int                bid;
	PreparedStatement  ps;
	  
	  ps = con.prepareStatement("DELETE FROM borrower WHERE bid = ?");
	
	  System.out.print("\nBorrower ID: ");
	  bid = Integer.parseInt(in.readLine());
	  ps.setInt(1, bid);

	  int rowCount = ps.executeUpdate();

	  if (rowCount == 0)
	  {
	      System.out.println("\nBorrower " + bid + " does not exist!");
	  }

	  con.commit();

	  ps.close();

    }
    
  }





















