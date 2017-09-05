package rs.ac.uns.ftn.eo.StudentEnrollment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.eo.StudentEnrollment.dto.SubjectDTO;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.Subject;
import rs.ac.uns.ftn.eo.StudentEnrollment.service.SubjectService;

@RestController
@RequestMapping(value = "api/subject")
public class SubjectController {

	@Autowired
	private SubjectService subjectService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Subject> getOne(@PathVariable Long id) {
		Subject subject = subjectService.findOne(id);
		if (subjectService == null) {
			return new ResponseEntity<Subject>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Subject>(subject, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/all")
	public ResponseEntity<List<Subject>> getAll() {
		List<Subject> subjects = subjectService.findAll();

		return new ResponseEntity<List<Subject>>(subjects, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Subject> saveSubject(@RequestBody SubjectDTO subjectDTO) {
		if(subjectDTO.getName()==null){
			return new ResponseEntity<Subject>(HttpStatus.BAD_REQUEST);
		}
		if(subjectService.findByName(subjectDTO.getName())!=null){
			return new ResponseEntity<Subject>(HttpStatus.BAD_REQUEST);
		}
		
		Subject subject = new Subject();
		subject.setName(subjectDTO.getName());
		subject = subjectService.save(subject);
		//EntranceExamSubjects are set on EntranceExamController
		
		return new ResponseEntity<Subject>(subject, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json", value="/{id}")
	public ResponseEntity<Subject> editSubject(@PathVariable Long id,@RequestBody SubjectDTO subjectDTO) {
		if(subjectDTO.getName()==null){
			return new ResponseEntity<Subject>(HttpStatus.BAD_REQUEST);
		}
		if(subjectService.findByName(subjectDTO.getName())!=null){
			return new ResponseEntity<Subject>(HttpStatus.BAD_REQUEST);
		}
		
		Subject newSubject = subjectService.findOne(id);
		
		if(newSubject==null){
			return new ResponseEntity<Subject>(newSubject, HttpStatus.NOT_FOUND);
		}
		
		newSubject.setName(subjectDTO.getName());
		//EntranceExamSubjects are set on EntranceExamController
		
		newSubject = subjectService.save(newSubject);
		
		return new ResponseEntity<Subject>(newSubject, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Subject> deleteSubject(@PathVariable Long id) {
		Subject subject = subjectService.findOne(id);
		if (subject == null) {
			return new ResponseEntity<Subject>(HttpStatus.NOT_FOUND);
		}
		
		subjectService.remove(id);

		return new ResponseEntity<Subject>(HttpStatus.OK);
	}
	 
}
