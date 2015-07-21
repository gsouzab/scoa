package ufrj.scoa.model.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ufrj.scoa.model.VO.ComplaintSuggestion;

public class ComplaintSuggestionDAO {
	
	private static Connection conn;
	private static PreparedStatement ps;
	
	public static void saveComplaintSuggestion(ComplaintSuggestion cs) {
		
		try {
			
			conn = Connect.connectDB();
			
			ps = conn.prepareStatement("INSERT INTO complaint_suggestion VALUES(DEFAULT,?,?, ?)");
			ps.setInt(1, cs.getComplaint_or_suggestion());
			ps.setString(2, cs.getText());
			ps.setInt(3, cs.getStudent().getStudentId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Erro ao salvar reclamação/sugestão");
		} 
	}
	
	public static ArrayList<ComplaintSuggestion> list() {
		
		ArrayList<ComplaintSuggestion> complaintSuggestionList = new ArrayList<ComplaintSuggestion>();
		
		try {
			
			conn = Connect.connectDB();
			
			ps = conn.prepareStatement("SELECT * FROM complaint_suggestion");
			
			ResultSet rs = ps.executeQuery();
			
			StudentDAO studentDao = new StudentDAO();
			
			while(rs.next()) {
				ComplaintSuggestion complaintSuggestion = new ComplaintSuggestion(rs.getInt("id"), rs.getInt("complaint_or_suggestion"), rs.getString("text"), studentDao.getStudentById(rs.getInt("student_id")));
				complaintSuggestionList.add(complaintSuggestion);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Erro ao listar reclamações/sugestões");
		}
		
		return complaintSuggestionList; 
	}
	

}
