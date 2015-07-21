package ufrj.scoa.model.VO;

/*
 * Define uma reclamação/sugestão no sistema SCOA
 * 
 * */


public class ComplaintSuggestion {

	private int id;
	private int complaint_or_suggestion;
	private String text;
	private Student student;
	
	public ComplaintSuggestion() {
		super();
	}
	
	public ComplaintSuggestion(int id, int complaint_or_suggestion, String text, Student student) {
		super();
		this.id = id;
		this.complaint_or_suggestion = complaint_or_suggestion;
		this.text = text;
		this.student = student;
	}
	
	public ComplaintSuggestion(int complaint_or_suggestion, String text, Student student) {
		super();
		this.complaint_or_suggestion = complaint_or_suggestion;
		this.text = text;
		this.student = student;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getComplaint_or_suggestion() {
		return complaint_or_suggestion;
	}

	public void setComplaint_or_suggestion(int complaint_or_suggestion) {
		this.complaint_or_suggestion = complaint_or_suggestion;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
