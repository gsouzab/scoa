package ufrj.scoa.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ufrj.scoa.model.DAO.ClassDAO;
import ufrj.scoa.model.DAO.CourseDAO;
import ufrj.scoa.model.DAO.DisciplineDAO;
import ufrj.scoa.model.DAO.ProfessorDAO;
import ufrj.scoa.model.DAO.RoomDAO;
import ufrj.scoa.model.DAO.StudentDisciplineDAO;
import ufrj.scoa.model.VO.Class;
import ufrj.scoa.model.VO.Course;
import ufrj.scoa.model.VO.Discipline;
import ufrj.scoa.model.VO.Professor;
import ufrj.scoa.model.VO.Room;
import ufrj.scoa.model.VO.Student;
import ufrj.scoa.view.classes.ClassCreationView;
import ufrj.scoa.view.classes.ClassListView;
import ufrj.scoa.view.classes.ClassSearchView;
import ufrj.scoa.view.student.StudentCreationView;
import ufrj.scoa.view.student.StudentListView;
import ufrj.scoa.view.InsertFrequenciesDialog;
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
	private ProfessorDAO professorDAO = new ProfessorDAO();
	private InsertGradesDialog gradesDialog;
	private InsertFrequenciesDialog frequenciesDialog;

	public ClassController(ScoaBaseController baseController) {

		this.baseController = baseController;
		this.classCreationView = new ClassCreationView(courseDAO.list(), disciplineDAO.list(),roomDAO.list(), professorDAO.list());
		this.classCreationView.getBtnSalvar().addActionListener(this);
		this.classCreationView.getBtnCancelar().addActionListener(this);
		
		this.classSearchView = new ClassSearchView(courseDAO.list(), disciplineDAO.list(),roomDAO.list(), professorDAO.list());
		this.classSearchView.getBtnBuscar().addActionListener(this);
		this.classSearchView.getBtnVoltar().addActionListener(this);
		
		this.classListView = new ClassListView();
		this.classListView.getBtnExcluir().addActionListener(this);
		this.classListView.getBtnInsertGrades().addActionListener(this);
		this.classListView.getBtnInsertFrequencies().addActionListener(this);

		gradesDialog = new InsertGradesDialog(baseController.getBaseFrame(),this.classListView);			
		gradesDialog.getBtnInsert().addActionListener(this);
		gradesDialog.getBtnCancel().addActionListener(this);
		
		frequenciesDialog = new InsertFrequenciesDialog(baseController.getBaseFrame(),this.classListView);			
		frequenciesDialog.getBtnInsert().addActionListener(this);
		frequenciesDialog.getBtnCancel().addActionListener(this);
		
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
			int option = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir a turma selecionada?", "Excluir turma", JOptionPane.YES_NO_OPTION);

			if(option == 0) {
				deleteClass(classListView.getList().getSelectedValue());
			}
			
		} else if(event.getSource() == this.classListView.getBtnInsertGrades()) {
			if(classListView.getList().getSelectedValue() != null) {
				gradesDialog.openDialog(classListView.getList().getSelectedValue().getId());
			} else {
				JOptionPane.showMessageDialog(null, "Selecione uma turma");
			}
			
		} else if(event.getSource() == gradesDialog.getBtnInsert()) {
			JOptionPane.showMessageDialog(null, "Notas lançadas");
			updateStudentsGrade();

		} else if(event.getSource() == gradesDialog.getBtnCancel()) {
			gradesDialog.setVisible(false);

		} else if(event.getSource() == this.classListView.getBtnInsertFrequencies()) {
			if(classListView.getList().getSelectedValue() != null) {
				frequenciesDialog.openDialog(classListView.getList().getSelectedValue().getId());
			} else {
				JOptionPane.showMessageDialog(null, "Selecione uma turma");
			}
			
		} else if(event.getSource() == frequenciesDialog.getBtnInsert()) {
			JOptionPane.showMessageDialog(null, "Frequências lançadas");
			updateStudentsFrequency();
			
		} else if(event.getSource() == frequenciesDialog.getBtnCancel()) {
			frequenciesDialog.setVisible(false);
		}
		
		
		
	}
	
	private void updateStudentsGrade() {
		gradesDialog.setVisible(false);
		
		int rowcount = gradesDialog.getTable().getModel().getRowCount();
		float[] grades = new float[rowcount];
		int[] student_ids = new int[rowcount];
		
		for(int i = 0 ; i < rowcount ; i++) {
			student_ids[i] = (int) gradesDialog.getTable().getModel().getValueAt(i, 0);
			grades[i] = (float) gradesDialog.getTable().getModel().getValueAt(i, 3);
		}
		
		StudentDisciplineDAO.updateGrades(student_ids, grades);
	}
	
	private void updateStudentsFrequency() {
		frequenciesDialog.setVisible(false);
		
		int rowcount = frequenciesDialog.getTable().getModel().getRowCount();
		int[] frequencies = new int[rowcount];
		int[] student_ids = new int[rowcount];
		
		for(int i = 0 ; i < rowcount ; i++) {
			student_ids[i] = (int) frequenciesDialog.getTable().getModel().getValueAt(i, 0);
			frequencies[i] = (int) frequenciesDialog.getTable().getModel().getValueAt(i, 3);
		}
		
		StudentDisciplineDAO.updateFrequencies(student_ids, frequencies);
	}
	
	private void searchClasses() {
		String name = this.classSearchView.getTfName().getText();
		int credits = this.classSearchView.getTfCredits().getText().length() > 0 ? Integer.valueOf(this.classSearchView.getTfCredits().getText()) : 0 ;
		Course selectedCourse = (Course) this.classSearchView.getSelectedCourseComboBox().getSelectedItem();
		Discipline selectedDiscipline = (Discipline) this.classSearchView.getSelectedDisciplineComboBox().getSelectedItem();
		Room selectedRoom = (Room) this.classSearchView.getSelectedRoomComboBox().getSelectedItem();
		Professor selectedProfessor = (Professor) this.classSearchView.getSelectedProfessorComboBox().getSelectedItem();
		String timeOfClass = this.classSearchView.getTfTime().getText();
		
		int course_id = selectedCourse.getId();
		int discipline_id = selectedDiscipline.getId();
		int room_id = selectedRoom.getId();
		int professor_id = selectedProfessor.getProfessorID();
		
		ClassDAO classDAO = new ClassDAO();
		ArrayList<Class> classList = classDAO.search(name, course_id, discipline_id, room_id, professor_id, credits, timeOfClass);
		
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
		int credits = Integer.valueOf(this.classCreationView.getTfCredits().getText());
		Course selectedCourse = (Course) this.classCreationView.getSelectedCourseComboBox().getSelectedItem();
		Discipline selectedDiscipline = (Discipline) this.classCreationView.getSelectedDisciplineComboBox().getSelectedItem();
		Room selectedRoom = (Room) this.classCreationView.getSelectedRoomComboBox().getSelectedItem();
		Professor selectedProfessor = (Professor) this.classCreationView.getSelectedProfessorComboBox().getSelectedItem();
		String timeOfClass = this.classCreationView.getTfTime().getText();
		
		if(validateCreateFields(name, selectedCourse, timeOfClass)) {
			Class newClass = new Class(credits, name, timeOfClass, selectedCourse, selectedDiscipline, selectedRoom, selectedProfessor);
			ClassDAO classDAO = new ClassDAO();
			classDAO.save(newClass);
			
			JOptionPane.showMessageDialog(null, "Turma salva com sucesso");
			clearFieldsCreationView();
			
		} else {
			JOptionPane.showMessageDialog(null, "Preencha todos os campos marcados com (*)");
		}
	}
	
	private boolean validateCreateFields(String name, Course selectedCourse, String timeOfClass) {
		
		return name.length() > 0 && selectedCourse != null && timeOfClass.length() > 0;
	}
	
	private void clearFieldsCreationView() {
		this.classCreationView.getTfName().setText("");
		this.classCreationView.getTfCredits().setText("");
		this.classCreationView.getTfTime().setText("");
	}

}
