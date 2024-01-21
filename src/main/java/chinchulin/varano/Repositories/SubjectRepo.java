package chinchulin.varano.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import chinchulin.varano.Models.Subject;

public interface SubjectRepo extends JpaRepository<Subject, Long> {

    @Query(value = "SELECT * from subject u WHERE u.active = true", nativeQuery = true)
    List<Subject> getAllActive();

    @Query(value = "SELECT * from subject u WHERE u.id_module = :module", nativeQuery = true)
    List<Subject> getSubjectsByModule(@Param("module") Long module);

    @Query(value = "SELECT * FROM subject sub JOIN `student_subject` ss ON sub.id_subject = ss.subject_id WHERE ss.student_id = :student AND sub.active = true", nativeQuery = true)
    List<Subject> getActiveSubjectByStudent(@Param("student") Long studentId);
}
