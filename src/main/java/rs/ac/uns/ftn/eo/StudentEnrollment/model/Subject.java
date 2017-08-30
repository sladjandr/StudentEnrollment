package rs.ac.uns.ftn.eo.StudentEnrollment.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "subject")
public class Subject {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "subject")
	private List<EntranceExamSubject> entranceExamSubjects;
	
	//TO DO
	//Make Constuctors
	
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

	public List<EntranceExamSubject> getEntranceExamSubjects() {
		return entranceExamSubjects;
	}

	public void setEntranceExamSubjects(List<EntranceExamSubject> entranceExamSubjects) {
		this.entranceExamSubjects = entranceExamSubjects;
	}

}