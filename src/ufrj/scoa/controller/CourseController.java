package ufrj.scoa.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ufrj.scoa.model.DAO.CourseDAO;
import ufrj.scoa.model.VO.Course;
import ufrj.scoa.view.WelcomeView;
import ufrj.scoa.view.course.CourseCreationView;
import ufrj.scoa.view.course.CourseListView;
import ufrj.scoa.view.course.CourseSearchView;

public class CourseController implements ActionListener {
	
	private ScoaBaseController baseController;
	private CourseCreationView courseCreationView;
	private CourseListView courseListView;
	private CourseSearchView courseSearchView;
	
	public CourseController(ScoaBaseController baseController) {
		this.baseController = baseController;
		
		this.courseCreationView = new CourseCreationView();
		this.courseCreationView.getBtnSalvar().addActionListener(this);
		this.courseCreationView.getBtnCancelar().addActionListener(this);
		
		this.courseSearchView = new CourseSearchView();
		this.courseSearchView.getBtnBuscar().addActionListener(this);
		this.courseSearchView.getBtnVoltar().addActionListener(this);		
		
		this.courseListView = new CourseListView();
		this.courseListView.getBtnExcluir().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource() == this.courseCreationView.getBtnSalvar()) {
			saveCourse();
			
		} else if(event.getSource() == this.courseCreationView.getBtnCancelar()) {
			this.baseController.getBaseFrame().changePanel(new WelcomeView(), "Bem-vindo ao SCOA");
			
		} else if(event.getSource() == this.courseSearchView.getBtnBuscar()) {
			searchCourses();
			this.baseController.getBaseFrame().changePanel(courseListView, "Resultado da busca por cursos");
			
		} else if(event.getSource() == this.courseSearchView.getBtnVoltar()) {
			this.baseController.getBaseFrame().changePanel(new WelcomeView(), "Bem vindo ao SCOA");
			
		} else if(event.getSource() == this.courseListView.getBtnExcluir()) {
			int option = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir o curso selecionado?", "Excluir curso", JOptionPane.YES_NO_OPTION);

			if(option == 0) {
				deleteCourse(courseListView.getList().getSelectedValue());
			}
			else if (option == 1) {}
			
		}
	}
	
	private void saveCourse() {
		String name = this.courseCreationView.getTfName().getText();
		String code = this.courseCreationView.getTfCode().getText();
		String description = this.courseCreationView.getTaDescription().getText();
		
		if(this.validateCreateFields(name, code, description)) {
			Course newCourse = new Course(this.courseCreationView.getTfName().getText(), this.courseCreationView.getTfCode().getText(), this.courseCreationView.getTaDescription().getText());
			CourseDAO courseDAO = new CourseDAO();
			courseDAO.saveCourse(newCourse);
			
			JOptionPane.showMessageDialog(null, "Curso salvo com sucesso");
			clearFieldsCreationView();
			
		} else {
			JOptionPane.showMessageDialog(null, "Preencha todos os campos marcados com (*)");
		}
	}
	

	private void searchCourses() {
		String name = this.courseSearchView.getTfName().getText();
		String courseCode = this.courseSearchView.getTfCode().getText();
		String description = this.courseSearchView.getTaDescription().getText();
		
		CourseDAO courseDAO = new CourseDAO();
		ArrayList<Course> courseList = courseDAO.searchCourse(name, courseCode, description);
		
		for(Course course: courseList) {
			courseListView.getModel().addElement(course);
		}
	}
	
	private void deleteCourse(Course course) {
		int courseId = course.getId();
		
		CourseDAO courseDAO = new CourseDAO();
		courseDAO.deleteCourse(courseId);
		
		this.courseListView.resetList();
		searchCourses();
		
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

	public CourseListView getCoursesListView() {
		return courseListView;
	}
	
	
	public CourseSearchView getCourseSearchView() {
		return courseSearchView;
	}


}
