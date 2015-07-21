package ufrj.scoa.view.complaint_suggestion;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import ufrj.scoa.model.DAO.StudentDisciplineDAO;
import ufrj.scoa.model.VO.ComplaintSuggestion;
import ufrj.scoa.model.VO.Student;
import ufrj.scoa.model.VO.StudentDiscipline;
import ufrj.scoa.util.Constants;

public class ComplaintSuggestionListView extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;
	
	public ComplaintSuggestionListView() {
		setLayout(null);
			
	    model = new DefaultTableModel() {
	    	@Override
            public boolean isCellEditable(int row, int column) {
            	return false;
            }
	    };
	    
	    table = new JTable(model);
	    table.setFont(new Font("Arial", Font.PLAIN, 12));

	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setBounds(12, 96, 616, 331);
	    add(scrollPane);
	    
	    JLabel label = new JLabel("Sugestões/Reclamações");
	    label.setFont(new Font("Arial", Font.BOLD, 16));
	    label.setBounds(12, 12, 616, 26);
	    label.setHorizontalAlignment(SwingConstants.CENTER);
	    add(label);
	}
	
	public void populateTable(ArrayList<ComplaintSuggestion> complaintSuggestionList) {
		table.removeAll();
		
		model.addColumn("ID reclamção/sugestao");// Coluna escondida da tabela
		model.addColumn("Aluno");
		model.addColumn("Tipo");
		model.addColumn("Texto");
		
		model.setColumnCount(4);
		
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(350);
		
		table.removeColumn(table.getColumnModel().getColumn(0));
		
		model.setNumRows(0);
		
		for(ComplaintSuggestion cs : complaintSuggestionList) {
			model.addRow(new Object[]{cs.getId(), cs.getStudent().getName(), Constants.COMPLAINT_SUGGESTION_STRING[cs.getComplaint_or_suggestion()] , cs.getText()});
		}
		
	}

}
