package ufrj.scoa.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

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
		lblScoa.setBounds(10, 0, 620, 33);
		add(lblScoa);
		lblScoa.setFont(new Font("Dialog", Font.BOLD, 25));
		lblScoa.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel lblSistemaControle = new JLabel("Sistema de Controle Acad\u00EAmico");
		lblSistemaControle.setBounds(10, 38, 620, 21);
		add(lblSistemaControle);
		lblSistemaControle.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblSistemaControle.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel lblMenuAcima = new JLabel("Utilize o menu acima para navegar");
		lblMenuAcima.setBounds(10, 433, 620, 16);
		lblMenuAcima.setHorizontalAlignment(JLabel.CENTER);
		add(lblMenuAcima);
	}
}
