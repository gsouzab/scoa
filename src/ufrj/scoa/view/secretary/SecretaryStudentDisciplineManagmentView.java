package ufrj.scoa.view.secretary;

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

import ufrj.scoa.model.DAO.StudentDAO;
import ufrj.scoa.model.DAO.StudentDisciplineDAO;
import ufrj.scoa.model.VO.Student;
import ufrj.scoa.model.VO.StudentDiscipline;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;

import java.awt.Font;

public class SecretaryStudentDisciplineManagmentView extends JPanel {
	private JTable table;
	private JOptionPane optionPane;
	private DefaultTableModel model;
	private JButton btnApprove;
	private JButton btnDisapprove;
	private JButton btnBack;
	
	public SecretaryStudentDisciplineManagmentView() {
        super();
       
        setLayout(null);

        model = new DefaultTableModel(){

            @Override
            public boolean isCellEditable(int row, int column) {
            	return false;
            }

        };
        
        table = new JTable(model);
        table.setBounds(12, 12, 426, 213);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(12, 12, 617, 308);
        add(scrollPane);
        
        btnApprove = new JButton("Aprovar");
        btnApprove.setBounds(6, 327, 117, 29);
        add(btnApprove);
        
        btnDisapprove = new JButton("Rejeitar");
        btnDisapprove.setBounds(135, 327, 117, 29);
        add(btnDisapprove);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(0, 397, 640, 9);
        add(separator);
        
        btnBack = new JButton("Voltar");
        btnBack.setFont(new Font("Arial", Font.BOLD, 12));
        btnBack.setBounds(519, 418, 110, 29);
        add(btnBack);
       
        populateTable();
        
        setVisible(true);
	}
	
	public void populateTable() {
		table.removeAll();
		
		model.addColumn("ID Aluno");// coluna escondida da tabela
		model.addColumn("ID Curso");// coluna escondida da tabela
		model.addColumn("Matrícula");
		model.addColumn("Nome");
		model.addColumn("Turma");
		model.addColumn("Situação");
		
		model.setColumnCount(6);
		
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		table.getColumnModel().getColumn(5).setPreferredWidth(80);
		
		table.removeColumn(table.getColumnModel().getColumn(0));
		table.removeColumn(table.getColumnModel().getColumn(0));
		
		model.setNumRows(0);
		
		ArrayList<StudentDiscipline> newDisciplinesRequests = StudentDisciplineDAO.getNewDisciplinesRequests(); 
		
		for(StudentDiscipline sd : newDisciplinesRequests) {
			model.addRow(new Object[]{sd.getStudent().getStudentId(), sd.getStudentClass().getId(), sd.getStudent().getEntry(), sd.getStudent().getName(), sd.getStudentClass().getName(), sd.getStateString() });
		}
	}

	public JTable getTable() {
		return table;
	}

	public JButton getBtnApprove() {
		return btnApprove;
	}

	public JButton getBtnDisapprove() {
		return btnDisapprove;
	}
	
	public JButton getBtnBack() {
		return btnBack;
	}
}
