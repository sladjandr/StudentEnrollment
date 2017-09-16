package rs.ac.uns.ftn.eo.StudentEnrollment.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "exam")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id",
		  scope = Exam.class)
public class Exam {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "subject_name", unique=true, nullable=false)
	private String subjectName;

	@Column(name = "max_points")
	private int maxPoints;
	
	@Column(name = "location")
	private String location;

	@Column(name = "date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@OneToMany(mappedBy = "exam")
	private List<ExamStudent> studentExams;
	
	@ManyToMany(mappedBy = "exams")
	private List<StudyProgram> studyPrograms;

	
	public Exam() {
		super();
	}

	public Exam(Long id, String subjectName, int maxPoints, String location, Date date, List<ExamStudent> studentExams, List<StudyProgram> studyPrograms) {
		super();
		this.id = id;
		this.subjectName = subjectName;
		this.maxPoints = maxPoints;
		this.location = location;
		this.date = date;
		this.studentExams = studentExams;
		this.studyPrograms = studyPrograms;
	}

	public Exam(String subjectName, int maxPoints, String location, Date date) {
		super();
		this.subjectName = subjectName;
		this.maxPoints = maxPoints;
		this.location = location;
		this.date = date;
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
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
