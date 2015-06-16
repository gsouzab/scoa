package ufrj.scoa.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ufrj.scoa.model.VO.Person;
import ufrj.scoa.view.ScoaBaseFrame;

public class ScoaBaseController implements ActionListener {

	private ScoaBaseFrame baseFrame;
	private Person currentUser;
	
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
		baseFrame.getNewRoomMenuItem().addActionListener(this);
		baseFrame.getSearchRoomMenuItem().addActionListener(this);
		baseFrame.getNewSecretaryMenuItem().addActionListener(this);
		baseFrame.getNewClassMenuItem().addActionListener(this);
		baseFrame.getSearchClassMenuItem().addActionListener(this);
		baseFrame.getDetalharMenuItem().addActionListener(this);
		baseFrame.getNewStudentDisciplineMenuItem().addActionListener(this);
		baseFrame.getBuscarSecretariaMenuItem().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		if (event.getSource() == baseFrame.getNewCourseMenuItem()) {
			
			CourseController courseController = new CourseController(this);
			baseFrame.changePanel(courseController.getCourseCreationView(), "Cadastrar Curso");
	        
		} else if(event.getSource() == baseFrame.getNewStudentMenuItem()) {
			
			StudentController studentController = new StudentController(this);
			baseFrame.changePanel(studentController.getStudentCreationView(), "Cadastrar Aluno");
			
		} else if(event.getSource() == baseFrame.getListCoursesMenuItem()) {
			
			CourseController courseController = new CourseController(this);
			baseFrame.changePanel(courseController.getCourseSearchView(), "Buscar Cursos");
		
		} else if(event.getSource() == baseFrame.getListStudentsMenuItem()) {
			
			StudentController studentController = new StudentController(this);
			baseFrame.changePanel(studentController.getStudentSearchView(), "Buscar Alunos");
			
		} else if(event.getSource() == baseFrame.getLogoutMenuItem()) {
			
			AccessController acController = new AccessController(this);
			baseFrame.changePanel(acController.getLoginView(), "Login - SCOA", false);
			
		} else if(event.getSource() == baseFrame.getNewProfessorsMenuItem()) {
			
			ProfessorController professorController = new ProfessorController(this);
			baseFrame.changePanel(professorController.getProfessorCreationView(), "Cadastrar Professor");
			
		} else if(event.getSource() == baseFrame.getListProfessorsMenuItem()) {
			
			ProfessorController professorController = new ProfessorController(this);
			baseFrame.changePanel(professorController.getProfessorSearchView(), "Buscar Professores");
			
		} else if(event.getSource() == baseFrame.getNewDisciplineMenuItem()) {
			
			DisciplineController disciplineController = new DisciplineController(this);
			baseFrame.changePanel(disciplineController.getDisciplineCreationView(), "Cadastrar Disciplina");
			
		} else if(event.getSource() == baseFrame.getListDisciplinesMenuItem()) {
			
			DisciplineController disciplineController = new DisciplineController(this);
			baseFrame.changePanel(disciplineController.getDisciplineSearchView(), "Buscar Disciplinas");
			
		} else if(event.getSource() == baseFrame.getNewClassMenuItem()) {
			
			ClassController classController = new ClassController(this);			
			baseFrame.changePanel(classController.getClassCreationView(), "Cadastrar Turma");
		} else if(event.getSource() == baseFrame.getSearchClassMenuItem()) {
			
			ClassController classController = new ClassController(this);			
			baseFrame.changePanel(classController.getClassSearchView(), "Buscar Turma");
		} else if(event.getSource() == baseFrame.getNewRoomMenuItem()) {
			
			RoomController roomController = new RoomController(this);
			baseFrame.changePanel(roomController.getRoomCreationView(), roomController.getRoomCreationView().getWindowTitle());
			
		} else if(event.getSource() == baseFrame.getSearchRoomMenuItem()) {
			
			RoomController roomController = new RoomController(this);
			baseFrame.changePanel(roomController.getRoomSearchView(), roomController.getRoomSearchView().getWindowTitle());	
			
		} else if(event.getSource() == baseFrame.getNewSecretaryMenuItem()) {
			
			SecretaryController secretaryController = new SecretaryController(this);
			baseFrame.changePanel(secretaryController.getSecretaryCreationView(), "Cadastrar Secretaria");
			
		} else if(event.getSource() == baseFrame.getDetalharMenuItem()) {
			
			AccountInfoController accountInfoController = new AccountInfoController(this);
			accountInfoController.setCurrentUserInfo();
			baseFrame.changePanel(accountInfoController.getAccountInfoView(), "Minha Conta");
			
		} else if(event.getSource() == baseFrame.getNewStudentDisciplineMenuItem()) {
			
			StudentDisciplineController studentDisciplineController = new StudentDisciplineController(this);
			baseFrame.changePanel(studentDisciplineController.getStudentDisciplineCreationView(), "Realizar Inscricao");
			
		} else if(event.getSource() == baseFrame.getBuscarSecretariaMenuItem()) {
			
			SecretaryController secretaryController = new SecretaryController(this);
			baseFrame.changePanel(secretaryController.getSecretarySearchView(), "Buscar Secretários(as)");
		}
		
		
	}

	public ScoaBaseFrame getBaseFrame() {
		return baseFrame;
	}

	public Person getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(Person currentUser) {
		this.currentUser = currentUser;
	}
	
	
}
