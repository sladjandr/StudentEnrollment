package rs.ac.uns.ftn.eo.StudentEnrollment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "entrance_exam")
public class EntranceExam {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@OneToOne
	private StudyProgram study_program;
	
	@ManyToMany
	private EntranceExamSubject subject;
	
	@OneToMany(mappedBy = "entrance_exam")
	private Student student;
	
}
