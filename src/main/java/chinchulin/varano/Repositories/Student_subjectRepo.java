package chinchulin.varano.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import chinchulin.varano.Models.Student_subject;

public interface Student_subjectRepo extends JpaRepository<Student_subject, Long> {

    @Query(value = "SELECT * from student_subjects u WHERE u.id_student = :student AND u.id_subject = :subject", nativeQuery = true)
    Student_subject getOne(@Param("student") Long student, @Param("subject") Long subject);

}
