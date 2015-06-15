package ufrj.scoa.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.util.ArrayList;

import ufrj.scoa.model.VO.Student;

public class InsertGradesDialog extends JDialog implements ActionListener {
	private JTable table;

	public InsertGradesDialog(JFrame frame, JPanel parent, ArrayList<Student> studentsList) {
        super(frame, true);
        
        table = new JTable();
        getContentPane().add(table, BorderLayout.CENTER);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
