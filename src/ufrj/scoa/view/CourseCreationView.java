package ufrj.scoa.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;

public class CourseCreationView extends JFrame {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tfName;
    private JTextField tfCode;
    private JTextArea taDescription;
    private JButton btnSalvar;
    
    /**
     * Create the frame.
     */
    
    public CourseCreationView() {
        setTitle("Cadastro de Cursos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 640, 480);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel panelName = new JPanel();
        panelName.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panelName.setBounds(6, 81, 628, 29);
        contentPane.add(panelName);
        panelName.setLayout(null);
        
        JLabel lblName = new JLabel("Nome:");
        lblName.setBounds(6, 6, 110, 16);
        panelName.add(lblName);
        
        tfName = new JTextField();
        tfName.setBounds(128, 0, 494, 28);
        panelName.add(tfName);
        tfName.setColumns(10);
        
        JPanel panelCode = new JPanel();
        panelCode.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panelCode.setLayout(null);
        panelCode.setBounds(6, 49, 628, 29);
        contentPane.add(panelCode);
        
        JLabel lblCode = new JLabel("Código");
        lblCode.setBounds(6, 6, 110, 16);
        panelCode.add(lblCode);
        
        tfCode = new JTextField();
        tfCode.setColumns(10);
        tfCode.setBounds(128, 0, 110, 28);
        panelCode.add(tfCode);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(6, 387, 628, 6);
        contentPane.add(separator);
        
        JPanel panelDescription = new JPanel();
        panelDescription.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panelDescription.setLayout(null);
        panelDescription.setBounds(6, 113, 628, 191);
        contentPane.add(panelDescription);
        
        JLabel lblDescription = new JLabel("Descrição:");
        lblDescription.setBounds(6, 6, 110, 16);
        panelDescription.add(lblDescription);
        
        taDescription= new JTextArea();
        taDescription.setLineWrap(true);
        taDescription.setBounds(128, 6, 494, 179);
        panelDescription.add(taDescription);
        
        JPanel panelButtons = new JPanel();
        panelButtons.setBounds(6, 404, 628, 48);
        contentPane.add(panelButtons);
        panelButtons.setLayout(null);
        
        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(376, 6, 117, 29);
        panelButtons.add(btnSalvar);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(505, 6, 117, 29);
        panelButtons.add(btnCancelar);
    }

	public JButton getBtnSalvar() {
		return btnSalvar;
	}

	public void setBtnSalvar(JButton btnSalvar) {
		this.btnSalvar = btnSalvar;
	}

	public JTextField getTfName() {
		return tfName;
	}

	public void setTfName(JTextField tfName) {
		this.tfName = tfName;
	}

	public JTextField getTfCode() {
		return tfCode;
	}

	public void setTfCode(JTextField tfCode) {
		this.tfCode = tfCode;
	}

	public JTextArea getTaDescription() {
		return taDescription;
	}

	public void setTaDescription(JTextArea taDescription) {
		this.taDescription = taDescription;
	}
	
	
    
    
}
