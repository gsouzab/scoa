package ufrj.scoa.view;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import ufrj.scoa.model.VO.Course;

public class CoursesListView extends JPanel {


	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	
	private DefaultListModel<Course> model;
	
	public CoursesListView() {
		setLayout(null);
		
		model = new DefaultListModel<Course>();
		JList<Course> list = new JList<Course>(model);
		
		JLabel lblCursos = new JLabel("CURSOS CADASTRADOS");
		lblCursos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCursos.setFont(new Font("Arial", Font.BOLD, 15));
		lblCursos.setBounds(61, 48, 508, 37);
		add(lblCursos);

		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(71, 90, 478, 170);
		add(scrollPane);
		
	}

	public DefaultListModel<Course> getModel() {
		return model;
	}
	
	
}
