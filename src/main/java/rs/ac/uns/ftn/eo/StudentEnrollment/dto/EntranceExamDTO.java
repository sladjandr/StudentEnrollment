package rs.ac.uns.ftn.eo.StudentEnrollment.dto;

import java.util.List;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.Subject;

public class EntranceExamDTO {
	
	private Long id;
	
	private String name;
	
	private List<Subject> subjects;


	public EntranceExamDTO() {
		super();
	}

	public EntranceExamDTO(Long id, String name, List<Subject> subjects) {
		super();
		this.id = id;
		this.name = name;
		this.subjects = subjects;
	}
	
	public EntranceExamDTO(String name, List<Subject> subjects) {
		super();
		this.name = name;
		this.subjects = subjects;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

}
