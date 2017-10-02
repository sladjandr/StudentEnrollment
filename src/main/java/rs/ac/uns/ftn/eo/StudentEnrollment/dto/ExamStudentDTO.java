package rs.ac.uns.ftn.eo.StudentEnrollment.dto;

import java.util.Date;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.Exam;

public class ExamStudentDTO {

	private String location;
	private Date date;
	private Exam exam;
	
	
	public ExamStudentDTO() {
		super();
	}
	public ExamStudentDTO(String location, Date date, Exam exam) {
		super();
		this.location = location;
		this.date = date;
		this.exam = exam;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Exam getExam() {
		return exam;
	}
	public void setExam(Exam exam) {
		this.exam = exam;
	}

}
