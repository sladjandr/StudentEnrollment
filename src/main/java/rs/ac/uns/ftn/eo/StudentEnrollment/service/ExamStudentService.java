package rs.ac.uns.ftn.eo.StudentEnrollment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.Exam;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.Student;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.StudentExam;
import rs.ac.uns.ftn.eo.StudentEnrollment.repository.ExamStudentRepository;

@Service
public class ExamStudentService {
	
	@Autowired
	private ExamStudentRepository examStudentRepository;

	public StudentExam findOne(Long id) {
		return examStudentRepository.findOne(id);
	}

	public List<StudentExam> findByExam(Exam exam) {
		return examStudentRepository.findByExam(exam);
	}
	
	public StudentExam findbyStudentAndExam(Student student, Exam exam) {
		return examStudentRepository.findByStudentAndExam(student, exam);
	}
	
	public StudentExam save(StudentExam examStudent) {
		return examStudentRepository.save(examStudent);
	}

	public void remove(Long id) {
		examStudentRepository.delete(id);
	}
	
}
