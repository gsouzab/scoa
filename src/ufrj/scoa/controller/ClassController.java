package ufrj.scoa.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ufrj.scoa.model.DAO.ClassDAO;
import ufrj.scoa.model.DAO.CourseDAO;
import ufrj.scoa.model.DAO.DisciplineDAO;
import ufrj.scoa.model.DAO.RoomDAO;
import ufrj.scoa.model.VO.Class;
import ufrj.scoa.model.VO.Course;
import ufrj.scoa.model.VO.Discipline;
import ufrj.scoa.model.VO.Room;
import ufrj.scoa.view.WelcomeView;
import ufrj.scoa.view.classes.ClassCreationView;

public class ClassController implements ActionListener {

	private ClassCreationView classCreationView;
	private ScoaBaseController baseController;
	private CourseDAO courseDAO = new CourseDAO();
	private RoomDAO roomDAO = new RoomDAO();
	private DisciplineDAO disciplineDAO = new DisciplineDAO();

	public ClassController(ScoaBaseController baseController) {

		this.baseController = baseController;
		this.classCreationView = new ClassCreationView(courseDAO.listAllCourses(), disciplineDAO.list(),roomDAO.list());
		this.classCreationView.getBtnSalvar().addActionListener(this);
		this.classCreationView.getBtnCancelar().addActionListener(this);
		

	}

	public ClassCreationView getClassCreationView() {
		return classCreationView;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == this.classCreationView.getBtnSalvar()) {
			saveClass();
			
		} else if(event.getSource() == this.classCreationView.getBtnCancelar()) {
			this.baseController.getBaseFrame().changePanel(new WelcomeView(), "Bem-vindo ao SCOA");
			
		}
		
	}

	private void saveClass() {
		String name = this.classCreationView.getTfName().getText();
		String code = this.classCreationView.getTfCode().getText();
		int credits = Integer.valueOf(this.classCreationView.getTfCredits().getText());
		Course selectedCourse = (Course) this.classCreationView.getSelectedCourseComboBox().getSelectedItem();
		Discipline selectedDiscipline = (Discipline) this.classCreationView.getSelectedDisciplineComboBox().getSelectedItem();
		Room selectedRoom = (Room) this.classCreationView.getSelectedRoomComboBox().getSelectedItem();
		
		if(validateCreateFields(name, code, selectedCourse)) {
			Class newClass = new Class(credits,name,code,"ter-qui 10:00 às 12:00", selectedCourse,selectedDiscipline,selectedRoom);
			ClassDAO classDAO = new ClassDAO();
			classDAO.save(newClass);
			
			JOptionPane.showMessageDialog(null, "Turma salva com sucesso");
			clearFieldsCreationView();
			
		} else {
			JOptionPane.showMessageDialog(null, "Preencha todos os campos marcados com (*)");
		}
	}
	
	private boolean validateCreateFields(String name, String code, Course selectedCourse) {
		
		return name.length() > 0 && code.length() > 0 && selectedCourse != null;
	}
	
	private void clearFieldsCreationView() {
		this.classCreationView.getTfName().setText("");
		this.classCreationView.getTfCode().setText("");
		this.classCreationView.getTfCredits().setText("");
	}

}
