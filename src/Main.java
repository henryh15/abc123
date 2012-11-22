import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class Main {
	
    public static void main(String args[]){	    	
    	DBConnect db = new DBConnect();
    	Book b = new Book(db);
    	
    	
    	
//    	try
//    	{
//    		b.insertBook();
//    		b.deleteBook();
//    	}
//    	catch (IOException e)
//    	{
//    	    System.out.println("IOException!");
//    	}
//    	catch (SQLException ex)
//    	{
//    	    System.out.println("Message: " + ex.getMessage());
//    	}	
        
    }

}
