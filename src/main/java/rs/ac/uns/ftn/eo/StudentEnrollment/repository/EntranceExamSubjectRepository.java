package rs.ac.uns.ftn.eo.StudentEnrollment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.EntranceExam;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.EntranceExamSubject;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.Subject;

@Repository
public interface EntranceExamSubjectRepository extends JpaRepository<EntranceExamSubject, Long> {

	List<EntranceExamSubject> findByEntranceExam(EntranceExam entranceExam);
	List<EntranceExamSubject> findBySubject(Subject subject);
	
}
