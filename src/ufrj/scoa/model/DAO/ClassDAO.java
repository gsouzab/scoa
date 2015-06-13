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
			
			ps = conn.prepareStatement("INSERT INTO class VALUES(DEFAULT,?,?,?,?)");
			ps.setInt(1, newClass.getCourse().getId());
			ps.setString(2, newClass.getName());
			ps.setString(3, newClass.getCode());
			ps.setBoolean(4, newClass.getIsActive());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public ArrayList<Class> list() {
		
		ArrayList<Class> classList = new ArrayList<Class>();
		
		try {
			
			conn = Connect.connectDB();
			
			ps = conn.prepareStatement("SELECT * FROM class");
			
			ResultSet rs = ps.executeQuery();
			
			CourseDAO courseDao = new CourseDAO();
			
			while(rs.next()) {
				
				Class c = new Class(rs.getInt("id"),rs.getString("name"), rs.getString("code"),courseDao.getCourseById(rs.getInt("course_id")), rs.getBoolean("is_active"));
				
				classList.add(c);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return classList; 
	}

}
