import java.sql.*;


public class HoldRequest {

	private Connection con;
	
	public HoldRequest(DBConnect db) {
		this.con = db.getCon(); 
	}
	
	public String insert(int hid, int bid, int callNumber, Date issuedDate) throws SQLException {

		String msg;
		
		PreparedStatement ps = con.prepareStatement("INSERT INTO holdRequest VALUES (?,?,?,?)");
		
		ps.setInt(1, hid);
		ps.setInt(2, bid);
		ps.setInt(3, callNumber);
		ps.setDate(4, issuedDate);
		
		if( ps.executeUpdate() == 0 ) {
			msg = "Insert fails.";
		}
		else {
			msg = "Hold Request " + hid + " is inserted.";
		}
		
		con.commit();
		ps.close();
		return msg;
		
	}
	
	public String delete(int hid) throws SQLException {
		
		String msg;
		
		PreparedStatement ps = con.prepareStatement("INSERT INTO holdRequest WHERE hid =?");
		
		ps.setInt(1, hid);
		
		if( ps.executeUpdate() == 0 ) {
			msg = "Hold Request " + hid + " does not exist.";
		}
		else {
			msg = "Hold Request " + hid + " is deleted.";
		}
		
		con.commit();
		ps.close();
		return msg;
		
	}
	
}
