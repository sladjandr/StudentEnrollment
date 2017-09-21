package rs.ac.uns.ftn.eo.StudentEnrollment.dto;


import java.util.List;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.StudyProgram;

public class StudentAndWishesDTO {
	
	private String name;
	private String surname;
	private String address;
	private String mail;
	private double highSchoolPoints;
	
	private int year;
	private List<StudyProgram> studyPrograms;
	
	
	
	public StudentAndWishesDTO() {
		super();
	}
	
	public StudentAndWishesDTO(String name, String surname, String address, String mail, double highSchoolPoints,
			int year, List<StudyProgram> studyPrograms) {
		super();
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.mail = mail;
		this.highSchoolPoints = highSchoolPoints;
		this.year = year;
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

	public List<StudyProgram> getStudyPrograms() {
		return studyPrograms;
	}

	public void setStudyPrograms(List<StudyProgram> studyPrograms) {
		this.studyPrograms = studyPrograms;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
