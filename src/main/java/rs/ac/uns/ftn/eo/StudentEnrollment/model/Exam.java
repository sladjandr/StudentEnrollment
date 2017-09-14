package rs.ac.uns.ftn.eo.StudentEnrollment.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "exam")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id",
		  scope = Exam.class)
public class Exam {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "subject_name", unique=true, nullable=false)
	private String subjectName;

	@OneToMany(mappedBy = "exam")
	private List<OneExamStudent> studentExams;
	
	@ManyToMany
	private List<StudyProgram> studyPrograms;

	
	public Exam() {
		super();
	}

	public Exam(Long id, String subjectName, List<OneExamStudent> studentExams, List<StudyProgram> studyPrograms) {
		super();
		this.id = id;
		this.subjectName = subjectName;
		this.studentExams = studentExams;
		this.studyPrograms = studyPrograms;
	}

	public Exam(String subjectName) {
		super();
		this.subjectName = subjectName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getSubjectName() {
		return subjectName;
	}


	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}


	public List<OneExamStudent> getStudentExams() {
		return studentExams;
	}


	public void setStudentExams(List<OneExamStudent> studentExams) {
		this.studentExams = studentExams;
	}


	public List<StudyProgram> getStudyPrograms() {
		return studyPrograms;
	}

	public void setStudyPrograms(List<StudyProgram> studyPrograms) {
		this.studyPrograms = studyPrograms;
	}

	
}
