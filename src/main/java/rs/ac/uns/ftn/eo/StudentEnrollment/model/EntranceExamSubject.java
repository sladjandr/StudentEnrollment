package rs.ac.uns.ftn.eo.StudentEnrollment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "entrance_exam_subject")
public class EntranceExamSubject {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
	private EntranceExam entranceExam;
	
	@ManyToOne
	private Subject subject;

	//TO DO
	//Make Constuctors and getters and setters
	
}
