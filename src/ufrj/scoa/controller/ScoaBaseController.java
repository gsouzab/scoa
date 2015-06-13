package ufrj.scoa.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ufrj.scoa.view.ScoaBaseFrame;

public class ScoaBaseController implements ActionListener {

	private ScoaBaseFrame baseFrame;
	
	public ScoaBaseController()
	{
		this.baseFrame = new ScoaBaseFrame();
		
		baseFrame.getNewCourseMenuItem().addActionListener(this);
		baseFrame.getListCoursesMenuItem().addActionListener(this);
		baseFrame.getNewStudentMenuItem().addActionListener(this);
		baseFrame.getListCoursesMenuItem().addActionListener(this);
		baseFrame.getListStudentsMenuItem().addActionListener(this);
		baseFrame.getLogoutMenuItem().addActionListener(this);
		baseFrame.getLogoutMenuItem().addActionListener(this);
		baseFrame.getListProfessorsMenuItem().addActionListener(this);
		baseFrame.getNewProfessorsMenuItem().addActionListener(this);
		baseFrame.getListDisciplinesMenuItem().addActionListener(this);
		baseFrame.getNewDisciplineMenuItem().addActionListener(this);
		baseFrame.getNewClassMenuItem().addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		if (event.getSource() == baseFrame.getNewCourseMenuItem()) {
			
			CourseController courseController = new CourseController(this);
			baseFrame.changePanel(courseController.getCourseCreationView(), "Cadastro de Curso");
	        
		} else if(event.getSource() == baseFrame.getNewStudentMenuItem()) {
			
			StudentController studentController = new StudentController(this);
			baseFrame.changePanel(studentController.getStudentCreationView(), "Cadastro de Aluno");
			
		} else if(event.getSource() == baseFrame.getListCoursesMenuItem()) {
			
			CourseController courseController = new CourseController(this);
			courseController.listCourses();
			baseFrame.changePanel(courseController.getCoursesListView(), "Listagem de Cursos");
		
		} else if(event.getSource() == baseFrame.getListStudentsMenuItem()) {
			
			StudentController studentController = new StudentController(this);
			baseFrame.changePanel(studentController.getStudentSearchView(), "Listagem de Alunos");
		} else if(event.getSource() == baseFrame.getLogoutMenuItem()) {
			
			AccessController acController = new AccessController(this);
			
			baseFrame.changePanel(acController.getLoginView(), "Login - SCOA", false);
		} else if(event.getSource() == baseFrame.getNewProfessorsMenuItem()) {
			
			ProfessorController professorController = new ProfessorController(this);
			baseFrame.changePanel(professorController.getProfessorCreationView(), "Cadastro de Professor");
			
		} else if(event.getSource() == baseFrame.getListProfessorsMenuItem()) {
			
			ProfessorController professorController = new ProfessorController(this);
			professorController.listProfessors();
			baseFrame.changePanel(professorController.getProfessorListView(), "Listagem de Professores");
		} else if(event.getSource() == baseFrame.getNewDisciplineMenuItem()) {
			
			DisciplineController disciplineController = new DisciplineController(this);
			baseFrame.changePanel(disciplineController.getDisciplineCreationView(), "Cadastro de Disciplina");
			
		} else if(event.getSource() == baseFrame.getListDisciplinesMenuItem()) {
			
			DisciplineController disciplineController = new DisciplineController(this);
			disciplineController.listDisciplines();
			baseFrame.changePanel(disciplineController.getDisciplinesListView(), "Listagem de Disciplinas");
		} else if(event.getSource() == baseFrame.getNewClassMenuItem()) {
			
			ClassController classController = new ClassController(this);			
			baseFrame.changePanel(classController.getClassCreationView(), "Cadastro de Turma");
		}
		
	}

	public ScoaBaseFrame getBaseFrame() {
		return baseFrame;
	}
	
}
