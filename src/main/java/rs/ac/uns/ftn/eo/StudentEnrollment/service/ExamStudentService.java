package rs.ac.uns.ftn.eo.StudentEnrollment.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.Exam;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.Student;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.ExamStudent;
import rs.ac.uns.ftn.eo.StudentEnrollment.repository.ExamStudentRepository;

@Service
public class ExamStudentService {
	
	@Autowired
	private ExamStudentRepository examStudentRepository;

	public ExamStudent findOne(Long id) {
		return examStudentRepository.findOne(id);
	}
	
	public List<ExamStudent> findByStudent(Student student) {
		return examStudentRepository.findByStudent(student);
	}

	public List<ExamStudent> findByExamThisYear(Exam exam) {
		int thisYear = Calendar.getInstance().get(Calendar.YEAR);
		List<ExamStudent> listByExam = examStudentRepository.findByExam(exam);
		List<ExamStudent> listByExamThisYear = new ArrayList<ExamStudent>();
		for (ExamStudent examStudent : listByExam){
			if(examStudent.getDate()!=null){
				Calendar cal = Calendar.getInstance();
		    	cal.setTime(examStudent.getDate());
		    	int examYear = cal.get(Calendar.YEAR);
				if(examYear == thisYear){
					listByExamThisYear.add(examStudent);
				}else if(!examStudent.isFinished()){
					listByExamThisYear.add(examStudent);
				}
			}else if(!examStudent.isFinished()){
				listByExamThisYear.add(examStudent);
			}
		}
		return listByExamThisYear;
	}
	
	public ExamStudent findbyStudentAndExam(Student student, Exam exam) {
		return examStudentRepository.findByStudentAndExam(student, exam);
	}
	
	public ExamStudent save(ExamStudent examStudent) {
		return examStudentRepository.save(examStudent);
	}

	public void remove(Long id) {
		examStudentRepository.delete(id);
	}
	
}
