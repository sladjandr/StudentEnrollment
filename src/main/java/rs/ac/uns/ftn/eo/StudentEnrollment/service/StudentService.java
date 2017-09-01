package rs.ac.uns.ftn.eo.StudentEnrollment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.EntranceExamStudent;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.Student;
import rs.ac.uns.ftn.eo.StudentEnrollment.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	public Student findOne(Long id) {
		return studentRepository.findOne(id);
	}

	public List<Student> findAll() {
		return studentRepository.findAll();
	}
	
	public Student findByEntranceExamStudent(EntranceExamStudent entranceExamStudent){
		return studentRepository.findByEntranceExamStudent(entranceExamStudent);
	}

	public Student save(Student student) {
		return studentRepository.save(student);
	}

	public void remove(Long id) {
		studentRepository.delete(id);
	}

}
