package rs.ac.uns.ftn.eo.StudentEnrollment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.Exam;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.Student;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.StudentExam;

@Repository
public interface ExamStudentRepository extends JpaRepository<StudentExam, Long> {
	
	List<StudentExam> findByExam(Exam exam);
	StudentExam findByStudentAndExam(Student student, Exam exam);

}
