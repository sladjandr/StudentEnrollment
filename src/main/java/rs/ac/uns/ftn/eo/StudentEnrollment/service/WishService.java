package rs.ac.uns.ftn.eo.StudentEnrollment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.Student;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.Wish;
import rs.ac.uns.ftn.eo.StudentEnrollment.repository.WishRepository;

@Service
public class WishService {
	
	@Autowired
	private WishRepository wishRepository;
	
	
	public Wish findOne(Long id) {
		return wishRepository.findOne(id);
	}

	public List<Wish> findAll() {
		return wishRepository.findAll();
	}
	
	public List<Wish> findByStudent(Student student){
		return wishRepository.findByStudent(student);
	}

	public Wish save(Wish wishes) {
		return wishRepository.save(wishes);
	}

	public void remove(Long id) {
		wishRepository.delete(id);
	}

}
