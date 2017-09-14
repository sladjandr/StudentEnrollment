package rs.ac.uns.ftn.eo.StudentEnrollment.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "entrance_exam_student")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id",
		  scope = EntranceExamStudent.class)
public class EntranceExamStudent {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "total_points")
	private double totalPoints;
	
	@OneToMany(mappedBy = "entranceExamStudent")
	private List<OneExamStudent> exams;
	
	@ManyToOne
	private Student student;
	
	public EntranceExamStudent() {
		super();
	}

	public EntranceExamStudent(Long id, double totalPoints, List<OneExamStudent> exams, Student student) {
		super();
		this.id = id;
		this.totalPoints = totalPoints;
		this.exams = exams;
		this.student = student;
	}

	public EntranceExamStudent(double totalPoints, List<OneExamStudent> exams, Student student) {
		super();
		this.totalPoints = totalPoints;
		this.exams = exams;
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
	
	public List<OneExamStudent> getExams() {
		return exams;
	}

	public void setExams(List<OneExamStudent> exams) {
		this.exams = exams;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
