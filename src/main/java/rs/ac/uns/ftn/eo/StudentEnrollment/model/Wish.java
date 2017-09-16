package rs.ac.uns.ftn.eo.StudentEnrollment.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "wishes")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id",
		  scope = Wish.class)
public class Wish {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "total_points")
	private double totalPoints;
	
	@ManyToMany(mappedBy = "wishes")
	private List<StudentExam> studentExams;
	
	@ManyToOne
	private Student student;
	
	@ManyToOne
	private StudyProgram studyProgram;

	public Wish() {
		super();
	}

	public Wish(Long id,  double totalPoints, StudyProgram studyProgram, Student student) {
		super();
		this.id = id;
		this.totalPoints = totalPoints;
		this.studyProgram = studyProgram;
		this.student = student;
	}
	
	public Wish( double totalPoints, StudyProgram studyProgram, Student student) {
		super();
		this.totalPoints = totalPoints;
		this.studyProgram = studyProgram;
		this.student = student;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public double getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(double totalPoints) {
		this.totalPoints = totalPoints;
	}
	
	public List<StudentExam> getStudentExams() {
		return studentExams;
	}

	public void setStudentExams(List<StudentExam> studentExams) {
		this.studentExams = studentExams;
	}
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public StudyProgram getStudyProgram() {
		return studyProgram;
	}

	public void setStudyProgram(StudyProgram studyProgram) {
		this.studyProgram = studyProgram;
	}
	

}
