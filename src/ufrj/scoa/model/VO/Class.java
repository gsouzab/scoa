package ufrj.scoa.model.VO;

/*
 * Define uma turma no sistema SCOA
 * 
 * */


public class Class {

	private int id;
	private int credits;
	private String name;
	private String code;
	private Course course;
	private Discipline discipline;
	private String timeOfClass;
	private Room room;
	
	public Class() {
		super();
	}
	
	public Class(int credits, String name, String code, String timeIfClass, Course course, Discipline discipline, Room room) {
		super();
		this.credits = credits;
		this.name = name;
		this.code = code;
		this.timeOfClass = timeIfClass;
		this.course = course;
		this.discipline = discipline;
		this.room = room;
	}
	
	public Class(int id, int credits, String name, String code,String timeIfClass, Course course, Discipline discipline, Room room) {
		super();
		this.id = id;
		this.credits = credits;
		this.name = name;
		this.code = code;
		this.timeOfClass = timeIfClass;
		this.course = course;
		this.discipline = discipline;
		this.room = room;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	
	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
	public String getTimeOfClass() {
		return timeOfClass;
	}

	public void setTimeOfClass(String timeOfClass) {
		this.timeOfClass = timeOfClass;
	}

	@Override
    public String toString() {
        return code + " - " + name;
    }
}
