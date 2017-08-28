package rs.ac.uns.ftn.eo.StudentEnrollment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "study_program")
public class StudyProgram {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "program_name")
	private String programName;
	
	@Column(name = "level")
	@Enumerated(EnumType.STRING)
	private StudyProgramLevel level;
	
	@Column(name = "duration")
	private int duration;
	
	@Column(name = "scientific_areas")
	private String scientificAreas;
	
	@Column(name = "espb_points")
	private int espbPoints;
	
	@Column(name = "budget_students")
	private int budgetStudents;
	
	@Column(name = "self_financing_students")
	private int selfFinancingStudents;
	
	@ManyToOne
	private EntranceExam entranceExam;
	
	//TO DO
	//Make Constuctors and getters and setters
	
}
