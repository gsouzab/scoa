package ufrj.scoa.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;

import ufrj.scoa.model.DAO.ProfessorDAO;

import ufrj.scoa.model.VO.Professor;
import ufrj.scoa.view.ProfessorCreationView;
import ufrj.scoa.view.ProfessorListView;

import ufrj.scoa.view.WelcomeView;

public class ProfessorController implements ActionListener {

	private ProfessorCreationView professorCreationView;
	private ProfessorListView professorListView;
	private ScoaBaseController baseController;
	
	public ProfessorController(ScoaBaseController baseController) {

		this.baseController = baseController;
		this.professorCreationView = new ProfessorCreationView();
		this.professorCreationView.getBtnSalvar().addActionListener(this);
		this.professorCreationView.getBtnCancelar().addActionListener(this);
		
		this.professorListView = new ProfessorListView();

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == this.professorCreationView.getBtnSalvar()) {
			saveProfessor();
		} else if(event.getSource() == this.professorCreationView.getBtnCancelar()) {
			this.baseController.getBaseFrame().changePanel(new WelcomeView(), "Bem vindo ao SCOA");
		}

	}

	private void saveProfessor() {

		String name = this.professorCreationView.getTfName().getText();
		String email = this.professorCreationView.getTfEmail().getText();
		String cpf = this.professorCreationView.getTfCpf().getText();
		String birthdate = this.professorCreationView.getTfDate().getText();
		

		if(this.validadeCreateFields(name, email, this.professorCreationView.getTfCpf(), this.professorCreationView.getTfDate())) {

			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

			Date date = null;

			try {

				date = formatter.parse(birthdate);

			} catch (ParseException e) {
				e.printStackTrace();
			}

			Professor professor = new Professor(name, cpf, email, date);
			ProfessorDAO professorDao = new ProfessorDAO();

			professorDao.save(professor);

			JOptionPane.showMessageDialog(null, "Professor salvo com sucesso");
			clearFieldsCreationView();
		} else {
			JOptionPane.showMessageDialog(null, "Preencha todos os campos marcados com (*)");
		}
	}
	
	public void listProfessors() {
		ProfessorDAO professorDAO = new ProfessorDAO();
		ArrayList<Professor> professors = professorDAO.list();
		
		for(Professor professor: professors) {
			Object[] row = {professor.getName(), professor.getCpf(), professor.getEmail() };
			this.professorListView.getProfessorModel().addRow(row);
			
		}
		
		professorListView.resizeColumnWidth(professorListView.getTable());

	}

	private boolean validadeCreateFields(String name, String email, JFormattedTextField cpf, JFormattedTextField birthdate) {
		return name.length() > 0 && email.length() > 0 &&  birthdate.getValue() != null && cpf.getValue() != null;
	}

	private void clearFieldsCreationView() {
		this.professorCreationView.getTfName().setText("");;
		this.professorCreationView.getTfEmail().setText("");
		this.professorCreationView.getTfCpf().setText("");
		this.professorCreationView.getTfDate().setText("");
	}


	public ProfessorCreationView getProfessorCreationView() {
		return professorCreationView;
	}
	
	public ProfessorListView getProfessorListView() {
		return professorListView;
	}


}