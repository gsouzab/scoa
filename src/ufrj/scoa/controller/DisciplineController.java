package ufrj.scoa.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ufrj.scoa.model.DAO.DisciplineDAO;
import ufrj.scoa.model.DAO.ProfessorDAO;
import ufrj.scoa.model.VO.Discipline;
import ufrj.scoa.view.DisciplineCreationView;
import ufrj.scoa.view.DisciplineListView;
import ufrj.scoa.view.WelcomeView;

public class DisciplineController implements ActionListener {
	
	private DisciplineCreationView disciplineCreationView;
	private ScoaBaseController baseController;
	private DisciplineListView disciplineListView;
	private ProfessorDAO professorDAO = new ProfessorDAO();

	public DisciplineController(ScoaBaseController baseController) {
		this.baseController = baseController;
		this.disciplineCreationView = new DisciplineCreationView(professorDAO.list());
		this.disciplineListView = new DisciplineListView();
		this.disciplineCreationView.getBtnSalvar().addActionListener(this);
		this.disciplineCreationView.getBtnCancelar().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource() == this.disciplineCreationView.getBtnSalvar()) {
			saveDiscipline();
			
		} else if(event.getSource() == this.disciplineCreationView.getBtnCancelar()) {
			this.baseController.getBaseFrame().changePanel(new WelcomeView(), "Bem-vindo ao SCOA");
			
		}
	}
	
	private void saveDiscipline() {
		String name = this.disciplineCreationView.getTfName().getText();
		String credits = this.disciplineCreationView.getTfCredits().getText();
		String description = this.disciplineCreationView.getTaDescription().getText();
		
		if(this.validateCreateFields(name, credits, description)) {
			
			int creditsInt = Integer.parseInt(this.disciplineCreationView.getTfCredits().getText());
			Discipline newDiscipline = new Discipline(this.disciplineCreationView.getTfName().getText(), creditsInt, this.disciplineCreationView.getTaDescription().getText());
			DisciplineDAO disciplineDAO = new DisciplineDAO();
			disciplineDAO.save(newDiscipline);
			
			JOptionPane.showMessageDialog(null, "Disciplina salva com sucesso");
			clearFieldsCreationView();
			
		} else {
			JOptionPane.showMessageDialog(null, "Preencha todos os campos marcados com (*)");
		}
	}
	
	public void listDisciplines() {
		DisciplineDAO disciplineDAO = new DisciplineDAO();
		ArrayList<Discipline> disciplines = disciplineDAO.list();
		
		for(Discipline discipline: disciplines) {
			this.disciplineListView.getModel().addElement(discipline);
			
		}

	}
	
	private boolean validateCreateFields(String name, String code, String description) {
		return name.length() > 0 && code.length() > 0 && description.length() > 0;
	}
	
	private void clearFieldsCreationView() {
		this.disciplineCreationView.getTfName().setText("");;
		this.disciplineCreationView.getTfCredits().setText("");
		this.disciplineCreationView.getTaDescription().setText("");
	}
	
	public DisciplineCreationView getDisciplineCreationView() {
		return disciplineCreationView;
	}

	public DisciplineListView getDisciplinesListView() {
		return disciplineListView;
	}
	
	

}
