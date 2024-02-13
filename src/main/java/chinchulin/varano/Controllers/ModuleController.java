package chinchulin.varano.Controllers;

import java.util.List;

import chinchulin.varano.Payloads.DTO.ModuleDTO;
import chinchulin.varano.Payloads.DTO.SubjectDTO;
import chinchulin.varano.Payloads.Request.ModuleRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chinchulin.varano.Services.Module.ModuleService;

@RequestMapping("/api/module")
@RestController
public class ModuleController {

    @Autowired
    ModuleService service;

    @GetMapping("/all")
    List<ModuleDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/get/subject/{name}")
    List<SubjectDTO> getSubjectByModule(@PathVariable("name") String name) {

        // TODO: Validar el parámetro `name`

        return service.getSubjectByModule(name);
    }

    @GetMapping("/active")
    List<ModuleDTO> getAllActive() {
        return service.getAllActive();
    }

    @PostMapping("/add")
    ModuleDTO add(@Valid @RequestBody ModuleRequest module) {

        // TODO: Especificar el status de retorno en HttpStatus.CREATED
        return service.add(module);
    }

    @PutMapping("/toggle/{name}")
    ModuleDTO inactiveSubject(@PathVariable("name") String name) {

        // TODO: Validar el parámetro `name`

        return service.inactiveSubject(name);
    }
}
