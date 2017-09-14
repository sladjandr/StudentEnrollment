package rs.ac.uns.ftn.eo.StudentEnrollment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.Exam;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.ExamStudent;
import rs.ac.uns.ftn.eo.StudentEnrollment.repository.ExamStudentRepository;

@Service
public class ExamStudentService {
	
	@Autowired
	private ExamStudentRepository examStudentRepository;

	public ExamStudent findOne(Long id) {
		return examStudentRepository.findOne(id);
	}

	public List<ExamStudent> findByExam(Exam exam) {
		return examStudentRepository.findByExam(exam);
	}

	public ExamStudent save(ExamStudent examStudent) {
		return examStudentRepository.save(examStudent);
	}

	public void remove(Long id) {
		examStudentRepository.delete(id);
	}
	
}
