package chinchulin.varano.Repositories;

import chinchulin.varano.Models.AcademicRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademicRecordRepo extends JpaRepository<AcademicRecord, Long> {

    @Query(value = "SELECT * FROM academic_records u WHERE u.unique_record_code = :code", nativeQuery = true)
    AcademicRecord getOneByCode(@Param("code") String code);

    @Query(value = "SELECT * FROM academic_records u WHERE u.id_course = :course AND u.id_student = :student", nativeQuery = true)
    AcademicRecord getOneByCourseAndStudent(
            @Param("course") Long course,
            @Param("student") Long student
    );

    @Query(value = "SELECT ar.* FROM academic_records ar JOIN students s ON ar.id_student = s.id_student WHERE (s.name LIKE %:term% OR s.last_name LIKE %:term%) AND s.active = true LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<AcademicRecord> getAllByTerm(
            @Param("term") String term,
            @Param("limit") Integer limit,
            @Param("offset") Integer offset
    );

    @Query(value = "SELECT * FROM academic_records u LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<AcademicRecord> getAllByBlankTerm(
            @Param("limit") Integer limit,
            @Param("offset") Integer offset
    );

    @Query(value = "SELECT COUNT(ar.id_academic_record) FROM academic_records ar JOIN students s ON ar.id_student = s.id_student WHERE (s.name LIKE %:term% OR s.last_name LIKE %:term%) AND s.active = true", nativeQuery = true)
    int countByTerm(@Param("term") String term);

    @Query(value = "SELECT COUNT(ar.id_academic_record) FROM academic_records ar JOIN students s ON ar.id_student = s.id_student WHERE s.active = true", nativeQuery = true)
    int countTotal();

    @Query(value = "SELECT COUNT(u.id_academic_record) FROM academic_records u WHERE u.id_student = :student AND u.id_course = :course AND u.state = 'GRADUATED'", nativeQuery = true)
    Long courseIsCompleted(@Param("student") Long student_id, @Param("course") Long course_id);
}
