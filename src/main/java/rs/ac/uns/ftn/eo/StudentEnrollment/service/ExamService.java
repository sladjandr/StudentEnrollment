package rs.ac.uns.ftn.eo.StudentEnrollment.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.Exam;
import rs.ac.uns.ftn.eo.StudentEnrollment.repository.ExamRepository;

@Service
public class ExamService {
	
	@Autowired
	private ExamRepository examRepository;
	
	public Exam findOne(Long id) {
		return examRepository.findOne(id);
	}
	
	public List<Exam> findAll() {
		return examRepository.findAll();
	}

	public Exam findBySubjectName(String subjectName) {
		return examRepository.findBySubjectName(subjectName);
	}
	
	public Exam save(Exam exam) {
		return examRepository.save(exam);
	}

	public void remove(Long id) {
		examRepository.delete(id);
	}


}
