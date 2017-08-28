package rs.ac.uns.ftn.eo.StudentEnrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.Student;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
	
	User findByStudent(Student student);

}
