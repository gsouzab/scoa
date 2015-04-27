package ufrj.scoa.view;

import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JButton;

import ufrj.scoa.model.VO.Course;

public class StudentCreationView extends JPanel {

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
	private JComboBox<Course> cbCourse;
	private ArrayList<Course> coursesList;

	/**
	 * Create the frame.
	 */
	public StudentCreationView(ArrayList<Course> coursesList) {
		this.coursesList = coursesList;
		
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
		separator.setBounds(0, 400, 640, 9);
		this.add(separator);
		
		JLabel lblName = new JLabel("Nome*");
		lblName.setBounds(9, 66, 131, 16);
		add(lblName);
		
		tfName = new JTextField();
		tfName.setBounds(128, 60, 500, 28);
		add(tfName);
		tfName.setColumns(10);
		
		JLabel lblCourse = new JLabel("Curso*");
		lblCourse.setBounds(9, 22, 131, 16);
		add(lblCourse);
		
		cbCourse = new JComboBox<Course>();
		
		populateComboBox();
		
		cbCourse.setBounds(128, 17, 500, 28);
		add(cbCourse);
		
		JLabel lblCpf = new JLabel("CPF*");
		lblCpf.setBounds(9, 144, 131, 16);
		add(lblCpf);
		
		tfCpf = new JFormattedTextField(cpfMask);
		tfCpf.setBounds(128, 138, 116, 28);
		add(tfCpf);
		
		JLabel lblBirthdate = new JLabel("Data de Nascimento*");
		lblBirthdate.setBounds(383, 144, 145, 16);
		add(lblBirthdate);
		
//		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		tfDate = new JFormattedTextField(dateMask);
		tfDate.setBounds(538, 138, 90, 28);
		add(tfDate);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(384, 420, 117, 29);
		add(btnSalvar);
		
		btnCancelar = new JButton("Voltar");
		btnCancelar.setBounds(511, 420, 117, 29);
		add(btnCancelar);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(128, 99, 500, 28);
		add(tfEmail);
		tfEmail.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(9, 105, 131, 16);
		add(lblEmail);
	}
	
	private void populateComboBox() {
		for(Course c : coursesList) {
			cbCourse.addItem(c);
		}
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

	public JComboBox<Course> getCbCourse() {
		return cbCourse;
	}
	
}