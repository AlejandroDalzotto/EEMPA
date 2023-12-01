package chinchulin.varano.Security.Repository;

import chinchulin.varano.Security.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String nombreUsuario);

    boolean existsByUsername(String nombreUsuario);

    boolean existsByEmail(String email);

}
