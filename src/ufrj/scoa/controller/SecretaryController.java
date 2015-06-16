package ufrj.scoa.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;

import ufrj.scoa.model.DAO.CourseDAO;
import ufrj.scoa.model.DAO.ProfessorDAO;
import ufrj.scoa.model.DAO.SecretaryDAO;
import ufrj.scoa.model.DAO.StudentDisciplineDAO;
import ufrj.scoa.model.VO.Course;
import ufrj.scoa.model.VO.Professor;
import ufrj.scoa.model.VO.Secretary;
import ufrj.scoa.util.Constants;
import ufrj.scoa.util.Util;
import ufrj.scoa.view.WelcomeView;
import ufrj.scoa.view.course.CourseListView;
import ufrj.scoa.view.course.CourseSearchView;
import ufrj.scoa.view.secretary.SecretaryCreationView;
import ufrj.scoa.view.secretary.SecretaryListView;
import ufrj.scoa.view.secretary.SecretarySearchView;
import ufrj.scoa.view.secretary.SecretaryStudentDisciplineManagmentView;



public class SecretaryController implements ActionListener{
	
	private ScoaBaseController baseController;
	private SecretaryCreationView secretaryCreationView;
	private SecretarySearchView secretarySearchView;
	private SecretaryListView secretaryListView;
	private SecretaryStudentDisciplineManagmentView secretaryStudentDisciplineManagmentView;
	
	public SecretaryController(ScoaBaseController baseController) {

		this.baseController = baseController;
		
		this.secretaryCreationView = new SecretaryCreationView();
		this.secretaryCreationView.getBtnSalvar().addActionListener(this);
		this.secretaryCreationView.getBtnCancelar().addActionListener(this);
		
		this.secretarySearchView = new SecretarySearchView();
		this.secretarySearchView.getBtnBuscar().addActionListener(this);
		this.secretarySearchView.getBtnVoltar().addActionListener(this);		
		
		this.secretaryListView = new SecretaryListView();
		this.secretaryListView.getBtnExcluir().addActionListener(this);
		
		this.secretaryStudentDisciplineManagmentView = new SecretaryStudentDisciplineManagmentView();
		this.secretaryStudentDisciplineManagmentView.getBtnDisapprove().addActionListener(this);
		this.secretaryStudentDisciplineManagmentView.getBtnApprove().addActionListener(this);
		this.secretaryStudentDisciplineManagmentView.getBtnBack().addActionListener(this);		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource() == this.secretaryCreationView.getBtnSalvar()) 
		{
			saveSecretary();
			
		} else if(event.getSource() == this.secretaryCreationView.getBtnCancelar() ||
				event.getSource() == this.secretarySearchView.getBtnVoltar() || 
				event.getSource() == this.secretaryStudentDisciplineManagmentView.getBtnBack()) {
			
			this.baseController.getBaseFrame().changePanel(new WelcomeView(), "Bem vindo ao SCOA");
			
		} else if(event.getSource() == this.secretarySearchView.getBtnBuscar()) {
			
			searchSecretary();
			this.baseController.getBaseFrame().changePanel(secretaryListView, "Resultado da busca por secretários(as)");
			
		} else if(event.getSource() == this.secretaryListView.getBtnExcluir()) {
			
			int option = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir o(a) secretário(a) selecionado(a)?", "Excluir secretário(a)", JOptionPane.YES_NO_OPTION);

			if(option == 0) {
				
			}
			else if (option == 1) {}
			
		} else if(event.getSource() == this.secretaryStudentDisciplineManagmentView.getBtnApprove()) {
			
			if(this.secretaryStudentDisciplineManagmentView.getTable().getSelectedRowCount() > 0) {
				int[] selectedRows = secretaryStudentDisciplineManagmentView.getTable().getSelectedRows();
				
				approveInscrition(selectedRows);
				this.secretaryStudentDisciplineManagmentView.populateTable();
				JOptionPane.showMessageDialog(null, "Pedidos aprovados com sucesso");

			} else {
				JOptionPane.showMessageDialog(null, "Selecione pelo menos um aluno");
			}
			
			this.secretaryStudentDisciplineManagmentView.populateTable();
			
		} else if(event.getSource() == this.secretaryStudentDisciplineManagmentView.getBtnDisapprove()) {
			
			if(this.secretaryStudentDisciplineManagmentView.getTable().getSelectedRowCount() > 0) {
				int[] selectedRows = secretaryStudentDisciplineManagmentView.getTable().getSelectedRows();
				
				disapproveInscrition(selectedRows);
				this.secretaryStudentDisciplineManagmentView.populateTable();
				JOptionPane.showMessageDialog(null, "Pedidos rejeitados com sucesso");

			} else {
				JOptionPane.showMessageDialog(null, "Selecione pelo menos um aluno");
			}
		}

	}
	
	private void approveInscrition(int[] selectedRows) {
		
		for(int selectedRowIndex : selectedRows) {
			
			int student_id = (int) this.secretaryStudentDisciplineManagmentView.getTable().getModel().getValueAt(selectedRowIndex, 0);
			int course_id = (int) this.secretaryStudentDisciplineManagmentView.getTable().getModel().getValueAt(selectedRowIndex, 1);
			
			StudentDisciplineDAO.changeState(student_id,course_id,Constants.STUDENT_CLASS_APPROVED);
		}
	}
	
	private void disapproveInscrition(int[] selectedRows) {
		for(int selectedRowIndex : selectedRows) {
			
			int student_id = (int) this.secretaryStudentDisciplineManagmentView.getTable().getModel().getValueAt(selectedRowIndex, 0);
			int course_id = (int) this.secretaryStudentDisciplineManagmentView.getTable().getModel().getValueAt(selectedRowIndex, 1);
			
			StudentDisciplineDAO.changeState(student_id,course_id,Constants.STUDENT_CLASS_DENIED);
		}
	}
	
	private void saveSecretary() {

		String name = this.secretaryCreationView.getTfName().getText();
		String email = this.secretaryCreationView.getTfEmail().getText();
		String cpf = this.secretaryCreationView.getTfCpf().getText();
		String birthdate = this.secretaryCreationView.getTfDate().getText();
		String entry = null;
		String password = null;
		

		if(this.validadeCreateFields(name, email, this.secretaryCreationView.getTfCpf(), this.secretaryCreationView.getTfDate())) {

			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

			Date date = null;

			try {

				date = formatter.parse(birthdate);

			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			password = Util.generateNewPassword(cpf);
			entry = Util.unmaskCPF(cpf);

			Secretary secretary = new Secretary(name, cpf, email, date, entry, password);
			SecretaryDAO secretaryDao = new SecretaryDAO();

			secretaryDao.saveSecretary(secretary);

			JOptionPane.showMessageDialog(null, "Secretária salva com sucesso");
			clearFieldsCreationView();
		} else {
			JOptionPane.showMessageDialog(null, "Preencha todos os campos marcados com (*)");
		}
	}
	
	private void clearFieldsCreationView() {
		this.secretaryCreationView.getTfName().setText("");;
		this.secretaryCreationView.getTfEmail().setText("");
		this.secretaryCreationView.getTfCpf().setText("");
		this.secretaryCreationView.getTfDate().setText("");
	}

	private boolean validadeCreateFields(String name, String email, JFormattedTextField cpf, JFormattedTextField birthdate) {
		return name.length() > 0 && email.length() > 0 &&  birthdate.getValue() != null && cpf.getValue() != null;
	}

	public SecretaryCreationView getSecretaryCreationView() {
		return secretaryCreationView;
	}
	
	
	
	public SecretarySearchView getSecretarySearchView() {
		return secretarySearchView;
	}

	public SecretaryListView getSecretaryListView() {
		return secretaryListView;
	}
	
	public SecretaryStudentDisciplineManagmentView getSecretaryStudentDisciplineManagmentView() {
		return secretaryStudentDisciplineManagmentView;
	}

	private void searchSecretary() {
		String name = this.secretarySearchView.getTfName().getText();
		String email = this.secretarySearchView.getTfEmail().getText();
		String cpf = this.secretarySearchView.getTfCpf().getText();
		String birthdate = this.secretarySearchView.getTfDate().getText();
		
		if(Util.unmaskDate(birthdate).length() > 0) {
			birthdate = Util.formatDateToSql(birthdate);
		} else {
			birthdate = Util.unmaskDate(birthdate);
		}
		
		
		if(Util.unmaskCPF(cpf).length() == 0) {
			cpf = "";
		}
		
		SecretaryDAO secretaryDAO = new SecretaryDAO();
		ArrayList<Secretary> secretaryList = secretaryDAO.search(name, email, cpf, birthdate);
		
		for(Secretary secretary: secretaryList) {
			secretaryListView.getModel().addElement(secretary);
		}
	}
	
	private void deleteSecretary(Course course) {

		
	}
	
}
