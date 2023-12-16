package chinchulin.varano.Security.Service;

import chinchulin.varano.Security.Models.Rol;
import chinchulin.varano.Security.Repository.RolRepository;
import chinchulin.varano.Security.Utils.RolName;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolName(RolName rolName) {
        return rolRepository.findByRolName(rolName);
    }

    public void save(Rol rol) {
        rolRepository.save(rol);
    }
}
