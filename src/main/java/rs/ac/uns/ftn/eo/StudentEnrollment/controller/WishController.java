package rs.ac.uns.ftn.eo.StudentEnrollment.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.Wish;
import rs.ac.uns.ftn.eo.StudentEnrollment.service.WishService;

@RestController
@RequestMapping(value = "api/wish")
public class WishController {
	@Autowired
	private WishService wishService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Wish> getById(@PathVariable Long id) {
		Wish wish = wishService.findOne(id);
		if (wish == null) {
			return new ResponseEntity<Wish>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Wish>(wish, HttpStatus.OK);
	}
	
	//POST
	//Wishes get created when Student associated with them is created.
	
	//PUT
	//Wishes can't be changed. (totalPoints is updated in ExamStudentController)
	
	//DELETE
	//Wishes are deleted only when student associated with them is deleted.
	
}
