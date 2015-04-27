package ufrj.scoa.view;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.DropMode;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class CoursesListView extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private JTextArea txtrAwcCurso;
	
	public CoursesListView() {
		setLayout(null);
		
		
		txtrAwcCurso = new JTextArea();
		txtrAwcCurso.setEditable(false);
		txtrAwcCurso.setBackground(UIManager.getColor("Button.background"));
		txtrAwcCurso.setBounds(199, 98, 170, 96);
		txtrAwcCurso.setLineWrap(true);
		//add(textPane);
		
		JScrollPane scrollPane = new JScrollPane(txtrAwcCurso);
		scrollPane.setBounds(61, 98, 508, 128);
		
		add(scrollPane);
		
		JLabel lblCursos = new JLabel("CURSOS CADASTRADOS");
		lblCursos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCursos.setFont(new Font("Arial", Font.BOLD, 15));
		lblCursos.setBounds(61, 48, 508, 37);
		add(lblCursos);
		
		

	}

	public JTextArea getTxtrAwcCurso() {
		return txtrAwcCurso;
	}

	public void setTxtrAwcCurso(JTextArea txtrAwcCurso) {
		this.txtrAwcCurso = txtrAwcCurso;
	}
	
	
}
