package chinchulin.varano.Repositories;

import chinchulin.varano.Models.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepo extends JpaRepository<Module, Long> {

    @Query(value = "SELECT * FROM modules LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Module> getAll(
            @Param("limit") Integer limit,
            @Param("offset") Integer offset
    );

    @Query(value = "SELECT * FROM modules u WHERE u.name LIKE %:term% LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Module> getAllByTerm(
            @Param("term") String term,
            @Param("limit") Integer limit,
            @Param("offset") Integer offset
    );

    @Query(value = "SELECT * FROM modules u WHERE u.name = :name", nativeQuery = true)
    Module getByName(@Param("name") String name);

    @Query(value = "SELECT COUNT(u.id_module) FROM modules u", nativeQuery = true)
    Long countTotal();

    @Query(value = "SELECT COUNT(u.id_module) FROM modules u WHERE u.name LIKE %:term%", nativeQuery = true)
    Long countByTerm(@Param("term") String term);
}
