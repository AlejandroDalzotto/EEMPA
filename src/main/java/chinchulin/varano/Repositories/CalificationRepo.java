package chinchulin.varano.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import chinchulin.varano.Models.Calification;

public interface CalificationRepo extends JpaRepository<Calification, Long> {

    @Query(value = "SELECT * FROM calification u WHERE u.student_subject = :student-subject", nativeQuery = true)
    List<Calification> getByStudentAndSubject(@Param("student-subject") Long student_subject);
}
