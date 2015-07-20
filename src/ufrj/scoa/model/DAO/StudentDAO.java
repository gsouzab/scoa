package ufrj.scoa.model.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ufrj.scoa.model.VO.Course;
import ufrj.scoa.model.VO.Room;
import ufrj.scoa.model.VO.Student;
import ufrj.scoa.model.VO.StudentDiscipline;
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
			//e.printStackTrace();
			System.out.println("Erro ao salvar estudante");
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
			//e.printStackTrace();
			System.out.println("Erro ao listar estudantes");
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
			//e.printStackTrace();
			System.out.println("Erro ao pegar estudante por id");
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
			//e.printStackTrace();
			System.out.println("Erro ao pegar estudante por id de pessoa");
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
			//e.printStackTrace();
			System.out.println("Erro ao pegar estudante por id");
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
			//e.printStackTrace();
			System.out.println("Erro ao buscar estudante");
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
			//e.printStackTrace();
			System.out.println("Erro ao deletar estudante");
		} 
	}
	
	public ArrayList<ufrj.scoa.model.VO.Class> getTimeOfClassByStudentId(int student_id) {
		
		ArrayList<ufrj.scoa.model.VO.Class> classList = null;
		
		try {
			
			classList = new ArrayList<ufrj.scoa.model.VO.Class>();
			
			conn = Connect.connectDB();
			
			PreparedStatement ps = conn.prepareStatement("SELECT c.name, c.time_of_class, r.building, r.floor, r.number FROM student_class sc, student s, class c, room r " +
														 " WHERE sc.student_id = ? " +
												 		 " AND sc.student_id = s.id " +
												 		 " AND sc.period =  s.currentPeriod " +
												 		 " AND sc.class_id = c.id" +
												 		 " AND c.room_id = r.id ");
			ps.setInt(1, student_id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				ufrj.scoa.model.VO.Class theClass = new ufrj.scoa.model.VO.Class();
				theClass.setName(rs.getString("name"));
				theClass.setTimeOfClass(rs.getString("time_of_class"));
				Room room = new Room(rs.getString("building"), rs.getInt("number"), rs.getInt("floor"));
				theClass.setRoom(room);
				classList.add(theClass);

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return classList; 
	}

	public boolean newPeriod() {
	
		PreparedStatement newPeriod;
		
		try {
			conn = Connect.connectDB();
			
			newPeriod = conn.prepareStatement("UPDATE student SET currentPeriod = currentPeriod + 1 ");
			newPeriod.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
		
		return true;
	}
	
public ArrayList<StudentDiscipline> getHistory(int student_id) {
		
		ArrayList<StudentDiscipline> disciplinesList = null;
		
		try {
			
			disciplinesList = new ArrayList<StudentDiscipline>();
			conn = Connect.connectDB();
			
			PreparedStatement ps = conn.prepareStatement(" SELECT c.name, c.credits, sc.grade, sc.frequency, sc.period FROM student_class sc, class c " +
														 " WHERE sc.student_id = ? " +
														 " AND sc.class_id = c.id "+
														 " ORDER BY  sc.period ASC ");

			ps.setInt(1, student_id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {

				StudentDiscipline sd = new StudentDiscipline();
 				ufrj.scoa.model.VO.Class theClass = new ufrj.scoa.model.VO.Class();
 				
				theClass.setName(rs.getString("name"));
				theClass.setCredits(rs.getInt("credits"));
				sd.setGrade(rs.getFloat("grade"));
				sd.setAttendance(rs.getInt("frequency"));
				sd.setPeriod(rs.getInt("period"));
				sd.setStudentClass(theClass);
				disciplinesList.add(sd);

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return disciplinesList; 
	}


}
