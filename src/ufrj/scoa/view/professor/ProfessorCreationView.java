package ufrj.scoa.view.professor;

import java.awt.Font;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;


public class ProfessorCreationView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfName;
	private JTextField tfEmail;
	private JButton btnCancelar;
	private JButton btnSalvar;
	private JFormattedTextField tfDate;
	private JFormattedTextField tfCpf;

	/**
	 * Create the frame.
	 */
	public ProfessorCreationView() {
		
		setLayout(null);
		
		MaskFormatter cpfMask = null;
		MaskFormatter dateMask = null;
		
		try {
			cpfMask = new MaskFormatter("###.###.###-##");
			dateMask = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 397, 640, 9);
		this.add(separator);
		
		JLabel lblName = new JLabel("Nome*");
		lblName.setFont(new Font("Arial", Font.BOLD, 12));
		lblName.setBounds(9, 19, 109, 15);
		add(lblName);
		
		tfName = new JTextField();
		tfName.setFont(new Font("Arial", Font.PLAIN, 13));
		tfName.setBounds(128, 12, 500, 28);
		add(tfName);
		tfName.setColumns(10);
		
		
		JLabel lblCpf = new JLabel("CPF*");
		lblCpf.setFont(new Font("Arial", Font.BOLD, 12));
		lblCpf.setBounds(9, 101, 109, 15);
		add(lblCpf);
		
		tfCpf = new JFormattedTextField(cpfMask);
		tfCpf.setFont(new Font("Arial", Font.PLAIN, 13));
		tfCpf.setBounds(128, 94, 116, 28);
		add(tfCpf);
		
		JLabel lblBirthdate = new JLabel("Data de Nascimento*");
		lblBirthdate.setFont(new Font("Arial", Font.BOLD, 12));
		lblBirthdate.setBounds(384, 101, 159, 15);
		add(lblBirthdate);
		
//		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		tfDate = new JFormattedTextField(dateMask);
		tfDate.setFont(new Font("Arial", Font.PLAIN, 13));
		tfDate.setBounds(538, 94, 90, 28);
		add(tfDate);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Arial", Font.BOLD, 12));
		btnSalvar.setBounds(400, 420, 110, 29);
		add(btnSalvar);
		
		btnCancelar = new JButton("Voltar");
		btnCancelar.setFont(new Font("Arial", Font.BOLD, 12));
		btnCancelar.setBounds(520, 420, 110, 29);
		add(btnCancelar);
		
		tfEmail = new JTextField();
		tfEmail.setFont(new Font("Arial", Font.PLAIN, 13));
		tfEmail.setBounds(128, 52, 500, 28);
		add(tfEmail);
		tfEmail.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email*");
		lblEmail.setFont(new Font("Arial", Font.BOLD, 12));
		lblEmail.setBounds(9, 59, 109, 15);
		add(lblEmail);
	}
	

	public JTextField getTfName() {
		return tfName;
	}

	public JTextField getTfEmail() {
		return tfEmail;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JButton getBtnSalvar() {
		return btnSalvar;
	}

	public JFormattedTextField getTfDate() {
		return tfDate;
	}

	public JFormattedTextField getTfCpf() {
		return tfCpf;
	}


}