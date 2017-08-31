package rs.ac.uns.ftn.eo.StudentEnrollment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.EntranceExamStudent;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.Student;

@Repository
public interface EntranceExamStudentRepository extends JpaRepository<EntranceExamStudent, Long> {
	List<EntranceExamStudent> findByStudent(Student student);
}
