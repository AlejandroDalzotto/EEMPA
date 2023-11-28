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

import chinchulin.varano.Models.Student;
import chinchulin.varano.Models.Subject;
import chinchulin.varano.Services.Subject.SubjectService;

@RequestMapping("/api/subject")
@RestController
public class SubjectController {

    @Autowired
    SubjectService service;

    @GetMapping("/get/student/{id}")
    public List<Student> getStudentBySubject(@PathVariable Long id) {
        return service.getStudentBySubject(id);
    }

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
