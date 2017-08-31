package rs.ac.uns.ftn.eo.StudentEnrollment.dto;


import rs.ac.uns.ftn.eo.StudentEnrollment.model.StudyProgram;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.UserRole;

public class StudentUserWishesDTO {

	private Long id;
	
	private String name;
	private String surname;
	private String address;
	private String mail;
	private double highSchoolPoints;
	
	private UserRole role;
	private String username;
	private String password;
	
	private StudyProgram firstWish;
	private StudyProgram secondWish;
	private StudyProgram thirdWish;
	
	
	
	public StudentUserWishesDTO() {
		super();
	}

	public StudentUserWishesDTO(Long id, String name, String surname, String address, String mail,
			double highSchoolPoints, UserRole role, String username, String password, StudyProgram firstWish,
			StudyProgram secondWish, StudyProgram thirdWish) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.mail = mail;
		this.highSchoolPoints = highSchoolPoints;
		this.role = role;
		this.username = username;
		this.password = password;
		this.firstWish = firstWish;
		this.secondWish = secondWish;
		this.thirdWish = thirdWish;
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

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
}
