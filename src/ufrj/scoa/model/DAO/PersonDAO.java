package ufrj.scoa.model.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonDAO {
	
	private Connection conn;
	
	public PersonDAO() {
	}
	
	public boolean validateLogin(int entry, String password) {
		
		boolean canLogin = false;
		
		try {
			PreparedStatement selectPersonStatement;
			
			conn = Connect.connectDB();
			
			selectPersonStatement = conn.prepareStatement("SELECT COUNT(*) FROM PERSON WHERE entry = ? AND password = md5(?)");
			selectPersonStatement.setInt(1, entry);
			selectPersonStatement.setString(2, password);
			
			ResultSet rs = selectPersonStatement.executeQuery();
			
			if (rs.next()) {
		        canLogin = rs.getInt(1) != 0;
		    } else {
		    	canLogin = false;
		    }

			conn.close();

			return canLogin;
			
		} catch (SQLException e) {
			return canLogin;
		}
	}

}
