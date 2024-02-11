package chinchulin.varano.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import chinchulin.varano.Models.Module;
import org.springframework.data.repository.query.Param;

public interface ModuleRepo extends JpaRepository<Module, Long> {

    @Query(value = "SELECT * from module u WHERE u.active = true", nativeQuery = true)
    List<Module> getAllActive();

    @Query(value = "SELECT * FROM module u WHERE u.name = :name AND u.active = true", nativeQuery = true)
    Module getByName(@Param("name") String name);

}
