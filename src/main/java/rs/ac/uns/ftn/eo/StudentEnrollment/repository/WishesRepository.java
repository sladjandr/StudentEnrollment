package rs.ac.uns.ftn.eo.StudentEnrollment.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.Student;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.Wish;

@Repository
public interface WishesRepository extends JpaRepository<Wish, Long> {

	Wish findByStudent(Student student);
	
}
