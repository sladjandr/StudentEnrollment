package rs.ac.uns.ftn.eo.StudentEnrollment.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "entrance_exam")
public class EntranceExam {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name", unique = true)
	private String name;
	
	@OneToMany(mappedBy = "entrance_exam")
	private List<StudyProgram> studyPrograms;
	
	@OneToMany(mappedBy = "entrance_exam")
	private List<EntranceExamSubject> entranceExamSubjects;
	
	@OneToMany(mappedBy = "entrance_exam")
	private List<EntranceExamStudent> entanceExamStudents;

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

	public List<StudyProgram> getStudyPrograms() {
		return studyPrograms;
	}
	
	public void setStudyPrograms(List<StudyProgram> studyPrograms) {
		this.studyPrograms = studyPrograms;
	}

	public List<EntranceExamSubject> getEntranceExamSubjects() {
		return entranceExamSubjects;
	}

	public void setEntranceExamSubjects(List<EntranceExamSubject> entranceExamSubjects) {
		this.entranceExamSubjects = entranceExamSubjects;
	}

	public List<EntranceExamStudent> getEntanceExamStudents() {
		return entanceExamStudents;
	}

	public void setEntanceExamStudents(List<EntranceExamStudent> entanceExamStudents) {
		this.entanceExamStudents = entanceExamStudents;
	}
	
}


