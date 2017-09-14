package rs.ac.uns.ftn.eo.StudentEnrollment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.Student;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.Wish;
import rs.ac.uns.ftn.eo.StudentEnrollment.repository.WishesRepository;

@Service
public class WishesService {
	
	@Autowired
	private WishesRepository wishesRepository;
	
	
	public Wish findOne(Long id) {
		return wishesRepository.findOne(id);
	}

	public List<Wish> findAll() {
		return wishesRepository.findAll();
	}
	
	public Wish findByStudent(Student student){
		return wishesRepository.findByStudent(student);
	}

	public Wish save(Wish wishes) {
		return wishesRepository.save(wishes);
	}

	public void remove(Long id) {
		wishesRepository.delete(id);
	}

}
