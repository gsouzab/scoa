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
	
	public Professor(int professorId, String name, String cpf, String email, Date birthdate, String entry, String password) {
		super(name, cpf, email, birthdate, entry, password, Constants.ROLE_PROFESSOR);
		this.professorID = professorId;
	}

	@Override
	public String toString() {
		if(this.getEntry().length() > 0) return this.getEntry() + " - " + this.getName();
		
		return this.getName();
	}
	
	

}
