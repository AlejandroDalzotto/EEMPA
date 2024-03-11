package chinchulin.varano.Services.Module;

import chinchulin.varano.Exceptions.EntityNotFoundException;
import chinchulin.varano.Exceptions.EntityRepeatedException;
import chinchulin.varano.Models.Course;
import chinchulin.varano.Models.Module;
import chinchulin.varano.Payloads.DTO.ModuleDTO;
import chinchulin.varano.Payloads.Request.ModuleRequest;
import chinchulin.varano.Repositories.CourseRepo;
import chinchulin.varano.Repositories.ModuleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ModuleService implements ModuleServiceInt {

    @Autowired
    private ModuleRepo repo;

    @Autowired
    private CourseRepo courseRepo;

    @Override
    public List<ModuleDTO> getAllModules(Integer limit, Integer offset) {
        List<Module> modules = repo.getAll(limit, offset);

        return ModuleDTO.fromListModule(modules);
    }

    @Override
    public List<ModuleDTO> getAllByTerm(String term, Integer limit, Integer offset) {
        List<Module> modules = repo.getAllByTerm(term, limit, offset);

        return ModuleDTO.fromListModule(modules);
    }

    @Override
    public List<ModuleDTO> getAllByCourse(String course_name) {
        Course course = courseRepo.getByName(course_name);

        if (course == null) {
            throw new EntityNotFoundException("El curso " + course_name + " no existe en la base de datos.");
        }

        List<Module> modules = course.getModules();
        return ModuleDTO.fromListModule(modules);
    }

    @Override
    public Long countModules() {

        return repo.countTotal();
    }

    @Override
    public Long countModulesByTerm(String term) {
        return repo.countByTerm(term);
    }

    @Override
    public ModuleDTO saveModule(ModuleRequest newEntry) {

        Module existByName = repo.getByName(newEntry.getName());

        if (existByName != null) {
            throw new EntityRepeatedException("El modulo " + newEntry.getName() + " ya se encuentra registrado");
        }

        Course course = courseRepo.getByName(newEntry.getCourse_name());

        if (course == null) {
            throw new EntityNotFoundException("No existe el curso " + newEntry.getCourse_name() + " en la base de datos.");
        }

        Module module = new Module();

        module.setName(newEntry.getName());
        module.setExams(Collections.emptyList());
        module.setCourse(course);

        repo.save(module);

        return ModuleDTO.fromModule(module);
    }

    @Override
    public ModuleDTO updateModule(String name, ModuleRequest newAttributes) {

        Module moduleFromDb = repo.getByName(name);

        if (moduleFromDb == null) {
            throw new EntityNotFoundException("El modulo " + name + " no se encuentra en la base de datos.");
        }

        Module hasRepeatedName = repo.getByName(newAttributes.getName());

        if (hasRepeatedName != null && !newAttributes.getName().equals(name)) {
            throw new EntityRepeatedException("El modulo " + newAttributes.getName() + " ya se encuentra registrado.");
        }

        Course course = courseRepo.getByName(newAttributes.getCourse_name());

        if (course == null) {
            throw new EntityNotFoundException("El curso " + newAttributes.getCourse_name() + " no se encuentra en la base de datos.");
        }

        moduleFromDb.setName(newAttributes.getName());
        moduleFromDb.setCourse(course);

        repo.save(moduleFromDb);

        return ModuleDTO.fromModule(moduleFromDb);
    }

    @Override
    public ModuleDTO deleteModule(String name) {

        Module module = repo.getByName(name);

        if (module == null) {
            throw new EntityNotFoundException("El modulo " + name + " no se encuentra en la base de datos.");
        }

        repo.delete(module);
        return ModuleDTO.fromModule(module);
    }
}
