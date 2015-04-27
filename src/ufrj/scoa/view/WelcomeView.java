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
		lblScoa.setBounds(284, 0, 71, 29);
		add(lblScoa);
		lblScoa.setFont(new Font("Arial", Font.BOLD, 25));
		lblScoa.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel lblSistemaControle = new JLabel("Sistema de Controle Acad\u00EAmico");
		lblSistemaControle.setBounds(206, 38, 227, 19);
		add(lblSistemaControle);
		lblSistemaControle.setFont(new Font("Arial", Font.PLAIN, 16));
		lblSistemaControle.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel lblMenuAcima = new JLabel("Utilize o menu acima para navegar");
		lblMenuAcima.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMenuAcima.setBounds(225, 433, 190, 15);
		lblMenuAcima.setHorizontalAlignment(JLabel.CENTER);
		add(lblMenuAcima);
	}
}
