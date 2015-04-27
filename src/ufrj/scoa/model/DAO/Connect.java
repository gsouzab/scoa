package ufrj.scoa.model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
	
   public static Connection connectDB() {
	   
	   Connection conn = null;
	   
        try
        {
            String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost/scoa";
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection (url, userName, password);
            
            return conn;
        }
        catch (Exception e)
        {
            System.err.println ("N�o foi poss�vel estabelecer conex�o com o BD!");
            return conn;
        }
        
   }
   
   public static void close(Connection conn)
   {
   
       try
       {
    	   if (conn != null)
           {
    		   conn.close ();
           }
           System.out.println ("Conex�o finalizada");
       }
       catch (Exception e) { /* ignore close errors */ }
   
   }
   
}
