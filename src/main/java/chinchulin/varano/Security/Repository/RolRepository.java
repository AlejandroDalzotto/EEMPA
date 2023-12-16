package chinchulin.varano.Security.Repository;

import chinchulin.varano.Security.Models.Rol;
import chinchulin.varano.Security.Utils.RolName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol,Long> {

    Optional<Rol> findByRolName(RolName rolName);

}
