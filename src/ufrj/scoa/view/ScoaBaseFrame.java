package ufrj.scoa.view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ScoaBaseFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public ScoaBaseFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 640, 480);
        
        this.generateMenu();
	}
	
	private void generateMenu()
    {
    	JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu courseMenu = new JMenu("Cursos");
        JMenuItem newCourseItem = new JMenuItem("Novo Curso");
        JMenuItem listCoursesItem = new JMenuItem("Listar Cursos");
        
        courseMenu.add(newCourseItem);
        courseMenu.add(listCoursesItem);
        
        JMenu studentsMenu = new JMenu("Alunos");
        JMenuItem newStudentItem = new JMenuItem("Novo Aluno");
        JMenuItem listStudentsItem = new JMenuItem("Listar Alunos");
        
        studentsMenu.add(newStudentItem);
        studentsMenu.add(listStudentsItem);
        
        menuBar.add(courseMenu);
        menuBar.add(studentsMenu);
    	
    }
}
