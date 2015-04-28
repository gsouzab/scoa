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
	
	public void save(Course course) {
		
		try {
			
			conn = Connect.connectDB();
			
			ps = conn.prepareStatement("INSERT INTO course VALUES(DEFAULT,?,?,?)");
			ps.setString(1, course.getName());
			ps.setString(2, course.getCode());
			ps.setString(3, course.getDescription());
			ps.executeUpdate();
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
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
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return courseList; 
	}

}
