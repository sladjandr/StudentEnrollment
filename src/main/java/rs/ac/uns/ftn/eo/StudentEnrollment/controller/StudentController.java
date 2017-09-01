package rs.ac.uns.ftn.eo.StudentEnrollment.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.eo.StudentEnrollment.dto.StudentUserWishesDTO;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.EntranceExamStudent;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.Student;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.User;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.UserRole;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.Wishes;
import rs.ac.uns.ftn.eo.StudentEnrollment.service.EntranceExamStudentService;
import rs.ac.uns.ftn.eo.StudentEnrollment.service.StudentService;
import rs.ac.uns.ftn.eo.StudentEnrollment.service.UserService;
import rs.ac.uns.ftn.eo.StudentEnrollment.service.WishesService;

@RestController
@RequestMapping(value = "api/student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	@Autowired
	private UserService userService;
	@Autowired
	private WishesService wishesService;
	@Autowired
	private EntranceExamStudentService entranceExamStudentService;
	
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<Student>> getStudents() {

		List<Student> students = studentService.findAll();

		return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable Long id) {
		Student student = studentService.findOne(id);
		if (student == null) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
	
	//Creating Student and User, Wishes, EntranceExamStudents associated with it.
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Student> saveStudentUserWishes(@RequestBody StudentUserWishesDTO studentUserWishesDTO){
		//password might not be needed after implementation of security
		if (studentUserWishesDTO.getAddress()==null || studentUserWishesDTO.getMail()==null 
				|| studentUserWishesDTO.getName()==null || studentUserWishesDTO.getSurname()==null
				|| studentUserWishesDTO.getUsername()==null || studentUserWishesDTO.getPassword()==null 
				|| studentUserWishesDTO.getFirstWish()==null) {
			return new ResponseEntity<Student>(HttpStatus.BAD_REQUEST);
		}
		
		if (userService.findByUsername(studentUserWishesDTO.getUsername()) != null){
			return new ResponseEntity<Student>(HttpStatus.BAD_REQUEST);
		}
		
		//creating Student
	    Student student = new Student();
	    student.setAddress(studentUserWishesDTO.getAddress());
	    student.setHighSchoolPoints(studentUserWishesDTO.getHighSchoolPoints());
	    student.setMail(studentUserWishesDTO.getMail());
	    student.setName(studentUserWishesDTO.getName());
	    student.setSurname(studentUserWishesDTO.getSurname());
		student = studentService.save(student);
		
		//creating User
		User user = new User();
		user.setPassword(studentUserWishesDTO.getPassword());//might not be needed after implementation of security
		user.setRole(UserRole.STUDENT);
		user.setUsername(studentUserWishesDTO.getUsername());
		user.setStudent(student);
		user = userService.save(user);
	    
		//creating Wishes
		Wishes wishes = new Wishes();
		wishes.setFirstWish(studentUserWishesDTO.getFirstWish());
		wishes.setSecondWish(studentUserWishesDTO.getSecondWish());
	    wishes.setThirdWish(studentUserWishesDTO.getThirdWish());
	    wishes.setStudent(student);
	    wishes = wishesService.save(wishes);
	    
	    //creating EntranceExamStudents
	    List<EntranceExamStudent> entranceExamStudents = new ArrayList<EntranceExamStudent>();
	    if (wishes.getFirstWish() != null){	    	
	    	EntranceExamStudent entranceExamStudent1 = new EntranceExamStudent();
	    	entranceExamStudent1.setEntranceExam(wishes.getFirstWish().getEntranceExam());
	    	entranceExamStudent1.setStudent(student);
	    	entranceExamStudent1.setEntranceExam(wishes.getFirstWish().getEntranceExam());
	    	entranceExamStudent1 = entranceExamStudentService.save(entranceExamStudent1);
	    	entranceExamStudents.add(entranceExamStudent1);
	    }
	    if (wishes.getSecondWish() != null){
	    	EntranceExamStudent entranceExamStudent2 = new EntranceExamStudent();
	    	entranceExamStudent2.setEntranceExam(wishes.getSecondWish().getEntranceExam());
	    	entranceExamStudent2.setStudent(student);
	    	entranceExamStudent2.setEntranceExam(wishes.getSecondWish().getEntranceExam());
	    	entranceExamStudent2 = entranceExamStudentService.save(entranceExamStudent2);
	    	entranceExamStudents.add(entranceExamStudent2);
	    }
	    if (wishes.getThirdWish() != null){
	    	EntranceExamStudent entranceExamStudent3 = new EntranceExamStudent();
	    	entranceExamStudent3.setEntranceExam(wishes.getThirdWish().getEntranceExam());
	    	entranceExamStudent3.setStudent(student);
	    	entranceExamStudent3.setEntranceExam(wishes.getThirdWish().getEntranceExam());
	    	entranceExamStudent3 = entranceExamStudentService.save(entranceExamStudent3);
	    	entranceExamStudents.add(entranceExamStudent3);
	    }
	    
	    //adding User, Wishes and EntranceExamStudents to Student
	    //should test if this is even necessary...student is already added once in code above
	    //probably not necessary
	    student.setUser(user);
	    student.setWishes(wishes);
	    student.setEntranceExamStudents(entranceExamStudents);
	    student = studentService.save(student);
	    
		return new ResponseEntity<Student>(student, HttpStatus.CREATED);	
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Student> editStudent(@PathVariable Long id, @RequestBody Student student) {

		if (student.getAddress()==null || student.getMail()==null ){
			return new ResponseEntity<Student>(HttpStatus.BAD_REQUEST);
		}
		
		Student newStudent = studentService.findOne(id);
		if (newStudent == null || newStudent.getId() != id) {
			return new ResponseEntity<Student>(HttpStatus.BAD_REQUEST);
		}

		//wishes, user and EntranceExamStudents can't be changed
		//name and surname can't be changed
		newStudent.setAddress(student.getAddress());
		newStudent.setHighSchoolPoints(student.getHighSchoolPoints()); //highschool points should probably be set by admin
		newStudent.setMail(student.getMail());
		
		newStudent = studentService.save(newStudent);

		return new ResponseEntity<Student>(newStudent, HttpStatus.OK);

	}

	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
		
		Student student = studentService.findOne(id);
		if (student == null){
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
		studentService.remove(id);
		//remove user, wishes and EntranceExamStudents connected with the student
		userService.remove(userService.findByStudent(student).getId());
		wishesService.remove(wishesService.findByStudent(student).getId());
		List<EntranceExamStudent> entranceExamStudents = entranceExamStudentService.findByStudent(student);
		for(int i=0; i<entranceExamStudents.size();i++){
			entranceExamStudentService.remove(entranceExamStudents.get(i).getId());
		}
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}



	
}
