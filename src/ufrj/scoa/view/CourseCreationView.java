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
        setLayout(null);
        
        tfName = new JTextField();
        tfName.setBounds(139, 6, 494, 28);
        add(tfName);
        tfName.setColumns(10);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(6, 392, 627, 12);
        this.add(separator);
        
        JPanel panelDescription = new JPanel();
        panelDescription.setBounds(230, 10, 1, 1);
        panelDescription.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panelDescription.setLayout(null);
        this.add(panelDescription);
        
        JPanel panelButtons = new JPanel();
        panelButtons.setBounds(236, 10, 1, 1);
        this.add(panelButtons);
        panelButtons.setLayout(null);
        
        JLabel lblName = new JLabel("Nome*");
        lblName.setBounds(6, 10, 110, 16);
        add(lblName);
        
        JLabel lblCode = new JLabel("Código*");
        lblCode.setBounds(6, 39, 110, 16);
        add(lblCode);
        
        tfCode = new JTextField();
        tfCode.setBounds(139, 33, 110, 28);
        add(tfCode);
        tfCode.setColumns(10);
        
        JLabel lblDescription = new JLabel("Descrição*");
        lblDescription.setBounds(6, 67, 110, 16);
        add(lblDescription);
        
        taDescription= new JTextArea();
        taDescription.setBounds(139, 64, 494, 179);
        add(taDescription);
        taDescription.setLineWrap(true);
        
        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(387, 405, 117, 29);
        add(btnSalvar);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(516, 405, 117, 29);
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
