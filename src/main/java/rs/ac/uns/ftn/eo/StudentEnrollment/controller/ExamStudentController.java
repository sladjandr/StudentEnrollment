package rs.ac.uns.ftn.eo.StudentEnrollment.controller;

import java.util.ArrayList;
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

import rs.ac.uns.ftn.eo.StudentEnrollment.dto.DateLocationDTO;
import rs.ac.uns.ftn.eo.StudentEnrollment.dto.ExamStudentDTO;
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
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<ExamStudent> getOne(@PathVariable Long id) {
		ExamStudent examStudent = examStudentService.findOne(id);
		if (examStudent == null) {
			return new ResponseEntity<ExamStudent>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ExamStudent>(examStudent, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/exam/{id}")
	public ResponseEntity<List<ExamStudent>> getByExam(@PathVariable Long id) {
		Exam exam = examService.findOne(id);
		if (exam == null) {
			return new ResponseEntity<List<ExamStudent>>(HttpStatus.NOT_FOUND);
		}
		
		List<ExamStudent> studentExams = examStudentService.findByExamThisYear(exam);
		
		return new ResponseEntity<List<ExamStudent>>(studentExams, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
	@RequestMapping(method = RequestMethod.GET, value = "/student/{id}")
	public ResponseEntity<List<ExamStudentDTO>> getByStudent(@PathVariable Long id) {
		Student student = studentService.findOne(id);
		if (student == null) {
			return new ResponseEntity<List<ExamStudentDTO>>(HttpStatus.NOT_FOUND);
		}
		
		List<ExamStudent> studentExams = examStudentService.findByStudent(student);
		List<ExamStudentDTO> examStudentDTOs = new ArrayList<ExamStudentDTO>();
		
		for (ExamStudent examStudent : studentExams){
			ExamStudentDTO examStudentDTO = new ExamStudentDTO();
			examStudentDTO.setDate(examStudent.getDate());
			examStudentDTO.setExam(examStudent.getExam());
			examStudentDTO.setLocation(examStudent.getLocation());
			examStudentDTOs.add(examStudentDTO);
		}
		
		return new ResponseEntity<List<ExamStudentDTO>>(examStudentDTOs, HttpStatus.OK);
	}
	
	//POST
	//ExamStudent gets created only when Student associated with it is created.
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json", value="/exam/{examId}")
	public ResponseEntity<ExamStudent> setDateAndLocation(@PathVariable Long examId, @RequestBody DateLocationDTO dateLocationDTO) {
		
		Exam exam = examService.findOne(examId);
		List<ExamStudent> studentExams = examStudentService.findByExamThisYear(exam); 
		
		for (ExamStudent studentExam : studentExams){
			studentExam.setDate(dateLocationDTO.getDate());
			studentExam.setLocation(dateLocationDTO.getLocation());
			examStudentService.save(studentExam);
		}
		
		return new ResponseEntity<ExamStudent>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
