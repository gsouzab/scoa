package ufrj.scoa.model.VO;

import java.util.Date;

import ufrj.scoa.util.Constants;

public class Professor extends Person{

	private int professorID;
	
	public int getProfessorID() {
		return professorID;
	}

	public void setProfessorID(int professorID) {
		this.professorID = professorID;
	}

	public Professor(String name, String cpf, String email, Date birthdate, String entry, String password) {
		super(name, cpf, email, birthdate, entry, password, Constants.ROLE_PROFESSOR);
		
	}

	@Override
	public String toString() {
		return this.getName() + " - " + this.getCpf();
	}
	
	

}
