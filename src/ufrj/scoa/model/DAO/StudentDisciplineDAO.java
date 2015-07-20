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
	
	public static void save(StudentDiscipline studentDiscipline, int userRole) {
		
		try {
			
			conn = Connect.connectDB();
			
			ps = conn.prepareStatement("INSERT INTO student_class (student_id, class_id, grade, frequency, period, state) VALUES (?, ?, ?, ?, (SELECT currentPeriod FROM student WHERE id = ?), ?)");
			ps.setInt(1, studentDiscipline.getStudentId());
			ps.setInt(2, studentDiscipline.getClassId());
			ps.setFloat(3, 0);
			ps.setInt(4, 0);
			ps.setInt(5, studentDiscipline.getStudentId());
			
			if(userRole == Constants.ROLE_STUDENT) {
				ps.setInt(6, Constants.STUDENT_CLASS_PENDENT);
			}
			else {
				ps.setInt(6, Constants.STUDENT_CLASS_APPROVED);
			}
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Erro ao salvar estudante-disciplina");
		} 
	}
	
	public static boolean jaInscrito(StudentDiscipline studentDiscipline) {
		
		try {
			
			conn = Connect.connectDB();
			
			ps = conn.prepareStatement(" SELECT * FROM scoa.student_class " +
									   " WHERE student_id = ? " +
									   " AND class_id = ? " +
									   " AND period = (SELECT currentPeriod FROM student WHERE id = ?) ");
			ps.setInt(1, studentDiscipline.getStudentId());
			ps.setInt(2, studentDiscipline.getClassId());
			ps.setInt(3, studentDiscipline.getStudentId());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} 
		
		return false;
	}
	
	
	public static void updateGradesAndFrequencies(int[] student_ids, float[] grades, int[] frequencies, int class_id) {
		
		conn = Connect.connectDB();
		
		for(int i = 0  ; i < student_ids.length ; i++) {
			try {
				ps = conn.prepareStatement("UPDATE student_class SET grade = ?, frequency = ? WHERE student_id = ? AND class_id = ?");
				ps.setFloat(1, grades[i]);
				ps.setFloat(2, frequencies[i]);
				ps.setInt(3, student_ids[i]);
				ps.setInt(4, class_id);
	
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("Erro ao atualizar notas e frequencia");
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
			//e.printStackTrace();
			System.out.println("Erro ao pegar nota");
		}
		
		return grade;
	}
	
	public static void changeState(int student_id, int course_id, int new_state) {
		conn = Connect.connectDB();

		try {
			ps = conn.prepareStatement("UPDATE student_class SET state = ? WHERE student_id = ? AND class_id = ? ");
			ps.setInt(1, new_state);
			ps.setInt(2, student_id);
			ps.setInt(3, course_id);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Erro ao mudar estado");
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
			//e.printStackTrace();
			System.out.println("Erro ao pegar frequencia");
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
			//e.printStackTrace();
			System.out.println("Erro ao pegar requisicao de inscricao em disciplinas");
		}
		
		return list;
	}
	
	public static ArrayList<StudentDiscipline> getNewDisciplinesRequestsStudent(int studentId) {
		ArrayList<StudentDiscipline> list = new ArrayList<StudentDiscipline>();
		
		try {
			conn = Connect.connectDB();
			
			ps = conn.prepareStatement("SELECT class_id, state FROM student_class sc, student s WHERE student_id = ? AND sc.student_id = s.id AND sc.period = s.currentPeriod ");
			
			ps.setInt(1, studentId);

			ResultSet rs = ps.executeQuery();
			
			StudentDAO stdDAO = new StudentDAO();
			ClassDAO cDAO = new ClassDAO();
			
			while(rs.next()) {
				StudentDiscipline sd = new StudentDiscipline(stdDAO.getStudentById(studentId), cDAO.getClassById(rs.getInt("class_id")));
				sd.setState(rs.getInt("state"));
				list.add(sd);
			}
			
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("Erro ao pegar requisicao de inscricao por estudante");
		}
		
		return list;
	}
	
	public static boolean releaseGrades(int class_id) {
		
		try {
			conn = Connect.connectDB();
			
			ps = conn.prepareStatement(" UPDATE scoa.student_class SET state = ? WHERE class_id = ? AND state = ? ");
			ps.setInt(1, Constants.STUDENT_CLASS_GRADES_RELEASED);
			ps.setInt(2, class_id);
			ps.setInt(3, Constants.STUDENT_CLASS_APPROVED);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public static boolean notasLiberadas(int class_id) {
		
		try {
			conn = Connect.connectDB();
			
			ps = conn.prepareStatement(" SELECT state FROM scoa.student_class WHERE class_id = ? AND state = ? ");
			ps.setInt(1, class_id);
			ps.setInt(2, Constants.STUDENT_CLASS_GRADES_RELEASED);

			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			//e.printStackTrace();
		}
		
		return false;
	}
}
