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

import rs.ac.uns.ftn.eo.StudentEnrollment.model.EntranceExam;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.EntranceExamSubject;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.Subject;
import rs.ac.uns.ftn.eo.StudentEnrollment.service.EntranceExamSubjectService;

@RestController
@RequestMapping(value = "api/entranceexamsubject")
public class EntranceExamSubjectController {
	
	@Autowired
	private EntranceExamSubjectService entranceExamSubjectService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<EntranceExamSubject> getById(@PathVariable Long id) {
		EntranceExamSubject entranceExamSubject = entranceExamSubjectService.findOne(id);
		if (entranceExamSubject == null) {
			return new ResponseEntity<EntranceExamSubject>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<EntranceExamSubject>(entranceExamSubject, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/entranceExam")
	public ResponseEntity<List<EntranceExamSubject>> getByEntranceExam(@RequestBody EntranceExam entranceExam) {
		List<EntranceExamSubject> entranceExamSubjects = entranceExamSubjectService.findByEntranceExam(entranceExam);
		if (entranceExamSubjects == null) {
			return new ResponseEntity<List<EntranceExamSubject>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<EntranceExamSubject>>(entranceExamSubjects, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/subject")
	public ResponseEntity<List<EntranceExamSubject>> getBySubject(@RequestBody Subject subject) {
		List<EntranceExamSubject> entranceExamSubjects = entranceExamSubjectService.findBySubject(subject);
		if (entranceExamSubjects == null) {
			return new ResponseEntity<List<EntranceExamSubject>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<EntranceExamSubject>>(entranceExamSubjects, HttpStatus.OK);
	}
	
	//POST
	//EntranceExamSubject gets created when EntranceExam is created.
	
	//PUT
	//EntranceExamSubject can't be changed.
	
	//DELETE
	//EntranceExamSubject gets deleted when EntranceExam is deleted.

}
