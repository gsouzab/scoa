package ufrj.scoa.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ufrj.scoa.model.DAO.CourseDAO;
import ufrj.scoa.model.VO.Course;
import ufrj.scoa.view.CourseCreationView;
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
			
		} else if(event.getSource() == this.studentCreationView.getBtnCancelar()) {
			this.baseController.getBaseFrame().changePanel(new WelcomeView(), "Bem vindo ao Scoa");
		}
		
	}
	
	public StudentCreationView getStudentCreationView() {
		return studentCreationView;
	}
	

}
