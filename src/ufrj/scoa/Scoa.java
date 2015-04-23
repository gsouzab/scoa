package ufrj.scoa;

import ufrj.scoa.model.DAO.CourseDAO;
import ufrj.scoa.model.VO.Course;
import ufrj.scoa.view.CourseCreationView;
import ufrj.scoa.view.StudentCreationView;

public class Scoa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		StudentCreationView studentCreationView = new StudentCreationView();
		CourseCreationView courseCreationView = new CourseCreationView();
		
       // new ControllerPrincipal(framePrincipal);
		courseCreationView.setVisible(true);
		
		Course course = new Course("Ciência da Computação", "CC123", "MUITO BOM!! FACIU PRA CARAMBA");
		
		CourseDAO couseDao = new CourseDAO();
		
		couseDao.save(course);

	}

}
