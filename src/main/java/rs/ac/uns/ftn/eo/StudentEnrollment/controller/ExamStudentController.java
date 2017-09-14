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

import rs.ac.uns.ftn.eo.StudentEnrollment.model.Exam;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.ExamStudent;
import rs.ac.uns.ftn.eo.StudentEnrollment.service.ExamService;
import rs.ac.uns.ftn.eo.StudentEnrollment.service.ExamStudentService;

@RestController
@RequestMapping(value = "api/examstudent")
public class ExamStudentController {
	
	@Autowired
	private ExamStudentService examStudentService;
	@Autowired
	private ExamService examService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<ExamStudent> getOne(@PathVariable Long id) {
		ExamStudent examStudent = examStudentService.findOne(id);
		if (examStudent == null) {
			return new ResponseEntity<ExamStudent>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ExamStudent>(examStudent, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/exam/{id}")
	public ResponseEntity<List<ExamStudent>> getByExam(@PathVariable Long id) {
		Exam exam = examService.findOne(id);
		if (exam == null) {
			return new ResponseEntity<List<ExamStudent>>(HttpStatus.NOT_FOUND);
		}
		
		List<ExamStudent> studentExams = examStudentService.findByExam(exam);
		
		return new ResponseEntity<List<ExamStudent>>(studentExams, HttpStatus.OK);
	}
	
	//POST
	//ExamStudent gets created only when Student associated with it is created.
	
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json", value="/{id}")
	public ResponseEntity<ExamStudent> edit(@PathVariable Long id, @RequestBody ExamStudent examStudent) {
		
		ExamStudent editedExamStudent = examStudentService.findOne(id);
		if (editedExamStudent == null){
			return new ResponseEntity<ExamStudent>(HttpStatus.NOT_FOUND);
		}	
		if (examStudent.getPoints() > editedExamStudent.getExam().getMaxPoints()) {
			return new ResponseEntity<ExamStudent>(HttpStatus.BAD_REQUEST);
		}
		
		editedExamStudent.setPoints(examStudent.getPoints());
		
		editedExamStudent = examStudentService.save(editedExamStudent);
		
		return new ResponseEntity<ExamStudent>(editedExamStudent, HttpStatus.OK);
	}
	
	//DELETE
	//ExamStuden gets deleted only when Student associated with it is deleted.
	
}
