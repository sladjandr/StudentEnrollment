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
	//Make Constuctors and getters and setters
}
