package ufrj.scoa.view.classes;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import ufrj.scoa.model.VO.Student;

public class ScheduleView extends JPanel {

	/**
	 * Create the panel.
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;
	JLabel lblInsertStudent;
	
	public ScheduleView() {
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
		    
		    JLabel lblHorarios = new JLabel("Horários");
		    lblHorarios.setFont(new Font("Arial", Font.BOLD, 16));
		    lblHorarios.setBounds(12, 12, 616, 26);
		    lblHorarios.setHorizontalAlignment(SwingConstants.CENTER);
		    add(lblHorarios);
		    
		    lblInsertStudent = new JLabel("Insira aluno aqui");
		    lblInsertStudent.setFont(new Font("Arial", Font.BOLD, 14));
		    lblInsertStudent.setBounds(12, 50, 616, 19);
		    lblInsertStudent.setHorizontalAlignment(SwingConstants.CENTER);
		    add(lblInsertStudent);
		

	}
	
	public void populateTable(ArrayList<ufrj.scoa.model.VO.Class> classesList, String studentName) {
		table.removeAll();
		
		lblInsertStudent.setText(studentName);
		
		model.addColumn("Disciplina");
		model.addColumn("Horário");
		model.addColumn("Prédio");
		model.addColumn("Andar");
		model.addColumn("Sala");
		model.setColumnCount(5);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		
		
		model.setNumRows(0);
		
		for(ufrj.scoa.model.VO.Class c : classesList) {
			model.addRow(new Object[]{c.getDiscipline().getName(), c.getTimeOfClass(), c.getRoom().getBuilding(), c.getRoom().getFloor(), c.getRoom().getNumber()});
		}
	}

}
