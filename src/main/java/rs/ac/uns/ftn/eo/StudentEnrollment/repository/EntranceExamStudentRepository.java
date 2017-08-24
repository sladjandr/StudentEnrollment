package rs.ac.uns.ftn.eo.StudentEnrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.EntranceExamStudent;

@Repository
public interface EntranceExamStudentRepository extends JpaRepository<EntranceExamStudent, Long> {

}
