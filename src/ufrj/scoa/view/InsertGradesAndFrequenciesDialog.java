package ufrj.scoa.view;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;

import ufrj.scoa.model.DAO.StudentDAO;
import ufrj.scoa.model.DAO.StudentDisciplineDAO;
import ufrj.scoa.model.VO.Student;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class InsertGradesAndFrequenciesDialog extends JDialog {
	private JTable table;
	private JOptionPane optionPane;
	private DefaultTableModel model;
	private JButton btnInsert;
	private JButton btnCancel;
	
	public InsertGradesAndFrequenciesDialog(JFrame frame, JPanel parent) {
        super(frame, true);
        setTitle("Lançamento de Notas/Frequências");
        setBounds(100, 100, 520, 300);

        getContentPane().setLayout(null);

        model = new DefaultTableModel(){

            @Override
            public boolean isCellEditable(int row, int column) {
            	return column == 3 || column == 4 ? true : false;
            }

            @Override
            public Class getColumnClass(int column) {
            	if(column == 3) {
            		return Float.class;
            	} else if (column == 4) {
            		return Integer.class;
            	}
            	
            	return String.class;
            }
        };
        
        table = new JTable(model);
        table.setBounds(12, 12, 426, 213);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(12, 12, 496, 185);
        getContentPane().add(scrollPane);
        
        btnInsert = new JButton("Lançar");
        btnInsert.setBounds(12, 209, 117, 29);
        getContentPane().add(btnInsert);
        
        btnCancel = new JButton("Cancelar");
        btnCancel.setIcon(new ImageIcon(InsertGradesAndFrequenciesDialog.class.getResource("/com/sun/java/swing/plaf/motif/icons/Error.gif")));
        btnCancel.setBounds(141, 209, 136, 29);
        getContentPane().add(btnCancel);
        
       
        setLocationRelativeTo(parent);
        setResizable(false);
        setVisible(false);
	}
	
	public void openDialog(int class_id) {
		StudentDAO studentDao = new StudentDAO();
	        
        populateTable(studentDao.getStudentsByClassId(class_id),class_id);
	    setVisible(true);
	}
	
	private void populateTable(ArrayList<Student> studentsList, int class_id) {
		table.removeAll();
		
		model.addColumn("ID Aluno");// Coluna escondida da tabela
		model.addColumn("Matrícula");
		model.addColumn("Nome");
		model.addColumn("Nota (0..10)");
		model.addColumn("Frequência (%)");
		
		model.setColumnCount(5);
		
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(30);
		
		table.removeColumn(table.getColumnModel().getColumn(0));
		
		model.setNumRows(0);
		
		for(Student s : studentsList) {
			model.addRow(new Object[]{s.getStudentId(), s.getEntry(), s.getName(), StudentDisciplineDAO.getGrade(s.getStudentId(), class_id) , StudentDisciplineDAO.getFrequency(s.getStudentId(), class_id)});
		}
	}

	public JTable getTable() {
		return table;
	}

	public JButton getBtnInsert() {
		return btnInsert;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}
	
	
		
}
