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

import rs.ac.uns.ftn.eo.StudentEnrollment.dto.EntranceExamDTO;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.EntranceExam;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.EntranceExamSubject;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.StudyProgram;
import rs.ac.uns.ftn.eo.StudentEnrollment.service.EntranceExamService;
import rs.ac.uns.ftn.eo.StudentEnrollment.service.EntranceExamSubjectService;

@RestController
@RequestMapping(value = "api/entranceexam")
public class EntranceExamController {
	
	@Autowired
	private EntranceExamService entranceExamService;
	@Autowired
	private EntranceExamSubjectService entranceExamSubjectService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<EntranceExam> getOne(@PathVariable Long id) {
		EntranceExam entranceExam = entranceExamService.findOne(id);
		if (entranceExam == null) {
			return new ResponseEntity<EntranceExam>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<EntranceExam>(entranceExam, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/all")
	public ResponseEntity<List<EntranceExam>> getAll() {
		List<EntranceExam> entranceExams = entranceExamService.findAll();

		return new ResponseEntity<List<EntranceExam>>(entranceExams, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/studyProgram")
	public ResponseEntity<EntranceExam> getByStudyProgram(@RequestBody StudyProgram studyProgram) { 
		EntranceExam entranceExam = studyProgram.getEntranceExam();
		if (entranceExam == null) {
			return new ResponseEntity<EntranceExam>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<EntranceExam>(entranceExam, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<EntranceExam> saveEntranceExam(@RequestBody EntranceExamDTO entranceExamDTO) {
		
		if(entranceExamDTO.getSubjects()==null || entranceExamDTO.getName()==null){
			return new ResponseEntity<EntranceExam>(HttpStatus.BAD_REQUEST);
		}
		
		EntranceExam entranceExam = new EntranceExam();
		entranceExam.setName(entranceExamDTO.getName());
		entranceExam = entranceExamService.save(entranceExam);
		
		for(int i=0;i<entranceExamDTO.getSubjects().size();i++){
			EntranceExamSubject entranceExamSubject = new EntranceExamSubject();
			entranceExamSubject.setSubject(entranceExamDTO.getSubjects().get(i));
			entranceExamSubject.setEntranceExam(entranceExam);
			entranceExamSubjectService.save(entranceExamSubject);
		}
		
		
		entranceExam = entranceExamService.save(entranceExam);

		return new ResponseEntity<EntranceExam>(entranceExam, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json", value="/{id}")
	public ResponseEntity<EntranceExam> editEntranceExam(@PathVariable Long id, @RequestBody EntranceExamDTO entranceExamDTO) {
		
		if(entranceExamDTO.getName()==null || entranceExamDTO.getSubjects()==null){
			return new ResponseEntity<EntranceExam>(HttpStatus.BAD_REQUEST);
		}
		
		EntranceExam newEntranceExam =  entranceExamService.findOne(id);
		newEntranceExam.setName(entranceExamDTO.getName());
		//delete old EntranceExamSubjects to make space for new ones
	    List<EntranceExamSubject> oldEntranceExamSubjects = entranceExamSubjectService.findByEntranceExam(newEntranceExam);
	    for(int i=0; i<oldEntranceExamSubjects.size();i++){
	    	entranceExamSubjectService.remove(oldEntranceExamSubjects.get(i).getId());
	    }
	    //add new EntranceExamSubjects
		for(int i=0;i<entranceExamDTO.getSubjects().size();i++){
			EntranceExamSubject entranceExamSubject = new EntranceExamSubject();
			entranceExamSubject.setSubject(entranceExamDTO.getSubjects().get(i));
			entranceExamSubject.setEntranceExam(newEntranceExam);
			entranceExamSubjectService.save(entranceExamSubject);
		}
		//study programs and students are added on StudyProgramController and StudentController
		
		newEntranceExam = entranceExamService.save(newEntranceExam);

		return new ResponseEntity<EntranceExam>(newEntranceExam, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<EntranceExam> removeEntranceExam(@PathVariable Long id) {
		EntranceExam entranceExam = entranceExamService.findOne(id);
		if (entranceExam == null) {
			return new ResponseEntity<EntranceExam>(HttpStatus.NOT_FOUND);
		}
		//remove EntranceExamSubjects connected to Entrance Exam
		List<EntranceExamSubject> entranceExamSubjects = entranceExamSubjectService.findByEntranceExam(entranceExam);
	    for(int i=0; i<entranceExamSubjects.size();i++){
	    	entranceExamSubjectService.remove(entranceExamSubjects.get(i).getId());
	    }
		//remove EntranceExam
		entranceExamService.remove(id);

		return new ResponseEntity<EntranceExam>(HttpStatus.OK);
	}

}
