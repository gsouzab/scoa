package ufrj.scoa.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DropMode;
import javax.swing.JScrollPane;

public class CourseCreationView extends JPanel {
    

	private static final long serialVersionUID = 1L;
    private JTextField tfName;
    private JTextField tfCode;
    private JTextArea taDescription;
    private JButton btnSalvar;
    private JButton btnCancelar;
    
    /**
     * Create the frame.
     */
    
    public CourseCreationView() {
        
        tfName = new JTextField();
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
        lblName.setBounds(10, 31, 110, 16);
        
        JLabel lblCode = new JLabel("C\u00F3digo");
        lblCode.setBounds(10, 70, 110, 16);
        
        tfCode = new JTextField();
        tfCode.setBounds(139, 64, 483, 28);
        tfCode.setColumns(10);
        
        JLabel lblDescription = new JLabel("Descri\u00E7\u00E3o");
        lblDescription.setBounds(10, 113, 110, 16);
        
        taDescription= new JTextArea();
        taDescription.setBounds(139, 109, 483, 168);
        taDescription.setLineWrap(true);
        
        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(389, 420, 112, 29);
        
        btnCancelar = new JButton("Voltar");
        btnCancelar.setBounds(510, 420, 112, 29);
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
        add(btnSalvar);
        add(btnCancelar);
        setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblName, tfName, lblCode, separator, panelDescription, lblDescription, panelButtons, btnCancelar}));
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


	public JTextArea getTaDescription() {
		return taDescription;
	}
}
