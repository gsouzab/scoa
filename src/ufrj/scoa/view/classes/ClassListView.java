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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClassListView extends JPanel {


	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	
	JLabel lblCursos;
	
	private DefaultListModel<Class> model;
	private JList<Class> list;
	private JButton btnInsertGradesAndFrequencies;
	private JButton btnConsultaDiarioClasse;
	
	public ClassListView() {
		setLayout(null);
		
		model = new DefaultListModel<Class>();
		list = new JList<Class>(model);
		
		lblCursos = new JLabel("Resultado da busca por turmas");
		lblCursos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCursos.setFont(new Font("Arial", Font.BOLD, 15));
		lblCursos.setBounds(60, 12, 508, 37);
		add(lblCursos);

		JScrollPane scrollPane;scrollPane = new JScrollPane(list);
		scrollPane.setBounds(12, 90, 616, 235);
		add(scrollPane);
		
		btnInsertGradesAndFrequencies = new JButton("Lançar notas/frequências");
		btnInsertGradesAndFrequencies.setBounds(12, 337, 247, 25);
		add(btnInsertGradesAndFrequencies);
		
		btnConsultaDiarioClasse = new JButton("Consultar diário de classe");
		btnConsultaDiarioClasse.setBounds(282, 337, 232, 25);
		add(btnConsultaDiarioClasse);
		
	}
	
	public JButton getBtnInsertGradesAndFrequencies() {
		return btnInsertGradesAndFrequencies;
	}
	

	public JButton getBtnConsultaDiarioClasse() {
		return btnConsultaDiarioClasse;
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

	public JLabel getLblCursos() {
		return lblCursos;
	}
}
