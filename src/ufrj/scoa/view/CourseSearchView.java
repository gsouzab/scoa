package ufrj.scoa.view;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

public class CourseSearchView extends JPanel {
    

	private static final long serialVersionUID = 1L;
    private JTextField tfName;
    private JTextField tfCode;
    private JTextArea taDescription;
    private JButton btnBuscar;
    private JButton btnVoltar;
    
    /**
     * Create the frame.
     */
    
    public CourseSearchView() {
        
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
        
        JLabel lblName = new JLabel("Nome");
        lblName.setFont(new Font("Arial", Font.BOLD, 12));
        lblName.setBounds(10, 31, 119, 15);
        
        JLabel lblCode = new JLabel("Código");
        lblCode.setFont(new Font("Arial", Font.BOLD, 12));
        lblCode.setBounds(10, 70, 119, 15);
        
        tfCode = new JTextField();
        tfCode.setFont(new Font("Arial", Font.PLAIN, 13));
        tfCode.setBounds(139, 64, 119, 28);
        tfCode.setColumns(10);
        
        JLabel lblDescription = new JLabel("Descrição");
        lblDescription.setFont(new Font("Arial", Font.BOLD, 12));
        lblDescription.setBounds(10, 113, 119, 15);
        
        taDescription= new JTextArea();
        taDescription.setFont(new Font("Arial", Font.PLAIN, 13));
        taDescription.setBounds(139, 109, 483, 132);
        taDescription.setLineWrap(true);
        
        btnBuscar = new JButton("Buscar");
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 12));
        btnBuscar.setBounds(400, 420, 110, 29);
        
        btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 12));
        btnVoltar.setBounds(520, 420, 110, 29);
        
        setLayout(null);
        add(tfName);
        add(separator);
        add(panelDescription);
        add(panelButtons);
        add(lblName);
        add(lblCode);
        add(tfCode);
        add(lblDescription);
        add(taDescription);
        add(btnBuscar);
        add(btnVoltar);	
        setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblName, tfName, lblCode, separator, panelDescription, lblDescription, panelButtons, btnVoltar}));
   	}



	public JButton getBtnBuscar() {
		return btnBuscar;
	}



	public JButton getBtnVoltar() {
		return btnVoltar;
	}


	public JTextField getTfName() {
		return tfName;
	}

	public JTextField getTfCode() {
		return tfCode;
	}


	public JTextArea getTaDescription() {
		return taDescription;
	}
}
