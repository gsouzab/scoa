package ufrj.scoa.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ufrj.scoa.model.DAO.CourseDAO;
import ufrj.scoa.model.VO.Course;
import ufrj.scoa.view.CourseCreationView;
import ufrj.scoa.view.StudentCreationView;

public class StudentController extends ScoaBaseController implements ActionListener {
	
	StudentCreationView studentView = new StudentCreationView();
	
	public StudentController(StudentCreationView studentView) {
		super(studentView);
		this.studentView = studentView;
		//this.studentView.getBtnSalvar().addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
//		if(event.getSource() == this.courseView.getBtnSalvar()) {
//			Course newCourse = new Course(this.courseView.getTfName().getText(), this.courseView.getTfCode().getText(), this.courseView.getTaDescription().getText());
//			CourseDAO courseDAO = new CourseDAO();
//			courseDAO.save(newCourse);
//		}
		
	}
	
	

}
