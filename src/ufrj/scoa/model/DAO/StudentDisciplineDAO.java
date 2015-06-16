package ufrj.scoa.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ufrj.scoa.model.VO.Room;
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
	
	
	public static void updateGrades(int[] student_ids, float[] grades) {
		
		conn = Connect.connectDB();
		
		for(int i = 0  ; i < student_ids.length ; i++) {
			try {
				ps = conn.prepareStatement("UPDATE student_classes SET grade = ? WHERE student_id = ?");
				ps.setFloat(1, grades[i]);
				ps.setInt(2, student_ids[i]);
	
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
	
	public static float getGrade(int student_id, int class_id) {
		float grade = 0;
		
		try {
			conn = Connect.connectDB();
			
			ps = conn.prepareStatement("SELECT grade FROM student_classes WHERE class_id = ? AND student_id = ?");
			ps.setInt(1, class_id);
			ps.setInt(2, student_id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				grade = rs.getFloat("grade");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return grade;
	}
	
}
