package ufrj.scoa.model.VO;

import java.util.Date;

public class Person {
	
	private String name;
	private String cpf;
	private String email;
	private String password;
	private Date birthdate;
	private int entry;
	
	public Person(String name, String cpf, String email, Date birthdate) {
		super();
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.birthdate = birthdate;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getBirthdate() {
		return birthdate;
	}
	
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public int getEntry() {
		return entry;
	}

	public void setEntry(int entry) {
		this.entry = entry;
	}
	
	
	
	

}
