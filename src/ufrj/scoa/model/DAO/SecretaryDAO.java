package ufrj.scoa.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ufrj.scoa.model.VO.Secretary;

public class SecretaryDAO {
	
	private Connection conn;
	
	public void saveSecretary(Secretary secretary) {
		
		try {
			PreparedStatement insertPersonStatement, insertSecretaryStatement;
			
			conn = Connect.connectDB();
			
			insertPersonStatement = conn.prepareStatement("INSERT INTO person (id, name, cpf, email, birthdate, password, entry, role_id) VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
			insertPersonStatement.setString(1, secretary.getName());
			insertPersonStatement.setString(2, secretary.getCpf());
			insertPersonStatement.setString(3, secretary.getEmail());
			insertPersonStatement.setDate(4, new java.sql.Date(secretary.getBirthdate().getTime()));
			insertPersonStatement.setString(5, secretary.getPassword());
			insertPersonStatement.setString(6, secretary.getEntry());
			insertPersonStatement.setInt(7, secretary.getRole());
			insertPersonStatement.executeUpdate();
			
			ResultSet rs = insertPersonStatement.getGeneratedKeys();
            
			int person_id = 0;
			if(rs.next())
            {
                person_id = rs.getInt(1);
            }
            insertSecretaryStatement = conn.prepareStatement("INSERT INTO secretary VALUES(DEFAULT,?)");
            insertSecretaryStatement.setInt(1, person_id);
            
            insertSecretaryStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
