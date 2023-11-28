package chinchulin.varano.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import chinchulin.varano.Models.Subject;

public interface SubjectRepo extends JpaRepository<Subject, Long> {

    @Query(value = "SELECT * from subject u WHERE u.active = true", nativeQuery = true)
    List<Subject> getAllActive();
}
