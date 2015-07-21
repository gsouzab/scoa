package ufrj.scoa.view.complaint_suggestion;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
public class ComplaintSuggestionCreationView extends JPanel {
    

	private static final long serialVersionUID = 1L;
    private JButton btnSave;
    private JButton btnBack;
	private JTextArea textArea;
	private JRadioButton suggestion;
	private JRadioButton complaint;
	private ButtonGroup bGroup;
	
    public ComplaintSuggestionCreationView() {
    	setLayout(null);
    	
    	JSeparator separator = new JSeparator();
        separator.setBounds(0, 397, 649, 12);
        add(separator);
    	
    	suggestion = new JRadioButton("Sugestão");
    	suggestion.setSelected(true);
    	suggestion.setBounds(6, 6, 141, 23);
    	add(suggestion);
    	
    	complaint = new JRadioButton("Reclamação");
    	complaint.setBounds(159, 6, 141, 23);
    	add(complaint);
    	
    	bGroup = new ButtonGroup();
    	
    	bGroup.add(suggestion);
    	bGroup.add(complaint);
    	
    	textArea = new JTextArea();
    	textArea.setBounds(6, 45, 628, 284);
    	add(textArea);
    	
    	btnSave = new JButton("Salvar");
    	btnSave.setFont(new Font("Arial", Font.BOLD, 12));
    	btnSave.setBounds(400, 420, 110, 29);
    	add(btnSave);
        
    	btnBack = new JButton("Voltar");
        btnBack.setFont(new Font("Arial", Font.BOLD, 12));
        btnBack.setBounds(520, 420, 110, 29);
        add(btnBack);
    }

    public ButtonGroup getBGroup() {
    	return bGroup;
    }
    
    public JButton getBtnSave() {
		return btnSave;
	}
    
	public JButton getBtnBack() {
		return btnBack;
	}
	
	public JTextArea getTextArea() {
		return textArea;
	}

	public JRadioButton getSuggestion() {
		return suggestion;
	}

	public JRadioButton getComplaint() {
		return complaint;
	}
}
