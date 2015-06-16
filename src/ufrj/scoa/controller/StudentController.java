package ufrj.scoa.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;

import ufrj.scoa.model.DAO.CourseDAO;
import ufrj.scoa.model.DAO.StudentDAO;
import ufrj.scoa.model.VO.Course;
import ufrj.scoa.model.VO.Student;
import ufrj.scoa.util.Util;
import ufrj.scoa.view.WelcomeView;
import ufrj.scoa.view.student.StudentCreationView;
import ufrj.scoa.view.student.StudentListView;
import ufrj.scoa.view.student.StudentSearchView;

public class StudentController implements ActionListener {

	private StudentCreationView studentCreationView;
	private StudentListView studentListView;
	private ScoaBaseController baseController;
	private CourseDAO courseDAO = new CourseDAO();
	private StudentSearchView studentSearchView;
	ArrayList<Course> coursesList;

	public StudentController(ScoaBaseController baseController) {

		coursesList = courseDAO.list();
		
		this.baseController = baseController;
		
		this.studentCreationView = new StudentCreationView(coursesList);
		this.studentCreationView.getBtnSalvar().addActionListener(this);
		this.studentCreationView.getBtnCancelar().addActionListener(this);
		
		Course courseNull = new Course("Todos os cursos", "", "");
		coursesList.add(0, courseNull);
		this.studentSearchView = new StudentSearchView(coursesList);
		this.studentSearchView.getBtnBuscar().addActionListener(this);
		this.studentSearchView.getBtnVoltar().addActionListener(this);
		
		this.studentListView = new StudentListView();
		this.studentListView.getBtnExcluir().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == this.studentCreationView.getBtnSalvar()) {
			saveStudent();
			
		} else if(event.getSource() == this.studentCreationView.getBtnCancelar()) {
			this.baseController.getBaseFrame().changePanel(new WelcomeView(), "Bem vindo ao SCOA");
			
		} else if(event.getSource() == this.studentSearchView.getBtnBuscar()) {
			searchStudents();
			this.baseController.getBaseFrame().changePanel(studentListView, "Resultado da busca por alunos");
			
		} else if(event.getSource() == this.studentSearchView.getBtnVoltar()) {
			this.baseController.getBaseFrame().changePanel(new WelcomeView(), "Bem vindo ao SCOA");
			
		} else if(event.getSource() == this.studentListView.getBtnExcluir()) {
			int option = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir o aluno selecionado?", "Excluir aluno", JOptionPane.YES_NO_OPTION);

			if(option == 0) {
				deleteStudent(studentListView.getList().getSelectedValue());
			}
			else if (option == 1) {}
			
		}

	}

	private void saveStudent() {

		String name = this.studentCreationView.getTfName().getText();
		String email = this.studentCreationView.getTfEmail().getText();
		String cpf = this.studentCreationView.getTfCpf().getText();
		String birthdate = this.studentCreationView.getTfDate().getText();
		Course selectedCourse = (Course) this.studentCreationView.getCbCourse().getSelectedItem();
		String password = null;
		String entry;

		if(this.validadeCreateFields(name, email, this.studentCreationView.getTfCpf(), this.studentCreationView.getTfDate(), selectedCourse)) {

			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

			Date date = null;

			try {

				date = formatter.parse(birthdate);

			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			password = Util.generateNewPassword(cpf);
			entry = Util.unmaskCPF(cpf);

			Student student = new Student(name, cpf, email, date, selectedCourse, entry, password);
			StudentDAO studentDao = new StudentDAO();

			studentDao.saveStudent(student);

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
	
	public StudentListView getStudentListView() {
		return studentListView;
	}

	public StudentSearchView getStudentSearchView() {
		return studentSearchView;
	}
	
	public void searchStudents() {
		Course course = (Course) this.studentSearchView.getCbCourse().getSelectedItem();
		String courseCode = course.getCode();
		String courseName = course.getName();
		String studentName = this.studentSearchView.getTfName().getText();
		String email = this.studentSearchView.getTfEmail().getText();
		String cpf = this.studentSearchView.getTfCpf().getText();
		String birthdate = this.studentSearchView.getTfDate().getText();
		
		if(Util.unmaskDate(birthdate).length() > 0) {
			birthdate = Util.formatDateToSql(birthdate);
		} else {
			birthdate = Util.unmaskDate(birthdate);
		}
		
		
		if(Util.unmaskCPF(cpf).length() == 0) {
			cpf = "";
		}
		
		if(courseName.equalsIgnoreCase("Todos os cursos")) { 
			courseName = "";
			courseCode = "";
		}
		
		StudentDAO studentDAO = new StudentDAO();
		ArrayList<Student> students = studentDAO.searchStudent(courseCode, courseName, studentName, email, cpf, birthdate);
		
		for(Student student: students) {
			studentListView.getModel().addElement(student);
		}
	}
	
	private void deleteStudent(Student student) {
		int studentId = student.getStudentId();
		int personId = student.getPersonId();
		
		StudentDAO studentDAO = new StudentDAO();
		studentDAO.deleteStudent(personId, studentId);
		
		this.studentListView.resetList();
		searchStudents();
		
	}
	

}
