package rs.ac.uns.ftn.eo.StudentEnrollment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.Student;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.User;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.UserRole;
import rs.ac.uns.ftn.eo.StudentEnrollment.service.EmailService;
import rs.ac.uns.ftn.eo.StudentEnrollment.service.UserService;

@RestController
@RequestMapping(value = "api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private EmailService emailService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value="/all")
	public ResponseEntity<List<User>> getAll() {
		List<User> users = userService.findAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
	@RequestMapping(method = RequestMethod.GET, value = "/username/{username}")
	public ResponseEntity<User> getByUsername(@PathVariable String username) {
		User user = userService.findByUsername(username);
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	//Student User is created when Student associated with the account is created.
	//Bellow is method for creating Admin User.
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", value = "/admin")
	public ResponseEntity<User> saveAdminUser(@RequestBody User user) {
		if (user.getUsername()==null ||user.getPassword()==null) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}	
		if (userService.findByUsername(user.getUsername())!=null){
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		user.setRole(UserRole.ADMIN);
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(user.getPassword()); 
		user.setPassword(hashedPassword);
		
		user = userService.save(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json", value = "/{id}")
	public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody String newPassword) {
		
		System.out.println(newPassword); //will be removed later
		
		if(newPassword==null){
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		
		User editedUser = userService.findOne(id);
		if (editedUser == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPasswordNew = passwordEncoder.encode(newPassword);
		
		editedUser.setPassword(hashedPasswordNew); 
		
		editedUser = userService.save(editedUser);
		
		//send new credentials to student
		if (editedUser.getRole().equals(UserRole.STUDENT)){
			Student student = editedUser.getStudent();
			String mailText = "Username: " + editedUser.getUsername() + "   Password: " + newPassword;
			emailService.sendMessage(student.getMail(), "New Student Enrollment Credentials - Your password has been changed.", mailText);
		}
		
		return new ResponseEntity<User>(editedUser, HttpStatus.OK);
	}

	
	//Student User gets deleted when student associated with the account is deleted.
	//Bellow is method for deleting Admin User.
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.DELETE, value = "/admin/{id}")
	public ResponseEntity<User> deleteAdminUser(@PathVariable Long id) {
		
		User user = userService.findOne(id);
		if (user == null){
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		if (!user.getRole().equals("ADMIN")){
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
	    userService.remove(id);
		return new ResponseEntity<User>(HttpStatus.OK);
	}
}
