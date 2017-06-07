package rs.ac.uns.ftn.eo.StudentEnrollment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "study_program")
public class StudyProgram {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@ManyToMany
	private EntranceExamSubject subject;
	
}
