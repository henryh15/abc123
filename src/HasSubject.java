import java.sql.*;


public class HasSubject {

	private Connection con;
	
	public HasSubject(DBConnect db) {
		this.con = db.getCon();  
	}
	
	public String insert(int callNumber, String subject) throws SQLException {
		
		String msg;
		
		PreparedStatement ps = con.prepareStatement("INSERT INTO hasSubject VALUES (?,?)");
		
		ps.setInt(1, callNumber);
		ps.setString(2, subject);
		
		if (ps.executeUpdate() == 0 ) {
			msg = "Insert fails.";
		}
		else {
			msg = "HasSubject " + callNumber + ", " + subject + " is inserted.";
		}
		
		con.commit();
		ps.close();
		return msg;
	}
	
	public String delete(int callNumber, String subject) throws SQLException {
		
		String msg;
		
		PreparedStatement ps = con.prepareStatement("DELETE FROM hasSubject WHERE callNumber=? AND subject=?");
		
		ps.setInt(1, callNumber);
		ps.setString(2, subject);
		
		if( ps.executeUpdate() == 0 ) {
			msg = "HasSubject " + callNumber + ", " + subject + " does not exist.";
		}
		else {
			msg = "HasSubject " + callNumber + ", " + subject + " is deleted.";
		}
		
		con.commit();
		ps.close();
		return msg;
		
	}
}
