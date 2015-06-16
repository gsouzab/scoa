package ufrj.scoa.model.DAO;

import ufrj.scoa.util.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ufrj.scoa.model.VO.StudentDiscipline;

public class StudentDisciplineDAO {
	
	private static Connection conn;
	private static PreparedStatement ps;
	
	public static void save(StudentDiscipline studentDiscipline) {
		
		try {
			
			conn = Connect.connectDB();
			
			ps = conn.prepareStatement("INSERT INTO student_class (student_id, class_id, grade, frequency, period, state) VALUES (?, ?, ?, ?,?,?)");
			ps.setInt(1, studentDiscipline.getStudentId());
			ps.setInt(2, studentDiscipline.getClassId());
			ps.setFloat(3, 0);
			ps.setInt(4, 0);
			ps.setInt(5, 1);
			ps.setInt(6,0);
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
				ps = conn.prepareStatement("UPDATE student_class SET grade = ? WHERE student_id = ?");
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
			
			ps = conn.prepareStatement("SELECT grade FROM student_class WHERE class_id = ? AND student_id = ?");
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
	
	public static void updateFrequencies(int[] student_ids, int[] frequencies) {
		
		conn = Connect.connectDB();
		
		for(int i = 0  ; i < student_ids.length ; i++) {
			try {
				ps = conn.prepareStatement("UPDATE student_class SET frequency = ? WHERE student_id = ?");
				ps.setFloat(1, frequencies[i]);
				ps.setInt(2, student_ids[i]);
	
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
	
	public static int getFrequency(int student_id, int class_id) {
		int attendance = 0;
		
		try {
			conn = Connect.connectDB();
			
			ps = conn.prepareStatement("SELECT frequency FROM student_class WHERE class_id = ? AND student_id = ?");
			ps.setInt(1, class_id);
			ps.setInt(2, student_id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				attendance = rs.getInt("frequency");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return attendance;
	}
	
	public static ArrayList<StudentDiscipline> getNewDisciplinesRequests() {
		ArrayList<StudentDiscipline> list = new ArrayList<StudentDiscipline>();
		
		try {
			conn = Connect.connectDB();
			
			ps = conn.prepareStatement("SELECT student_id, class_id FROM student_class WHERE state = ?");
			ps.setInt(1, Constants.STUDENT_CLASS_PENDENT);

			ResultSet rs = ps.executeQuery();
			
			StudentDAO stdDAO = new StudentDAO();
			ClassDAO cDAO = new ClassDAO();
			
			while(rs.next()) {
				StudentDiscipline sd = new StudentDiscipline(stdDAO.getStudentById(rs.getInt("student_id")), cDAO.getClassById(rs.getInt("class_id")));
				list.add(sd);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
