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
import ufrj.scoa.model.DAO.StudentDAO;
import ufrj.scoa.model.DAO.StudentDisciplineDAO;
import ufrj.scoa.model.VO.Class;
import ufrj.scoa.model.VO.Course;
import ufrj.scoa.model.VO.Discipline;
import ufrj.scoa.model.VO.Professor;
import ufrj.scoa.model.VO.Room;
import ufrj.scoa.model.VO.Student;
import ufrj.scoa.util.Constants;
import ufrj.scoa.view.classes.ClassBookView;
import ufrj.scoa.view.classes.ClassCreationView;
import ufrj.scoa.view.classes.ClassListView;
import ufrj.scoa.view.classes.ClassSearchView;
import ufrj.scoa.view.classes.ScheduleView;
import ufrj.scoa.view.student.StudentCreationView;
import ufrj.scoa.view.student.StudentListView;
import ufrj.scoa.view.InsertGradesAndFrequenciesDialog;
import ufrj.scoa.view.WelcomeView;

public class ClassController implements ActionListener {

	private ClassCreationView classCreationView;
	private ClassSearchView classSearchView;
	private ClassListView classListView;
	private ClassBookView classBookView;
	private ScoaBaseController baseController;
	private CourseDAO courseDAO = new CourseDAO();
	private RoomDAO roomDAO = new RoomDAO();
	private DisciplineDAO disciplineDAO = new DisciplineDAO();
	private ProfessorDAO professorDAO = new ProfessorDAO();
	private InsertGradesAndFrequenciesDialog gradesAndFrequenciesDialog;
	private ScheduleView scheduleView;
	

	public ClassController(ScoaBaseController baseController) {

		this.baseController = baseController;
		this.classCreationView = new ClassCreationView(courseDAO.list(), disciplineDAO.list(),roomDAO.list(), professorDAO.list());
		this.classCreationView.getBtnSalvar().addActionListener(this);
		this.classCreationView.getBtnCancelar().addActionListener(this);
		
		this.classSearchView = new ClassSearchView(courseDAO.list(), disciplineDAO.list(),roomDAO.list(), professorDAO.list());
		this.classSearchView.getBtnBuscar().addActionListener(this);
		this.classSearchView.getBtnVoltar().addActionListener(this);
		
		this.classListView = new ClassListView();
		this.classListView.getBtnInsertGradesAndFrequencies().addActionListener(this);
		this.classListView.getBtnConsultaDiarioClasse().addActionListener(this);
		this.classListView.getBtnReleaseGrades().addActionListener(this);
		
		this.classBookView = new ClassBookView();
		this.classBookView.getBtnVoltar().addActionListener(this);

		gradesAndFrequenciesDialog = new InsertGradesAndFrequenciesDialog(baseController.getBaseFrame(),this.classListView);			
		gradesAndFrequenciesDialog.getBtnInsert().addActionListener(this);
		gradesAndFrequenciesDialog.getBtnCancel().addActionListener(this);
		
		this.scheduleView = new ScheduleView();
		
	}

	public ClassCreationView getClassCreationView() {
		return classCreationView;
	}
	
	public ClassSearchView getClassSearchView() {
		return classSearchView;
	}
	
	
	public ScheduleView getScheduleView() {
		return scheduleView;
	}
	
	

	public void setScheduleView(ScheduleView scheduleView) {
		this.scheduleView = scheduleView;
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
			
		}  else if(event.getSource() == this.classListView.getBtnInsertGradesAndFrequencies()) {
			
			if(classListView.getList().getSelectedValue() != null) {
				
				int class_id = classListView.getList().getSelectedValue().getId();
				gradesAndFrequenciesDialog.openDialog(class_id);
			} else {
				JOptionPane.showMessageDialog(null, "Selecione uma turma");
			}
			
		} else if(event.getSource() == gradesAndFrequenciesDialog.getBtnInsert()) {
			updateStudentsGradeAndFrequencie();
			JOptionPane.showMessageDialog(null, "Notas/Frequências lançadas");

		} else if(event.getSource() == gradesAndFrequenciesDialog.getBtnCancel()) {
			gradesAndFrequenciesDialog.setVisible(false);

		} else if(event.getSource() == this.classListView.getBtnConsultaDiarioClasse()) {
			if(classListView.getList().getSelectedValue() != null) {
				loadClassBook(classListView.getList().getSelectedValue().getId());
				this.baseController.getBaseFrame().changePanel(classBookView, "Diário de Classe");
				
			} else {
				JOptionPane.showMessageDialog(null, "Selecione uma turma");
			}
			
		} else if(event.getSource() == classListView.getBtnReleaseGrades()) {
		
			
			if(classListView.getList().getSelectedValue() != null) {
				
				int class_id = classListView.getList().getSelectedValue().getId();
			
				if(StudentDisciplineDAO.notasLiberadas(class_id)) {
					JOptionPane.showMessageDialog(null, "As notas já foram liberadas para esta turma!");
				} else {
				
					int dialogResult = JOptionPane.showConfirmDialog (null, "Você tem certeza que deseja liberar as notas para os alunos?", "Atenção!", JOptionPane.YES_NO_OPTION);
					
					if(dialogResult == JOptionPane.YES_OPTION){
						if(StudentDisciplineDAO.releaseGrades(class_id)) {
							classListView.getBtnReleaseGrades().setVisible(false);
							JOptionPane.showMessageDialog(null, "As notas foram liberadas com sucesso!");
						}
						else {
							JOptionPane.showMessageDialog(null, "Algo de errado aconteceu, as notas não foram liberadas.");
						}
					}
				}
			}
			
			else {
				JOptionPane.showMessageDialog(null, "Selecione uma turma!");
			}
			
		} else if(event.getSource() == classBookView.getBtnVoltar()) {
			
			if(baseController.getCurrentUser().getRole() == Constants.ROLE_PROFESSOR) {
				this.baseController.getBaseFrame().changePanel(classListView, "Turmas");
			}
			else {
				
				this.baseController.getBaseFrame().changePanel(classListView, "Buscar Turma");
			}
		}
		
		
	}
	
	private void loadClassBook(int classId) {
		StudentDAO studentDAO = new StudentDAO();
		ClassDAO classDAO = new ClassDAO();
		ArrayList<Student> studentList = studentDAO.getStudentsByClassId(classId);
		String className = classDAO.getClassById(classId).getName();
		classBookView.populateTable(studentList, className);
	}
	
	private void updateStudentsGradeAndFrequencie() {
		gradesAndFrequenciesDialog.setVisible(false);
		
		int rowcount = gradesAndFrequenciesDialog.getTable().getModel().getRowCount();
		float[] grades = new float[rowcount];
		int[] frequencies = new int[rowcount];
		int[] student_ids = new int[rowcount];
		
		for(int i = 0 ; i < rowcount ; i++) {
			student_ids[i] = (int) gradesAndFrequenciesDialog.getTable().getModel().getValueAt(i, 0);
			grades[i] = (float) gradesAndFrequenciesDialog.getTable().getModel().getValueAt(i, 3);
			frequencies[i] = (int) gradesAndFrequenciesDialog.getTable().getModel().getValueAt(i, 4);
		}
		
		int selected_class_id = classListView.getList().getSelectedValue().getId();
		StudentDisciplineDAO.updateGradesAndFrequencies(student_ids, grades,frequencies, selected_class_id);
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
	
	public void searchClassesProfessor(int person_id) {
		
		ClassDAO classDAO = new ClassDAO();
		ProfessorDAO professorDAO = new ProfessorDAO();
		
		String name = "";
		String timeOfClass = "";
		int credits = 0;
		
		int course_id = 0;
		int discipline_id = 0;
		int room_id = 0;
		
		Professor professor = professorDAO.getProfessorByPersonId(person_id);
		int professor_id = professor.getProfessorID();
		

		ArrayList<Class> classList = classDAO.search(name, course_id, discipline_id, room_id, professor_id, credits, timeOfClass);
		
		this.classListView.setList(classList);
		
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

	public ClassListView getClassListView() {
		return classListView;
	}
	

}
