package ufrj.scoa.view.classes;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import ufrj.scoa.model.VO.Class;

import javax.swing.JButton;

public class ClassListView extends JPanel {


	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	
	private DefaultListModel<Class> model;
	private JButton btnExcluir;
	private JButton btnInsertGrades;
	private JList<Class> list;
	private JButton btnInsertFrequencies;
	
	public ClassListView() {
		setLayout(null);
		
		model = new DefaultListModel<Class>();
		list = new JList<Class>(model);
		
		JLabel lblCursos = new JLabel("Resultado da busca por turmas");
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
		
		btnInsertGrades = new JButton("Lançar notas");
		btnInsertGrades.setBounds(12, 61, 133, 25);
		add(btnInsertGrades);
		
		btnInsertFrequencies = new JButton("Lançar frequências");
		btnInsertFrequencies.setBounds(157, 61, 154, 25);
		add(btnInsertFrequencies);
		
	}

	public JButton getBtnExcluir() {
		return btnExcluir;
	}
	
	public JButton getBtnInsertGrades() {
		return btnInsertGrades;
	}

	public JButton getBtnInsertFrequencies() {
		return btnInsertFrequencies;
	}

	public JList<Class> getList() {
		return list;
	}

	public void setList(ArrayList<Class> list) {
		this.model.removeAllElements();
		
		for(Class c : list) {
			this.model.addElement(c);			
		}		
	}
	
	public void resetList() {
		this.model.removeAllElements();
	}
	
	public String getWindowTitle() {
		return "Listar turmas";
		
	}
}
