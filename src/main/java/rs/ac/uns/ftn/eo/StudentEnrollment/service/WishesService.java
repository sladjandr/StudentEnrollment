package rs.ac.uns.ftn.eo.StudentEnrollment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.Student;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.Wishes;
import rs.ac.uns.ftn.eo.StudentEnrollment.repository.WishesRepository;

@Service
public class WishesService {
	
	@Autowired
	private WishesRepository wishesRepository;
	
	
	public Wishes findOne(Long id) {
		return wishesRepository.findOne(id);
	}

	public List<Wishes> findAll() {
		return wishesRepository.findAll();
	}
	
	public Wishes findByStudent(Student student){
		return wishesRepository.findByStudent(student);
	}

	public Wishes save(Wishes wishes) {
		return wishesRepository.save(wishes);
	}

	public void remove(Long id) {
		wishesRepository.delete(id);
	}

}
