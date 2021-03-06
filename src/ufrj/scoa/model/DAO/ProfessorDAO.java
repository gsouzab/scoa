package ufrj.scoa.model.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ufrj.scoa.model.VO.Professor;
import ufrj.scoa.model.VO.Room;

public class ProfessorDAO {
	
	private Connection conn;
	
	public ProfessorDAO() {
	}
	
	public void save(Professor professor) {
		
		try {
			PreparedStatement insertPersonStatement, insertProfessorStatement;
			
			conn = Connect.connectDB();
			
			insertPersonStatement = conn.prepareStatement("INSERT INTO person (id, name, cpf, email, birthdate, password, entry, role_id) VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
			insertPersonStatement.setString(1, professor.getName());
			insertPersonStatement.setString(2, professor.getCpf());
			insertPersonStatement.setString(3, professor.getEmail());
			insertPersonStatement.setDate(4, new java.sql.Date(professor.getBirthdate().getTime()));
			insertPersonStatement.setString(5, professor.getPassword());
			insertPersonStatement.setString(6, professor.getEntry());
			insertPersonStatement.setInt(7, professor.getRole());
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
			
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("Erro ao salvar professor");
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
				
				Professor professor = new Professor(rs.getInt("professorID") ,rs.getString("name"), rs.getString("cpf"), rs.getString("email"), rs.getDate("birthdate"), rs.getString("entry"), rs.getString("password"));
				
				professorList.add(professor);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Erro ao listar professor");
		}
		
		return professorList; 
	}

	public ArrayList<Professor> search(String name, String email, String cpf, String birthdate) {
		
		ArrayList<Professor> professorList = new ArrayList<Professor>();
		
		try {
			
			conn = Connect.connectDB();
			
			String baseQuery = " SELECT pe.* FROM scoa.person pe, scoa.professor pr " +
							   " WHERE pe.id = pr.person_id ";
			
			
			if(name.length() > 0) {
				baseQuery += " AND pe.name like '%" +name+ "%' ";
			}
			
			if(email.length() > 0){ 
				baseQuery += " AND pe.email like '%" +email+ "%' ";
			}
			
			if(cpf.length() > 0) {
				baseQuery += " AND pe.cpf = '" +cpf+ "' ";
			}
			
			if(birthdate.length() > 0) {
				baseQuery += "AND pe.birthdate = '" +birthdate+ "' ";
			}
			
			PreparedStatement ps = conn.prepareStatement(baseQuery);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Professor professor = new Professor(rs.getString("name"), rs.getString("cpf"), rs.getString("email"), rs.getDate("birthdate"), rs.getString("entry"), rs.getString("password"));
				
				professorList.add(professor);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Erro ao procurar professor");
		}
		
		return professorList; 
	}

	public Professor getProfessorById(int professorId) {
		Professor professor = null;
		
		try {
			conn = Connect.connectDB();
			
			PreparedStatement ps = conn.prepareStatement("SELECT pe.*, pr.id AS professor_id FROM scoa.person pe, scoa.professor pr WHERE pr.person_id = pe.id AND pr.id = ? ");
			ps.setInt(1, professorId);
			
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				professor = new Professor(rs.getInt("professor_id"), rs.getString("name"), rs.getString("cpf"), rs.getString("email"), rs.getDate("birthdate"), rs.getString("entry"), rs.getString("password"));
			}
			
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("Erro ao pegar professor por id");
		}
		
		return professor;
	}
	
	public Professor getProfessorByPersonId(int personId) {
		Professor professor = null;
		
		try {
			conn = Connect.connectDB();
			
			PreparedStatement ps = conn.prepareStatement("SELECT pe.*, pr.id AS professor_id FROM scoa.person pe, scoa.professor pr WHERE pr.person_id = pe.id AND pe.id = ? ");
			ps.setInt(1, personId);
			
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			professor = new Professor(rs.getInt("professor_id"), rs.getString("name"), rs.getString("cpf"), rs.getString("email"), rs.getDate("birthdate"), rs.getString("entry"), rs.getString("password"));

			
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("Erro ao pegar professor por id de pessoa");
		}
		
		return professor;
	}

}
