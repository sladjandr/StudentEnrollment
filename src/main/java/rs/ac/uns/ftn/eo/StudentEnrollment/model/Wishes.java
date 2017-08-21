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

}
