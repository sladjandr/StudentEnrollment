package rs.ac.uns.ftn.eo.StudentEnrollment.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "exam")
public class Exam {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "subject_name", unique=true, nullable=false)
	private String subjectName;

	@Column(name = "max_points")
	private int maxPoints;

	@Column(name = "is_active")
	private boolean isActive;
	
	@OneToMany(mappedBy = "exam")
	private List<ExamStudent> studentExams;
	
	@JsonBackReference(value = "ex-sp") //back reference needs to have value to avoid Multiple back-reference properties with same name error
	@ManyToMany(mappedBy = "exams")
	private List<StudyProgram> studyPrograms;

	
	public Exam() {
		super();
	}

	public Exam(Long id, String subjectName, int maxPoints, boolean isActive, 
			List<ExamStudent> studentExams, List<StudyProgram> studyPrograms) {
		super();
		this.id = id;
		this.subjectName = subjectName;
		this.maxPoints = maxPoints;

		this.isActive = isActive;
		this.studentExams = studentExams;
		this.studyPrograms = studyPrograms;
	}

	public Exam(String subjectName, int maxPoints, boolean isActive) {
		super();
		this.subjectName = subjectName;
		this.maxPoints = maxPoints;
		this.isActive = isActive;
	}
	
	
	@Override
	public boolean equals(Object object)
    {
        if (object != null && object instanceof Exam)
        {
            return this.id == ((Exam) object).id;
        }else{
        return false;
        }
    }
	
	@Override
	public int hashCode(){
		return this.id.intValue();
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getSubjectName() {
		return subjectName;
	}


	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	public int getMaxPoints() {
		return maxPoints;
	}

	public void setMaxPoints(int maxPoints) {
		this.maxPoints = maxPoints;
	}
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public List<ExamStudent> getStudentExams() {
		return studentExams;
	}


	public void setStudentExams(List<ExamStudent> studentExams) {
		this.studentExams = studentExams;
	}

	public List<StudyProgram> getStudyPrograms() {
		return studyPrograms;
	}

	public void setStudyPrograms(List<StudyProgram> studyPrograms) {
		this.studyPrograms = studyPrograms;
	}
	
	

	
}
