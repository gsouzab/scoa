package ufrj.scoa.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ufrj.scoa.model.DAO.CourseDAO;
import ufrj.scoa.model.VO.Course;
import ufrj.scoa.view.CourseCreationView;
import ufrj.scoa.view.CoursesListView;
import ufrj.scoa.view.WelcomeView;

public class CourseController implements ActionListener {
	
	private CourseCreationView courseCreationView;
	private ScoaBaseController baseController;
	private CoursesListView coursesListView;
	
	public CourseController(ScoaBaseController baseController) {
		this.baseController = baseController;
		this.courseCreationView = new CourseCreationView();
		this.coursesListView = new CoursesListView();
		this.courseCreationView.getBtnSalvar().addActionListener(this);
		this.courseCreationView.getBtnCancelar().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource() == this.courseCreationView.getBtnSalvar()) {
			saveCourse();
			
		} else if(event.getSource() == this.courseCreationView.getBtnCancelar()) {
			this.baseController.getBaseFrame().changePanel(new WelcomeView(), "Bem-vindo ao SCOA");
			
		}
	}
	
	private void saveCourse() {
		String name = this.courseCreationView.getTfName().getText();
		String code = this.courseCreationView.getTfCode().getText();
		String description = this.courseCreationView.getTaDescription().getText();
		
		if(this.validateCreateFields(name, code, description)) {
			Course newCourse = new Course(this.courseCreationView.getTfName().getText(), this.courseCreationView.getTfCode().getText(), this.courseCreationView.getTaDescription().getText());
			CourseDAO courseDAO = new CourseDAO();
			courseDAO.save(newCourse);
			
			JOptionPane.showMessageDialog(null, "Curso salvo com sucesso");
			clearFieldsCreationView();
			
		} else {
			JOptionPane.showMessageDialog(null, "Preencha todos os campos marcados com (*)");
		}
	}
	
	public void listCourses() {
		CourseDAO courseDAO = new CourseDAO();
		ArrayList<Course> courses = courseDAO.list();
		
		for(Course course: courses) {
			this.coursesListView.getModel().addElement(course);
			
		}

	}
	
	private boolean validateCreateFields(String name, String code, String description) {
		return name.length() > 0 && code.length() > 0 && description.length() > 0;
	}
	
	private void clearFieldsCreationView() {
		this.courseCreationView.getTfName().setText("");;
		this.courseCreationView.getTfCode().setText("");
		this.courseCreationView.getTaDescription().setText("");
	}
	
	public CourseCreationView getCourseCreationView() {
		return courseCreationView;
	}

	public CoursesListView getCoursesListView() {
		return coursesListView;
	}
	
	

}
