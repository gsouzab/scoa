package ufrj.scoa.model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
	
   public boolean connectBD() {
	   
	   Connection conn = null;
	   
        try
        {
            String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost/scoa";
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection (url, userName, password);
            System.out.println ("Conexão com o BD estabelecida!");
            return true;
        }
        catch (Exception e)
        {
            System.err.println ("Não foi possível estabelecer conexão com o BD");
            return false;
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close ();
                    System.out.println ("Conexão finalizada");
                }
                catch (Exception e) { /* ignore close errors */ }
            }
        }
        
   }
        
}
