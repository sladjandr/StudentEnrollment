package rs.ac.uns.ftn.eo.StudentEnrollment.dto;

public class SubjectDTO {

	private Long id;

	private String name;
	
	public SubjectDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public SubjectDTO(String name) {
		super();
		this.name = name;
	}

	public SubjectDTO() {
		super();
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
	
}
