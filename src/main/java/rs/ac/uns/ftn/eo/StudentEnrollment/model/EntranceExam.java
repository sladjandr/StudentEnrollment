package rs.ac.uns.ftn.eo.StudentEnrollment.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name = "entrance_exam")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id",
		  scope = EntranceExam.class)
public class EntranceExam {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name", unique = true)
	private String name;
	
	@OneToMany(mappedBy = "entranceExam")
	private List<StudyProgram> studyPrograms;
	
	@OneToMany(mappedBy = "entranceExam")
	private List<EntranceExamSubject> entranceExamSubjects;
	
	@OneToMany(mappedBy = "entranceExam")
	private List<EntranceExamStudent> entanceExamStudents;
	
	
	public EntranceExam() {
		super();
	}

	public EntranceExam(Long id, String name, List<StudyProgram> studyPrograms,
			List<EntranceExamSubject> entranceExamSubjects, List<EntranceExamStudent> entanceExamStudents) {
		super();
		this.id = id;
		this.name = name;
		this.studyPrograms = studyPrograms;
		this.entranceExamSubjects = entranceExamSubjects;
		this.entanceExamStudents = entanceExamStudents;
	}
	
	public EntranceExam(Long id, String name, List<EntranceExamSubject> entranceExamSubjects) {
		super();
		this.id = id;
		this.name = name;
		this.entranceExamSubjects = entranceExamSubjects;
	}

	public EntranceExam(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public EntranceExam(String name) {
		super();
		this.name = name;
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


