package ufrj.scoa.view.classes;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import ufrj.scoa.model.DAO.StudentDAO;
import ufrj.scoa.model.VO.Person;
import ufrj.scoa.model.VO.Student;
import ufrj.scoa.model.VO.Class;
import ufrj.scoa.util.Constants;

public class StudentDisciplineCreationView extends JPanel {

	

	private static final long serialVersionUID = 1L;
    
	private JButton btnSalvar;
    private JButton btnCancelar;
	private JComboBox<Student> cbStudent;
	private JComboBox<Class> cbClass;

    private ArrayList<Student> studentsList;
    private ArrayList<Class> classesList;

    private Person currentUser = null;
    
    /**
     * Create the frame.
     */
    
    public StudentDisciplineCreationView(ArrayList<Student> studentsList, ArrayList<Class> classesList, Person currentUser) {
		
		this.studentsList = studentsList;
		this.classesList = classesList;
		this.currentUser = currentUser;
		        
        btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 12));
        btnSalvar.setBounds(400, 420, 110, 29);
        
        btnCancelar = new JButton("Voltar");
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 12));
        btnCancelar.setBounds(520, 420, 110, 29);
        setLayout(null);
        
        JLabel lblTurma = new JLabel("Turma*");
        lblTurma.setFont(new Font("Arial", Font.BOLD, 12));
        lblTurma.setBounds(6, 87, 119, 15);
        add(lblTurma);
        
        JLabel lblClass = new JLabel("Aluno*");
        lblClass.setFont(new Font("Arial", Font.BOLD, 12));
        lblClass.setBounds(6, 133, 119, 15);
        add(lblClass);
                        
        cbStudent = new JComboBox<Student>();
        cbStudent.setFont(new Font("Arial", Font.PLAIN, 13));
        cbStudent.setBounds(143, 126, 483, 28);
        add(cbStudent);
        
        cbClass = new JComboBox<Class>();
        cbClass.setFont(new Font("Arial", Font.PLAIN, 13));
        cbClass.setBounds(143, 80, 483, 28);
        add(cbClass);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(0, 397, 649, 12);
        add(separator);
        add(btnSalvar);
        add(btnCancelar);
                
		populateComboBoxes();
   	}
    
	private void populateComboBoxes() {

		if(currentUser != null && currentUser.getRole() == Constants.ROLE_STUDENT) {
			StudentDAO dao = new StudentDAO();
			
			cbStudent.addItem(dao.getStudentByPersonId(currentUser.getPersonId()));
			cbStudent.setEditable(false);
			cbStudent.setEnabled(false);
		} else {
			for(Student r : studentsList) {
				cbStudent.addItem(r);
			}
		}
		
		for(Class d : classesList) {
			cbClass.addItem(d);
		}
	}
    
	public JButton getBtnSalvar() {
		return btnSalvar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}
	
	public JComboBox<Student> getSelectedStudentComboBox() {
		return cbStudent;
	}

	public JComboBox<Class> getSelectedClassComboBox() {
		return cbClass;
	}

}
