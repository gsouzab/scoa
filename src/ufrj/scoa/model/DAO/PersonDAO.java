package ufrj.scoa.model.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import ufrj.scoa.model.VO.Person;
import ufrj.scoa.util.Util;

public class PersonDAO {
	
	private Connection conn;
	
	public PersonDAO() {
	}
	
	public boolean validateLogin(String entry, String password) {
		
		boolean canLogin = false;
		
		try {
			PreparedStatement selectPersonStatement;
			
			conn = Connect.connectDB();
						
			selectPersonStatement = conn.prepareStatement("SELECT COUNT(*) FROM person WHERE entry = ? AND password = md5(?)");
			selectPersonStatement.setString(1, entry);
			selectPersonStatement.setString(2, password);
			
			ResultSet rs = selectPersonStatement.executeQuery();
			
			if (rs.next()) {
		        canLogin = rs.getInt(1) != 0;
		    } else {
		    	canLogin = false;
		    }

			return canLogin;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return canLogin;
		}
	}
	
	public void setPassword(String password,String entry){
		try {
			PreparedStatement updatePasswordStatement;
			
			conn = Connect.connectDB();
			
			updatePasswordStatement = conn.prepareStatement("UPDATE person SET password = md5(?) WHERE entry = ?");
			updatePasswordStatement.setString(1,password);
			updatePasswordStatement.setString(2,entry);
			updatePasswordStatement.executeUpdate();
		}catch(SQLException e){
			System.out.println("Failed to set new password");
			e.printStackTrace();
		}
	}
	
	public String getPassword(String entry){
		String passwordBd = null;
		try {
			PreparedStatement selectPersonStatement;
			
			conn = Connect.connectDB();
			selectPersonStatement = conn.prepareStatement("SELECT password FROM person WHERE entry = ?");
			selectPersonStatement.setString(1, entry);
			
			
			ResultSet rs = selectPersonStatement.executeQuery();
			if (rs.next()) {
		        passwordBd = rs.getString(1);		   
			}
			else{
				passwordBd = "Fail";
			}
			
			return passwordBd;
			
		}catch(SQLException e){
			System.out.println("Failed to get current password.");
			passwordBd = "Fail";
			return passwordBd;
		}
		
	}
	
public Person getCurrentUser(String entry, String password) {
	
	Person currentUser = null;
				
		try {
			PreparedStatement selectPersonStatement;
			
			conn = Connect.connectDB();
			
			selectPersonStatement = conn.prepareStatement("SELECT * FROM person WHERE entry = ? AND password = md5(?)");
			selectPersonStatement.setString(1, entry);
			selectPersonStatement.setString(2, password);
			
			ResultSet rs = selectPersonStatement.executeQuery();
			
			  if(rs != null && rs.next()) {  
	                	String name = rs.getString("name");
	        			String cpf = rs.getString("cpf");
	        			String email = rs.getString("email");
	        			Date birthdate = rs.getDate("birthdate");
	        			String passwordMD5 = rs.getString("password");
	        			int role = rs.getInt("role_id");
	        			currentUser = new Person(name, cpf, email, birthdate, entry, passwordMD5, role);
	          }  
			  
			return currentUser;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
