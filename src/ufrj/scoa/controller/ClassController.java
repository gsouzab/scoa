package ufrj.scoa.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ufrj.scoa.model.DAO.ClassDAO;
import ufrj.scoa.model.DAO.CourseDAO;
import ufrj.scoa.model.DAO.DisciplineDAO;
import ufrj.scoa.model.DAO.RoomDAO;
import ufrj.scoa.model.DAO.StudentDisciplineDAO;
import ufrj.scoa.model.VO.Class;
import ufrj.scoa.model.VO.Course;
import ufrj.scoa.model.VO.Discipline;
import ufrj.scoa.model.VO.Room;
import ufrj.scoa.model.VO.Student;
import ufrj.scoa.view.classes.ClassCreationView;
import ufrj.scoa.view.classes.ClassListView;
import ufrj.scoa.view.classes.ClassSearchView;
import ufrj.scoa.view.student.StudentCreationView;
import ufrj.scoa.view.student.StudentListView;
import ufrj.scoa.view.InsertGradesDialog;
import ufrj.scoa.view.WelcomeView;

public class ClassController implements ActionListener {

	private ClassCreationView classCreationView;
	private ClassSearchView classSearchView;
	private ClassListView classListView;
	private ScoaBaseController baseController;
	private CourseDAO courseDAO = new CourseDAO();
	private RoomDAO roomDAO = new RoomDAO();
	private DisciplineDAO disciplineDAO = new DisciplineDAO();
	
	private InsertGradesDialog dialog;

	public ClassController(ScoaBaseController baseController) {

		this.baseController = baseController;
		this.classCreationView = new ClassCreationView(courseDAO.listAllCourses(), disciplineDAO.list(),roomDAO.list());
		this.classCreationView.getBtnSalvar().addActionListener(this);
		this.classCreationView.getBtnCancelar().addActionListener(this);
		
		this.classSearchView = new ClassSearchView(courseDAO.listAllCourses(), disciplineDAO.list(),roomDAO.list());
		this.classSearchView.getBtnBuscar().addActionListener(this);
		this.classSearchView.getBtnVoltar().addActionListener(this);
		
		this.classListView = new ClassListView();
		this.classListView.getBtnExcluir().addActionListener(this);
		this.classListView.getBtnInsertGrades().addActionListener(this);
		
		dialog = new InsertGradesDialog(baseController.getBaseFrame(),this.classListView);			
		dialog.getBtnInsert().addActionListener(this);
		dialog.getBtnCancel().addActionListener(this);
		
	}

	public ClassCreationView getClassCreationView() {
		return classCreationView;
	}
	
	public ClassSearchView getClassSearchView() {
		return classSearchView;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == this.classCreationView.getBtnSalvar()) {
			saveClass();
			
		} else if(event.getSource() == this.classCreationView.getBtnCancelar()) {
			this.baseController.getBaseFrame().changePanel(new WelcomeView(), "Bem-vindo ao SCOA");
			
		} else if(event.getSource() == this.classSearchView.getBtnBuscar()) {
			searchClasses();
			this.baseController.getBaseFrame().changePanel(classListView, "Resultado da busca por salas");
			
		} else if(event.getSource() == this.classSearchView.getBtnVoltar()) {
			this.baseController.getBaseFrame().changePanel(new WelcomeView(), "Bem vindo ao SCOA");
			
		} else if(event.getSource() == this.classListView.getBtnExcluir()) {
			int option = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir a turma selecionado?", "Excluir turma", JOptionPane.YES_NO_OPTION);

			if(option == 0) {
				deleteClass(classListView.getList().getSelectedValue());
			}
			
		} else if(event.getSource() == this.classListView.getBtnInsertGrades()) {
			if(classListView.getList().getSelectedValue() != null) {
				dialog.openDialog(classListView.getList().getSelectedValue().getId());
			} else {
				JOptionPane.showMessageDialog(null, "Selecione uma turma");
			}
			
			
		} else if(event.getSource() == dialog.getBtnInsert()) {
			
			JOptionPane.showMessageDialog(null, "Notas lançadas");
			updateStudentsGrade();
		} else if(event.getSource() == dialog.getBtnCancel()) {
			dialog.setVisible(false);
		}
		
		
	}
	
	private void updateStudentsGrade() {
		dialog.setVisible(false);
		
		int rowcount = dialog.getTable().getModel().getRowCount();
		float[] grades = new float[rowcount];
		int[] student_ids = new int[rowcount];
		
		for(int i = 0 ; i < rowcount ; i++) {
			student_ids[i] = (int) dialog.getTable().getModel().getValueAt(i, 0);
			grades[i] = (float) dialog.getTable().getModel().getValueAt(i, 3);
		}
		
		StudentDisciplineDAO.updateGrades(student_ids, grades);
	}
	
	private void searchClasses() {
		String code = this.classSearchView.getTfCode().getText();
		String name = this.classSearchView.getTfName().getText();
		int credits = this.classSearchView.getTfCredits().getText().length() > 0 ? Integer.valueOf(this.classSearchView.getTfCredits().getText()) : 0 ;
		Course selectedCourse = (Course) this.classSearchView.getSelectedCourseComboBox().getSelectedItem();
		Discipline selectedDiscipline = (Discipline) this.classSearchView.getSelectedDisciplineComboBox().getSelectedItem();
		Room selectedRoom = (Room) this.classSearchView.getSelectedRoomComboBox().getSelectedItem();
		
		int course_id = selectedCourse.getId();
		int discipline_id = selectedDiscipline.getId();
		int room_id = selectedRoom.getId();
		
		ClassDAO classDAO = new ClassDAO();
		ArrayList<Class> classList = classDAO.search(code, name, course_id, discipline_id, room_id, credits);
		
		this.classListView.setList(classList);
		
	}
	
	private void deleteClass(Class c) {
		int classId = c.getId();
		
		ClassDAO classDAO = new ClassDAO();
		classDAO.delete(classId);
		
		searchClasses();
		
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
