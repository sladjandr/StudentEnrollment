package rs.ac.uns.ftn.eo.StudentEnrollment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.Exam;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.ExamStudent;

@Repository
public interface ExamStudentRepository extends JpaRepository<ExamStudent, Long> {
	
	List<ExamStudent> findByExam(Exam exam);

}
