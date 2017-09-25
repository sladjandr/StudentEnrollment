package rs.ac.uns.ftn.eo.StudentEnrollment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.Exam;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.Student;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.ExamStudent;

@Repository
public interface ExamStudentRepository extends JpaRepository<ExamStudent, Long> {
	
	List<ExamStudent> findByStudent(Student student);
	List<ExamStudent> findByExamAndYear(Exam exam, int year);
	ExamStudent findByStudentAndExam(Student student, Exam exam);

}
