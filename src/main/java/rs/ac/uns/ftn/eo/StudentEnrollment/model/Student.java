package rs.ac.uns.ftn.eo.StudentEnrollment.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "student")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id",
		  scope = Student.class)
public class Student {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name", nullable=false)
	private String name;
	
	@Column(name = "surname", nullable=false)
	private String surname;
	
	@Column(name = "address", nullable=false)
	private String address;
	
	@Column(name = "mail", nullable=false)
	private String mail;
	
	@Column(name = "high_school_points")
	private double highSchoolPoints;

	@OneToMany(mappedBy = "student")
	private List<StudentExam> studentExams;
	
	@OneToMany(mappedBy = "student")
	private List<Wish> wishes;
	
	@OneToOne(mappedBy = "student")
	private User user;
	
	public Student() {
		super();
	}

	public Student(Long id, String name, String surname, String address, String mail, double highSchoolPoints,
			List<StudentExam> studentExams, List<Wish> wishes, User user) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.mail = mail;
		this.highSchoolPoints = highSchoolPoints;
		this.studentExams = studentExams;
		this.wishes = wishes;
		this.user = user;
	}

	public Student(String name, String surname, String address, String mail, double highSchoolPoints,
			List<Wish> wishes, User user) {
		super();
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.mail = mail;
		this.highSchoolPoints = highSchoolPoints;
		this.wishes = wishes;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public double getHighSchoolPoints() {
		return highSchoolPoints;
	}

	public void setHighSchoolPoints(double highSchoolPoints) {
		this.highSchoolPoints = highSchoolPoints;
	}

	public List<StudentExam> getStudentExams() {
		return studentExams;
	}

	public void setStudentExams(List<StudentExam> studentExams) {
		this.studentExams = studentExams;
	}
	
	public List<Wish> getWishes() {
		return wishes;
	}

	public void setWishes(List<Wish> wishes) {
		this.wishes = wishes;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
