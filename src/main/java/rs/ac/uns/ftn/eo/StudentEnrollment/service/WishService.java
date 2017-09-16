package rs.ac.uns.ftn.eo.StudentEnrollment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.Student;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.StudentExam;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.StudyProgram;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.Wish;
import rs.ac.uns.ftn.eo.StudentEnrollment.repository.WishRepository;

@Service
public class WishService {
	
	@Autowired
	private WishRepository wishRepository;
	
	
	public Wish findOne(Long id) {
		return wishRepository.findOne(id);
	}

	public List<Wish> findAll() {
		return wishRepository.findAll();
	}
	
	public List<Wish> findByStudent(Student student){
		return wishRepository.findByStudent(student);
	}
	
	public Wish findByStudentAndStudyProgram(Student student, StudyProgram studyProgram ){
		return wishRepository.findByStudentAndStudyProgram(student, studyProgram);
	}

	public Wish save(Wish wish) {
		return wishRepository.save(wish);
	}

	public void remove(Long id) {
		wishRepository.delete(id);
	}
	
	public void updateTotalPoints(Wish wish){
		double totalPoints = wish.getStudent().getHighSchoolPoints();
		for (StudentExam studentExam : wish.getStudentExams()){
			totalPoints = totalPoints + studentExam.getPoints();
		}
		wish.setTotalPoints(totalPoints);
		wishRepository.save(wish);
	}

}
