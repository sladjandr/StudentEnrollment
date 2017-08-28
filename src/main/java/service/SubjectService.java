package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.Subject;
import rs.ac.uns.ftn.eo.StudentEnrollment.repository.SubjectRepository;

@Service
public class SubjectService {
	
	@Autowired
	SubjectRepository subjectRepository;
	
	public Subject findOne(Long id) {
		return subjectRepository.findOne(id);
	}

	public List<Subject> findAll() {
		return subjectRepository.findAll();
	}

	public Subject save(Subject subject) {
		return subjectRepository.save(subject);
	}

	public void remove(Long id) {
		subjectRepository.delete(id);
	}

}
