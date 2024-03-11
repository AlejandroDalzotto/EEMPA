package chinchulin.varano.Controllers;

import java.util.List;

import chinchulin.varano.Payloads.ApiResponse;
import chinchulin.varano.Payloads.DTO.StudentDTO;
import chinchulin.varano.Payloads.DTO.SubjectDTO;
import chinchulin.varano.Payloads.Request.StudentSubjectRequest;
import chinchulin.varano.Payloads.Request.SubjectRequest;
import chinchulin.varano.Services.Student_subject.Student_subjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import chinchulin.varano.Services.Subject.SubjectService;

@RequestMapping("/api/subject")
@RestController
public class SubjectController {

    @Autowired
    SubjectService service;

    @Autowired
    Student_subjectService studentSubjectService;

    @GetMapping("/get/student/{id}")
    public ApiResponse<List<StudentDTO>> getStudentBySubject(@PathVariable Long id) {
        List<StudentDTO> students = service.getStudentBySubject(id);

        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Registros obtenidos con éxito",
                true,
                students
        );
    }

    @GetMapping("/all")
    public ApiResponse<List<SubjectDTO>> getAll() {
        List<SubjectDTO> subjects = service.getAll();

        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Registros obtenidos con éxito",
                true,
                subjects
        );
    }

    @GetMapping("/active")
    public ApiResponse<List<SubjectDTO>> getAllActive() {
        List<SubjectDTO> subjects = service.getAllActive();

        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Registros obtenidos con éxito",
                true,
                subjects
        );
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<SubjectDTO> add(@Valid @RequestBody SubjectRequest subject) {
        SubjectDTO savedSubject = service.add(subject);

        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Registro guardado con éxito",
                true,
                savedSubject
        );
    }

    @PutMapping("/add/student")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<SubjectDTO> addStudentToSubject(@Valid @RequestBody StudentSubjectRequest studentSubjectRequest) {
        SubjectDTO subject = studentSubjectService.addStudent(studentSubjectRequest);

        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Registro guardado con éxito",
                true,
                subject
        );
    }

    @PutMapping("/toggle/{id}")
    public ApiResponse<SubjectDTO> inactiveSubject(@PathVariable Long id) {
        SubjectDTO subject = service.inactiveSubject(id);

        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Registro deshabilitado con éxito",
                true,
                subject
        );
    }

}
