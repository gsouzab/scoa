package ufrj.scoa.model.VO;

public class Class {

	private int id;
	private String name;
	private String code;
	private Course course;
	private boolean isActive;
	
	public Class() {
		super();
	}
	
	public Class(String name, String code, Course course, boolean isActive) {
		super();
		this.name = name;
		this.code = code;
		this.course = course;
		this.isActive = isActive;
	}
	
	public Class(int id,String name, String code, Course course, boolean isActive) {
		super();
		this.name = name;
		this.code = code;
		this.course = course;
		this.isActive = isActive;
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

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	@Override
    public String toString() {
        return code + " - " + name;
    }
}
