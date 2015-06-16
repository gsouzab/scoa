package ufrj.scoa.view.secretary;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import ufrj.scoa.model.VO.Course;
import ufrj.scoa.model.VO.Secretary;

import javax.swing.JButton;

public class SecretaryListView extends JPanel {


	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	
	private DefaultListModel<Secretary> model;
	private JButton btnExcluir;
	private JList<Secretary> list;
	
	
	
	public SecretaryListView() {
		setLayout(null);
		
		model = new DefaultListModel<Secretary>();
		list = new JList<Secretary>(model);
		
		JLabel lblCursos = new JLabel("Resultado da busca por secretários(as)");
		lblCursos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCursos.setFont(new Font("Arial", Font.BOLD, 15));
		lblCursos.setBounds(60, 12, 508, 37);
		add(lblCursos);

		JScrollPane scrollPane;scrollPane = new JScrollPane(list);
		scrollPane.setBounds(12, 90, 616, 235);
		add(scrollPane);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(12, 337, 103, 25);
		add(btnExcluir);
		
	}

	public DefaultListModel<Secretary> getModel() {
		return model;
	}

	public JButton getBtnExcluir() {
		return btnExcluir;
	}

	public JList<Secretary> getList() {
		return list;
	}

	public void setModel(DefaultListModel<Secretary> model) {
		this.model = model;
	}

	public void setList(JList<Secretary> list) {
		this.list = list;
	}
	
	public void resetList() {
		this.model.removeAllElements();
	}
	
	
}
