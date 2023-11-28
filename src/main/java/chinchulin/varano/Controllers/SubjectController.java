package chinchulin.varano.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import chinchulin.varano.Models.Subject;
import chinchulin.varano.Services.Subject.SubjectService;

@RequestMapping("/api/subject")
public class SubjectController {

    @Autowired
    SubjectService service;

    @GetMapping("/all")
    public List<Subject> getAll() {
        return service.getAll();
    }

    @GetMapping("/active")
    public List<Subject> getAllActive() {
        return service.getAllActive();
    }

    @PostMapping("/add")
    public Subject add(@RequestBody Subject newSubject) {
        return service.add(newSubject);
    }

    @PutMapping("/toggle/{id}")
    public Subject inactiveSubject(@PathVariable Long id) {
        return service.inactiveSubject(id);
    }

}
