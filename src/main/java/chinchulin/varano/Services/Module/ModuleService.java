package chinchulin.varano.Services.Module;

import java.util.Collections;
import java.util.List;

import chinchulin.varano.Exceptions.EntityRepeatedException;
import chinchulin.varano.Models.Subject;
import chinchulin.varano.Payloads.DTO.ModuleDTO;
import chinchulin.varano.Payloads.DTO.SubjectDTO;
import chinchulin.varano.Payloads.Request.ModuleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chinchulin.varano.Exceptions.EntityNotFoundException;
import chinchulin.varano.Models.Module;
import chinchulin.varano.Repositories.ModuleRepo;
import chinchulin.varano.Repositories.SubjectRepo;

@Service
public class ModuleService implements ModuleServiceInt {

    @Autowired
    ModuleRepo repo;

    @Autowired
    SubjectRepo subjectRepo;

    @Override
    public List<ModuleDTO> getAll() {
        List<Module> modules = repo.findAll();
        return ModuleDTO.fromListModule(modules);
    }

    @Override
    public List<ModuleDTO> getAllActive() {
        List<Module> modules = repo.getAllActive();
        return ModuleDTO.fromListModule(modules);
    }

    @Override
    public ModuleDTO add(ModuleRequest module) {
        Module isRegistered = repo.getByName(module.getName());

        if (isRegistered != null) {
            throw new EntityRepeatedException("El modulo " + module.getName() + " ya existe en la base de datos");
        }

        Module moduleToSave = new Module();
        moduleToSave.setName(module.getName());
        moduleToSave.setActive(true);
        moduleToSave.setSubjects(Collections.emptyList());
        repo.save(moduleToSave);
        return ModuleDTO.fromModule(moduleToSave);
    }

    @Override
    public ModuleDTO inactiveSubject(String name) {
        Module module = repo.getByName(name);

        if (module == null) {
            throw new EntityNotFoundException("El modulo " + name + " no se ha encontrado.");
        }

        module.setActive(false);
        repo.save(module);
        return ModuleDTO.fromModule(module);
    }

    @Override
    public List<SubjectDTO> getSubjectByModule(String name) {

        Module module = repo.getByName(name);

        if (module == null) {
            throw new EntityNotFoundException("No se ha encontrado el modulo " + name);
        }

        List<Subject> subject = subjectRepo.getSubjectsByModule(module.getId_module());
        return SubjectDTO.fromListSubject(subject);
    }

}
