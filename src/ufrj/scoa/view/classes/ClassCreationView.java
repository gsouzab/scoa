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
import ufrj.scoa.model.VO.Room;
import ufrj.scoa.model.VO.Discipline;

public class ClassCreationView extends JPanel {

	private static final long serialVersionUID = 1L;
    private JTextField tfName;
    private JTextField tfCode;
    private JTextField tfCredits;
    private JButton btnSalvar;
    private JButton btnCancelar;
	private JComboBox<Course> cbCourse;
	private JComboBox<Room> cbRoom;
	private JComboBox<Discipline> cbDiscipline;
	
	//listas dos comboboxes	
    private ArrayList<Course> coursesList;
    private ArrayList<Discipline> disciplinesList;
    private ArrayList<Room> roomsList;
    
    
    /**
     * Create the frame.
     */
    
    public ClassCreationView(ArrayList<Course> coursesList,ArrayList<Discipline> disciplinesList,ArrayList<Room> roomsList) {
		this.coursesList = coursesList;
		this.disciplinesList = disciplinesList;
		this.roomsList = roomsList;
        
        JLabel lblName = new JLabel("Nome*");
        lblName.setFont(new Font("Arial", Font.BOLD, 12));
        lblName.setBounds(6, 56, 119, 15);
        
        JLabel lblCode = new JLabel("Código*");
        lblCode.setFont(new Font("Arial", Font.BOLD, 12));
        lblCode.setBounds(6, 25, 119, 15);
        
        tfCode = new JTextField();
        tfCode.setFont(new Font("Arial", Font.PLAIN, 13));
        tfCode.setBounds(137, 16, 119, 28);
        tfCode.setColumns(10);
		        
        btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 12));
        btnSalvar.setBounds(400, 420, 110, 29);
        
        btnCancelar = new JButton("Voltar");
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 12));
        btnCancelar.setBounds(520, 420, 110, 29);
        setLayout(null);
        add(lblName);
        add(lblCode);
        
        JLabel label = new JLabel("Curso*");
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setBounds(6, 87, 119, 15);
        add(label);
        
        JLabel lblDiscipline = new JLabel("Disciplina*");
        lblDiscipline.setFont(new Font("Arial", Font.BOLD, 12));
        lblDiscipline.setBounds(6, 114, 119, 15);
        add(lblDiscipline);
        
        JLabel lblRoom = new JLabel("Sala");
        lblRoom.setFont(new Font("Arial", Font.BOLD, 12));
        lblRoom.setBounds(6, 140, 119, 15);
        add(lblRoom);
        
        JLabel lblCredits = new JLabel("Créditos*");
        lblCredits.setFont(new Font("Arial", Font.BOLD, 12));
        lblCredits.setBounds(375, 23, 99, 15);
        add(lblCredits);
        
        tfName = new JTextField();
        tfName.setFont(new Font("Arial", Font.PLAIN, 13));
        tfName.setBounds(137, 49, 483, 28);
        tfName.setColumns(10);
        add(tfName);
        add(tfCode);
        
        cbCourse = new JComboBox<Course>();
        cbCourse.setFont(new Font("Arial", Font.PLAIN, 13));
        cbCourse.setBounds(137, 81, 483, 28);
        add(cbCourse);
        
        cbRoom = new JComboBox<Room>();
        cbRoom.setFont(new Font("Arial", Font.PLAIN, 13));
        cbRoom.setBounds(137, 134, 483, 28);
        add(cbRoom);
        
        cbDiscipline = new JComboBox<Discipline>();
        cbDiscipline.setFont(new Font("Arial", Font.PLAIN, 13));
        cbDiscipline.setBounds(137, 108, 483, 28);
        add(cbDiscipline);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(0, 397, 649, 12);
        add(separator);
        add(btnSalvar);
        add(btnCancelar);
        
        tfCredits = new JTextField();
        tfCredits.setBounds(486, 17, 134, 28);
        add(tfCredits);
        tfCredits.setColumns(10);
        
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

	public JTextField getTfCode() {
		return tfCode;
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
}
