package ufrj.scoa.model.VO;

import java.util.Date;

import ufrj.scoa.util.Constants;

public class Secretary extends Person{

	private int secretaryID;
	
	public int getProfessorID() {
		return secretaryID;
	}

	public void setSecretaryID(int secretaryID) {
		this.secretaryID = secretaryID;
	}

	public Secretary(String name, String cpf, String email, Date birthdate, String entry, String password) {
		super(name, cpf, email, birthdate, entry, password, Constants.ROLE_SECRETARY);
		
	}

	@Override
	public String toString() {
		return this.getName() + " - " + this.getCpf();
	}

}
