package ufrj.scoa.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class WelcomeView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public WelcomeView() {
		setLayout(null);
		
		JLabel lblScoa = new JLabel("SCOA");
		lblScoa.setBounds(284, 6, 71, 31);
		add(lblScoa);
		lblScoa.setFont(new Font("Dialog", Font.BOLD, 25));
		
		JLabel lblSistemaControle = new JLabel("Sistema de Controle Acadêmico");
		lblSistemaControle.setBounds(196, 45, 248, 20);
		add(lblSistemaControle);
		lblSistemaControle.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		JLabel lblMenuAcima = new JLabel("Utilize o menu acima para navegar");
		lblMenuAcima.setBounds(212, 418, 216, 16);
		add(lblMenuAcima);
	}
}
