package ufrj.scoa.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ufrj.scoa.model.DAO.PersonDAO;
import ufrj.scoa.view.LoginView;
import ufrj.scoa.view.WelcomeView;

public class AccessController implements ActionListener {

	private LoginView loginView;
	private ScoaBaseController baseController;
	private PersonDAO personDAO = new PersonDAO();

	public AccessController(ScoaBaseController baseController) {

		this.baseController = baseController;
		this.loginView = new LoginView();
		this.loginView.getBtnLogin().addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == this.loginView.getBtnLogin()) {
			login();
		}
	}

	private void login() {

		String entry = this.loginView.getUsernameField().getText();
		String password = String.valueOf(this.loginView.getPasswordField().getPassword());

		if(this.validadeLoginFields(entry, password)) {
			int entryInt = 0;
			
			try 
			{
				entryInt = Integer.parseInt(entry);
				
				if(this.personDAO.validateLogin(entryInt,password)) {

					this.baseController.getBaseFrame().changePanel(new WelcomeView(), "Bem vindo ao SCOA");
				} else {
					
					JOptionPane.showMessageDialog(null, "Login inválido.");
				}
				
			} catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Dados incorretos.");
			}
			
			
		} else {
			JOptionPane.showMessageDialog(null, "Preencha corretamente os campos.");
		}
	}

	private boolean validadeLoginFields(String entry, String password) {
		return entry.length() > 0 && password.length() > 0;
	}

	public LoginView getLoginView() {
		return this.loginView;
	}
}
