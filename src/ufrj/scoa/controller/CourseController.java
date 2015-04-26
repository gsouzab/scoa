package ufrj.scoa.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ufrj.scoa.model.DAO.CourseDAO;
import ufrj.scoa.model.VO.Course;
import ufrj.scoa.view.CourseCreationView;

public class CourseController implements ActionListener {
	
	CourseCreationView courseView = new CourseCreationView();
	
	public CourseController (CourseCreationView courseView) {
		this.courseView = courseView;
		this.courseView.getBtnSalvar().addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == this.courseView.getBtnSalvar()) {
			Course newCourse = new Course(this.courseView.getTfName().getText(), this.courseView.getTfCode().getText(), this.courseView.getTaDescription().getText());
			CourseDAO courseDAO = new CourseDAO();
			courseDAO.save(newCourse);
		}
		
	}
	
	

}
