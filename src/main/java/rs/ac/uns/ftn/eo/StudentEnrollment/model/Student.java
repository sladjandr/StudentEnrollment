package rs.ac.uns.ftn.eo.StudentEnrollment.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "surname")
	private String surname;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "mail")
	private String mail;
	
	@Column(name = "high_school_points")
	private double highSchoolPoints;
		
	@OneToMany(mappedBy = "student")
	private List<EntranceExamStudent> entranceExamStudents;
	
	@OneToOne
	private Wishes wishes;
	
	@OneToOne
	private User user;
	
	//TO DO
	//Make Constuctors and getters and setters
	
}
