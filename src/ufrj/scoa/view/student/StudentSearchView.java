package ufrj.scoa.view.student;

import java.awt.Font;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import ufrj.scoa.model.VO.Course;

public class StudentSearchView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfName;
	private JTextField tfEmail;
	private JButton btnVoltar;
	private JButton btnBuscar;
	private JFormattedTextField tfDate;
	private JFormattedTextField tfCpf;
	private JComboBox<Course> cbCourse;
	private ArrayList<Course> coursesList;

	/**
	 * Create the frame.
	 */
	public StudentSearchView(ArrayList<Course> coursesList) {
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
		separator.setBounds(0, 397, 640, 9);
		this.add(separator);
		
		JLabel lblName = new JLabel("Nome");
		lblName.setFont(new Font("Arial", Font.BOLD, 12));
		lblName.setBounds(9, 66, 109, 15);
		add(lblName);
		
		tfName = new JTextField();
		tfName.setFont(new Font("Arial", Font.PLAIN, 13));
		tfName.setBounds(128, 60, 500, 28);
		add(tfName);
		tfName.setColumns(10);
		
		JLabel lblCourse = new JLabel("Curso");
		lblCourse.setFont(new Font("Arial", Font.BOLD, 12));
		lblCourse.setBounds(9, 22, 109, 15);
		add(lblCourse);
		
		cbCourse = new JComboBox<Course>();
		cbCourse.setFont(new Font("Arial", Font.PLAIN, 13));
		
		populateComboBox();
		
		cbCourse.setBounds(128, 17, 500, 28);
		add(cbCourse);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Arial", Font.BOLD, 12));
		lblCpf.setBounds(9, 144, 109, 15);
		add(lblCpf);
		
		tfCpf = new JFormattedTextField(cpfMask);
		tfCpf.setFont(new Font("Arial", Font.PLAIN, 13));
		tfCpf.setBounds(128, 138, 116, 28);
		add(tfCpf);
		
		JLabel lblBirthdate = new JLabel("Data de Nascimento");
		lblBirthdate.setFont(new Font("Arial", Font.BOLD, 12));
		lblBirthdate.setBounds(369, 144, 159, 15);
		add(lblBirthdate);
		
		tfDate = new JFormattedTextField(dateMask);
		tfDate.setFont(new Font("Arial", Font.PLAIN, 13));
		tfDate.setBounds(538, 138, 90, 28);
		add(tfDate);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Arial", Font.BOLD, 12));
		btnBuscar.setBounds(400, 420, 110, 29);
		add(btnBuscar);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Arial", Font.BOLD, 12));
		btnVoltar.setBounds(520, 420, 110, 29);
		add(btnVoltar);
		
		tfEmail = new JTextField();
		tfEmail.setFont(new Font("Arial", Font.PLAIN, 13));
		tfEmail.setBounds(128, 99, 500, 28);
		add(tfEmail);
		tfEmail.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Arial", Font.BOLD, 12));
		lblEmail.setBounds(9, 105, 109, 15);
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

	public JButton getBtnVoltar() {
		return btnVoltar;
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

	public JButton getBtnBuscar() {
		return btnBuscar;
	}
	
	
	
}