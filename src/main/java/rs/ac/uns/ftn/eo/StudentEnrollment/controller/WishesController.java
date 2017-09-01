package rs.ac.uns.ftn.eo.StudentEnrollment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.Student;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.Wishes;
import rs.ac.uns.ftn.eo.StudentEnrollment.service.WishesService;

@RestController
@RequestMapping(value = "api/wishes")
public class WishesController {
	@Autowired
	private WishesService wishesService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Wishes> getById(@PathVariable Long id) {
		Wishes wishes = wishesService.findOne(id);
		if (wishes == null) {
			return new ResponseEntity<Wishes>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Wishes>(wishes, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/student")
	public ResponseEntity<Wishes> getByStudent(@RequestBody Student student) {
		Wishes wishes = wishesService.findByStudent(student);
		if (wishes == null) {
			return new ResponseEntity<Wishes>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Wishes>(wishes, HttpStatus.OK);
	}
	
	//POST
	//Wishes get created when Student associated with them is created, after that it is
	//possible to change them using PUT method.
	
	//PUT
	//Wishes once selected can't be changed.
	
	//DELETE
	//Wishes are deleted only when student associated with them is deleted.
	
}
