package rs.ac.uns.ftn.eo.StudentEnrollment.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.Student;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.Wish;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {

	List<Wish> findByStudent(Student student);
	
}
