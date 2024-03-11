package chinchulin.varano.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import chinchulin.varano.Models.Calification;

public interface CalificationRepo extends JpaRepository<Calification, Long> {

    @Query(value = "SELECT * FROM califications u WHERE u.student_subject = :student_subject", nativeQuery = true)
    List<Calification> getByStudentAndSubject(@Param("student_subject") Long student_subject);

    @Query(value = "SELECT AVG(califications.value) FROM students JOIN califications ON students.id_student = califications.id_student JOIN subjects ON califications.id_subject = subjects.id_subject WHERE students.id_student = :student_id AND subjects.id_course = :course_id", nativeQuery = true)
    Double getAverageOfStudent(@Param("student_id") Long student, @Param("course_id") Long course);
}
