package ufrj.scoa.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.text.PasswordView;

import ufrj.scoa.model.VO.Person;
import ufrj.scoa.model.DAO.PersonDAO;
import ufrj.scoa.util.Constants;
import ufrj.scoa.util.Util;
import ufrj.scoa.view.AccountInfoView;
import ufrj.scoa.view.NewPasswordView;
import ufrj.scoa.view.WelcomeView;

public class AccountInfoController implements ActionListener {

	private AccountInfoView accountInfoView;
	private ScoaBaseController baseController;
	private NewPasswordView passwordView;
	private PersonDAO personDAO = new PersonDAO();	

	public AccountInfoController(ScoaBaseController baseController) {

		this.baseController = baseController;
		this.accountInfoView = new AccountInfoView();
		this.accountInfoView.getBtnVoltar().addActionListener(this);
		this.accountInfoView.getBtnTrocarSenha().addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == this.accountInfoView.getBtnVoltar()) {
			this.baseController.getBaseFrame().changePanel(new WelcomeView(), "Bem-vindo ao SCOA");
			
		} else if(event.getSource() == this.accountInfoView.getBtnTrocarSenha()) {
			this.baseController.getBaseFrame().changePanel(this.passwordView, "Redefinição de senha",false);
			
		} else if(event.getSource() == this.passwordView.getBtnAccept()){
			personDAO.setPassword(String.valueOf(this.passwordView.getPassword1Field().getPassword()), passwordView.getEntry());
			this.baseController.getBaseFrame().changePanel(new WelcomeView(), "Bem-vindo ao SCOA");
		}
		
	}

	public JPanel getAccountInfoView() {
		return this.accountInfoView;
	}
	
	public void setCurrentUserInfo() {
		Person currentUser = baseController.getCurrentUser();
		this.passwordView = new NewPasswordView(currentUser.getEntry());
		this.passwordView.getBtnAccept().addActionListener(this);
		
		accountInfoView.getFieldName().setText(currentUser.getName());
		accountInfoView.getFieldBirthdate().setText(Util.formatDateFromSql(currentUser.getBirthdate().toString()));
		accountInfoView.getFieldCPF().setText(currentUser.getCpf());
		accountInfoView.getFieldEmail().setText(currentUser.getEmail());
		accountInfoView.getFieldEntry().setText(currentUser.getEntry());
		
		switch (currentUser.getRole()) {
			case Constants.ROLE_ADMINISTRATOR:
				accountInfoView.getFieldRole().setText(Constants.STRING_ADMINISTRATOR);
				break;
			
			case Constants.ROLE_SECRETARY:
				accountInfoView.getFieldRole().setText(Constants.STRING_SECRETARY);
				break;
				
			case Constants.ROLE_PROFESSOR:
				accountInfoView.getFieldRole().setText(Constants.STRING_PROFESSOR);
				break;
				
			case Constants.ROLE_STUDENT:
				accountInfoView.getFieldRole().setText(Constants.STRING_STUDENT);
				break;
	
			default:
				accountInfoView.getFieldRole().setText("");
				break;
		}
		
		
	}
}
