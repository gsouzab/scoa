package ufrj.scoa.view.student;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import ufrj.scoa.model.VO.StudentDiscipline;

public class HistoryView extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;
	JLabel lblInsertStudent;
	
	public HistoryView() {
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
		    
		    JLabel lblHorarios = new JLabel("Histórico");
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
	
	public void populateTable(ArrayList<StudentDiscipline> disciplinesList, String studentName) {
		table.removeAll();
		
		lblInsertStudent.setText(studentName);
		
		model.addColumn("Disciplina");
		model.addColumn("Creditos");
		model.addColumn("Grau");
		model.addColumn("Frequencia");
		model.addColumn("Situacao Final");
		model.setColumnCount(5);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		
		
		model.setNumRows(0);
		
		int currentPeriod = 0;
		
		for(StudentDiscipline sd : disciplinesList) {
			
			if(sd.getPeriod() > currentPeriod) {
				currentPeriod = sd.getPeriod();
				
				if(currentPeriod > 1) {
					model.addRow(new Object[]{"", "", "", "", ""});
				}
				
				model.addRow(new Object[]{currentPeriod + "o período", "", "", "", ""});
			}
			
			String situacaoFinal;
			
			if(sd.getGrade() >= 5) {
				situacaoFinal = "AP";
			}
			else {
				situacaoFinal = "RM";
			}
			
			model.addRow(new Object[]{sd.getStudentClass().getName(), sd.getStudentClass().getCredits(), sd.getGrade(), sd.getAttendance(), situacaoFinal});
						
		}
		
	}

}
