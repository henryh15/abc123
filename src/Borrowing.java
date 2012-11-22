import java.sql.*;


public class Borrowing {

	private Connection con;
	
	public Borrowing(DBConnect db) {
		con = db.getCon();
	}
	
	public String insert(int borid, int bid, int callNumber, int copyNo, Date outDate, Date inDate) throws SQLException {
		
		String msg;
		
		PreparedStatement ps = con.prepareStatement("INSERT INTO borrowing VALUES (?,?,?,?,?,?)");
		
		ps.setInt(1, borid);
		ps.setInt(2, bid);
		ps.setInt(3, callNumber);
		ps.setInt(4, copyNo);
		ps.setDate(5, outDate);
		ps.setDate(6, inDate);
		
		if( ps.executeUpdate() == 0 ) {
			msg = "Insert fails";
		}
		else {
			msg = "Borrowing " +borid + " is inserted.";
		}
		
		con.commit();
		ps.close();
		return msg;
		
	}
	
	public String delete(int borid) throws SQLException {
		
		String msg;
		
		PreparedStatement ps = con.prepareStatement("DELETE FROM borrowing WHERE borid=?");
		
		ps.setInt(1, borid);
		
		if( ps.executeUpdate() == 0 ) {
			msg = "Borrowing " + borid + " does not exist.";
		}
		else {
			msg = "Borrowing " +borid + " is deleted.";
		}
		
		con.commit();
		ps.close();
		return msg;
		
	}
}
