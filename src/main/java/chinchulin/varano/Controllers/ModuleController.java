package chinchulin.varano.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chinchulin.varano.Models.Module;
import chinchulin.varano.Services.Module.ModuleService;

@RequestMapping("/api/module")
@RestController
public class ModuleController {

    @Autowired
    ModuleService service;

    @GetMapping("/all")
    List<Module> getAll() {
        return service.getAll();
    }

    @GetMapping("/active")
    List<Module> getAllActive() {
        return service.getAllActive();
    }

    @PostMapping("/add")
    Module add(@RequestBody Module module) {
        return service.add(module);
    }

    @PutMapping("/toggle/{id}")
    Module inactiveSubject(@PathVariable Long id) {
        return service.inactiveSubject(id);
    }
}
