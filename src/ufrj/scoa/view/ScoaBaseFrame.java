package ufrj.scoa.view;

import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ScoaBaseFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JMenuBar menuBar;
	
	//	
	//	Itens do menu de Alunos	
	//	
	private JMenu studentsMenu;
	private JMenuItem listStudentsMenuItem;
	private JMenuItem newStudentMenuItem;

	//	
	//	Itens do menu de Cursos	
	//	
	private JMenu courseMenu;
	private JMenuItem listCoursesMenuItem;
	private JMenuItem newCourseMenuItem;
	
	// Itens menu Professores
	private JMenu professorsMenu;
	private JMenuItem listProfessorsMenuItem;
	private JMenuItem newProfessorsMenuItem;
	
	// Itens menu Disciplinas
	private JMenu disciplinesMenu;
	private JMenuItem listDisciplinesMenuItem;
	private JMenuItem newDisciplinesMenuItem;

	// Itens menu Salas
	private JMenu roomsMenu;
	private JMenuItem newRoomMenuItem;
	private JMenuItem searchRoomMenuItem;

	// Itens menu Turma
	private JMenu classesMenu;
	private JMenuItem newClassMenuItem;
	private JMenuItem searchClassMenuItem;
	
	//Itens menu Secretaria
	private JMenu secretaryMenu;
	private JMenuItem newSecretaryMenuItem;
	private JMenuItem mntmBuscarSecretaria;
	private JMenuItem manageStudentDisciplineMenuItem;
	
	//Itens menu Minha Conta
	private JMenu mnMinhaConta;
	private JMenuItem mntmDetalharMenuItem;
	private JMenuItem mntmLogout;
	
	JPanel contentPane;
	
	// Itens menu Pedidos	
	private JMenu studentRequestsMenu;
	private JMenuItem newStudentDisciplineMenuItem;
	
	public ScoaBaseFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 640, 520);
        setResizable(false);
        
        this.generateMenu();
	}
	
	private void generateMenu()
    {
    	menuBar = new JMenuBar();
    	menuBar.setFont(new Font("Arial", Font.BOLD, 12));
        setJMenuBar(menuBar);
        
        courseMenu = new JMenu("Cursos");
        courseMenu.setHorizontalAlignment(SwingConstants.LEFT);
        courseMenu.setFont(new Font("Arial", Font.PLAIN, 12));
        newCourseMenuItem = new JMenuItem("Cadastrar Curso");
        newCourseMenuItem.setFont(new Font("Arial", Font.PLAIN, 12));
        listCoursesMenuItem = new JMenuItem("Buscar Cursos");
        listCoursesMenuItem.setFont(new Font("Arial", Font.PLAIN, 12));
        
        courseMenu.add(newCourseMenuItem);
        courseMenu.add(listCoursesMenuItem);
        
        studentsMenu = new JMenu("Alunos");
        studentsMenu.setHorizontalAlignment(SwingConstants.LEFT);
        studentsMenu.setFont(new Font("Arial", Font.PLAIN, 12));
        newStudentMenuItem = new JMenuItem("Cadastrar Aluno");
        newStudentMenuItem.setFont(new Font("Arial", Font.PLAIN, 12));
        listStudentsMenuItem = new JMenuItem("Buscar Alunos");
        listStudentsMenuItem.setFont(new Font("Arial", Font.PLAIN, 12));
        
        studentsMenu.add(newStudentMenuItem);
        studentsMenu.add(listStudentsMenuItem);
        
        professorsMenu = new JMenu("Professores");
        professorsMenu.setHorizontalAlignment(SwingConstants.LEFT);
        professorsMenu.setFont(new Font("Arial", Font.PLAIN, 12));
        newProfessorsMenuItem = new JMenuItem("Cadastrar Professor");
        newProfessorsMenuItem.setFont(new Font("Arial", Font.PLAIN, 12));
        listProfessorsMenuItem = new JMenuItem("Buscar Professores");
        listProfessorsMenuItem.setFont(new Font("Arial", Font.PLAIN, 12));
        
        professorsMenu.add(newProfessorsMenuItem);
        professorsMenu.add(listProfessorsMenuItem);
        
        disciplinesMenu = new JMenu("Disciplinas");
        disciplinesMenu.setHorizontalAlignment(SwingConstants.LEFT);
        disciplinesMenu.setFont(new Font("Arial", Font.PLAIN, 12));
        newDisciplinesMenuItem = new JMenuItem("Cadastrar Disciplina");
        newDisciplinesMenuItem.setFont(new Font("Arial", Font.PLAIN, 12));
        listDisciplinesMenuItem = new JMenuItem("Buscar Disciplinas");
        listDisciplinesMenuItem.setFont(new Font("Arial", Font.PLAIN, 12));
        
        disciplinesMenu.add(newDisciplinesMenuItem);
        disciplinesMenu.add(listDisciplinesMenuItem);
        
        secretaryMenu = new JMenu("Secretaria");
        secretaryMenu.setHorizontalAlignment(SwingConstants.LEFT);
        secretaryMenu.setFont(new Font("Arial", Font.PLAIN, 12));
        newSecretaryMenuItem = new JMenuItem("Cadastrar Secretaria");
        newSecretaryMenuItem.setFont(new Font("Arial", Font.PLAIN, 12));
        
        secretaryMenu.add(newSecretaryMenuItem);
        menuBar.add(courseMenu);
        menuBar.add(studentsMenu);
        menuBar.add(professorsMenu);
        menuBar.add(disciplinesMenu);
        menuBar.add(secretaryMenu);
        
        mntmBuscarSecretaria = new JMenuItem("Buscar Secretaria");
        mntmBuscarSecretaria.setFont(new Font("Arial", Font.PLAIN, 12));
        secretaryMenu.add(mntmBuscarSecretaria);
        
        manageStudentDisciplineMenuItem = new JMenuItem("Gerenciar inscrições");
        secretaryMenu.add(manageStudentDisciplineMenuItem);
        manageStudentDisciplineMenuItem.setFont(new Font("Arial", Font.PLAIN, 12));

        roomsMenu = new JMenu("Salas");
        roomsMenu.setHorizontalAlignment(SwingConstants.LEFT);
        roomsMenu.setFont(new Font("Arial", Font.PLAIN, 12));

        menuBar.add(roomsMenu);
        
        newRoomMenuItem = new JMenuItem("Cadastrar Sala");
        newRoomMenuItem.setFont(new Font("Arial", Font.PLAIN, 12));
        roomsMenu.add(newRoomMenuItem);
        
        searchRoomMenuItem = new JMenuItem("Buscar Salas");
        searchRoomMenuItem.setFont(new Font("Arial", Font.PLAIN, 12));
        roomsMenu.add(searchRoomMenuItem);
        
        classesMenu = new JMenu("Turmas");
        classesMenu.setHorizontalAlignment(SwingConstants.LEFT);
        classesMenu.setFont(new Font("Arial", Font.PLAIN, 12));
        menuBar.add(classesMenu);
        
        newClassMenuItem = new JMenuItem("Cadastrar Turma");
        newClassMenuItem.setFont(new Font("Arial", Font.PLAIN, 12));
        classesMenu.add(newClassMenuItem);
        
        searchClassMenuItem = new JMenuItem("Buscar Turma");
        searchClassMenuItem.setFont(new Font("Arial", Font.PLAIN, 12));
        classesMenu.add(searchClassMenuItem);
        
        studentRequestsMenu = new JMenu("Pedidos");
        studentRequestsMenu.setFont(new Font("Arial", Font.PLAIN, 12));
        menuBar.add(studentRequestsMenu);
        newStudentDisciplineMenuItem = new JMenuItem("Inscrição em disciplina");
        studentRequestsMenu.add(newStudentDisciplineMenuItem);
        newStudentDisciplineMenuItem.setFont(new Font("Arial", Font.PLAIN, 12));
        
        mnMinhaConta = new JMenu("Minha Conta");
        mnMinhaConta.setHorizontalAlignment(SwingConstants.RIGHT);
        mnMinhaConta.setFont(new Font("Arial", Font.PLAIN, 12));
        mnMinhaConta.setHorizontalTextPosition(SwingConstants.CENTER);
        menuBar.add(mnMinhaConta);
        
        mntmDetalharMenuItem = new JMenuItem("Detalhar");
        mntmDetalharMenuItem.setFont(new Font("Arial", Font.PLAIN, 12));
        mnMinhaConta.add(mntmDetalharMenuItem);
        
        mntmLogout = new JMenuItem("Logout");
        mnMinhaConta.add(mntmLogout);
        mntmLogout.setFont(new Font("Arial", Font.PLAIN, 12));
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 624, Short.MAX_VALUE)
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 421, Short.MAX_VALUE)
        );
        getContentPane().setLayout(groupLayout);
        
        setVisible(true);
    	
    }
	
	private void hideMenu()
    {
    	menuBar.setVisible(false);
    }
	
	private void showMenu()
    {
    	menuBar.setVisible(true);
    }

	public JMenuItem getListStudentsMenuItem() {
		return listStudentsMenuItem;
	}

	public JMenuItem getNewStudentMenuItem() {
		return newStudentMenuItem;
	}

	public JMenuItem getListProfessorsMenuItem() {
		return listProfessorsMenuItem;
	}

	public JMenuItem getNewProfessorsMenuItem() {
		return newProfessorsMenuItem;
	}
	
	public JMenuItem getListCoursesMenuItem() {
		return listCoursesMenuItem;
	}

	public JMenuItem getNewCourseMenuItem() {
		return newCourseMenuItem;
	}
	
	public JMenuItem getListDisciplinesMenuItem() {
		return listDisciplinesMenuItem;
	}

	public JMenuItem getNewDisciplineMenuItem() {
		return newDisciplinesMenuItem;
	}
	public JMenuItem getNewSecretaryMenuItem() {
		return newSecretaryMenuItem;
	}	
	public JMenuItem getNewStudentDisciplineMenuItem() {
		return newStudentDisciplineMenuItem;
	}	
	public JMenuItem getLogoutMenuItem() {
		return this.mntmLogout;
	}
	
	public JMenuItem getNewClassMenuItem() {
		return this.newClassMenuItem;
	}
	
	public JMenuItem getNewRoomMenuItem() {
		return newRoomMenuItem;
	}
	
	public JMenuItem getSearchRoomMenuItem() {
		return searchRoomMenuItem;
	}
	
	public JMenuItem getSearchClassMenuItem() {
		return searchClassMenuItem;
	}
	
	public JMenuItem getDetalharMenuItem() {
		return mntmDetalharMenuItem;
	}

	public void changePanel(JPanel newPanel, String newTitle) {
		this.showMenu();
		this._changePanel(newPanel, newTitle);
	}
	
	
	public JMenuItem getBuscarSecretariaMenuItem() {
		return mntmBuscarSecretaria;
	}
	
	public JMenuItem getManageStudentDisciplineMenuItem() {
		return manageStudentDisciplineMenuItem;
	}

	public JMenu getSecretaryMenu() {
		return secretaryMenu;
	}
	

	public JMenu getStudentsMenu() {
		return studentsMenu;
	}

	public JMenu getCourseMenu() {
		return courseMenu;
	}

	public JMenu getProfessorsMenu() {
		return professorsMenu;
	}

	public JMenu getDisciplinesMenu() {
		return disciplinesMenu;
	}

	public JMenu getRoomsMenu() {
		return roomsMenu;
	}

	public JMenu getClassesMenu() {
		return classesMenu;
	}
	
	public JMenu getStudentRequestsMenu() {
		return studentRequestsMenu;
	}

	public void changePanel(JPanel newPanel, String newTitle, boolean showMenu) {
		if(showMenu) {
			this.showMenu();
		} else {
			this.hideMenu();	
		}
		
		this._changePanel(newPanel, newTitle);
	}
	
	private void _changePanel(JPanel newPanel, String newTitle) {
		setTitle(newTitle);
		this.setContentPane(newPanel);
		this.revalidate();
	}
	
	public void setAllMenusVisible() {
		getStudentsMenu().setVisible(true);
		getCourseMenu().setVisible(true);
		getDisciplinesMenu().setVisible(true);
		getProfessorsMenu().setVisible(true);
		getRoomsMenu().setVisible(true);
		getClassesMenu().setVisible(true);
		getSecretaryMenu().setVisible(true);
		getStudentRequestsMenu().setVisible(true);
	}
	
	public void setAllMenusInvisible() {
		getStudentsMenu().setVisible(false);
		getCourseMenu().setVisible(false);
		getDisciplinesMenu().setVisible(false);
		getProfessorsMenu().setVisible(false);
		getRoomsMenu().setVisible(false);
		getClassesMenu().setVisible(false);
		getSecretaryMenu().setVisible(false);
		getStudentRequestsMenu().setVisible(false);
	}
}
