package rs.ac.uns.ftn.eo.StudentEnrollment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "role", nullable=false)
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	@Column(name = "user_name", unique = true, nullable=false)
	private String userName;
	
	@Column(name = "password", nullable=false)
	private String password;
	
	@OneToOne
	private Student student;
	
	
	public User() {
		super();
	}

	public User(Long id, UserRole role, String username, String password, Student student) {
		super();
		this.id = id;
		this.role = role;
		this.userName = username;
		this.password = password;
		this.student = student;
	}
	
	public User(String username, String password) {
		super();
		this.userName = username;
		this.password = password;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getUsername() {
		return userName;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
}
