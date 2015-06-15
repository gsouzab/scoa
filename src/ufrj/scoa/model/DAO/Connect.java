package ufrj.scoa.model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
	private static Connection conn = null; 

	private Connect() {

		try
		{
			String userName = "root";
			String password = "root";
			String url = "jdbc:mysql://localhost/scoa";
			Class.forName ("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection (url, userName, password);

		}
		catch (Exception e)
		{
			System.err.println ("N�o foi poss�vel estabelecer conex�o com o BD!");
		}

	}

	public synchronized static Connection connectDB() {  
		if (conn == null) {  
			new Connect();  
		}  
		return conn;  
	}  

}
