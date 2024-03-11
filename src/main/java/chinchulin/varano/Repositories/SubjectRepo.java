package chinchulin.varano.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import chinchulin.varano.Models.Subject;

public interface SubjectRepo extends JpaRepository<Subject, Long> {

    @Query(value = "SELECT * from subjects u WHERE u.active = true", nativeQuery = true)
    List<Subject> getAllActive();

    @Query(value = "SELECT * FROM subjects u WHERE u.name = :name", nativeQuery = true)
    Subject getByName(@Param("name") String name);

    @Query(value = "SELECT * from subjects u WHERE u.id_course = :id", nativeQuery = true)
    List<Subject> getSubjectsByCourse(@Param("id") Long id);

    @Query(value = "SELECT * FROM subjects sub JOIN `student_subjects` ss ON sub.id_subject = ss.id_subject WHERE ss.id_student = :student AND sub.active = true", nativeQuery = true)
    List<Subject> getActiveSubjectByStudent(@Param("student") Long studentId);
}
