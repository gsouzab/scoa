package ufrj.scoa.model.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ufrj.scoa.model.VO.Course;
import ufrj.scoa.model.VO.Student;
import ufrj.scoa.util.Constants;

public class StudentDAO {
	
	private Connection conn;
	
	public StudentDAO() {
	}
	
	public void saveStudent(Student student) {
		
		try {
			PreparedStatement insertPersonStatement, insertStudentStatement;
			
			conn = Connect.connectDB();
			
			insertPersonStatement = conn.prepareStatement("INSERT INTO person (id, name, cpf, email, birthdate, password, entry, role_id) VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
			insertPersonStatement.setString(1, student.getName());
			insertPersonStatement.setString(2, student.getCpf());
			insertPersonStatement.setString(3, student.getEmail());
			insertPersonStatement.setDate(4, new java.sql.Date(student.getBirthdate().getTime()));
			insertPersonStatement.setString(5, student.getPassword());
			insertPersonStatement.setString(6, student.getEntry());
			insertPersonStatement.setInt(7, student.getRole());
			insertPersonStatement.executeUpdate();
			
			ResultSet rs = insertPersonStatement.getGeneratedKeys();
            
			int person_id = 0;
			if(rs.next())
            {
                person_id = rs.getInt(1);
            }
            
            insertStudentStatement = conn.prepareStatement("INSERT INTO student (id, person_id, cr, course_id) VALUES(DEFAULT,?,?,?)");
            insertStudentStatement.setInt(1, person_id);
            insertStudentStatement.setFloat(2, student.getCR());
            insertStudentStatement.setInt(3, student.getCourse().getId());
            
            insertStudentStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public ArrayList<Student> listAllStudent() {
		
		ArrayList<Student> studentList = new ArrayList<Student>();
		
		try {
			
			conn = Connect.connectDB();
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM student s INNER JOIN person p ON p.id = s.person_id");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				CourseDAO courseDAO = new CourseDAO();
				Student student = new Student(rs.getString("name"), rs.getString("cpf"), rs.getString("email"), rs.getDate("birthdate"), courseDAO.getCourseById(rs.getInt("course_id")), rs.getString("entry"), rs.getString("password"));
				student.setStudentId(rs.getInt("id"));
				student.setPersonId(rs.getInt("person_id"));
				
				studentList.add(student);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return studentList; 
	}
	
	public Student getStudentById(int student_id) {
		
		Student student = null;
		
		try {
			
			conn = Connect.connectDB();
			
			PreparedStatement ps = conn.prepareStatement("SELECT s.*, p.name, p.email, p.birthdate, p.cpf, p.entry, p.password FROM student s INNER JOIN person p ON p.id = s.person_id WHERE s.id = ? ");
			ps.setInt(1, student_id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				CourseDAO courseDAO = new CourseDAO();
				student = new Student(rs.getString("name"), rs.getString("cpf"), rs.getString("email"), rs.getDate("birthdate"), courseDAO.getCourseById(rs.getInt("course_id")), rs.getString("entry"), rs.getString("password"));
				student.setStudentId(rs.getInt("id"));
				student.setPersonId(rs.getInt("person_id"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return student; 
	}
	
	public Student getStudentByPersonId(int person_id) {
		
		Student student = null;
		
		try {
			
			conn = Connect.connectDB();
			
			PreparedStatement ps = conn.prepareStatement("SELECT s.*, p.name, p.email, p.birthdate, p.cpf, p.entry, p.password FROM person p INNER JOIN student s ON s.person_id = p.id WHERE p.id = ? ");
			ps.setInt(1, person_id);
			
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()) {
				
				CourseDAO courseDAO = new CourseDAO();
				student = new Student(rs.getString("name"), rs.getString("cpf"), rs.getString("email"), rs.getDate("birthdate"), courseDAO.getCourseById(rs.getInt("course_id")), rs.getString("entry"), rs.getString("password"));
				student.setStudentId(rs.getInt("id"));
				student.setPersonId(rs.getInt("person_id"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return student; 
	}
	
	public ArrayList<Student> getStudentsByClassId(int class_id) {
		
		ArrayList<Student> studentList = new ArrayList<Student>();
		
		try {
			
			conn = Connect.connectDB();
			
			PreparedStatement ps = conn.prepareStatement("SELECT s.*, p.name, p.email, p.birthdate, p.cpf, p.entry, p.password, sc.grade FROM student s INNER JOIN person p ON p.id = s.person_id INNER JOIN student_class sc ON sc.student_id = s.id WHERE sc.class_id = ? AND sc.state = ? ");
			ps.setInt(1, class_id);
			ps.setInt(2, Constants.STUDENT_CLASS_APPROVED);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				CourseDAO courseDAO = new CourseDAO();
				Student student = new Student(rs.getString("name"), rs.getString("cpf"), rs.getString("email"), rs.getDate("birthdate"), courseDAO.getCourseById(rs.getInt("course_id")), rs.getString("entry"), rs.getString("password"));
				student.setStudentId(rs.getInt("id"));
				student.setPersonId(rs.getInt("person_id"));
				
				studentList.add(student);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return studentList; 
	}
	
	public ArrayList<Student> searchStudent(String courseCode,String courseName,String studentName,String email,String cpf, String birthdate) {
		
		ArrayList<Student> studentList = new ArrayList<Student>();
		
		try {
			
			conn = Connect.connectDB();
			
			String baseQuery = " SELECT p.*, c.name AS courseName, c.code AS courseCode, c.description AS courseDescription, s.id AS studentId FROM scoa.person p, scoa.student s, scoa.course c" +
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
				
				Student student = new Student(rs.getString("name"), rs.getString("cpf"), rs.getString("email"), rs.getDate("birthdate"), new Course(rs.getString("courseName"), rs.getString("courseCode"), rs.getString("courseDescription")), rs.getString("entry"), rs.getString("password"));
				student.setStudentId(rs.getInt("studentId"));
				student.setPersonId(rs.getInt("id"));
				
				studentList.add(student);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return studentList; 
	}
	
	public void deleteStudent(int personId, int studentId) {
		
		PreparedStatement deletePersonStatement, deleteStudentStatement;
		
		try {
			conn = Connect.connectDB();
			
			deleteStudentStatement = conn.prepareStatement(" DELETE FROM scoa.student WHERE id = ? ");
			deleteStudentStatement.setInt(1, studentId);
			deleteStudentStatement.executeUpdate();
			
			deletePersonStatement = conn.prepareStatement(" DELETE FROM scoa.person WHERE id = ? ");
			deletePersonStatement.setInt(1, personId);
			deletePersonStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
