package rs.ac.uns.ftn.eo.StudentEnrollment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "wishes")
public class Wishes {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	@Column(name = "first_wish")
	private StudyProgram firstWish;
	
	@Column(name = "second_wish")
	private StudyProgram secondWish;
	
	@Column(name = "third_wish")
	private StudyProgram thirdWish;
	
	@OneToOne
	private Student student;
	
	//TO DO
	//Make Constuctors
	
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
