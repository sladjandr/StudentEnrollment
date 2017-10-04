package rs.ac.uns.ftn.eo.StudentEnrollment.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "wish")
public class Wish {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "year")
	private int year;

	@Column(name = "total_points")
	private double totalPoints;
	
	@ManyToMany(mappedBy = "wishes")
	private List<ExamStudent> studentExams;
	
	@ManyToOne
	private Student student;
	
	@JsonBackReference(value = "wi-sp")
	@ManyToOne
	private StudyProgram studyProgram;

	public Wish() {
		super();
	}

	public Wish(Long id, int year,  double totalPoints, StudyProgram studyProgram, Student student) {
		super();
		this.id = id;
		this.year = year;
		this.totalPoints = totalPoints;
		this.studyProgram = studyProgram;
		this.student = student;
	}
	
	public Wish(int year, double totalPoints, StudyProgram studyProgram, Student student) {
		super();
		this.year = year;
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
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public double getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(double totalPoints) {
		this.totalPoints = totalPoints;
	}

	public List<ExamStudent> getStudentExams() {
		return studentExams;
	}

	public void setStudentExams(List<ExamStudent> studentExams) {
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
