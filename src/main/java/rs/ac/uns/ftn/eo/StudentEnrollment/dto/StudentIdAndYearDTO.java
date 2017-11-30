package rs.ac.uns.ftn.eo.StudentEnrollment.dto;

public class StudentIdAndYearDTO {
	
	private Long studentId;
	private int year;
	
	public StudentIdAndYearDTO() {
		super();
	}
	public StudentIdAndYearDTO(Long studentId, int year) {
		super();
		this.studentId = studentId;
		this.year = year;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}

}
