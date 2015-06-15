package ufrj.scoa.view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AccountInfoView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel fieldName;
	JLabel fieldCPF;
	JLabel fieldEmail;
	JLabel fieldBirthdate;
	JLabel fieldEntry;
	JLabel fieldRole;
	JButton btnTrocarSenha;
	JButton btnVoltar;
	
	/**
	 * Create the panel.
	 */
	public AccountInfoView() {
		setLayout(null);
		
		JLabel lblMyProfile = new JLabel("Minha Conta");
		lblMyProfile.setBounds(12, 12, 620, 29);
		add(lblMyProfile);
		lblMyProfile.setFont(new Font("Arial", Font.BOLD, 25));
		lblMyProfile.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel lblName = new JLabel("Nome: ");
		lblName.setFont(new Font("Arial", Font.BOLD, 15));
		lblName.setBounds(12, 67, 64, 15);
		add(lblName);
		
		JLabel lblCpf = new JLabel("CPF: ");
		lblCpf.setFont(new Font("Arial", Font.BOLD, 15));
		lblCpf.setBounds(363, 165, 64, 15);
		add(lblCpf);
		
		JLabel lblEmail = new JLabel("E-mail: ");
		lblEmail.setFont(new Font("Arial", Font.BOLD, 15));
		lblEmail.setBounds(12, 115, 70, 15);
		add(lblEmail);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento: ");
		lblDataDeNascimento.setFont(new Font("Arial", Font.BOLD, 15));
		lblDataDeNascimento.setBounds(12, 165, 177, 15);
		add(lblDataDeNascimento);
		
		JLabel lblMatrcula = new JLabel("Matrícula: ");
		lblMatrcula.setFont(new Font("Arial", Font.BOLD, 15));
		lblMatrcula.setBounds(12, 215, 89, 15);
		add(lblMatrcula);
		
		JLabel lblPapelNoSistema = new JLabel("Papel: ");
		lblPapelNoSistema.setFont(new Font("Arial", Font.BOLD, 15));
		lblPapelNoSistema.setBounds(357, 215, 70, 15);
		add(lblPapelNoSistema);
		
		fieldName = new JLabel("Insira nome aqui");
		fieldName.setFont(new Font("Arial", Font.PLAIN, 15));
		fieldName.setBounds(73, 67, 537, 15);
		add(fieldName);
		
		fieldCPF = new JLabel("Insira cpf aqui");
		fieldCPF.setFont(new Font("Arial", Font.PLAIN, 15));
		fieldCPF.setBounds(414, 165, 152, 15);
		add(fieldCPF);
		
		fieldEmail = new JLabel("Insira e-mail aqui");
		fieldEmail.setFont(new Font("Arial", Font.PLAIN, 15));
		fieldEmail.setBounds(73, 115, 537, 15);
		add(fieldEmail);
		
		fieldBirthdate = new JLabel("Insira data aqui");
		fieldBirthdate.setFont(new Font("Arial", Font.PLAIN, 15));
		fieldBirthdate.setBounds(172, 165, 152, 15);
		add(fieldBirthdate);
		
		fieldEntry = new JLabel("Insira matrícula aqui");
		fieldEntry.setFont(new Font("Arial", Font.PLAIN, 15));
		fieldEntry.setBounds(93, 215, 148, 15);
		add(fieldEntry);
		
		fieldRole = new JLabel("Insira papel aqui");
		fieldRole.setFont(new Font("Arial", Font.PLAIN, 15));
		fieldRole.setBounds(414, 215, 187, 15);
		add(fieldRole);
		
		btnTrocarSenha = new JButton("Trocar senha");
		btnTrocarSenha.setBounds(12, 275, 145, 25);
		add(btnTrocarSenha);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(493, 418, 117, 25);
		add(btnVoltar);
	}

	public JLabel getFieldName() {
		return fieldName;
	}

	public JLabel getFieldCPF() {
		return fieldCPF;
	}

	public JLabel getFieldEmail() {
		return fieldEmail;
	}

	public JLabel getFieldBirthdate() {
		return fieldBirthdate;
	}

	public JLabel getFieldEntry() {
		return fieldEntry;
	}

	public JLabel getFieldRole() {
		return fieldRole;
	}

	public JButton getBtnTrocarSenha() {
		return btnTrocarSenha;
	}

	public JButton getBtnVoltar() {
		return btnVoltar;
	}
	
	
}
