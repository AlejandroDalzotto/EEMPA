package chinchulin.varano.Repositories;

import chinchulin.varano.Models.ExamRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRecordRepo extends JpaRepository<ExamRecord, Long> {

    @Query(value = "SELECT * FROM exam_records", nativeQuery = true)
    List<ExamRecord> getAllByStudent(@Param("student") String student);
}
