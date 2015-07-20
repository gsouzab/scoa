package ufrj.scoa.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ufrj.scoa.model.VO.Professor;
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
			//e.printStackTrace();
			System.out.println("Erro ao salvar secretaria");
		} 
	}
	
	public ArrayList<Secretary> search(String name, String email, String cpf, String birthdate) {
		
		ArrayList<Secretary> secretaryList = new ArrayList<Secretary>();
		
		try {
			
			conn = Connect.connectDB();
			
			String baseQuery = " SELECT p.* FROM scoa.person p, scoa.secretary s " +
							   " WHERE p.id = s.person_id ";
			
			
			if(name.length() > 0) {
				baseQuery += " AND p.name like '%" +name+ "%' ";
			}
			
			if(email.length() > 0){ 
				baseQuery += " AND p.email like '%" +email+ "%' ";
			}
			
			if(cpf.length() > 0) {
				baseQuery += " AND p.cpf = '" +cpf+ "' ";
			}
			
			if(birthdate.length() > 0) {
				baseQuery += "AND p.birthdate = '" +birthdate+ "' ";
			}
			
			PreparedStatement ps = conn.prepareStatement(baseQuery);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Secretary secretary = new Secretary(rs.getString("name"), rs.getString("cpf"), rs.getString("email"), rs.getDate("birthdate"), rs.getString("entry"), rs.getString("password"));
				
				secretaryList.add(secretary);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Erro ao procurar secretaria");
		}
		
		return secretaryList; 
	}

}
