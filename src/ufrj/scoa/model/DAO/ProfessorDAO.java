package ufrj.scoa.model.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ufrj.scoa.model.VO.Professor;

public class ProfessorDAO {
	
	private Connection conn;
	
	public ProfessorDAO() {
	}
	
	public void save(Professor professor) {
		
		try {
			PreparedStatement insertPersonStatement, insertProfessorStatement;
			
			conn = Connect.connectDB();
			
			insertPersonStatement = conn.prepareStatement("INSERT INTO person VALUES(DEFAULT,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			insertPersonStatement.setString(1, professor.getName());
			insertPersonStatement.setString(2, professor.getCpf());
			insertPersonStatement.setString(3, professor.getEmail());
			insertPersonStatement.setDate(4, new java.sql.Date(professor.getBirthdate().getTime()));
			insertPersonStatement.setString(5, professor.getPassword());
			insertPersonStatement.setInt(6, professor.getEntry());
			insertPersonStatement.executeUpdate();
			
			ResultSet rs = insertPersonStatement.getGeneratedKeys();
            
			int person_id = 0;
			if(rs.next())
            {
                person_id = rs.getInt(1);
            }
            
            insertProfessorStatement = conn.prepareStatement("INSERT INTO professor VALUES(DEFAULT,?)");
            insertProfessorStatement.setInt(1, person_id);
            
            
            insertProfessorStatement.executeUpdate();
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public ArrayList<Professor> list() {
		
		ArrayList<Professor> professorList = new ArrayList<Professor>();
		
		try {
			
			conn = Connect.connectDB();
			
			PreparedStatement ps = conn.prepareStatement(" SELECT p.*, pr.id AS professorID FROM scoa.person p, scoa.professor pr"
														+ " WHERE p.id = pr.person_id;");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Professor professor = new Professor(rs.getString("name"), rs.getString("cpf"), rs.getString("email"), rs.getDate("birthdate"));
				
				professorList.add(professor);
			}
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return professorList; 
	}

}