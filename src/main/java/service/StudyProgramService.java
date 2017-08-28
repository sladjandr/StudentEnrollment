package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.StudyProgram;
import rs.ac.uns.ftn.eo.StudentEnrollment.repository.StudyProgramRepository;

@Service
public class StudyProgramService {
	
	@Autowired
	StudyProgramRepository studyProgramRepository;
	
	public StudyProgram findOne(Long id) {
		return studyProgramRepository.findOne(id);
	}

	public List<StudyProgram> findAll() {
		return studyProgramRepository.findAll();
	}

	public StudyProgram save(StudyProgram studyProgram) {
		return studyProgramRepository.save(studyProgram);
	}

	public void remove(Long id) {
		studyProgramRepository.delete(id);
	}

}
