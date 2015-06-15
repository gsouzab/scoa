package ufrj.scoa.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;

import ufrj.scoa.model.DAO.SecretaryDAO;
import ufrj.scoa.model.VO.Secretary;
import ufrj.scoa.util.Util;
import ufrj.scoa.view.WelcomeView;
import ufrj.scoa.view.secretary.SecretaryCreationView;
import ufrj.scoa.view.student.StudentCreationView;



public class SecretaryController implements ActionListener{
	
	private ScoaBaseController baseController;
	private SecretaryCreationView secretaryCreationView;
	
	public SecretaryController(ScoaBaseController baseController) {

		this.baseController = baseController;
		
		this.secretaryCreationView = new SecretaryCreationView();
		this.secretaryCreationView.getBtnSalvar().addActionListener(this);
		this.secretaryCreationView.getBtnCancelar().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == this.secretaryCreationView.getBtnSalvar()) 
		{
			saveSecretary();
		} else if(event.getSource() == this.secretaryCreationView.getBtnCancelar()) {
			this.baseController.getBaseFrame().changePanel(new WelcomeView(), "Bem vindo ao SCOA");
			
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
	
}
