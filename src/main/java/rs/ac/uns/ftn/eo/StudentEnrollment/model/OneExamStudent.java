package rs.ac.uns.ftn.eo.StudentEnrollment.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "one_exam_student")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id",
		  scope = OneExamStudent.class)
public class OneExamStudent {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "points")
	private double points;

	@Column(name = "location")
	private String location;
	
	@Column(name = "date")
	private Date date;
	
	@ManyToOne
	private EntranceExamStudent entranceExamStudent;
	
	@ManyToOne
	private Exam exam;
	
	
	public OneExamStudent() {
		super();
	}

	public OneExamStudent(Long id, double points, String location, Date date, EntranceExamStudent entranceExamStudent,
			Exam exam) {
		super();
		this.id = id;
		this.points = points;
		this.location = location;
		this.date = date;
		this.entranceExamStudent = entranceExamStudent;
		this.exam = exam;
	}

	public OneExamStudent(double points, String location, Date date, EntranceExamStudent entranceExamStudent,
			Exam exam) {
		super();
		this.points = points;
		this.location = location;
		this.date = date;
		this.entranceExamStudent = entranceExamStudent;
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
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public EntranceExamStudent getEntranceExamStudent() {
		return entranceExamStudent;
	}

	public void setEntranceExamStudent(EntranceExamStudent entranceExamStudent) {
		this.entranceExamStudent = entranceExamStudent;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

}
