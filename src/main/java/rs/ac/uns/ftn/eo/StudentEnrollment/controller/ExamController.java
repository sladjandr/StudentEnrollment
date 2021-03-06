package rs.ac.uns.ftn.eo.StudentEnrollment.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.Exam;
import rs.ac.uns.ftn.eo.StudentEnrollment.service.ExamService;

@RestController
@RequestMapping(value = "api/exam")
public class ExamController {
	
	@Autowired
	private ExamService examService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Exam> getOne(@PathVariable Long id) {
		Exam exam = examService.findOne(id);
		if (exam == null) {
			return new ResponseEntity<Exam>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Exam>(exam, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/all")
	public ResponseEntity<List<Exam>> getAll() {
		List<Exam> exams = examService.findAll();
		
		return new ResponseEntity<List<Exam>>(exams, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Exam> saveExam(@RequestBody Exam exam) {
		
		if (exam.getSubjectName() == null) {
			return new ResponseEntity<Exam>(HttpStatus.BAD_REQUEST);
		}
		
		if(examService.findBySubjectName(exam.getSubjectName()) != null){
			return new ResponseEntity<Exam>(HttpStatus.BAD_REQUEST);
		}
		
		exam.setActive(true);
		exam = examService.save(exam);

		return new ResponseEntity<Exam>(exam, HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json", value="/{id}")
	public ResponseEntity<Exam> editExam(@RequestBody Exam exam, @PathVariable Long id) {
		Exam editedExam = examService.findOne(id);
		if (editedExam == null){
			return new ResponseEntity<Exam>(HttpStatus.NOT_FOUND);
		}
		
		
		editedExam.setActive(exam.isActive());
		
		
		editedExam = examService.save(editedExam);

		return new ResponseEntity<Exam>(editedExam, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Exam> removeExam(@PathVariable Long id) {
		Exam entranceExam = examService.findOne(id);
		if (entranceExam == null) {
			return new ResponseEntity<Exam>(HttpStatus.NOT_FOUND);
		}
		
		if (!entranceExam.getStudyPrograms().isEmpty()) {
			return new ResponseEntity<Exam>(entranceExam, HttpStatus.BAD_REQUEST);
		}
		
		examService.remove(id);

		return new ResponseEntity<Exam>(HttpStatus.OK);
	}

}
