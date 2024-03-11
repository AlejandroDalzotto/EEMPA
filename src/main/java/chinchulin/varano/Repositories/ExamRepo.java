package chinchulin.varano.Repositories;

import chinchulin.varano.Models.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExamRepo extends JpaRepository<Exam, Long> {

    @Query(value = "SELECT * FROM exams e WHERE e.exam_key = :exam_key", nativeQuery = true)
    Exam getByKey(@Param("exam_key") String key);

    @Query(value = "SELECT * FROM exams e WHERE e.exam_key LIKE %:term% LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Exam> getByFilterTerm(
            @Param("term") String term,
            @Param("limit") Integer limit,
            @Param("offset") Integer offset
    );

    @Query(value = "SELECT * FROM exams LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Exam> getByBlankTerm(
            @Param("limit") Integer limit,
            @Param("offset") Integer offset
    );

    @Query(value = "SELECT * FROM exams e WHERE e.date = :date", nativeQuery = true)
    List<Exam> getByDate(@Param("date") LocalDate date);

    @Query(value = "SELECT * FROM exams e WHERE e.date BETWEEN :start AND :finish", nativeQuery = true)
    List<Exam> getBetweenDates(@Param("start") LocalDate start, @Param("finish") LocalDate finish);

    @Query(value = "SELECT COUNT(e.id_exam) FROM exams e", nativeQuery = true)
    Long countExams();

    @Query(value = "SELECT COUNT(e.id_exam) FROM exams e WHERE e.exam_key LIKE %:term%", nativeQuery = true)
    Long countByTerm(@Param("term") String term);
}
