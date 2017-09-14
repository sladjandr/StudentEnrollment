package rs.ac.uns.ftn.eo.StudentEnrollment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.User;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
	List<User> findByRole(UserRole role);

}
