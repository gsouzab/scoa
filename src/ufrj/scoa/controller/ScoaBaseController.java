package ufrj.scoa.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import ufrj.scoa.model.DAO.StudentDAO;
import ufrj.scoa.model.DAO.StudentDisciplineDAO;
import ufrj.scoa.model.VO.Discipline;
import ufrj.scoa.model.VO.Person;
import ufrj.scoa.model.VO.Student;
import ufrj.scoa.model.VO.StudentDiscipline;
import ufrj.scoa.util.Constants;
import ufrj.scoa.view.ScoaBaseFrame;
import ufrj.scoa.view.classes.ScheduleView;
import ufrj.scoa.view.student.HistoryView;

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
		baseFrame.getManageStudentDisciplineMenuItem().addActionListener(this);
		baseFrame.getScheduleMenu().addActionListener(this);
		baseFrame.getHistoryMenu().addActionListener(this);
		baseFrame.getMntmInicarNovoPeriodo().addActionListener(this);
	
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
			baseFrame.setAllMenusVisible();
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
			
			if(currentUser.getRole() == Constants.ROLE_PROFESSOR) {
				classController.searchClassesProfessor(currentUser.getPersonId());
				classController.getClassListView().getLblCursos().setText("Suas turmas");
				baseFrame.changePanel(classController.getClassListView(), "Turmas");
			}
			else {
				
				baseFrame.changePanel(classController.getClassSearchView(), "Buscar Turma");
			}
			
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
			baseFrame.changePanel(studentDisciplineController.getStudentDisciplineCreationView(), "Inscrever Aluno");
			
		} else if(event.getSource() == baseFrame.getBuscarSecretariaMenuItem()) {
			
			SecretaryController secretaryController = new SecretaryController(this);
			baseFrame.changePanel(secretaryController.getSecretarySearchView(), "Buscar Secretários(as)");
			
		} else if(event.getSource() ==  baseFrame.getManageStudentDisciplineMenuItem()) {
			
			SecretaryController secretaryController = new SecretaryController(this);
			
			if(currentUser.getRole() == Constants.ROLE_SECRETARY) {

				baseFrame.changePanel(secretaryController.getSecretaryStudentDisciplineManagmentView(), "Gerenciamento de inscrição em disciplinas");
			}
			else if(currentUser.getRole() == Constants.ROLE_STUDENT) {
				
				StudentDAO studentDAO = new StudentDAO();
				
				Student currentStudent = studentDAO.getStudentByPersonId(currentUser.getPersonId());
				ArrayList<StudentDiscipline> disciplinesList = StudentDisciplineDAO.getNewDisciplinesRequestsStudent(currentStudent.getStudentId());
				secretaryController.getSecretaryStudentDisciplineManagmentView().populateTableStudent(disciplinesList);
				
				secretaryController.getSecretaryStudentDisciplineManagmentView().getBtnApprove().setVisible(false);
				secretaryController.getSecretaryStudentDisciplineManagmentView().getBtnDisapprove().setVisible(false);
				baseFrame.changePanel(secretaryController.getSecretaryStudentDisciplineManagmentView(), "Gerenciamento de inscrição em disciplinas");

			}

		} else if(event.getSource() ==  baseFrame.getScheduleMenu()) {
			
			StudentDAO studentDAO = new StudentDAO();
			Student student = studentDAO.getStudentByPersonId(currentUser.getPersonId());
			int studentId = student.getStudentId();
			StudentDAO sDAO = new StudentDAO();
			ArrayList<ufrj.scoa.model.VO.Class> classesList = sDAO.getTimeOfClassByStudentId(studentId);
			String studentName = currentUser.getName();
			
			ScheduleView sv = new ScheduleView();
			sv.populateTable(classesList, studentName);
			
			ClassController classController = new ClassController(this);
			classController.setScheduleView(sv);
			baseFrame.changePanel(classController.getScheduleView(), "Horários");
			
			
		} else if(event.getSource() ==  baseFrame.getMntmInicarNovoPeriodo()) {
			if(currentUser.getRole() == Constants.ROLE_ADMINISTRATOR) {
				int dialogResult = JOptionPane.showConfirmDialog (null, "Você tem certeza que quer iniciar um novo período?", "Atenção!", JOptionPane.YES_NO_OPTION);
				
				if(dialogResult == JOptionPane.YES_OPTION){
					StudentDAO sd = new StudentDAO();
					if(sd.newPeriod()) {
						JOptionPane.showMessageDialog(null, "Um novo período foi iniciado!");
					}
					else {
						JOptionPane.showMessageDialog(null, "Algo de errado aconteceu. O período não foi criado.");
					}
				}
			}
			
		} else if(event.getSource() ==  baseFrame.getHistoryMenu()) {
			
			String studentName = currentUser.getName();
			StudentDAO sdao = new StudentDAO();
			ArrayList<StudentDiscipline> disciplines = sdao.getHistory(sdao.getStudentByPersonId(currentUser.getPersonId()).getStudentId());
			HistoryView hv = new HistoryView();
			hv.populateTable(disciplines, studentName);
			
			StudentController sc = new StudentController(this);
			sc.setHistoryView(hv);
			baseFrame.changePanel(sc.getHistoryView(), "Histórico");
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
	
	public void generateMenus() {
		
		switch (currentUser.getRole()) {
			case Constants.ROLE_ADMINISTRATOR:
				generateAdmMenu();
				break;
		
			case Constants.ROLE_SECRETARY:
				generateSecretaryMenu();
				break;
			
			case Constants.ROLE_PROFESSOR:
				generateProfessorMenu();
				break;
			
			case Constants.ROLE_STUDENT:
				generateStudentMenu();
				break;

			default:
				break;
		}
	}

	private void generateAdmMenu() {
		baseFrame.setAllMenusVisible();
		baseFrame.getMnConsultas().setVisible(false);
		
	}
	
	private void generateSecretaryMenu() {
		baseFrame.setAllMenusVisible();
		baseFrame.getSecretaryMenu().setVisible(false);
		baseFrame.getMnPeriodo().setVisible(false);;
		baseFrame.getMnConsultas().setVisible(false);
	}
	
	private void generateProfessorMenu() {
		baseFrame.setAllMenusInvisible();
		baseFrame.getClassesMenu().setVisible(true);
		baseFrame.getNewClassMenuItem().setVisible(false);
	}
	
	private void generateStudentMenu() {
		baseFrame.setAllMenusInvisible();
		baseFrame.getStudentRequestsMenu().setVisible(true);
		baseFrame.getMnConsultas().setVisible(true);
	}

	
}
