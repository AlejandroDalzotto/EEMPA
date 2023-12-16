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

    @Query(value = "SELECT* from student u WHERE u.id_subject=:subject", nativeQuery = true)
    List<Student> getStudentBySubject(@Param("subject") Long id);

    @Query(value = "SELECT * FROM student u WHERE u.name ILIKE %:query% OR u.last_name ILIKE %:query% OR u.address ILIKE %:query% OR CAST(u.dni AS text) ILIKE %:query% OR CAST(u.legajo AS text) ILIKE %:query% AND u.active = true LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Student> getByFilterQuery(@Param("query") String query, @Param("limit") int limit,
            @Param("offset") int offset);

}
