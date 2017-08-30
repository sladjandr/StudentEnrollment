package rs.ac.uns.ftn.eo.StudentEnrollment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	//TO DO
	//Make Constuctors 
	
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
