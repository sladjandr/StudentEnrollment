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

import rs.ac.uns.ftn.eo.StudentEnrollment.dto.EntranceExamDTO;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.EntranceExam;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.EntranceExamSubject;
import rs.ac.uns.ftn.eo.StudentEnrollment.service.EntranceExamService;

@RestController
@RequestMapping(value = "api/entranceExam")
public class EntranceExamController {
	
	@Autowired
	private EntranceExamService entranceExamService;
	
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
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<EntranceExam> saveEntranceExam(@RequestBody EntranceExamDTO entranceExamDTO) {
		
		if(entranceExamDTO.getSubjects()==null || entranceExamDTO.getName()==null){
			return new ResponseEntity<EntranceExam>(HttpStatus.BAD_REQUEST);
		}
		
		EntranceExam entranceExam = new EntranceExam();
		entranceExam.setName(entranceExamDTO.getName());
		List<EntranceExamSubject> entranceExamSubjects = new ArrayList<EntranceExamSubject>();
		for(int i=0;i<entranceExamDTO.getSubjects().size();i++){
			EntranceExamSubject entranceExamSubject = new EntranceExamSubject();
			entranceExamSubject.setSubject(entranceExamDTO.getSubjects().get(i));
			entranceExamSubjects.add(entranceExamSubject);
		}
		entranceExam.setEntranceExamSubjects(entranceExamSubjects);
		
		
		entranceExam = entranceExamService.save(entranceExam);

		return new ResponseEntity<EntranceExam>(entranceExam, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<EntranceExam> editEntranceExam(@PathVariable Long id, @RequestBody EntranceExamDTO entranceExamDTO) {
		
		if(entranceExamDTO.getName()==null || entranceExamDTO.getSubjects()==null){
			return new ResponseEntity<EntranceExam>(HttpStatus.BAD_REQUEST);
		}
		
		EntranceExam newEntranceExam = new EntranceExam();
		newEntranceExam.setName(entranceExamDTO.getName());
		List<EntranceExamSubject> entranceExamSubjects = new ArrayList<EntranceExamSubject>();
		for(int i=0;i<entranceExamDTO.getSubjects().size();i++){
			EntranceExamSubject entranceExamSubject = new EntranceExamSubject();
			entranceExamSubject.setSubject(entranceExamDTO.getSubjects().get(i));
			entranceExamSubjects.add(entranceExamSubject);
		}
		newEntranceExam.setEntranceExamSubjects(entranceExamSubjects);
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
		
		entranceExamService.remove(id);

		return new ResponseEntity<EntranceExam>(entranceExam, HttpStatus.OK);
	}

}
