package rs.ac.uns.ftn.eo.StudentEnrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.EntranceExamSubject;

@Repository
public interface EntranceExamSubjectRepository extends JpaRepository<EntranceExamSubject, Long> {

}
