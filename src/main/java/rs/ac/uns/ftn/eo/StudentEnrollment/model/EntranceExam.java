package rs.ac.uns.ftn.eo.StudentEnrollment.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "entrance_exam")
public class EntranceExam {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name", unique = true)
	private String name;
	
	@OneToMany(mappedBy = "entrance_exam")
	private List<StudyProgram> studyPrograms;
	
	@OneToMany(mappedBy = "entrance_exam")
	private List<EntranceExamSubject> entranceExamSubjects;
	
	@OneToMany(mappedBy = "entrance_exam")
	private List<EntranceExamStudent> entanceExamStudents;
	
}
