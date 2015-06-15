package ufrj.scoa.model.VO;

public class StudentDiscipline {
		
	private int studentId;
	private int classId;
	private float grade;
	private int attendance;
	
	public StudentDiscipline(Student student, Class classId){
		this.studentId = student.getStudentId();
		this.classId = classId.getId();
		
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
	
}
