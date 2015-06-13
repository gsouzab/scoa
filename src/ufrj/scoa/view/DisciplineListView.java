package ufrj.scoa.view;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import ufrj.scoa.model.VO.Discipline;

public class DisciplineListView extends JPanel {


	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	
	private DefaultListModel<Discipline> model;
	
	public DisciplineListView() {
		setLayout(null);
		
		model = new DefaultListModel<Discipline>();
		JList<Discipline> list = new JList<Discipline>(model);
		
		JLabel lblDisciplines = new JLabel("DISCIPLINAS CADASTRADAS");
		lblDisciplines.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisciplines.setFont(new Font("Arial", Font.BOLD, 15));
		lblDisciplines.setBounds(61, 48, 508, 37);
		add(lblDisciplines);

		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(20, 90, 600, 350);
		add(scrollPane);
		
	}

	public DefaultListModel<Discipline> getModel() {
		return model;
	}
	
	
}
