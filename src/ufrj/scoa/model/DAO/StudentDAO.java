package ufrj.scoa.model.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ufrj.scoa.model.VO.Course;
import ufrj.scoa.model.VO.Student;

public class StudentDAO {
	
	private Connection conn;
	
	public StudentDAO() {
	}
	
	public void save(Student student) {
		
		try {
			PreparedStatement insertPersonStatement, insertStudentStatement;
			
			conn = Connect.connectDB();
			
			insertPersonStatement = conn.prepareStatement("INSERT INTO person VALUES(DEFAULT,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			insertPersonStatement.setString(1, student.getName());
			insertPersonStatement.setString(2, student.getCpf());
			insertPersonStatement.setString(3, student.getEmail());
			insertPersonStatement.setDate(4, new java.sql.Date(student.getBirthdate().getTime()));
			insertPersonStatement.setString(5, student.getPassword());
			insertPersonStatement.setInt(6, student.getEntry());
			insertPersonStatement.executeUpdate();
			
			ResultSet rs = insertPersonStatement.getGeneratedKeys();
            
			int person_id = 0;
			if(rs.next())
            {
                person_id = rs.getInt(1);
            }
            
            insertStudentStatement = conn.prepareStatement("INSERT INTO student VALUES(DEFAULT,?,?,?)");
            insertStudentStatement.setInt(1, person_id);
            insertStudentStatement.setFloat(2, student.getCR());
            insertStudentStatement.setInt(3, student.getCourse().getId());
            
            insertStudentStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public ArrayList<Student> search(String courseCode,String courseName,String studentName,String email,String cpf, String birthdate) {
		
		ArrayList<Student> studentList = new ArrayList<Student>();
		
		try {
			
			conn = Connect.connectDB();
			
			String baseQuery = " SELECT p.*, c.name AS courseName, c.code AS courseCode, c.description AS courseDescription FROM scoa.person p, scoa.student s, scoa.course c" +
							   " WHERE p.id = s.person_id " +
							   " AND s.course_id = c.id ";
			
			if(courseCode.length() > 0 && courseName.length() > 0) {
				baseQuery += " AND c.code = '" +courseCode+ "' " +
							 " AND c.name = '" +courseName+ "' ";
			}
			
			if(studentName.length() > 0) {
				baseQuery += " AND p.name like '%" +studentName+ "%' ";
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
				
				Student student = new Student(rs.getString("name"), rs.getString("cpf"), rs.getString("email"), rs.getDate("birthdate"), new Course(rs.getString("courseName"), rs.getString("courseCode"), rs.getString("courseDescription")));
				
				studentList.add(student);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return studentList; 
	}

}
