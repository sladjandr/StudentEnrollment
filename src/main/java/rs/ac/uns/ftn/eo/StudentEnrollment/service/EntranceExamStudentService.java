package rs.ac.uns.ftn.eo.StudentEnrollment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.EntranceExamStudent;
import rs.ac.uns.ftn.eo.StudentEnrollment.repository.EntranceExamStudentRepository;

@Service
public class EntranceExamStudentService {
	
	@Autowired
	private EntranceExamStudentRepository entranceExamStudentRepository;

	public EntranceExamStudent findOne(Long id) {
		return entranceExamStudentRepository.findOne(id);
	}

	public List<EntranceExamStudent> findAll() {
		return entranceExamStudentRepository.findAll();
	}

	public EntranceExamStudent save(EntranceExamStudent entranceExamStudent) {
		return entranceExamStudentRepository.save(entranceExamStudent);
	}

	public void remove(Long id) {
		entranceExamStudentRepository.delete(id);
	}
	
}
