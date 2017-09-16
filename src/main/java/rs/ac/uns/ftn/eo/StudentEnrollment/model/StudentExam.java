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
@Table(name = "student_exam")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id",
		  scope = StudentExam.class)
public class StudentExam {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "points")
	private double points;

	@ManyToOne
	private Student student;

	@ManyToMany
	private List<Wish> wishes;

	@ManyToOne
	private Exam exam;
	
	
	public StudentExam() {
		super();
	}

	public StudentExam(Long id, double points, Student student, List<Wish> wishes, Exam exam) {
		super();
		this.id = id;
		this.points = points;
		this.student = student;
		this.wishes = wishes;
		this.exam = exam;
	}

	public StudentExam(double points, Student student, List<Wish> wishes, Exam exam) {
		super();
		this.points = points;
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
