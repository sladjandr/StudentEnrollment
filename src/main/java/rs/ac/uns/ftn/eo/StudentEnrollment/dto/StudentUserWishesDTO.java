package rs.ac.uns.ftn.eo.StudentEnrollment.dto;


import java.util.List;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.StudyProgram;

public class StudentUserWishesDTO {
	
	private String name;
	private String surname;
	private String address;
	private String mail;
	private double highSchoolPoints;
	
	private String username;
	
	private List<StudyProgram> studyPrograms;
	
	
	public StudentUserWishesDTO() {
		super();
	}
	
	public StudentUserWishesDTO(String name, String surname, String address, String mail, double highSchoolPoints,
			String username, List<StudyProgram> studyPrograms) {
		super();
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.mail = mail;
		this.highSchoolPoints = highSchoolPoints;
		this.username = username;
		this.studyPrograms = studyPrograms;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<StudyProgram> getStudyPrograms() {
		return studyPrograms;
	}

	public void setStudyPrograms(List<StudyProgram> studyPrograms) {
		this.studyPrograms = studyPrograms;
	}

	
}
