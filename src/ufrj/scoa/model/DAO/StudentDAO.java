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
		Connection conn = null;
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
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
//	public ArrayList<Course> list() {
//		
//		ArrayList<Course> courseList = new ArrayList<Course>();
//		
//		try {
//			
//			conn = Connect.connectDB();
//			
//			ps = conn.prepareStatement("SELECT * FROM course");
//			
//			ResultSet rs = ps.executeQuery();
//			
//			while(rs.next()) {
//				courseList.add(new Course(rs.getString("name"), rs.getString("code"), rs.getString("description")));
//			}
//			
//			conn.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return courseList; 
//	}

}
