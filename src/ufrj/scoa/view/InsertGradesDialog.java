package ufrj.scoa.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.util.ArrayList;

import ufrj.scoa.model.VO.Student;
import javax.swing.JScrollPane;

public class InsertGradesDialog extends JDialog implements ActionListener {
	private JTable table;
	private JOptionPane optionPane;
	private DefaultTableModel model;
	
	public InsertGradesDialog(JFrame frame, JPanel parent, ArrayList<Student> studentsList) {
        super(frame, true);
        setBounds(100, 100, 450, 280);

        getContentPane().setLayout(null);

        model = new DefaultTableModel(){

            @Override
            public boolean isCellEditable(int row, int column) {
            	return column == 2 ? true : false;
            }

       };
        
        table = new JTable(model);
        table.setBounds(12, 12, 426, 213);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(12, 12, 426, 185);
        getContentPane().add(scrollPane);
        
        populateTable(studentsList);
        
        setLocationRelativeTo(parent);
        setResizable(false);
        setVisible(true);
	}
	
	private void populateTable(ArrayList<Student> studentsList) {
		model.addColumn("Matrícula");
		model.addColumn("Nome");
		model.addColumn("Nota");
		
		
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		
		model.setNumRows(0);
		
		for(Student s : studentsList) {
			model.addRow(new Object[]{s.getEntry(), s.getName(), 0});
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
