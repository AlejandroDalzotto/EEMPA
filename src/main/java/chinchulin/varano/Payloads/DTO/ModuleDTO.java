package chinchulin.varano.Payloads.DTO;

import chinchulin.varano.Models.Module;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuleDTO {

    private String name;

    private List<SubjectDTO> subjects;

    public static ModuleDTO fromModule(Module module) {
        return new ModuleDTO(
                module.getName(),
                SubjectDTO.fromListSubject(module.getSubjects())
        );
    }

    public static List<ModuleDTO> fromListModule(List<Module> modules) {
        return modules.stream().map(ModuleDTO::fromModule).toList();
    }
}
