package ufrj.scoa.view.classes;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import ufrj.scoa.model.VO.Course;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;

public class ClassCreationView extends JPanel {

	private static final long serialVersionUID = 1L;
    private JTextField tfName;
    private JTextField tfCode;
    private JButton btnSalvar;
    private JButton btnCancelar;
	private JComboBox<Course> cbCourse;
    private ArrayList<Course> coursesList;
    private JCheckBox activeClass;
    
    /**
     * Create the frame.
     */
    
    public ClassCreationView(ArrayList<Course> coursesList) {
		this.coursesList = coursesList;
        
        tfName = new JTextField();
        tfName.setFont(new Font("Arial", Font.PLAIN, 13));
        tfName.setBounds(137, 95, 483, 28);
        tfName.setColumns(10);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(0, 397, 649, 12);
        
        JLabel lblName = new JLabel("Nome*");
        lblName.setFont(new Font("Arial", Font.BOLD, 12));
        lblName.setBounds(6, 102, 119, 15);
        
        JLabel lblCode = new JLabel("Código*");
        lblCode.setFont(new Font("Arial", Font.BOLD, 12));
        lblCode.setBounds(6, 64, 119, 15);
        
        tfCode = new JTextField();
        tfCode.setFont(new Font("Arial", Font.PLAIN, 13));
        tfCode.setBounds(137, 55, 119, 28);
        tfCode.setColumns(10);
        
        cbCourse = new JComboBox<Course>();
		cbCourse.setFont(new Font("Arial", Font.PLAIN, 13));
		
		populateComboBox();
		
		cbCourse.setBounds(137, 133, 483, 28);
		add(cbCourse);
        
        btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 12));
        btnSalvar.setBounds(400, 420, 110, 29);
        
        btnCancelar = new JButton("Voltar");
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 12));
        btnCancelar.setBounds(520, 420, 110, 29);
        setLayout(null);
        add(tfName);
        add(separator);
        add(lblName);
        add(lblCode);
        
        JLabel label = new JLabel("Curso*");
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setBounds(6, 139, 119, 15);
        add(label);
        add(tfCode);
        add(btnSalvar);
        add(btnCancelar);
        
        JLabel lblNewLabel = new JLabel("Turma ativa");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 12));
        lblNewLabel.setBounds(6, 27, 83, 16);
        add(lblNewLabel);
        
        activeClass = new JCheckBox("");
        activeClass.setSelected(true);
        activeClass.setBounds(137, 20, 36, 23);
        add(activeClass);
   	}

    public JCheckBox getActiveClass() {
		return activeClass;
	}

	private void populateComboBox() {
		for(Course c : coursesList) {
			cbCourse.addItem(c);
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
	
	public JComboBox<Course> getSelectedCourseComboBox() {
		return cbCourse;
	}
}
