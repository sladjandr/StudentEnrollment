package rs.ac.uns.ftn.eo.StudentEnrollment.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "exam_student")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id",
		  scope = ExamStudent.class)
public class ExamStudent {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "points")
	private double points;
	
	@ManyToOne
	private Student student;
	
	@ManyToOne
	private Exam exam;
	
	
	public ExamStudent() {
		super();
	}

	public ExamStudent(Long id, double points, Student student, Exam exam) {
		super();
		this.id = id;
		this.points = points;
		this.student = student;
		this.exam = exam;
	}

	public ExamStudent(double points, Student student, Exam exam) {
		super();
		this.points = points;
		this.student = student;
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

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

}
