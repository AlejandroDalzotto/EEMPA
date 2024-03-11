package chinchulin.varano.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import chinchulin.varano.Models.Course;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {

    @Query(value = "SELECT * from courses u WHERE u.active = true LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Course> getAllActive(@Param("limit") Integer limit, @Param("offset") Integer offset);

    @Query(value = "SELECT * FROM courses u WHERE u.name LIKE %:term% AND u.active = true LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Course> getAllActiveByTerm(@Param("term") String term, @Param("limit") Integer limit, @Param("offset") Integer offset);

    @Query(value = "SELECT * FROM courses u WHERE u.name = :name AND u.active = true", nativeQuery = true)
    Course getByName(@Param("name") String name);

    @Query(value = "SELECT COUNT(u.id_course) FROM courses u WHERE u.active = true", nativeQuery = true)
    Long countActive();

    @Query(value = "SELECT COUNT(u.id_course) FROM courses u WHERE u.name LIKE %:term% AND u.active = true", nativeQuery = true)
    Long countActiveByTerm(@Param("term") String term);
}
