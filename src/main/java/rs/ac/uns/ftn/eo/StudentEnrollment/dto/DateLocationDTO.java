package rs.ac.uns.ftn.eo.StudentEnrollment.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class DateLocationDTO {

	@Column(name = "location")
	private String location;

	@Column(name = "date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	
	public DateLocationDTO() {
		super();
	}

	public DateLocationDTO(String location, Date date) {
		super();
		this.location = location;
		this.date = date;
	}
	
	public DateLocationDTO(String location) {
		super();
		this.location = location;
	}
	
	public DateLocationDTO(Date date) {
		super();
		this.date = date;
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
	
}


