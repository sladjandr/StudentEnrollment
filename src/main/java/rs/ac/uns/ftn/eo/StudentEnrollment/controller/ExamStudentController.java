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
import rs.ac.uns.ftn.eo.StudentEnrollment.model.Student;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.Wish;
import rs.ac.uns.ftn.eo.StudentEnrollment.service.ExamService;
import rs.ac.uns.ftn.eo.StudentEnrollment.service.ExamStudentService;
import rs.ac.uns.ftn.eo.StudentEnrollment.service.StudentService;
import rs.ac.uns.ftn.eo.StudentEnrollment.service.WishService;

@RestController
@RequestMapping(value = "api/examstudent")
public class ExamStudentController {
	
	@Autowired
	private ExamStudentService examStudentService;
	@Autowired
	private ExamService examService;
	@Autowired
	private WishService wishService;
	@Autowired
	private StudentService studentService;
	
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
		
		List<ExamStudent> studentExams = examStudentService.findByExamThisYear(exam);
		
		return new ResponseEntity<List<ExamStudent>>(studentExams, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/student/{id}")
	public ResponseEntity<List<ExamStudent>> getByStudent(@PathVariable Long id) {
		Student student = studentService.findOne(id);
		if (student == null) {
			return new ResponseEntity<List<ExamStudent>>(HttpStatus.NOT_FOUND);
		}
		
		List<ExamStudent> studentExams = examStudentService.findByStudent(student);
		
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
		editedExamStudent.setFinished(true);
		
		editedExamStudent = examStudentService.save(editedExamStudent);
		
		//updating wishes
		for (Wish wish : editedExamStudent.getWishes()){
			wishService.updateTotalPoints(wish);
		}
		
		return new ResponseEntity<ExamStudent>(editedExamStudent, HttpStatus.OK);
	}
	
	//DELETE
	//ExamStuden gets deleted only when Student associated with it is deleted.
	
}
