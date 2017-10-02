package rs.ac.uns.ftn.eo.StudentEnrollment.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.Student;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.StudyProgram;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.StudyProgramLevel;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.Wish;
import rs.ac.uns.ftn.eo.StudentEnrollment.service.StudentService;
import rs.ac.uns.ftn.eo.StudentEnrollment.service.StudyProgramService;
import rs.ac.uns.ftn.eo.StudentEnrollment.service.WishService;

@RestController
@RequestMapping(value = "api/studyprogram")
public class StudyProgramController {

	@Autowired
	private StudyProgramService studyProgramService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private WishService wishService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<StudyProgram> getOne(@PathVariable Long id) {
		StudyProgram studyProgram = studyProgramService.findOne(id);
		if (studyProgram == null) {
			return new ResponseEntity<StudyProgram>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<StudyProgram>(studyProgram, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/all")
	public ResponseEntity<List<StudyProgram>> getAll() {
		List<StudyProgram> studyProgram = studyProgramService.findAll();

		return new ResponseEntity<List<StudyProgram>>(studyProgram, HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.GET, value = "/allactive")
	public ResponseEntity<List<StudyProgram>> getAllActive() {
		List<StudyProgram> studyProgram = studyProgramService.findAllActive();

		return new ResponseEntity<List<StudyProgram>>(studyProgram, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/student/{studentId}")
	public ResponseEntity<List<StudyProgram>> getByStudent(@PathVariable Long studentId) {
		Student student = studentService.findOne(studentId);
		if (student == null) {
			return new ResponseEntity<List<StudyProgram>>(HttpStatus.NOT_FOUND);
		}
		List<Wish> wishes = wishService.findByStudent(student);
		List<StudyProgram> studyPrograms = new ArrayList<StudyProgram>();
		for (Wish wish : wishes){
			studyPrograms.add(wish.getStudyProgram());
		}
		
		return new ResponseEntity<List<StudyProgram>>(studyPrograms, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<StudyProgram> saveStudyProgram(@RequestBody StudyProgram studyProgram) {
		
		if(studyProgram.getLevel()!= StudyProgramLevel.BAS && studyProgram.getLevel()!= StudyProgramLevel.BVS){
			return new ResponseEntity<StudyProgram>(studyProgram, HttpStatus.BAD_REQUEST);
		}
		
		if(studyProgram.getProgramName()==null){
			return new ResponseEntity<StudyProgram>(studyProgram, HttpStatus.BAD_REQUEST);
		}
		
		if(studyProgramService.findByProgramName(studyProgram.getProgramName())!=null){
			return new ResponseEntity<StudyProgram>(studyProgram, HttpStatus.BAD_REQUEST);
		}
		
		studyProgram.setActive(true);
		studyProgram = studyProgramService.save(studyProgram);
		

		return new ResponseEntity<StudyProgram>(studyProgram, HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json", value="/{id}")
	public ResponseEntity<StudyProgram> editStudyProgram(@PathVariable Long id,@RequestBody StudyProgram studyProgram) {
	
		StudyProgram editedStudyProgram = studyProgramService.findOne(id);
		
		if(editedStudyProgram==null){
			return new ResponseEntity<StudyProgram>(HttpStatus.NOT_FOUND);			
		}
		
		editedStudyProgram.setBudgetStudents(studyProgram.getBudgetStudents());
		editedStudyProgram.setSelfFinancingStudents(studyProgram.getSelfFinancingStudents());
		editedStudyProgram.setActive(studyProgram.isActive());
		editedStudyProgram.setExams(studyProgram.getExams());
		
		editedStudyProgram = studyProgramService.save(editedStudyProgram);

		return new ResponseEntity<StudyProgram>(editedStudyProgram, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<StudyProgram> deleteStudyProgram(@PathVariable Long id) {
		StudyProgram studyProgram = studyProgramService.findOne(id);
		if(studyProgram==null){
			return new ResponseEntity<StudyProgram>(HttpStatus.NOT_FOUND);
		}
		
		if (!studyProgram.getWishes().isEmpty()){
			return new ResponseEntity<StudyProgram>(studyProgram, HttpStatus.BAD_REQUEST);
		}
		
		if (!studyProgram.getExams().isEmpty()){
			return new ResponseEntity<StudyProgram>(studyProgram, HttpStatus.BAD_REQUEST);
		}

		studyProgramService.remove(id);
		
		return new ResponseEntity<StudyProgram>(HttpStatus.OK);
	}
	
}
