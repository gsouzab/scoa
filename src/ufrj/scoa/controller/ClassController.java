package ufrj.scoa.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;

import ufrj.scoa.model.DAO.ClassDAO;
import ufrj.scoa.model.DAO.CourseDAO;
import ufrj.scoa.model.DAO.StudentDAO;
import ufrj.scoa.model.VO.Course;
import ufrj.scoa.model.VO.Student;
import ufrj.scoa.model.VO.Class;
import ufrj.scoa.view.classes.ClassCreationView;
import ufrj.scoa.view.StudentCreationView;
import ufrj.scoa.view.StudentListView;
import ufrj.scoa.view.WelcomeView;

public class ClassController implements ActionListener {

	private ClassCreationView classCreationView;
	private ScoaBaseController baseController;
	private CourseDAO courseDAO = new CourseDAO();

	public ClassController(ScoaBaseController baseController) {

		this.baseController = baseController;
		this.classCreationView = new ClassCreationView(courseDAO.list());
		this.classCreationView.getBtnSalvar().addActionListener(this);
		this.classCreationView.getBtnCancelar().addActionListener(this);
		
		
//		this.studentListView = new StudentListView();

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
		Course selectedCourse = (Course) this.classCreationView.getSelectedCourseComboBox().getSelectedItem();
		boolean isActive = this.classCreationView.getActiveClass().isSelected();
		
		if(validateCreateFields(name, code, selectedCourse)) {
			Class newClass = new Class(name,code, selectedCourse,isActive);
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
		this.classCreationView.getActiveClass().setSelected(true);
	}

}
