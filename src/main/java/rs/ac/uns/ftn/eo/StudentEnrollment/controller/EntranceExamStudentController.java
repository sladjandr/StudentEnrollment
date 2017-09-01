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
import rs.ac.uns.ftn.eo.StudentEnrollment.service.EntranceExamStudentService;
import rs.ac.uns.ftn.eo.StudentEnrollment.service.StudentService;

@RestController
@RequestMapping(value = "api/entranceExamStudent")
public class EntranceExamStudentController {
	
	@Autowired
	private EntranceExamStudentService entranceExamStudentService;
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<EntranceExamStudent> getById(@PathVariable Long id) {
		EntranceExamStudent entranceExamStudent = entranceExamStudentService.findOne(id);
		if (entranceExamStudent == null) {
			return new ResponseEntity<EntranceExamStudent>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<EntranceExamStudent>(entranceExamStudent, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/student")
	public ResponseEntity<List<EntranceExamStudent>> getByStudent(@RequestBody Student student) {
		List<EntranceExamStudent> entranceExamsStudent = entranceExamStudentService.findByStudent(student);
		if (entranceExamsStudent == null) {
			return new ResponseEntity<List<EntranceExamStudent>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<EntranceExamStudent>>(entranceExamsStudent, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/entranceExam")
	public ResponseEntity<List<EntranceExamStudent>> getByEntranceExam(@RequestBody EntranceExam entranceExam) {
		List<EntranceExamStudent> entranceExamsStudent = entranceExamStudentService.findByEntranceExam(entranceExam);
		if (entranceExamsStudent == null) {
			return new ResponseEntity<List<EntranceExamStudent>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<EntranceExamStudent>>(entranceExamsStudent, HttpStatus.OK);
	}
	
	//POST
	//EntranceExamStudent gets created only when Student associated with it is created.
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<EntranceExamStudent> edit(@PathVariable Long id, @RequestBody EntranceExamStudent entranceExamStudent) {
		
		EntranceExamStudent newEntranceExamStudent = entranceExamStudentService.findOne(id);
		if (newEntranceExamStudent.getId()==null){
			return new ResponseEntity<EntranceExamStudent>(HttpStatus.BAD_REQUEST);
		}
		
		double points = entranceExamStudent.getPoints();
		newEntranceExamStudent.setPoints(points);
		double highSchoolPoints = studentService.findByEntranceExamStudent(newEntranceExamStudent).getHighSchoolPoints();
		double totalPoints = points + highSchoolPoints;
		newEntranceExamStudent.setTotalPoints(totalPoints);
		
		return new ResponseEntity<EntranceExamStudent>(newEntranceExamStudent, HttpStatus.OK);
	}
	
	//DELETE
	//EntranceExamStudent gets deleted only when Student associated with it is deleted.
	
}