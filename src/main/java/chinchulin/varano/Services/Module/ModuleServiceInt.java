package chinchulin.varano.Services.Module;

import chinchulin.varano.Payloads.DTO.ModuleDTO;
import chinchulin.varano.Payloads.Request.ModuleRequest;

import java.util.List;

public interface ModuleServiceInt {

    List<ModuleDTO> getAllModules(Integer limit, Integer offset);

    List<ModuleDTO> getAllByTerm(String term, Integer limit, Integer offset);

    List<ModuleDTO> getAllByCourse(String course_name);

    Long countModules();

    Long countModulesByTerm(String term);

    ModuleDTO saveModule(ModuleRequest newEntry);

    ModuleDTO updateModule(String name, ModuleRequest newAttributes);

    ModuleDTO deleteModule(String name);
}
