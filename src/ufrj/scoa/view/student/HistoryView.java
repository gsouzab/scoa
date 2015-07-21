package ufrj.scoa.view.student;

import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import ufrj.scoa.model.DAO.StudentDisciplineDAO;
import ufrj.scoa.model.VO.StudentDiscipline;
import ufrj.scoa.util.Constants;

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
		
		int studentId = disciplinesList.get(0).getStudentId();
		DecimalFormat df =  new DecimalFormat("0.00");
		table.removeAll();
		
		lblInsertStudent.setText(studentName);
		
		model.addColumn("Disciplina");
		model.addColumn("Cred");
		model.addColumn("Grau Final");
		model.addColumn("Frequência");
		model.addColumn("Sit. Fin.");
		model.setColumnCount(5);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setMaxWidth(40);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setMaxWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setMaxWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(4).setMaxWidth(60);
		
		
		model.setNumRows(0);
		
		int currentPeriod = 0;
		
		for(StudentDiscipline sd : disciplinesList) {
	
			if(sd.getPeriod() > currentPeriod) {
				currentPeriod = sd.getPeriod();
				
				if(currentPeriod > 1) {
					float periodCr = 0.0f;
					ArrayList<StudentDiscipline> list = StudentDisciplineDAO.getGrauCreditosDisciplinasCursadas(sd.getStudentId(), currentPeriod-1);
					periodCr = calculateCR(list);
					model.addRow(new Object[]{"", "", "", "CR período:", df.format(periodCr)});
					model.addRow(new Object[]{"", "", "", "", ""});
				}
				
				model.addRow(new Object[]{currentPeriod +"° período", "", "", "", ""});
				model.addRow(new Object[]{"", "", "", "", ""});
			}
			
			String situacaoFinal;
			
			if(sd.getGrade() >= 5) {
				situacaoFinal = "AP";
			}
			else {
				situacaoFinal = "RM";
			}
			
			if(sd.getState() == Constants.STUDENT_CLASS_GRADES_RELEASED) {
				model.addRow(new Object[]{sd.getStudentClass().getDiscipline().getName(), sd.getStudentClass().getCredits(), sd.getGrade(), sd.getAttendance() + "%", situacaoFinal});
			}
			else if(sd.getState() == Constants.STUDENT_CLASS_APPROVED) {
				model.addRow(new Object[]{sd.getStudentClass().getDiscipline().getName(), sd.getStudentClass().getCredits(), "", "", ""});
			}

					
		}
		
		float periodCr = 0.0f;
		ArrayList<StudentDiscipline> listPeriod = StudentDisciplineDAO.getGrauCreditosDisciplinasCursadas(studentId, currentPeriod);
		periodCr = calculateCR(listPeriod);
		model.addRow(new Object[]{"", "", "", "CR período:", listPeriod.size() > 0 ? df.format(periodCr): ""});
		
		float totalCr = 0.0f;
		ArrayList<StudentDiscipline> list = StudentDisciplineDAO.getGrauCreditosDisciplinasCursadas(studentId, 0);
		totalCr = calculateCR(list);
		model.addRow(new Object[]{"", "", "", "", ""});
		model.addRow(new Object[]{"", "", "", "CR acumulado:", list.size() > 0 ? df.format(totalCr): ""});
		
		
	}
	
	private float calculateCR(ArrayList<StudentDiscipline> disciplinesList) {
		float sumCR = 0.0f;
		int sumCredits = 0;
		float returnCR = 0.0f;
		
		for(StudentDiscipline sd : disciplinesList) {
			sumCR += (sd.getGrade() * sd.getStudentClass().getCredits());
			sumCredits += sd.getStudentClass().getCredits();
		}
		
		returnCR = sumCR/sumCredits;
		
		return returnCR;
	}
	

}
