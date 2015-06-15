package ufrj.scoa.view.discipline;

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

public class DisciplineCreationView extends JPanel {
    

	private static final long serialVersionUID = 1L;
    private JTextField tfName;
    private JTextArea taDescription;

    private JButton btnSalvar;
    private JButton btnCancelar;
    private JTextField tfCode;
    
    /**
     * Create the frame.
     */
    
    public DisciplineCreationView() {

        tfName = new JTextField();
        tfName.setFont(new Font("Arial", Font.PLAIN, 13));
        tfName.setBounds(139, 10, 483, 28);
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
        lblName.setBounds(10, 17, 119, 15);
		        
        JLabel lblDescription = new JLabel("Descrição");
        lblDescription.setFont(new Font("Arial", Font.BOLD, 12));
        lblDescription.setBounds(10, 107, 119, 15);
        
        taDescription= new JTextArea();
        taDescription.setFont(new Font("Arial", Font.PLAIN, 13));
        taDescription.setBounds(139, 106, 483, 137);
        taDescription.setLineWrap(true);
        
        btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 12));
        btnSalvar.setBounds(400, 420, 110, 29);
        
        btnCancelar = new JButton("Voltar");
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 12));
        btnCancelar.setBounds(520, 420, 110, 29);
        
        tfCode = new JTextField();
        tfCode.setFont(new Font("Arial", Font.PLAIN, 13));
        tfCode.setColumns(10);
        tfCode.setBounds(139, 50, 141, 28);
        
        JLabel lblCodigo = new JLabel("Código*");
        lblCodigo.setFont(new Font("Arial", Font.BOLD, 12));
        lblCodigo.setBounds(10, 60, 119, 15);
        
        
        setLayout(null);
        add(tfName);
        add(separator);
        add(panelDescription);
        add(panelButtons);
        add(lblName);
        add(lblDescription);
        add(taDescription);
        add(btnSalvar);
        add(btnCancelar);
        add(lblCodigo);
        add(tfCode);
        
        setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblName, tfName, separator, panelDescription, lblDescription, panelButtons, btnCancelar}));
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

	public JTextArea getTaDescription() {
		return taDescription;
	}

	public JTextField getTfCode() {
		return tfCode;
	}
	
}
