package rs.ac.uns.ftn.eo.StudentEnrollment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.EntranceExamSubject;
import rs.ac.uns.ftn.eo.StudentEnrollment.repository.EntranceExamSubjectRepository;

@Service
public class EntranceExamSubjectService {
	
	@Autowired
	private EntranceExamSubjectRepository entranceExamSubjectRepository;
	
	public EntranceExamSubject findOne(Long id) {
		return entranceExamSubjectRepository.findOne(id);
	}

	public List<EntranceExamSubject> findAll() {
		return entranceExamSubjectRepository.findAll();
	}

	public EntranceExamSubject save(EntranceExamSubject entranceExamSubject) {
		return entranceExamSubjectRepository.save(entranceExamSubject);
	}

	public void remove(Long id) {
		entranceExamSubjectRepository.delete(id);
	}

}
