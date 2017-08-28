package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.EntranceExam;
import rs.ac.uns.ftn.eo.StudentEnrollment.repository.EntranceExamRepository;

@Service
public class EntranceExamService {
	
	@Autowired
	EntranceExamRepository entranceExamRepository;
	
	public EntranceExam findOne(Long id) {
		return entranceExamRepository.findOne(id);
	}

	public List<EntranceExam> findAll() {
		return entranceExamRepository.findAll();
	}

	public EntranceExam save(EntranceExam entranceExam) {
		return entranceExamRepository.save(entranceExam);
	}

	public void remove(Long id) {
		entranceExamRepository.delete(id);
	}


}
