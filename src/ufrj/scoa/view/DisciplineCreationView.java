package ufrj.scoa.view;

import java.awt.Component;
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

import org.eclipse.wb.swing.FocusTraversalOnArray;

import ufrj.scoa.model.VO.Professor;

public class DisciplineCreationView extends JPanel {
    

	private static final long serialVersionUID = 1L;
    private JTextField tfName;
    private JTextField tfCredits;
    private JTextArea taDescription;
	private JComboBox<Professor> cbProfessor;
	private ArrayList<Professor> professorList;

    private JButton btnSalvar;
    private JButton btnCancelar;
    
    /**
     * Create the frame.
     */
    
    public DisciplineCreationView(ArrayList<Professor> professorList) {
		this.professorList = professorList;

        tfName = new JTextField();
        tfName.setFont(new Font("Arial", Font.PLAIN, 13));
        tfName.setBounds(139, 25, 483, 28);
        tfName.setColumns(10);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(0, 397, 649, 12);
        
        JPanel panelDescription = new JPanel();
        panelDescription.setBounds(230, 10, 1, 1);
        panelDescription.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panelDescription.setLayout(null);
        
        JPanel panelButtons = new JPanel();
        panelButtons.setBounds(236, 10, 1, 1);
        panelButtons.setLayout(null);
        
        JLabel lblName = new JLabel("Nome*");
        lblName.setFont(new Font("Arial", Font.BOLD, 12));
        lblName.setBounds(10, 31, 119, 15);
        
        JLabel lblCredits = new JLabel("Credits*");
        lblCredits.setFont(new Font("Arial", Font.BOLD, 12));
        lblCredits.setBounds(10, 70, 119, 15);
        
        tfCredits = new JTextField();
        tfCredits.setFont(new Font("Arial", Font.PLAIN, 13));
        tfCredits.setBounds(139, 64, 483, 28);
        tfCredits.setColumns(10);
        
        JLabel lblProfessor = new JLabel("Professor*");
		lblProfessor.setFont(new Font("Arial", Font.BOLD, 12));
		lblProfessor.setBounds(9, 22, 109, 15);
		add(lblProfessor);
		
		cbProfessor = new JComboBox<Professor>();
		cbProfessor.setFont(new Font("Arial", Font.PLAIN, 13));
		
		populateComboBox();
		
		cbProfessor.setBounds(128, 17, 500, 28);
		add(cbProfessor);
        
        JLabel lblDescription = new JLabel("Descri\u00E7\u00E3o*");
        lblDescription.setFont(new Font("Arial", Font.BOLD, 12));
        lblDescription.setBounds(10, 113, 119, 15);
        
        taDescription= new JTextArea();
        taDescription.setFont(new Font("Arial", Font.PLAIN, 13));
        taDescription.setBounds(139, 109, 483, 168);
        taDescription.setLineWrap(true);
        
        btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 12));
        btnSalvar.setBounds(400, 420, 110, 29);
        
        btnCancelar = new JButton("Voltar");
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 12));
        btnCancelar.setBounds(520, 420, 110, 29);
        setLayout(null);
        add(tfName);
        add(separator);
        add(panelDescription);
        add(panelButtons);
        add(lblName);
        add(lblCredits);
        add(tfCredits);
        add(lblDescription);
        add(taDescription);
        add(btnSalvar);
        add(btnCancelar);
        setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblName, tfName, lblCredits, separator, panelDescription, lblDescription, panelButtons, btnCancelar}));
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

	public JTextField getTfCredits() {
		return tfCredits;
	}


	public JTextArea getTaDescription() {
		return taDescription;
	}
	private void populateComboBox() {
		for(Professor c : professorList) {
			cbProfessor.addItem(c);
		}
	}
	public JComboBox<Professor> getCbProfessor() {
		return cbProfessor;
	}
}
