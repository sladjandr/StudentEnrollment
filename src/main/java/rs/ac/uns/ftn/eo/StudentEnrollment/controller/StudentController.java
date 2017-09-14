package rs.ac.uns.ftn.eo.StudentEnrollment.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.eo.StudentEnrollment.dto.StudentUserWishesDTO;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.Exam;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.ExamStudent;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.Student;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.User;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.UserRole;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.Wish;
import rs.ac.uns.ftn.eo.StudentEnrollment.service.ExamStudentService;
import rs.ac.uns.ftn.eo.StudentEnrollment.service.StudentService;
import rs.ac.uns.ftn.eo.StudentEnrollment.service.UserService;
import rs.ac.uns.ftn.eo.StudentEnrollment.service.WishService;

@RestController
@RequestMapping(value = "api/student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	@Autowired
	private UserService userService;
	@Autowired
	private WishService wishService;
	@Autowired
	private ExamStudentService examStudentService;
	
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<Student>> getAll() {

		List<Student> students = studentService.findAll();

		return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Student> getOne(@PathVariable Long id) {
		Student student = studentService.findOne(id);
		if (student == null) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
	
	//Creating Student and User, Wishes, ExamStudents associated with it.
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Student> saveStudentUserWishes(@RequestBody StudentUserWishesDTO studentUserWishesDTO){
		if (studentUserWishesDTO.getAddress()==null||studentUserWishesDTO.getMail()==null
				||studentUserWishesDTO.getName()==null||studentUserWishesDTO.getStudyPrograms()==null||studentUserWishesDTO.getSurname()==null
				||studentUserWishesDTO.getUsername()==null) {
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
		user.setRole(UserRole.STUDENT);
		user.setUsername(studentUserWishesDTO.getUsername());
		user.setPassword("pass"); //will probably be replaced with random password maker
		user.setStudent(student);
		user = userService.save(user);
	    
		//creating Wishes
		for (int i=0; i<studentUserWishesDTO.getStudyPrograms().size(); i++){
			Wish wish = new Wish();
			wish.setStudent(student);
			wish.setStudyProgram(studentUserWishesDTO.getStudyPrograms().get(i));
			wish = wishService.save(wish);
		}
	    
	    //creating studentExams
		//hashset is being used for exams to avoid duplicates
		Set<Exam> exams = new HashSet<Exam>();
		for (int i=0; i < studentUserWishesDTO.getStudyPrograms().size(); i++){
			List<Exam> studyProgramExams = studentUserWishesDTO.getStudyPrograms().get(i).getExams();
			for(int j=0; i<studyProgramExams.size(); j++){
				exams.add(studyProgramExams.get(j));
			}
		}
		for (Exam exam : exams){
			ExamStudent examStudent = new ExamStudent();
			examStudent.setPoints(0);
			examStudent.setExam(exam);
			examStudent.setStudent(student);
			examStudent = examStudentService.save(examStudent);
		}
	    
	    //adding User, Wishes and EntranceExamStudents to Student
	    //this is probably not needed...will check it during endpoints testing
	    //student.setUser(user);
	    //student.setWishes(wishes);
	    //student.setEntranceExamStudents(entranceExamStudents);
	    //student = studentService.save(student);
	    
		return new ResponseEntity<Student>(student, HttpStatus.CREATED);	
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Student> editStudent(@PathVariable Long id, @RequestBody Student student) {

		if (student.getAddress()==null || student.getMail()==null ){
			return new ResponseEntity<Student>(HttpStatus.BAD_REQUEST);
		}
		
		Student editedStudent = studentService.findOne(id);
		if (editedStudent == null) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}

		editedStudent.setAddress(student.getAddress());
		editedStudent.setHighSchoolPoints(student.getHighSchoolPoints()); //highschool points should probably be set by admin
		editedStudent.setMail(student.getMail());
		
		editedStudent = studentService.save(editedStudent);

		return new ResponseEntity<Student>(editedStudent, HttpStatus.OK);

	}

	//DELETE
	//Student can't be deleted.
	/*
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
		
		Student student = studentService.findOne(id);
		if (student == null){
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
		
		//Long userId = student.getUser().getId();
		//List<Long> wishIds = 
		
		//set user, wishes and studentExams in student to null
		student.setUser(null);
		student.setWishes(null);
		student.setStudentExams(null);
		
		//remove user, wishes and EntranceExamStudents connected with the student
		userService.remove(userService.findByStudent(student).getId());
		wishService.remove(wishService.findByStudent(student).getId());
		List<EntranceExamStudent> entranceExamStudents = examStudentService.findByStudent(student);
		for(int i=0; i<entranceExamStudents.size();i++){
			examStudentService.remove(entranceExamStudents.get(i).getId());
		}
		
		//remove student
		studentService.remove(id);

		return new ResponseEntity<Student>(HttpStatus.OK);
	}
	*/


	
}
