package chinchulin.varano.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import chinchulin.varano.Models.Calification;
import chinchulin.varano.Models.Student;

public interface CalificationRepo extends JpaRepository<Calification, Long> {

    @Query(value = "SELECT * FROM calification u WHERE u.student-subject = :student-subject", nativeQuery = true)
    Student getByDNI(@Param("student-subject") Long student_subject);
}
