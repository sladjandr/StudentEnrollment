package rs.ac.uns.ftn.eo.StudentEnrollment.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "study_program")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id",
		  scope = StudyProgram.class)
public class StudyProgram {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "program_name", nullable=false, unique=true)
	private String programName;
	
	@Column(name = "level", nullable=false)
	@Enumerated(EnumType.STRING)
	private StudyProgramLevel level;
	
	@Column(name = "duration", nullable=false)
	private int duration;
	
	@Column(name = "espb_points", nullable=false)
	private int espbPoints;
	
	@Column(name = "budget_students")
	private int budgetStudents;
	
	@Column(name = "self_financing_students")
	private int selfFinancingStudents;

	@OneToMany(mappedBy="studyProgram")
	private List<Wish> wishes;
	
	@ManyToMany
	private List<Exam> exams;
	
	
	public StudyProgram() {
		super();
	}

	public StudyProgram(Long id, String programName, StudyProgramLevel level, int duration, 
			int espbPoints, int budgetStudents, int selfFinancingStudents, List<Wish> wishes, List<Exam> exams) {
		super();
		this.id = id;
		this.programName = programName;
		this.level = level;
		this.duration = duration;
		this.espbPoints = espbPoints;
		this.budgetStudents = budgetStudents;
		this.selfFinancingStudents = selfFinancingStudents;
		this.wishes = wishes;
		this.exams = exams;
	}

	public StudyProgram(String programName, StudyProgramLevel level, int duration, 
			int espbPoints, int budgetStudents, int selfFinancingStudents, List<Exam> exams) {
		super();
		this.programName = programName;
		this.level = level;
		this.duration = duration;
		this.espbPoints = espbPoints;
		this.budgetStudents = budgetStudents;
		this.selfFinancingStudents = selfFinancingStudents;
		this.exams = exams;
	}

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
	
	public List<Wish> getWishes() {
		return wishes;
	}

	public void setWishes(List<Wish> wishes) {
		this.wishes = wishes;
	}
	
	public List<Exam> getExams() {
		return exams;
	}

	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}

	
}
