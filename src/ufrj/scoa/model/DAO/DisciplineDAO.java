package ufrj.scoa.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ufrj.scoa.model.VO.Discipline;


public class DisciplineDAO {

	
		private Connection conn;
		private PreparedStatement ps;
		
		public void save(Discipline discipline) {
			
			try {
				
				conn = Connect.connectDB();
				
				ps = conn.prepareStatement("INSERT INTO discipline (id, name, description, code) VALUES (DEFAULT, ?, ?, ?)");
				ps.setString(1, discipline.getName());
				ps.setString(2, discipline.getDescription());
				ps.setString(3, discipline.getCode());
				ps.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		public Discipline getDisciplineById(int disciplineId) {
			Discipline discipline = null;
			
			try {
				conn = Connect.connectDB();
				
				ps = conn.prepareStatement("SELECT * FROM discipline WHERE id = ?");
				ps.setInt(1, disciplineId);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					discipline = new Discipline(rs.getString("name"), rs.getString("description"), rs.getString("code"));
					discipline.setId(rs.getInt("id"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return discipline;
		}
		
		public ArrayList<Discipline> list() {
			
			ArrayList<Discipline> disciplineList = new ArrayList<Discipline>();
			
			try {
				
				conn = Connect.connectDB();
				ps = conn.prepareStatement("SELECT d.id, d.name, d.description, d.code FROM discipline d");
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					
					Discipline discipline = new Discipline(rs.getString("name"), rs.getString("description"), rs.getString("code"));
					discipline.setId(rs.getInt("id"));
					disciplineList.add(discipline);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return disciplineList; 
		}

}
