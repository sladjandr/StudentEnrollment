package rs.ac.uns.ftn.eo.StudentEnrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.eo.StudentEnrollment.model.EntranceExam;
import rs.ac.uns.ftn.eo.StudentEnrollment.model.StudyProgram;

@Repository
public interface EntranceExamRepository extends JpaRepository<EntranceExam, Long> {

	EntranceExam findByStudyProgram(StudyProgram studyProgram);
	
}
