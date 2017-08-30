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
	//Make Constuctors
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public StudyProgramLevel getLevel() {
		return level;
	}

	public void setLevel(StudyProgramLevel level) {
		this.level = level;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public String getScientificAreas() {
		return scientificAreas;
	}

	public void setScientificAreas(String scientificAreas) {
		this.scientificAreas = scientificAreas;
	}

	public int getEspbPoints() {
		return espbPoints;
	}

	public void setEspbPoints(int espbPoints) {
		this.espbPoints = espbPoints;
	}

	public int getBudgetStudents() {
		return budgetStudents;
	}

	public void setBudgetStudents(int budgetStudents) {
		this.budgetStudents = budgetStudents;
	}

	public int getSelfFinancingStudents() {
		return selfFinancingStudents;
	}

	public void setSelfFinancingStudents(int selfFinancingStudents) {
		this.selfFinancingStudents = selfFinancingStudents;
	}
	
	public EntranceExam getEntranceExam() {
		return entranceExam;
	}

	public void setEntranceExam(EntranceExam entranceExam) {
		this.entranceExam = entranceExam;
	}
	
}
