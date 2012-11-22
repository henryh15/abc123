import java.sql.*;


public class Fine {

	private Connection con;
	
	public Fine(DBConnect db) {
		this.con = db.getCon();
		
	}
	
	public String insert(int fid, int amount, Date issuedDate, Date paidDate, int borid) throws SQLException {
		
		    String msg;
		    
			PreparedStatement ps = con.prepareStatement("INSERT INTO fine VALUES (?,?,?,?,?)");
			
			ps.setInt(1, fid);
			ps.setInt(2, amount);
			ps.setDate(3, issuedDate);
			if( paidDate == null ) {
				ps.setNull(4, Types.DATE);
			}
			else {
				ps.setDate(4, paidDate);
			}
			ps.setInt(5, borid);
			
			if (ps.executeUpdate() == 0 ) {
				msg = "Insert fails.";
			}
			else {
				msg = "Fine " + fid + " is inserted.";
			}
			
			con.commit();
			ps.close();
			return msg;

	}
	
	public String delete(int fid) throws SQLException {
		
		String msg;
		
		PreparedStatement ps = con.prepareStatement("DELETE FROM fine WHERE fid =?");
		
		ps.setInt(1, fid);
		
		if( ps.executeUpdate() == 0 ) {
			msg = "Fine " + fid + " does not exist.";
		}
		else {
			msg = "Fine " + fid + " is deleted.";
		}
		
		con.commit();
		ps.close();
		return msg;
		
	}

}
