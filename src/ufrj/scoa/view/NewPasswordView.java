package ufrj.scoa.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class NewPasswordView extends JPanel{

	private static final long serialVersionUID = 1L;
	private JPasswordField passwordField;
	private JPasswordField passwordField2;
	private JButton btnAccept;
	private String entry;

	/**
	 * Create the panel.
	 */
	public NewPasswordView(String entry) {
		setLayout(null);
		
		this.entry = entry;
		
		JLabel lblScoa = new JLabel("SCOA");
		lblScoa.setHorizontalAlignment(SwingConstants.CENTER);
		lblScoa.setFont(new Font("Arial", Font.BOLD, 36));
		lblScoa.setBounds(6, 142, 628, 39);
		add(lblScoa);
		
		JLabel lblSistemaDeControle = new JLabel("Redefinição de senha");
		lblSistemaDeControle.setHorizontalAlignment(SwingConstants.CENTER);
		lblSistemaDeControle.setFont(new Font("Arial", Font.PLAIN, 16));
		lblSistemaDeControle.setForeground(Color.DARK_GRAY);
		lblSistemaDeControle.setBounds(16, 179, 628, 16);
		add(lblSistemaDeControle);
		
		JLabel lblLogin = new JLabel("Nova senha");
		lblLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLogin.setBounds(82, 255, 101, 16);
		add(lblLogin);
		
		passwordField2 = new JPasswordField();
		passwordField2.setBounds(195, 278, 326, 28);
		add(passwordField2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(195, 249, 326, 28);
		add(passwordField);
		passwordField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Confirmação");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(82, 284, 101, 16);
		add(lblPassword);
		
		btnAccept = new JButton("Aceitar");
		btnAccept.setFont(new Font("Arial", Font.PLAIN, 13));
		btnAccept.setBounds(404, 318, 117, 29);
		add(btnAccept);
		
	}
	
	public JPasswordField getPassword2Field() {
		return passwordField2;
	}

	public JPasswordField getPassword1Field() {
		return passwordField;
	}

	public JButton getBtnAccept() {
		return btnAccept;
	}
	
	public String getEntry() {
		return entry;
	}	
	
}
