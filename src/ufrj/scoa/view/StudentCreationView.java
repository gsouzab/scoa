package ufrj.scoa.view;

import java.awt.EventQueue;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;

import java.text.Format;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JButton;

public class StudentCreationView extends ScoaBaseFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentCreationView frame = new StudentCreationView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentCreationView() {
		setTitle("Cadastro de Alunos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelName = new JPanel();
		panelName.setBounds(6, 85, 628, 31);
		contentPane.add(panelName);
		panelName.setLayout(null);
		
		JLabel lblName = new JLabel("Nome:");
		lblName.setBounds(6, 6, 41, 16);
		panelName.add(lblName);
		
		tfName = new JTextField();
		tfName.setBounds(205, 0, 417, 28);
		panelName.add(tfName);
		tfName.setColumns(10);
		
		JPanel panelEmail = new JPanel();
		panelEmail.setLayout(null);
		panelEmail.setBounds(6, 185, 628, 31);
		contentPane.add(panelEmail);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(6, 6, 41, 16);
		panelEmail.add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(206, 0, 416, 28);
		panelEmail.add(tfEmail);
		
		JPanel panelBirthdate = new JPanel();
		panelBirthdate.setLayout(null);
		panelBirthdate.setBounds(6, 150, 628, 31);
		contentPane.add(panelBirthdate);
		
		MaskFormatter cpfMask = null;
		MaskFormatter dateMask = null;
		
		try {
			cpfMask = new MaskFormatter("###.###.###-##");
			dateMask = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel lblBirthdate = new JLabel("Data de Nascimento:");
		lblBirthdate.setBounds(6, 6, 131, 16);
		panelBirthdate.add(lblBirthdate);
		
//		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		JFormattedTextField tfDate = new JFormattedTextField();
		tfDate.setBounds(204, 0, 135, 28);
		panelBirthdate.add(tfDate);
		
		dateMask.install(tfDate);
		
		JPanel panelCpf = new JPanel();
		panelCpf.setLayout(null);
		panelCpf.setBounds(6, 117, 628, 31);
		contentPane.add(panelCpf);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(6, 6, 131, 16);
		panelCpf.add(lblCpf);
		
		JFormattedTextField tfCpf = new JFormattedTextField(cpfMask);
		tfCpf.setBounds(205, 0, 135, 28);
		panelCpf.add(tfCpf);
		
		JPanel panelCurso = new JPanel();
		panelCurso.setLayout(null);
		panelCurso.setBounds(6, 50, 628, 31);
		contentPane.add(panelCurso);
		
		JLabel lblCourse = new JLabel("Curso:");
		lblCourse.setBounds(6, 6, 41, 16);
		panelCurso.add(lblCourse);
		
		JComboBox cbCourse = new JComboBox();
		cbCourse.setBounds(204, 2, 418, 27);
		panelCurso.add(cbCourse);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 383, 628, 12);
		contentPane.add(separator);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setBounds(6, 398, 628, 54);
		contentPane.add(panelButtons);
		panelButtons.setLayout(null);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(376, 6, 117, 29);
		panelButtons.add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(505, 6, 117, 29);
		panelButtons.add(btnCancelar);
	}
}