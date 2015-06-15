package ufrj.scoa.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ufrj.scoa.model.DAO.CourseDAO;
import ufrj.scoa.model.DAO.DisciplineDAO;
import ufrj.scoa.model.VO.Course;
import ufrj.scoa.model.VO.Discipline;
import ufrj.scoa.view.WelcomeView;
import ufrj.scoa.view.course.CourseListView;
import ufrj.scoa.view.discipline.DisciplineCreationView;
import ufrj.scoa.view.discipline.DisciplineListView;
import ufrj.scoa.view.discipline.DisciplineSearchView;

public class DisciplineController implements ActionListener {
	
	private DisciplineCreationView disciplineCreationView;
	private ScoaBaseController baseController;
	private DisciplineListView disciplineListView;
	private DisciplineSearchView disciplineSearchView;

	public DisciplineController(ScoaBaseController baseController) {
		this.baseController = baseController;
		
		this.disciplineCreationView = new DisciplineCreationView();
		this.disciplineCreationView.getBtnSalvar().addActionListener(this);
		this.disciplineCreationView.getBtnCancelar().addActionListener(this);
		
		this.disciplineSearchView = new DisciplineSearchView();
		this.disciplineSearchView.getBtnBuscar().addActionListener(this);
		this.disciplineSearchView.getBtnVoltar().addActionListener(this);	
		
		this.disciplineListView = new DisciplineListView();

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource() == this.disciplineCreationView.getBtnSalvar()) {
			saveDiscipline();
			
		} else if(event.getSource() == this.disciplineCreationView.getBtnCancelar()) {
			this.baseController.getBaseFrame().changePanel(new WelcomeView(), "Bem-vindo ao SCOA");
			
		} else if(event.getSource() == this.disciplineSearchView.getBtnBuscar()) {
			searchDisciplines();
			this.baseController.getBaseFrame().changePanel(disciplineListView, "Buscar Disciplinas");
			
		} else if(event.getSource() == this.disciplineSearchView.getBtnVoltar()) {
			this.baseController.getBaseFrame().changePanel(new WelcomeView(), "Bem vindo ao SCOA");
		}
	}
	
	private void saveDiscipline() {
		String name = this.disciplineCreationView.getTfName().getText();
		String description = this.disciplineCreationView.getTaDescription().getText();
		String code = this.disciplineCreationView.getTfCode().getText();
		
		if(this.validateCreateFields(name, code)) {
			
			Discipline newDiscipline = new Discipline(name, description, code);
			DisciplineDAO disciplineDAO = new DisciplineDAO();
			disciplineDAO.save(newDiscipline);
			
			JOptionPane.showMessageDialog(null, "Disciplina salva com sucesso");
			clearFieldsCreationView();
			
		} else {
			JOptionPane.showMessageDialog(null, "Preencha todos os campos marcados com (*)");
		}
	}
	
	public void listAllDisciplines() {
		DisciplineDAO disciplineDAO = new DisciplineDAO();
		ArrayList<Discipline> disciplines = disciplineDAO.list();
		
		for(Discipline discipline: disciplines) {
			this.disciplineListView.getModel().addElement(discipline);
			
		}

	}
	
	public void searchDisciplines() {
		String name = this.disciplineSearchView.getTfName().getText();
		String courseCode = this.disciplineSearchView.getTfCode().getText();
		String description = this.disciplineSearchView.getTaDescription().getText();
		
		DisciplineDAO disciplineDAO = new DisciplineDAO();
		ArrayList<Discipline> disciplineList = disciplineDAO.search(name, courseCode, description);
		
		for(Discipline discipline: disciplineList) {
			disciplineListView.getModel().addElement(discipline);
		}
	}
	
	private boolean validateCreateFields(String name, String code) {
		return name.length() > 0 && code.length() > 0;
	}
	
	private void clearFieldsCreationView() {
		this.disciplineCreationView.getTfName().setText("");;
		this.disciplineCreationView.getTfCode().setText("");
		this.disciplineCreationView.getTaDescription().setText("");
	}
	
	public DisciplineCreationView getDisciplineCreationView() {
		return disciplineCreationView;
	}

	public DisciplineListView getDisciplinesListView() {
		return disciplineListView;
	}

	public DisciplineSearchView getDisciplineSearchView() {
		return disciplineSearchView;
	}
	

}
