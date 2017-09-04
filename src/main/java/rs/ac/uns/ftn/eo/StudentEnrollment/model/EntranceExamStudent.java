package rs.ac.uns.ftn.eo.StudentEnrollment.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "entrance_exam_student")
public class EntranceExamStudent {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "points")
	private double points;
	
	@Column(name = "total_points")
	private double totalPoints;
	
	@ManyToOne
	private EntranceExam entranceExam;
	
	@ManyToOne
	private Student student;
	
	public EntranceExamStudent() {
		super();
	}

	public EntranceExamStudent(Long id, double points, double totalPoints, EntranceExam entranceExam, Student student) {
		super();
		this.id = id;
		this.points = points;
		this.totalPoints = totalPoints;
		this.entranceExam = entranceExam;
		this.student = student;
	}

	public EntranceExamStudent(Long id, EntranceExam entranceExam, Student student) {
		super();
		this.id = id;
		this.entranceExam = entranceExam;
		this.student = student;
	}
	
	public EntranceExamStudent(Long id, double points, double totalPoints) {
		super();
		this.id = id;
		this.points = points;
		this.totalPoints = totalPoints;
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

	public double getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(double totalPoints) {
		this.totalPoints = totalPoints;
	}
	
	public EntranceExam getEntranceExam() {
		return entranceExam;
	}

	public void setEntranceExam(EntranceExam entranceExam) {
		this.entranceExam = entranceExam;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
