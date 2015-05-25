package ufrj.scoa.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;

import ufrj.scoa.model.DAO.CourseDAO;
import ufrj.scoa.model.DAO.StudentDAO;
import ufrj.scoa.model.VO.Course;
import ufrj.scoa.model.VO.Student;
import ufrj.scoa.view.StudentCreationView;
import ufrj.scoa.view.WelcomeView;

public class StudentController implements ActionListener {

	private StudentCreationView studentCreationView;
	private ScoaBaseController baseController;
	private CourseDAO courseDAO = new CourseDAO();

	public StudentController(ScoaBaseController baseController) {

		this.baseController = baseController;
		this.studentCreationView = new StudentCreationView(courseDAO.list());
		this.studentCreationView.getBtnSalvar().addActionListener(this);
		this.studentCreationView.getBtnCancelar().addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == this.studentCreationView.getBtnSalvar()) {
			saveStudent();
		} else if(event.getSource() == this.studentCreationView.getBtnCancelar()) {
			this.baseController.getBaseFrame().changePanel(new WelcomeView(), "Bem vindo ao SCOA");
		}

	}

	private void saveStudent() {

		String name = this.studentCreationView.getTfName().getText();
		String email = this.studentCreationView.getTfEmail().getText();
		String cpf = this.studentCreationView.getTfCpf().getText();
		String birthdate = this.studentCreationView.getTfDate().getText();
		Course selectedCourse = (Course) this.studentCreationView.getCbCourse().getSelectedItem();

		if(this.validadeCreateFields(name, email, this.studentCreationView.getTfCpf(), this.studentCreationView.getTfDate(), selectedCourse)) {

			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

			Date date = null;

			try {

				date = formatter.parse(birthdate);

			} catch (ParseException e) {
				e.printStackTrace();
			}

			Student student = new Student(name, cpf, email, date, selectedCourse);
			StudentDAO studentDao = new StudentDAO();

			studentDao.save(student);

			JOptionPane.showMessageDialog(null, "Aluno salvo com sucesso");
			clearFieldsCreationView();
		} else {
			JOptionPane.showMessageDialog(null, "Preencha todos os campos marcados com (*)");
		}
	}

	private boolean validadeCreateFields(String name, String email, JFormattedTextField cpf, JFormattedTextField birthdate, Course selectedCourse) {
		return name.length() > 0 && email.length() > 0 && selectedCourse != null && birthdate.getValue() != null && cpf.getValue() != null;
	}

	private void clearFieldsCreationView() {
		this.studentCreationView.getTfName().setText("");;
		this.studentCreationView.getTfEmail().setText("");
		this.studentCreationView.getTfCpf().setText("");
		this.studentCreationView.getTfDate().setText("");
		this.studentCreationView.getCbCourse().setSelectedIndex(0);
	}


	public StudentCreationView getStudentCreationView() {
		return studentCreationView;
	}


}
