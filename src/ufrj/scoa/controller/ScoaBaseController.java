package ufrj.scoa.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ufrj.scoa.view.CourseCreationView;
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
			baseFrame.changePanel(courseController.getCourseCreationView(), "Novo curso");
	        
		} else if(event.getSource() == baseFrame.getNewStudentMenuItem()) {
			
			System.out.println("Novo evento botao do menu 2");
		}
		
		
	}

	public ScoaBaseFrame getBaseFrame() {
		return baseFrame;
	}
	
}
