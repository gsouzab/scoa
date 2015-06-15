package ufrj.scoa.view.classes;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import ufrj.scoa.model.VO.Student;
import ufrj.scoa.model.VO.Class;

public class StudentDisciplineCreationView extends JPanel {

	

	private static final long serialVersionUID = 1L;
    
	private JButton btnSalvar;
    private JButton btnCancelar;
	private JComboBox<Student> cbStudent;
	private JComboBox<Class> cbClass;
	//listas dos comboboxes	
    private ArrayList<Student> studentsList;
    private ArrayList<Class> classesList;

    
    /**
     * Create the frame.
     */
    
    public StudentDisciplineCreationView(ArrayList<Student> studentsList, ArrayList<Class> classesList) {
		
		this.studentsList = studentsList;
		this.classesList = classesList;
        
		        
        btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 12));
        btnSalvar.setBounds(400, 420, 110, 29);
        
        btnCancelar = new JButton("Voltar");
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 12));
        btnCancelar.setBounds(520, 420, 110, 29);
        setLayout(null);
        
        JLabel label = new JLabel("Turma");
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setBounds(6, 87, 119, 15);
        add(label);
        
        JLabel lblClass = new JLabel("Aluno*");
        lblClass.setFont(new Font("Arial", Font.BOLD, 12));
        lblClass.setBounds(6, 114, 119, 15);
        add(lblClass);
                        
        cbStudent = new JComboBox<Student>();
        cbStudent.setFont(new Font("Arial", Font.PLAIN, 13));
        cbStudent.setBounds(137, 134, 483, 28);
        add(cbStudent);
        
        cbClass = new JComboBox<Class>();
        cbClass.setFont(new Font("Arial", Font.PLAIN, 13));
        cbClass.setBounds(137, 108, 483, 28);
        add(cbClass);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(0, 397, 649, 12);
        add(separator);
        add(btnSalvar);
        add(btnCancelar);
                
		populateComboBoxes();
   	}
    
	private void populateComboBoxes() {
		
		
		for(Student r : studentsList) {
			cbStudent.addItem(r);
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
