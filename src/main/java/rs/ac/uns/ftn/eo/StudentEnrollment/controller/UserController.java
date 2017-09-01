package rs.ac.uns.ftn.eo.StudentEnrollment.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.User;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.UserRole;
import rs.ac.uns.ftn.eo.StudentEnrollment.service.UserService;

@RestController
@RequestMapping(value = "api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/principal")
	public Principal user(Principal principal) {
		return principal;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAll() {
		List<User> users = userService.findAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{username}")
	public ResponseEntity<User> getByUsername(@PathVariable String username) {
		User user = userService.findByUsername(username);
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	//Student User is created when Student associated with the account is created.
	//This may be changed when security gets implemented.
	//Bellow is method for creating Admin User.
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", value = "/admin")
	public ResponseEntity<User> saveAdminUser(@RequestBody User user) {
		//password may be removed when security gets implemented
		if (user.getUsername()==null ||user.getPassword()==null) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}	
		user.setRole(UserRole.ADMIN);
		user = userService.save(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json", value = "/{id}")
	public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody User user) {
		
		//password might be removed after security is implemented
		if(user.getPassword()==null){
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		
		User newUser = userService.findOne(id);
		if (newUser == null || newUser.getId() != id) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		// Username can't be changed because it's unique value. Student can't be changed either.
		// role change or student change is also not allowed
		newUser.setPassword(user.getPassword()); //Password will probably be present only in principal, and deleted from here.
		
		newUser = userService.save(newUser);
		return new ResponseEntity<User>(newUser, HttpStatus.OK);
	}

	
	//Student User gets deleted when student associated with the account is deleted.
	//Bellow is method for deleting Admin User.
	@RequestMapping(method = RequestMethod.DELETE, value = "/admin/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Long id) {
		
		User user = userService.findOne(id);
		if (user == null){
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		if (user.getRole() != UserRole.ADMIN){
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
	    userService.remove(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
