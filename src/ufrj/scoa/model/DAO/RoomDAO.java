package ufrj.scoa.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ufrj.scoa.model.VO.Room;

public class RoomDAO {

	
		private Connection conn;
		private PreparedStatement ps;
		
		public void save(Room room) {
			
			try {
				
				conn = Connect.connectDB();
				
				ps = conn.prepareStatement("INSERT INTO room (id,building,floor,number) VALUES(DEFAULT,?,?,?)");
				ps.setString(1, room.getBuilding());
				ps.setInt(2, room.getFloor());
				ps.setInt(3, room.getNumber());
				ps.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		public Room getRoomById(int roomId) {
			Room room = null;
			
			try {
				conn = Connect.connectDB();
				
				ps = conn.prepareStatement("SELECT * FROM room WHERE id = ?");
				ps.setInt(1, roomId);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					room = new Room(rs.getInt("id"),rs.getString("building"), rs.getInt("number"), rs.getInt("floor"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return room;
		}
		
		public ArrayList<Room> list() {
			
			ArrayList<Room> roomList = new ArrayList<Room>();
			
			try {
				
				conn = Connect.connectDB();
				ps = conn.prepareStatement("SELECT * FROM room");
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					Room room = new Room(rs.getInt("id"),rs.getString("building"), rs.getInt("number"), rs.getInt("floor"));
					roomList.add(room);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return roomList; 
		}
		
		public ArrayList<Room> search(String building, int number, int floor) {
			
			ArrayList<Room> roomList = new ArrayList<Room>();
			
			try {
				
				conn = Connect.connectDB();
				
				String query = "SELECT * FROM room ";
				String connector = "WHERE";
				
				if(building.length() > 0) {
					query += connector + " building LIKE '%" + building + "%'";
					connector = "AND";
				}
				
				if(number > 0) {
					query += connector + " number =" + number;
					connector = "AND";
				}
				
				if(floor > 0) {
					query += connector + " floor =" + floor;
				}
				
				ps = conn.prepareStatement(query);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					Room room = new Room(rs.getInt("id"),rs.getString("building"), rs.getInt("number"), rs.getInt("floor"));
					roomList.add(room);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return roomList; 
		}
		
		public void delete(int roomId) {
			
			PreparedStatement deleteStatement;
			
			try {
				conn = Connect.connectDB();
				
				deleteStatement = conn.prepareStatement(" DELETE FROM scoa.room WHERE id = ? ");
				deleteStatement.setInt(1, roomId);
				deleteStatement.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}

}
