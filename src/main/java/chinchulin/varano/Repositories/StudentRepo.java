package chinchulin.varano.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import chinchulin.varano.Models.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {

    @Query(value = "SELECT * FROM student u WHERE u.dni = :dni", nativeQuery = true)
    Student getByDNI(@Param("dni") Long dni);

    @Query(value = "SELECT * FROM student u WHERE u.legajo = :legajo", nativeQuery = true)
    Student getByLegajo(@Param("legajo") Long legajo);

    @Query(value = "SELECT * from student u WHERE u.active = true", nativeQuery = true)
    List<Student> getAllActive();
}
