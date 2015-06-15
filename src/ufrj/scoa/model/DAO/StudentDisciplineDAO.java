package ufrj.scoa.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ufrj.scoa.model.VO.StudentDiscipline;

public class StudentDisciplineDAO {
	
	private static Connection conn;
	private static PreparedStatement ps;
	
	public static void save(StudentDiscipline studentDiscipline) {
		
		try {
			
			conn = Connect.connectDB();
			
			ps = conn.prepareStatement("INSERT INTO student_classes (student_id, class_id, grade, attendance) VALUES (?, ?, ?, ?)");
			ps.setInt(1, studentDiscipline.getStudentId());
			ps.setInt(2, studentDiscipline.getClassId());
			ps.setFloat(3, 0);
			ps.setInt(4, 0);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	
	
}
