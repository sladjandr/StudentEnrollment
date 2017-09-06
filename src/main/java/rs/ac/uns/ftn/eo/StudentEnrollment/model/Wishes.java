package rs.ac.uns.ftn.eo.StudentEnrollment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "wishes")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id",
		  scope = Wishes.class)
public class Wishes {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	@OneToOne
	private StudyProgram firstWish;
	
	@OneToOne
	private StudyProgram secondWish;
	
	@OneToOne
	private StudyProgram thirdWish;
	
	@OneToOne
	private Student student;
	
	
	public Wishes() {
		super();
	}

	public Wishes(Long id, StudyProgram firstWish, StudyProgram secondWish, StudyProgram thirdWish, Student student) {
		super();
		this.id = id;
		this.firstWish = firstWish;
		this.secondWish = secondWish;
		this.thirdWish = thirdWish;
		this.student = student;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StudyProgram getFirstWish() {
		return firstWish;
	}

	public void setFirstWish(StudyProgram firstWish) {
		this.firstWish = firstWish;
	}

	public StudyProgram getSecondWish() {
		return secondWish;
	}

	public void setSecondWish(StudyProgram secondWish) {
		this.secondWish = secondWish;
	}

	public StudyProgram getThirdWish() {
		return thirdWish;
	}

	public void setThirdWish(StudyProgram thirdWish) {
		this.thirdWish = thirdWish;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
