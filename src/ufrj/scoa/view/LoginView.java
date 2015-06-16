package ufrj.scoa.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPasswordField passwordField;
	private JFormattedTextField entryField;
	private JButton btnLogin;

	/**
	 * Create the panel.
	 */
	public LoginView() {
		setLayout(null);
		
		JLabel lblScoa = new JLabel("SCOA");
		lblScoa.setHorizontalAlignment(SwingConstants.CENTER);
		lblScoa.setFont(new Font("Arial", Font.BOLD, 36));
		lblScoa.setBounds(6, 142, 628, 39);
		add(lblScoa);
		
		JLabel lblSistemaDeControle = new JLabel("Sistema de Controle Acadêmico");
		lblSistemaDeControle.setHorizontalAlignment(SwingConstants.CENTER);
		lblSistemaDeControle.setFont(new Font("Arial", Font.PLAIN, 16));
		lblSistemaDeControle.setForeground(Color.DARK_GRAY);
		lblSistemaDeControle.setBounds(16, 179, 628, 16);
		add(lblSistemaDeControle);
		
		JLabel lblLogin = new JLabel("Matrícula");
		lblLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLogin.setBounds(82, 255, 101, 16);
		add(lblLogin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(195, 278, 326, 28);
		add(passwordField);
		
		//campo de matrícula aceita somente numeros inteiros
		//MaskFormatter entryFormatter = null;
		
		//try {
		//	entryFormatter = new MaskFormatter("#########");
		//} catch (ParseException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		
		//entryField = new JFormattedTextField(entryFormatter);]
		entryField = new JFormattedTextField();
		entryField.setBounds(195, 249, 326, 28);
		add(entryField);
		entryField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Senha");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(82, 284, 101, 16);
		add(lblPassword);
		
		btnLogin = new JButton("Entrar");
		btnLogin.setFont(new Font("Arial", Font.PLAIN, 13));
		btnLogin.setBounds(404, 318, 117, 29);
		add(btnLogin);
		
	}
	
	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public JTextField getUsernameField() {
		return entryField;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}
}
