package ufrj.scoa.view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class ScoaBaseFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	//	
	//	Itens do menu de alunos	
	//	
	private JMenuItem listStudentsMenuItem;
	private JMenuItem newStudentMenuItem;
	//	
	//	Itens do menu de cursos	
	//	
	JMenuItem listCoursesMenuItem;
	JMenuItem newCourseMenuItem;
	
	JPanel contentPane;
	
	public ScoaBaseFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 640, 480);
        setResizable(false);
        
        this.generateMenu();
	}
	
	private void generateMenu()
    {
    	JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu courseMenu = new JMenu("Cursos");
        newCourseMenuItem = new JMenuItem("Novo Curso");
        listCoursesMenuItem = new JMenuItem("Listar Cursos");
        
        courseMenu.add(newCourseMenuItem);
        courseMenu.add(listCoursesMenuItem);
        
        JMenu studentsMenu = new JMenu("Alunos");
        newStudentMenuItem = new JMenuItem("Novo Aluno");
        listStudentsMenuItem = new JMenuItem("Listar Alunos");
        
        studentsMenu.add(newStudentMenuItem);
        studentsMenu.add(listStudentsMenuItem);
        
        menuBar.add(courseMenu);
        menuBar.add(studentsMenu);
        
        setVisible(true);
    	
    }

	public JMenuItem getListStudentsMenuItem() {
		return listStudentsMenuItem;
	}

	public JMenuItem getNewStudentMenuItem() {
		return newStudentMenuItem;
	}

	public JMenuItem getListCoursesMenuItem() {
		return listCoursesMenuItem;
	}

	public JMenuItem getNewCourseMenuItem() {
		return newCourseMenuItem;
	}
	
	
	public void changePanel(JPanel newPanel, String newTitle) {
		setTitle(newTitle);
		this.setContentPane(newPanel);
		this.revalidate();
	}
	
    
}
