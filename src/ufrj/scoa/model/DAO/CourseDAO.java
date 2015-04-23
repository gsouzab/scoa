package ufrj.scoa.model.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ufrj.scoa.model.VO.Course;

public class CourseDAO {
	
	private Connection conn;
	private PreparedStatement ps;
	
	public CourseDAO() {
		Connection conn = null;
		PreparedStatement ps = null;
	}
	
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

}
