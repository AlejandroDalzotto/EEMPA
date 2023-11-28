package chinchulin.varano.Services.Module;

import java.util.List;

import chinchulin.varano.Models.Module;

public interface ModuleServiceInt {

    List<Module> getAll();

    List<Module> getAllActive();

    Module add(Module module);

    Module inactiveSubject(Long id);

}
