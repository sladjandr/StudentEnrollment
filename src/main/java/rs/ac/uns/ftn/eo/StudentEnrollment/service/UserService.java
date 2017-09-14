package rs.ac.uns.ftn.eo.StudentEnrollment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.User;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.UserRole;
import rs.ac.uns.ftn.eo.StudentEnrollment.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public List<User> findAllAdmins() {
		return userRepository.findByRole(UserRole.ADMIN);
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public void remove(Long id) {
		userRepository.delete(id);
	}

}
