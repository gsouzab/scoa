package ufrj.scoa.model.VO;

import ufrj.scoa.util.Constants;

public class StudentDiscipline {
		
	private int studentId;
	private int classId;
	private float grade;
	private int state;
	private int attendance;
	private Student student;
	private Class student_class;
	private int period;
	
	public StudentDiscipline() {
		
	}
	
	public StudentDiscipline(Student student, Class studentClass){
		this.studentId = student.getStudentId();
		this.classId = studentClass.getId();
		this.student = student;
		this.student_class = studentClass;		
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}

	public int getAttendance() {
		return attendance;
	}

	public void setAttendance(int attendance) {
		this.attendance = attendance;
	}

	public Student getStudent() {
		return student;
	}

	public Class getStudentClass() {
		return student_class;
	}
	

	public void setStudentClass(Class student_class) {
		this.student_class = student_class;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateString() {
		return Constants.STUDENT_CLASS_STRING[state];
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}
	
	
}
