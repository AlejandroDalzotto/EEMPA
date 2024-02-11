package chinchulin.varano.Controllers;

import java.util.List;

import chinchulin.varano.Payloads.DTO.StudentDTO;
import chinchulin.varano.Payloads.DTO.SubjectDTO;
import chinchulin.varano.Payloads.Request.StudentSubjectRequest;
import chinchulin.varano.Payloads.Request.SubjectRequest;
import chinchulin.varano.Services.Student_subject.Student_subjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chinchulin.varano.Services.Subject.SubjectService;

@RequestMapping("/api/subject")
@RestController
public class SubjectController {

    @Autowired
    SubjectService service;

    @Autowired
    Student_subjectService studentSubjectService;

    @GetMapping("/get/student/{id}")
    public List<StudentDTO> getStudentBySubject(@PathVariable Long id) {
        return service.getStudentBySubject(id);
    }

    @GetMapping("/all")
    public List<SubjectDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/active")
    public List<SubjectDTO> getAllActive() {
        return service.getAllActive();
    }

    @PostMapping("/add")
    public SubjectDTO add(@Valid @RequestBody SubjectRequest subject) {
        return service.add(subject);
    }

    @PutMapping("/add/student")
    public SubjectDTO addStudentToSubject(@Valid @RequestBody StudentSubjectRequest studentSubjectRequest) {
        return studentSubjectService.addStudent(studentSubjectRequest);
    }

    @PutMapping("/toggle/{id}")
    public SubjectDTO inactiveSubject(@PathVariable Long id) {
        return service.inactiveSubject(id);
    }

}
