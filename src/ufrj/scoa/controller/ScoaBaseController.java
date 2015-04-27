package ufrj.scoa.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ufrj.scoa.view.ScoaBaseFrame;

public class ScoaBaseController implements ActionListener {

	private ScoaBaseFrame baseFrame;
	
	public ScoaBaseController(ScoaBaseFrame baseFrame)
	{
		this.baseFrame = baseFrame;
		
		baseFrame.getNewCourseMenuItem().addActionListener(this);
		baseFrame.getListCoursesMenuItem().addActionListener(this);
		baseFrame.getNewStudentMenuItem().addActionListener(this);
		baseFrame.getListCoursesMenuItem().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		if (event.getSource() == baseFrame.getNewCourseMenuItem()) {
			
			CourseController courseController = new CourseController(this);
			baseFrame.changePanel(courseController.getCourseCreationView(), "Cadastro de Curso");
	        
		} else if(event.getSource() == baseFrame.getNewStudentMenuItem()) {
			
			StudentController studentController = new StudentController(this);
			baseFrame.changePanel(studentController.getStudentCreationView(), "Cadastro de Aluno");
			
		} else if(event.getSource() == baseFrame.getListCoursesMenuItem()) {
			
			CourseController courseController = new CourseController(this);
			courseController.listCourses();
			baseFrame.changePanel(courseController.getCoursesListView(), "Listagem de Cursos");
		}
		
		
	}

	public ScoaBaseFrame getBaseFrame() {
		return baseFrame;
	}
	
}
