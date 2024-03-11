package chinchulin.varano.Controllers;

import java.util.List;

import chinchulin.varano.Payloads.ApiResponse;
import chinchulin.varano.Payloads.DTO.StudentDTO;
import chinchulin.varano.Payloads.DTO.SubjectDTO;
import chinchulin.varano.Payloads.Request.StudentRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import chinchulin.varano.Models.Subject;
import chinchulin.varano.Services.Student.StudentService;
import jakarta.annotation.Nullable;

@RequestMapping("/api/student")
@RestController
public class StudentController {
    @Autowired
    StudentService service;

    @GetMapping("/active")
    public ApiResponse<List<StudentDTO>> getAllActive() {
        List<StudentDTO> students = service.getAllActive();

        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Registros obtenidos con éxito",
                true,
                students
        );
    }

    @GetMapping("/all")
    public ApiResponse<List<StudentDTO>> getAll() {
        List<StudentDTO> students = service.getAll();

        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Registros obtenidos con éxito",
                true,
                students
        );
    }

    @GetMapping("get/dni/{dni}")
    public ApiResponse<StudentDTO> getByDNI(@PathVariable("dni") Long dni) {

        StudentDTO student = service.getByDNI(dni);

        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Registro obtenido con éxito",
                true,
                student
        );
    }

    @GetMapping("/get/legajo/{legajo}")
    public ApiResponse<StudentDTO> getByLegajo(@PathVariable("legajo") Long legajo) {

        StudentDTO student = service.getByLegajo(legajo);

        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Registro obtenido con éxito",
                true,
                student
        );
    }

    @GetMapping("/all/filter")
    public ApiResponse<List<StudentDTO>> getByFilter(
            @RequestParam(name = "query", required = false) @Nullable String query,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit,
            @RequestParam(name = "offset", defaultValue = "0") Integer offset
    ) {
        if (query == null || query.isEmpty()) {
            List<StudentDTO> students = service.getByQueryBlank(limit, offset);

            return new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Registros obtenidos con éxito",
                    true,
                    students
            );
        }
        List<StudentDTO> students = service.getByFilterQuery(query, limit, offset);

        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Registros obtenidos con éxito",
                true,
                students
        );
    }

    @GetMapping("/all/pages")
    public Long getTotalPage(
            @RequestParam(name = "query", required = false) @Nullable String query
    ) {
        if (query == null || query.isEmpty()) {
            return service.count();
        }
        return service.countByTerm(query);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<StudentDTO> newStudent(@Valid @RequestBody StudentRequest student) {
        StudentDTO studentDTO = service.newStudent(student);

        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Registro guardado con éxito",
                true,
                studentDTO
        );
    }

    @PutMapping("/edit/{dni}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<StudentDTO> editStudent(@PathVariable("dni") Long dni, @Valid @RequestBody StudentRequest student) {

        StudentDTO studentDTO = service.editStudent(dni, student);

        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Registro guardado con éxito",
                true,
                studentDTO
        );
    }

    @PutMapping("/inactive/{dni}")
    public ApiResponse<StudentDTO> inactiveStudent(@PathVariable("dni") Long dni) {

        StudentDTO student = service.inactiveStudent(dni);

        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Registro deshabilitado con éxito",
                true,
                student
        );
    }
}