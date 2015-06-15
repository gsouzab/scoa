package ufrj.scoa.view;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import ufrj.scoa.model.VO.Student;

public class StudentListView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DefaultListModel<Student> model;

	/**
	 * Create the panel.
	 */
	public StudentListView() {
		setLayout(null);
		
		model = new DefaultListModel<Student>();
		JList<Student> list = new JList<Student>(model);
		
		JLabel lblAlunosCadastrados = new JLabel("Resultado da busca por alunos");
		lblAlunosCadastrados.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlunosCadastrados.setFont(new Font("Arial", Font.BOLD, 15));
		lblAlunosCadastrados.setBounds(60, 12, 508, 37);
		add(lblAlunosCadastrados);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(12, 90, 616, 235);
		add(scrollPane);

	}
	
	public DefaultListModel<Student> getModel() {
		return model;
	}

	
}
