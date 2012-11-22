import java.sql.*;


public class HasAuthor {
	
	private Connection con;
	
	public HasAuthor(DBConnect db) {
		this.con = db.getCon(); 
	}
	
	public String insert(int callNumber, String name) throws SQLException { 
		
		String msg;
		
		PreparedStatement ps = con.prepareStatement("INSERT INTO hasAuthor VALUES (?,?)");
		
		ps.setInt(1, callNumber);
		ps.setString(2, name);
		
		if (ps.executeUpdate() == 0 ) {
			msg = "Insert fails.";
		}
		else {
			msg = "HasAuthor " + callNumber + ", " + name + " is inserted.";
		}
		
		con.commit();
		ps.close();
		return msg;
		
	}
	
	public String delete(int callNumber, String name) throws SQLException {
		
		String msg;
		
		PreparedStatement ps = con.prepareStatement("DELETE FROM hasAuthor WHERE callNumber=? AND name=?");
		
		ps.setInt(1, callNumber);
		ps.setString(2, name);
		
		if( ps.executeUpdate() == 0 ) {
			msg = "HasAuthor " + callNumber + ", " + name + " does not exist.";
		}
		else {
			msg = "HasAuthor " + callNumber + ", " + name + " is deleted.";
		}
		
		con.commit();
		ps.close();
		return msg;
	}

}
