package ufrj.scoa.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import ufrj.scoa.model.VO.Person;
import ufrj.scoa.util.Constants;
import ufrj.scoa.util.Util;
import ufrj.scoa.view.AccountInfoView;
import ufrj.scoa.view.WelcomeView;

public class AccountInfoController implements ActionListener {

	private AccountInfoView accountInfoView;
	private ScoaBaseController baseController;

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
			
			
		}
		
	}

	public JPanel getAccountInfoView() {
		return this.accountInfoView;
	}
	
	public void setCurrentUserInfo() {
		Person currentUser = baseController.getCurrentUser();
		
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
