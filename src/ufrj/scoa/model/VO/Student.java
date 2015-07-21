package ufrj.scoa.model.VO;

import java.util.Date;

import ufrj.scoa.util.Constants;

public class Student extends Person {
	
	private Course course;
	private float CR;
	private int studentId;

	public Student(String name,String cpf,String email,Date birthdate, Course course, String entry, String password) {
		super(name, cpf, email, birthdate, entry, password, Constants.ROLE_STUDENT);
		this.course = course;
		this.setCR(0);
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public float getCR() {
		return CR;
	}

	public void setCR(float cR) {
		CR = cR;
	}
	
	public String toString() {
		return this.getEntry() + " - " + this.getName() + " - " +this.getCourse();
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	
}
