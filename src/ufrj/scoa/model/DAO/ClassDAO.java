package ufrj.scoa.model.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ufrj.scoa.model.VO.Class;
import ufrj.scoa.model.VO.Course;

public class ClassDAO {
	
	private Connection conn;
	private PreparedStatement ps;
	
	public void save(Class newClass) {
		
		try {
			
			conn = Connect.connectDB();
			
			ps = conn.prepareStatement("INSERT INTO class (id, course_id, discipline_id, room_id, name, code, credits, time_of_class) VALUES(DEFAULT,?,?,?,?,?,?,?)");
			ps.setInt(1, newClass.getCourse().getId());
			ps.setInt(2, newClass.getDiscipline().getId());
			ps.setInt(3, newClass.getRoom().getId());
			ps.setString(4, newClass.getName());
			ps.setString(5, newClass.getCode());
			ps.setInt(6, newClass.getCredits());
			ps.setString(7, newClass.getTimeOfClass());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public ArrayList<Class> search(String code, String name, int course_id, int discipline_id, int room_id, int credits) {
		ArrayList<Class> classList = new ArrayList<Class>();
		
		try {
			
			conn = Connect.connectDB();
			
			String query = "SELECT * FROM class ";
			String connector = "WHERE";
			
			if(code.length() > 0) {
				query += connector + " code LIKE '%" + code + "%'";
				connector = "AND";
			}
			
			if(name.length() > 0) {
				query += connector + " name LIKE '%" + name + "%'";
				connector = "AND";
			}
			
			if(course_id > 0) {
				query += connector + " course_id =" + course_id;
				connector = "AND";
			}
			
			if(discipline_id > 0) {
				query += connector + " discipline_id =" + discipline_id;
				connector = "AND";
			}
			
			if(room_id > 0) {
				query += connector + " room_id =" + room_id;
				connector = "AND";
			}
			
			if(credits > 0) {
				query += connector + " credits =" + credits;
			}
			
			ps = conn.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			CourseDAO courseDao = new CourseDAO();
			DisciplineDAO disciplineDao = new DisciplineDAO();
			RoomDAO roomDao = new RoomDAO();
			
			while(rs.next()) {
				
				Class c = new Class(rs.getInt("id"), rs.getInt("credits"),rs.getString("name"), rs.getString("code"), rs.getString("time_of_class"), courseDao.getCourseById(rs.getInt("course_id")), disciplineDao.getDisciplineById(rs.getInt("discipline_id")), roomDao.getRoomById(rs.getInt("room_id")));
				classList.add(c);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return classList; 
	}
	
	public ArrayList<Class> list() {
		
		ArrayList<Class> classList = new ArrayList<Class>();
		
		try {
			
			conn = Connect.connectDB();
			
			ps = conn.prepareStatement("SELECT * FROM class");
			
			ResultSet rs = ps.executeQuery();
			
			CourseDAO courseDao = new CourseDAO();
			DisciplineDAO disciplineDao = new DisciplineDAO();
			RoomDAO roomDao = new RoomDAO();
			
			while(rs.next()) {
				
				Class c = new Class(rs.getInt("id"), rs.getInt("credits"),rs.getString("name"), rs.getString("code"), rs.getString("time_of_class"), courseDao.getCourseById(rs.getInt("course_id")), disciplineDao.getDisciplineById(rs.getInt("discipline_id")), roomDao.getRoomById(rs.getInt("room_id")));
				classList.add(c);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return classList; 
	}
	
	public void delete(int classId) {
		
		PreparedStatement deleteStatement;
		
		try {
			conn = Connect.connectDB();
			
			deleteStatement = conn.prepareStatement(" DELETE FROM scoa.class WHERE id = ? ");
			deleteStatement.setInt(1, classId);
			deleteStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}


}
