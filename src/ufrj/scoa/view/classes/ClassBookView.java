package ufrj.scoa.view.classes;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import ufrj.scoa.model.VO.Student;

import java.awt.Font;
import java.util.ArrayList;

public class ClassBookView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;
	JLabel lblInsertClass;

	/**
	 * Create the panel.
	 */
	public ClassBookView() {
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
	    
	    JLabel lblDirioDeClasse = new JLabel("Diário de Classe");
	    lblDirioDeClasse.setFont(new Font("Arial", Font.BOLD, 14));
	    lblDirioDeClasse.setBounds(12, 12, 616, 26);
	    lblDirioDeClasse.setHorizontalAlignment(SwingConstants.CENTER);
	    add(lblDirioDeClasse);
	    
	    lblInsertClass = new JLabel("Insira turma aqui");
	    lblInsertClass.setFont(new Font("Arial", Font.BOLD, 16));
	    lblInsertClass.setBounds(12, 50, 616, 19);
	    lblInsertClass.setHorizontalAlignment(SwingConstants.CENTER);
	    add(lblInsertClass);

	}
	
	public void populateTable(ArrayList<Student> studentsList, String className) {
		table.removeAll();
		
		lblInsertClass.setText(className);
		
		model.addColumn("Matrícula");
		model.addColumn("Nome");
		model.setColumnCount(2);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(0).setMaxWidth(100);
		
		model.setNumRows(0);
		
		for(Student s : studentsList) {
			model.addRow(new Object[]{s.getEntry(), s.getName()});
		}
	}
	
	
}
