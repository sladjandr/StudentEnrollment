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
@Table(name = "student_exam")
public class ExamStudent {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "points")
	private double points;
	
	@Column(name = "year")
	private int year;

	@Column(name = "is_finished")
	private boolean isFinished;

	@ManyToOne
	private Student student;

	@ManyToMany
	private List<Wish> wishes;

	@JsonBackReference
	@ManyToOne
	private Exam exam;
	
	
	public ExamStudent() {
		super();
	}

	public ExamStudent(Long id, double points, int year, boolean isFinished, Student student, List<Wish> wishes, Exam exam) {
		super();
		this.id = id;
		this.points = points;
		this.year = year;
		this.isFinished = isFinished;
		this.student = student;
		this.wishes = wishes;
		this.exam = exam;
	}

	public ExamStudent(double points, int year, Student student, List<Wish> wishes, Exam exam) {
		super();
		this.points = points;
		this.year = year;
		this.student = student;
		this.wishes = wishes;
		this.exam = exam;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	public List<Wish> getWishes() {
		return wishes;
	}

	public void setWishes(List<Wish> wishes) {
		this.wishes = wishes;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

}
