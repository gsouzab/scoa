package ufrj.scoa;

import ufrj.scoa.view.CourseCreationView;
import ufrj.scoa.view.StudentCreationView;

public class Scoa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		StudentCreationView studentCreationView = new StudentCreationView();
		CourseCreationView courseCreationView = new CourseCreationView();
		
       // new ControllerPrincipal(framePrincipal);
		courseCreationView.setVisible(true);

	}

}
