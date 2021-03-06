package ufrj.scoa.model.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ufrj.scoa.model.VO.Course;

public class CourseDAO {
	
	private Connection conn;
	private PreparedStatement ps;
	
	public void saveCourse(Course course) {
		
		try {
			
			conn = Connect.connectDB();
			
			ps = conn.prepareStatement("INSERT INTO course VALUES(DEFAULT,?,?,?)");
			ps.setString(1, course.getName());
			ps.setString(2, course.getCode());
			ps.setString(3, course.getDescription());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Erro ao salvar curso");
		} 
	}
	
	public void deleteCourse(int courseId) {
		
		try {
			conn = Connect.connectDB();
			
			ps = conn.prepareStatement(" DELETE FROM scoa.course WHERE id = ? ");
			ps.setInt(1, courseId);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Erro ao deletar curso.");
		} 
	}
	
	public Course getCourseById(int courseId) {
		Course course = null;
		
		try {
			conn = Connect.connectDB();
			
			ps = conn.prepareStatement("SELECT * FROM course WHERE id = ?");
			ps.setInt(1, courseId);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				course = new Course(rs.getString("name"), rs.getString("code"), rs.getString("description"));
				course.setId(rs.getInt("id"));
			}
			
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("Erro ao pegar curso");
		}
		
		return course;
	}
	
	public ArrayList<Course> list() {
		
		ArrayList<Course> courseList = new ArrayList<Course>();
		
		try {
			
			conn = Connect.connectDB();
			
			ps = conn.prepareStatement("SELECT * FROM course");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Course course = new Course(rs.getString("name"), rs.getString("code"), rs.getString("description"));
				course.setId(rs.getInt("id"));
				
				courseList.add(course);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Erro ao listar cursos");
		}
		
		return courseList; 
	}
	
	public ArrayList<Course> searchCourse(String name, String code, String description) {
		
		ArrayList<Course> courseList = new ArrayList<Course>();

		try {
			
			conn = Connect.connectDB();
			
			String query = " SELECT * FROM course WHERE id = id";
			
			if(name.length() > 0) {
				query += " AND name like '%" +name+ "%' ";
			}
			
			if(code.length() > 0) {
				query += " AND code like '%" +code+ "%' ";
			}
			
			if(description.length() > 0) {
				query += " AND description like '%" +description+ "%' ";
			}
			
			
			ps = conn.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Course course = new Course(rs.getString("name"), rs.getString("code"), rs.getString("description"));
				course.setId(rs.getInt("id"));
				
				courseList.add(course);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Erro ao buscar cursos");
		}
		
		return courseList; 
	}

}
