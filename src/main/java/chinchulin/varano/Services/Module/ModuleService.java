package chinchulin.varano.Services.Module;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chinchulin.varano.Exceptions.EntityNotFoundException;
import chinchulin.varano.Models.Module;
import chinchulin.varano.Repositories.ModuleRepo;

@Service
public class ModuleService implements ModuleServiceInt {

    @Autowired
    ModuleRepo repo;

    @Override
    public List<Module> getAll() {
        return repo.findAll();
    }

    @Override
    public List<Module> getAllActive() {
        return repo.getAllActive();
    }

    @Override
    public Module add(Module module) {
        return repo.save(module);
    }

    @Override
    public Module inactiveSubject(Long id) {
     return repo.findById(id)
                .map(module -> {
                    module.setActive(!module.getActive());
                    return repo.save(module);
                })
                .orElseThrow(() -> new EntityNotFoundException("Module not found with ID: " + id));
    }

}
