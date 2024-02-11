package chinchulin.varano.Services.Module;

import java.util.List;

import chinchulin.varano.Payloads.DTO.ModuleDTO;
import chinchulin.varano.Payloads.DTO.SubjectDTO;
import chinchulin.varano.Payloads.Request.ModuleRequest;

public interface ModuleServiceInt {

    List<ModuleDTO> getAll();

    List<SubjectDTO> getSubjectByModule(String name);

    List<ModuleDTO> getAllActive();

    ModuleDTO add(ModuleRequest module);

    ModuleDTO inactiveSubject(String name);

}
