package ufrj.scoa.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ufrj.scoa.model.DAO.PersonDAO;
import ufrj.scoa.model.VO.Person;
import ufrj.scoa.view.LoginView;
import ufrj.scoa.view.WelcomeView;
import ufrj.scoa.view.NewPasswordView;

import ufrj.scoa.util.Util;

public class AccessController implements ActionListener {

	private LoginView loginView;
	private NewPasswordView passwordView;
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
		if(event.getSource() == this.passwordView.getBtnAccept()) {
			personDAO.setPassword(String.valueOf(this.passwordView.getPassword1Field().getPassword()), passwordView.getEntry());
			this.baseController.getBaseFrame().changePanel(this.loginView, "Login - SCOA", false);
		}		
	
	}
	
	private void login() {

		String entry = this.loginView.getUsernameField().getText();
		String password = String.valueOf(this.loginView.getPasswordField().getPassword());
		this.passwordView = new NewPasswordView(entry);
		this.passwordView.getBtnAccept().addActionListener(this);
		
		if(this.validadeLoginFields(entry, password)) {
			
			try 
			{	
				if(this.personDAO.validateLogin(entry,password)) {

					Person currentUser = personDAO.getCurrentUser(entry, password);
					if(this.checkPassword(entry))
					{
						this.baseController.getBaseFrame().changePanel(this.passwordView, "Redefinição de senha",false);						
					}
					else
					{
						this.baseController.setCurrentUser(currentUser);
						this.baseController.getBaseFrame().changePanel(new WelcomeView(), "Bem vindo ao SCOA");
					}
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
	
	private boolean checkPassword(String entry){
		String password =  Util.generateNewPassword(entry);
		
		String passwordBd = personDAO.getPassword(entry);
		
		Boolean passwd = false;
		
		passwd = password.equalsIgnoreCase(passwordBd);
		
		return passwd;
	}

	private boolean validadeLoginFields(String entry, String password) {
		return entry.length() > 0 && password.length() > 0;
	}

	public LoginView getLoginView() {
		return this.loginView;
	}
}
