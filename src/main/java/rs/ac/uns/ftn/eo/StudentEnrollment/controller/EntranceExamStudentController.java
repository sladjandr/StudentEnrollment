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
import rs.ac.uns.ftn.eo.StudentEnrollment.model.EntranceExamStudent;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.Student;
import rs.ac.uns.ftn.eo.StudentEnrollment.service.EntranceExamService;
import rs.ac.uns.ftn.eo.StudentEnrollment.service.EntranceExamStudentService;
import rs.ac.uns.ftn.eo.StudentEnrollment.service.StudentService;

@RestController
@RequestMapping(value = "api/entranceexamstudent")
public class EntranceExamStudentController {
	
	@Autowired
	private EntranceExamStudentService entranceExamStudentService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private EntranceExamService entranceExamService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<EntranceExamStudent> getById(@PathVariable Long id) {
		EntranceExamStudent entranceExamStudent = entranceExamStudentService.findOne(id);
		if (entranceExamStudent == null) {
			return new ResponseEntity<EntranceExamStudent>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<EntranceExamStudent>(entranceExamStudent, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/student/{studentID}")
	public ResponseEntity<List<EntranceExamStudent>> getByStudent(@PathVariable Long studentID) {
		Student student = studentService.findOne(studentID);
		List<EntranceExamStudent> entranceExamsStudent = entranceExamStudentService.findByStudent(student);
		if (entranceExamsStudent == null) {
			return new ResponseEntity<List<EntranceExamStudent>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<EntranceExamStudent>>(entranceExamsStudent, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/entranceexam/{entranceExamID}")
	public ResponseEntity<List<EntranceExamStudent>> getByEntranceExam(@PathVariable Long entranceExamID) {
		EntranceExam entranceExam = entranceExamService.findOne(entranceExamID);
		List<EntranceExamStudent> entranceExamsStudent = entranceExamStudentService.findByEntranceExam(entranceExam);
		if (entranceExamsStudent == null) {
			return new ResponseEntity<List<EntranceExamStudent>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<EntranceExamStudent>>(entranceExamsStudent, HttpStatus.OK);
	}
	
	//POST
	//EntranceExamStudent gets created only when Student associated with it is created.
	
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json", value="/{id}")
	public ResponseEntity<EntranceExamStudent> edit(@PathVariable Long id, @RequestBody EntranceExamStudent entranceExamStudent) {
		
		EntranceExamStudent newEntranceExamStudent = entranceExamStudentService.findOne(id);
		if (newEntranceExamStudent.getId()==null){
			return new ResponseEntity<EntranceExamStudent>(HttpStatus.BAD_REQUEST);
		}
		
		double points = entranceExamStudent.getPoints();
		newEntranceExamStudent.setPoints(points);
		double highSchoolPoints = newEntranceExamStudent.getStudent().getHighSchoolPoints();
		double totalPoints = points + highSchoolPoints;
		newEntranceExamStudent.setTotalPoints(totalPoints);
		
		return new ResponseEntity<EntranceExamStudent>(newEntranceExamStudent, HttpStatus.OK);
	}
	
	//DELETE
	//EntranceExamStudent gets deleted only when Student associated with it is deleted.
	
}
