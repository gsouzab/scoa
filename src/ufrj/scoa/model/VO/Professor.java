package ufrj.scoa.model.VO;

import java.util.Date;

public class Professor extends Person{

	private int professorID;
	
	public int getProfessorID() {
		return professorID;
	}

	public void setProfessorID(int professorID) {
		this.professorID = professorID;
	}

	public Professor(String name, String cpf, String email, Date birthdate) {
		super(name, cpf, email, birthdate, 0, "");
		
	}

}
