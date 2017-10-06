package rs.ac.uns.ftn.eo.StudentEnrollment.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.eo.StudentEnrollment.dto.StudentAndWishesDTO;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.Exam;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.ExamStudent;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.StudyProgram;
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
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<Student>> getAll() {

		List<Student> students = studentService.findAll();
		
		System.out.println(students.size());
		if(students.size()>0){
			System.out.println(students.get(0));
		}
		if(students.size()>1){
			System.out.println(students.get(1));
		}

		return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Student> getOne(@PathVariable Long id) {
		Student student = studentService.findOne(id);
		if (student == null) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
	
	//Creating Student and User, Wishes, ExamStudents associated with it.
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Student> saveStudentUserWishes(@RequestBody StudentAndWishesDTO studentAndWishesDTO){
		if (studentAndWishesDTO.getAddress()==null||studentAndWishesDTO.getMail()==null
				||studentAndWishesDTO.getName()==null||studentAndWishesDTO.getStudyPrograms()==null
				||studentAndWishesDTO.getSurname()==null) {
			return new ResponseEntity<Student>(HttpStatus.BAD_REQUEST);
		}
		
		//creating Student
	    Student student = new Student();
	    student.setAddress(studentAndWishesDTO.getAddress());
	    student.setHighSchoolPoints(studentAndWishesDTO.getHighSchoolPoints());
	    student.setMail(studentAndWishesDTO.getMail());
	    student.setName(studentAndWishesDTO.getName());
	    student.setSurname(studentAndWishesDTO.getSurname());
		student = studentService.save(student);
		
		//creating User
		User user = new User();
		user.setRole(UserRole.STUDENT);
		user.setUsername("student" + Long.toString(student.getId()));
		user.setPassword(RandomStringUtils.randomAlphanumeric(10));
		user.setStudent(student);
		user = userService.save(user);
	    
		//creating Wishes
		for (int i=0; i<studentAndWishesDTO.getStudyPrograms().size(); i++){
			Wish wish = new Wish();
			wish.setTotalPoints(studentAndWishesDTO.getHighSchoolPoints());
			wish.setStudent(student);
			wish.setYear(studentAndWishesDTO.getYear());
			wish.setStudyProgram(studentAndWishesDTO.getStudyPrograms().get(i));
			wish = wishService.save(wish);
		}
	    
	    //creating studentExams
		//hashset is being used for exams to avoid duplicates
		List<Exam> exams = new ArrayList<Exam>();
		for (StudyProgram studyProgram : studentAndWishesDTO.getStudyPrograms()){
			List<Exam> studyProgramExams = studyProgram.getExams();
			for(Exam exam : studyProgramExams){
				if(exams.contains(exam)){
					ExamStudent examStudent = examStudentService.findbyStudentAndExam(student, exam);
					List<Wish> wishes = examStudent.getWishes();
					wishes.add(wishService.findByStudentAndStudyProgram(student, studyProgram));
					examStudent.setWishes(wishes);
					examStudent = examStudentService.save(examStudent);
				}
				if(!exams.contains(exam)){
					exams.add(exam);
					
					ExamStudent examStudent = new ExamStudent();
					examStudent.setPoints(0);
					examStudent.setFinished(false);
					examStudent.setExam(exam);
					examStudent.setStudent(student);
					List<Wish> wishes = new ArrayList<Wish>();
					Wish wish = wishService.findByStudentAndStudyProgram(student, studyProgram);
					wishes.add(wish);
					examStudent.setWishes(wishes);
					examStudent = examStudentService.save(examStudent);
				}
			}
		}

	    
		return new ResponseEntity<Student>(student, HttpStatus.CREATED);	
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
		editedStudent.setMail(student.getMail());
		
		editedStudent = studentService.save(editedStudent);

		return new ResponseEntity<Student>(editedStudent, HttpStatus.OK);

	}

	//DELETE
	//Student can't be deleted.

	
}
