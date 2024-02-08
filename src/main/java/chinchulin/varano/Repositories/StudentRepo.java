package chinchulin.varano.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import chinchulin.varano.Models.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {

        @Query(value = "SELECT * FROM student u WHERE u.dni = :dni OR u.mail = :mail OR u.legajo = :legajo", nativeQuery = true)
        Student isRegistered(@Param("dni") Long dni, @Param("mail") String mail, @Param("legajo") Long legajo);

        @Query(value = "SELECT * FROM student u WHERE u.dni = :dni", nativeQuery = true)
        Student getByDNI(@Param("dni") Long dni);

        @Query(value = "SELECT * FROM student u WHERE u.mail = :mail", nativeQuery = true)
        Student getByMail(@Param("mail") String mail);

        @Query(value = "SELECT * FROM student u WHERE u.legajo = :legajo", nativeQuery = true)
        Student getByLegajo(@Param("legajo") Long legajo);

        @Query(value = "SELECT * from student u WHERE u.active = true", nativeQuery = true)
        List<Student> getAllActive();

        @Query(value = "SELECT * from student u WHERE u.id_subject=:subject AND active=true", nativeQuery = true)
        List<Student> getActiveStudentBySubject(@Param("subject") Long id);

        @Query(value = "SELECT * FROM student u WHERE (u.name LIKE %:query% OR u.last_name LIKE %:query% OR u.address LIKE %:query% OR CAST(u.dni AS binary) LIKE %:query% OR CAST(u.legajo AS binary) LIKE %:query%) AND u.active = true LIMIT :limit OFFSET :offset", nativeQuery = true)
        List<Student> getByFilterQuery(@Param("query") String query, @Param("limit") int limit,
                        @Param("offset") int offset);

        @Query(value = "SELECT * FROM student u WHERE u.active = true LIMIT :limit OFFSET :offset", nativeQuery = true)
        List<Student> getByQueryBlank(@Param("limit") int limit,
                                       @Param("offset") int offset);

        @Query(value = "SELECT * FROM student u  WHERE u.active = true LIMIT :limit OFFSET :offset", nativeQuery = true)
        List<Student> getAmountActive(@Param("limit") int limit,
                        @Param("offset") int offset);

        @Query(value = "SELECT COUNT(u.id_student) FROM student u WHERE u.active = true", nativeQuery = true)
        int getTotal();

        @Query(value = "SELECT COUNT(u.id_student) FROM student u WHERE (u.name LIKE %:term% OR u.last_name LIKE %:term% OR u.address LIKE %:term% OR CAST(u.dni AS binary) LIKE %:term% OR CAST(u.legajo AS binary) LIKE %:term%) AND u.active = true", nativeQuery = true)
        int countByTerm(@Param("term") String term);
}
