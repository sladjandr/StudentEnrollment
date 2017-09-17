package rs.ac.uns.ftn.eo.StudentEnrollment.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
	
	Exam findBySubjectName(String subjectName);
	
}
