package ufrj.scoa.view;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import ufrj.scoa.model.VO.Professor;
import ufrj.scoa.model.VO.Student;

public class ProfessorListView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DefaultListModel<Professor> model;

	/**
	 * Create the panel.
	 */
	public ProfessorListView() {
		setLayout(null);

		model = new DefaultListModel<Professor>();
		JList<Professor> list = new JList<Professor>(model);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(12, 90, 616, 235);
		add(scrollPane);
		
		JLabel lblAlunosCadastrados = new JLabel("Resultado da busca por professores");
		lblAlunosCadastrados.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlunosCadastrados.setFont(new Font("Arial", Font.BOLD, 15));
		lblAlunosCadastrados.setBounds(60, 12, 508, 37);
		add(lblAlunosCadastrados);

	}

	public DefaultListModel<Professor> getModel() {
		return model;
	}
	
}
