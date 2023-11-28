package chinchulin.varano.Services.Module;

import java.util.List;

import chinchulin.varano.Models.Module;
import chinchulin.varano.Models.Subject;

public interface ModuleServiceInt {

    List<Module> getAll();

    List<Subject> getSubjectByModule(Long id);

    List<Module> getAllActive();

    Module add(Module module);

    Module inactiveSubject(Long id);

}
