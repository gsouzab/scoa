package ufrj.scoa.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class WelcomeView extends ScoaBaseFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public WelcomeView() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblScoa = new JLabel("SCOA");
		lblScoa.setFont(new Font("Dialog", Font.BOLD, 25));
		lblScoa.setBounds(269, 0, 89, 30);
		panel.add(lblScoa);
		
		JLabel lblSistemaControle = new JLabel("Sistema de Controle Acadêmico");
		lblSistemaControle.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblSistemaControle.setBounds(186, 38, 256, 15);
		panel.add(lblSistemaControle);
		
		JLabel lblMenuAcima = new JLabel("Utilize o menu acima para navegar");
		lblMenuAcima.setBounds(186, 370, 256, 15);
		panel.add(lblMenuAcima);
	}
}
