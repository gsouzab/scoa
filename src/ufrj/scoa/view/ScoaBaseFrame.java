package ufrj.scoa.view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;

public class ScoaBaseFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JMenuBar menuBar;
	
	//	
	//	Itens do menu de alunos	
	//	
	private JMenuItem listStudentsMenuItem;
	private JMenuItem newStudentMenuItem;
	//	
	//	Itens do menu de cursos	
	//	
	private JMenuItem listCoursesMenuItem;
	private JMenuItem newCourseMenuItem;
	private JMenuItem mntmLogout;
	
	JPanel contentPane;
	private Component horizontalStrut;
	
	public ScoaBaseFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 640, 520);
        setResizable(false);
        
        this.generateMenu();
	}
	
	private void generateMenu()
    {
    	menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu courseMenu = new JMenu("Cursos");
        courseMenu.setFont(new Font("Arial", Font.PLAIN, 12));
        newCourseMenuItem = new JMenuItem("Cadastrar Curso");
        newCourseMenuItem.setFont(new Font("Arial", Font.PLAIN, 12));
        listCoursesMenuItem = new JMenuItem("Listar Cursos");
        listCoursesMenuItem.setFont(new Font("Arial", Font.PLAIN, 12));
        
        courseMenu.add(newCourseMenuItem);
        courseMenu.add(listCoursesMenuItem);
        
        JMenu studentsMenu = new JMenu("Alunos");
        studentsMenu.setFont(new Font("Arial", Font.PLAIN, 12));
        newStudentMenuItem = new JMenuItem("Cadastrar Aluno");
        newStudentMenuItem.setFont(new Font("Arial", Font.PLAIN, 12));
        listStudentsMenuItem = new JMenuItem("Listar Alunos");
        listStudentsMenuItem.setFont(new Font("Arial", Font.PLAIN, 12));
        
        studentsMenu.add(newStudentMenuItem);
        studentsMenu.add(listStudentsMenuItem);
        
        menuBar.add(courseMenu);
        menuBar.add(studentsMenu);
        
        horizontalStrut = Box.createHorizontalStrut(479);
        menuBar.add(horizontalStrut);
        
        mntmLogout = new JMenuItem("Logout");
        mntmLogout.setHorizontalAlignment(SwingConstants.RIGHT);
        mntmLogout.setFont(new Font("Arial", Font.PLAIN, 12));
        menuBar.add(mntmLogout);
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

	public JMenuItem getListCoursesMenuItem() {
		return listCoursesMenuItem;
	}

	public JMenuItem getNewCourseMenuItem() {
		return newCourseMenuItem;
	}
	
	public JMenuItem getLogoutMenuItem() {
		return this.mntmLogout;
	}
	
	public void changePanel(JPanel newPanel, String newTitle) {
		this.showMenu();
		this._changePanel(newPanel, newTitle);
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
}
