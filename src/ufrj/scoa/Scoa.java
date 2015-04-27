package ufrj.scoa;

import ufrj.scoa.controller.CourseController;
import ufrj.scoa.model.DAO.CourseDAO;
import ufrj.scoa.model.VO.Course;
import ufrj.scoa.view.CourseCreationView;
import ufrj.scoa.view.StudentCreationView;
import ufrj.scoa.view.WelcomeView;

public class Scoa {

	public static void main(String[] args) {
		
		/*StudentCreationView studentCreationView = new StudentCreationView();
		CourseCreationView courseCreationView = new CourseCreationView();
		
        new CourseController(courseCreationView);
        
        courseCreationView.setVisible(true);*/
		
		WelcomeView welcomeView = new WelcomeView();
		welcomeView.setVisible(true);

	}

}
