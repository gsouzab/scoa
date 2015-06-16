package ufrj.scoa.view.classes;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import ufrj.scoa.model.VO.Course;
import ufrj.scoa.model.VO.Professor;
import ufrj.scoa.model.VO.Room;
import ufrj.scoa.model.VO.Discipline;

public class ClassCreationView extends JPanel {

	private static final long serialVersionUID = 1L;
    private JTextField tfName;
    private JTextField tfCredits;
    private JButton btnSalvar;
    private JButton btnCancelar;
	private JComboBox<Course> cbCourse;
	private JComboBox<Room> cbRoom;
	private JComboBox<Discipline> cbDiscipline;
	private JComboBox<Professor> cbProfessor;
	
    private ArrayList<Course> coursesList;
    private ArrayList<Discipline> disciplinesList;
    private ArrayList<Room> roomsList;
    private ArrayList<Professor> professorsList;
    private JLabel lblHorrio;
    private JTextField tfTime;
    
    
    /**
     * Create the frame.
     */
    
    public ClassCreationView(ArrayList<Course> coursesList,ArrayList<Discipline> disciplinesList,ArrayList<Room> roomsList, ArrayList<Professor> professorsList) {
		this.coursesList = coursesList;
		this.disciplinesList = disciplinesList;
		this.roomsList = roomsList;
		this.professorsList = professorsList;
        
        JLabel lblName = new JLabel("Nome*");
        lblName.setFont(new Font("Arial", Font.BOLD, 12));
        lblName.setBounds(6, 12, 119, 15);
		        
        btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 12));
        btnSalvar.setBounds(400, 420, 110, 29);
        
        btnCancelar = new JButton("Voltar");
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 12));
        btnCancelar.setBounds(520, 420, 110, 29);
        setLayout(null);
        add(lblName);
        
        JLabel label = new JLabel("Curso*");
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setBounds(6, 45, 119, 15);
        add(label);
        
        JLabel lblDiscipline = new JLabel("Disciplina*");
        lblDiscipline.setFont(new Font("Arial", Font.BOLD, 12));
        lblDiscipline.setBounds(6, 79, 119, 15);
        add(lblDiscipline);
        
        JLabel lblRoom = new JLabel("Sala*");
        lblRoom.setFont(new Font("Arial", Font.BOLD, 12));
        lblRoom.setBounds(6, 146, 119, 15);
        add(lblRoom);
        
        JLabel lblCredits = new JLabel("Créditos*");
        lblCredits.setFont(new Font("Arial", Font.BOLD, 12));
        lblCredits.setBounds(484, 12, 99, 15);
        add(lblCredits);
        
        tfName = new JTextField();
        tfName.setFont(new Font("Arial", Font.PLAIN, 13));
        tfName.setBounds(105, 5, 361, 28);
        tfName.setColumns(10);
        add(tfName);
        
        cbCourse = new JComboBox<Course>();
        cbCourse.setFont(new Font("Arial", Font.PLAIN, 13));
        cbCourse.setBounds(105, 39, 515, 28);
        add(cbCourse);
        
        cbRoom = new JComboBox<Room>();
        cbRoom.setFont(new Font("Arial", Font.PLAIN, 13));
        cbRoom.setBounds(105, 139, 515, 28);
        add(cbRoom);
        
        cbDiscipline = new JComboBox<Discipline>();
        cbDiscipline.setFont(new Font("Arial", Font.PLAIN, 13));
        cbDiscipline.setBounds(105, 72, 515, 28);
        add(cbDiscipline);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(0, 397, 649, 12);
        add(separator);
        add(btnSalvar);
        add(btnCancelar);
        
        tfCredits = new JTextField();
        tfCredits.setBounds(556, 5, 59, 28);
        add(tfCredits);
        tfCredits.setColumns(10);
        
        JLabel lblProfessor = new JLabel("Professor*");
        lblProfessor.setFont(new Font("Arial", Font.BOLD, 12));
        lblProfessor.setBounds(6, 113, 91, 15);
        add(lblProfessor);
        
        cbProfessor = new JComboBox<Professor>();
        cbProfessor.setFont(new Font("Arial", Font.PLAIN, 13));
        cbProfessor.setBounds(105, 106, 515, 28);
        add(cbProfessor);
        
        lblHorrio = new JLabel("Horário*");
        lblHorrio.setFont(new Font("Arial", Font.BOLD, 12));
        lblHorrio.setBounds(6, 180, 70, 15);
        add(lblHorrio);
        
        tfTime = new JTextField();
        tfTime.setBounds(105, 173, 361, 28);
        add(tfTime);
        tfTime.setColumns(10);
        
        JLabel lblSegqua = new JLabel("Ex: SEG-QUA, 10-12");
        lblSegqua.setBounds(474, 179, 146, 15);
        add(lblSegqua);
        
		populateComboBoxes();
   	}
    
	private void populateComboBoxes() {
		
		
		for(Course c : coursesList) {
			cbCourse.addItem(c);
		}
		
		for(Room r : roomsList) {
			cbRoom.addItem(r);
		}
		
		for(Discipline d : disciplinesList) {
			cbDiscipline.addItem(d);
		}
		
		for(Professor p: professorsList) {
			cbProfessor.addItem(p);
		}
	}
    
	public JButton getBtnSalvar() {
		return btnSalvar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JTextField getTfName() {
		return tfName;
	}
	
		
	public JTextField getTfTime() {
		return tfTime;
	}

	public JTextField getTfCredits() {
		return tfCredits;
	}

	public JComboBox<Room> getSelectedRoomComboBox() {
		return cbRoom;
	}

	public JComboBox<Discipline> getSelectedDisciplineComboBox() {
		return cbDiscipline;
	}

	public JComboBox<Course> getSelectedCourseComboBox() {
		return cbCourse;
	}
	
	public JComboBox<Professor> getSelectedProfessorComboBox() {
		return cbProfessor;
	}
}
