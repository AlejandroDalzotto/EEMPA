package chinchulin.varano.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import chinchulin.varano.Models.Module;

public interface ModuleRepo extends JpaRepository<Module, Long> {

    @Query(value = "SELECT * from module u WHERE u.active = true", nativeQuery = true)
    List<Module> getAllActive();

}
