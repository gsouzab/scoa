package ufrj.scoa.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ufrj.scoa.model.DAO.ComplaintSuggestionDAO;
import ufrj.scoa.model.DAO.StudentDAO;
import ufrj.scoa.model.VO.ComplaintSuggestion;
import ufrj.scoa.util.Constants;
import ufrj.scoa.view.WelcomeView;
import ufrj.scoa.view.complaint_suggestion.ComplaintSuggestionCreationView;
import ufrj.scoa.view.complaint_suggestion.ComplaintSuggestionListView;

public class ComplaintSuggestionController implements ActionListener {
	
	private ScoaBaseController baseController;
	private ComplaintSuggestionCreationView creationView;
	private ComplaintSuggestionListView listView;
	
	public ComplaintSuggestionController(ScoaBaseController baseController) {
		this.baseController = baseController;
		creationView = new ComplaintSuggestionCreationView();
		
		creationView.getBtnBack().addActionListener(this);
		creationView.getBtnSave().addActionListener(this);		
		
		listView = new ComplaintSuggestionListView();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if( event.getSource() == creationView.getBtnBack()) {
			this.baseController.getBaseFrame().changePanel(new WelcomeView(), "Bem vindo ao SCOA");
		} else if(event.getSource() == creationView.getBtnSave()) {
			if (creationView.getBGroup().isSelected(creationView.getComplaint().getModel())) {
				saveComplaint();
			} else {
				saveSuggestion();
			}
		}
		
	}
	
	private void saveComplaint() {
		_save(Constants.COMPLAINT);
	}
	
	private void saveSuggestion() {
		_save(Constants.SUGGESTION);
	}
	
	private void _save(int complaint_or_suggestion) {
		
		if(creationView.getTextArea().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o texto da " + Constants.COMPLAINT_SUGGESTION_STRING[complaint_or_suggestion]);			
		} else {
			
			StudentDAO dao = new StudentDAO();
			
			ComplaintSuggestion cs = new ComplaintSuggestion(complaint_or_suggestion, creationView.getTextArea().getText(),dao.getStudentByPersonId(baseController.getCurrentUser().getPersonId()));
			ComplaintSuggestionDAO.saveComplaintSuggestion(cs);
			
			JOptionPane.showMessageDialog(null, Constants.COMPLAINT_SUGGESTION_STRING[complaint_or_suggestion] + " salva com sucesso");
			creationView.getTextArea().setText("");
		}
	
	}

	public ComplaintSuggestionCreationView getCreationView() {
		return creationView;
	}
	
	public ComplaintSuggestionListView getListView() {
		return listView;
	}

}
