package chinchulin.varano.Controllers;

import java.util.List;
import chinchulin.varano.Payloads.DTO.StudentDTO;
import chinchulin.varano.Payloads.DTO.SubjectDTO;
import chinchulin.varano.Payloads.Request.StudentRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import chinchulin.varano.Models.Subject;
import chinchulin.varano.Services.Student.StudentService;
import jakarta.annotation.Nullable;

@RequestMapping("/api/student")
@RestController
public class StudentController {
    @Autowired
    StudentService service;

    @GetMapping("/active")
    public List<StudentDTO> getAllActive() {
        return service.getAllActive();
    }

    @GetMapping("/all")
    public List<StudentDTO> getAll() {
        return service.getAll();
    }

    // TODO: Este método puede quedar a consideración de removerse
    //  (El Front-end no va a contar con los IDs de los alumnos para enviarlos en los url y/o request).
    @GetMapping("/get/{id}")
    public StudentDTO getById(@PathVariable Long id) {

        // TODO: Validar el parámetro `id`.

        return service.getById(id);
    }

    // TODO: Este método puede quedar a consideración de removerse
    //  (El Front-end no va a contar con los IDs de las materias para enviarlos en los url y/o request).
    @GetMapping("/get/subject/{id}")
    public List<SubjectDTO> getSubjectByStudent(@PathVariable Long id) {
        return service.getSubjectByStudent(id);
    }

    @GetMapping("get/dni/{dni}")
    public StudentDTO getByDNI(@PathVariable Long dni) {

        // TODO: Validar el `dni` (null y mayor a 10M).

        return service.getByDNI(dni);
    }

    @GetMapping("/get/legajo/{legajo}")
    public StudentDTO getByLegajo(Long legajo) {

        // TODO: Validar el legajo

        return service.getByLegajo(legajo);
    }

    @GetMapping("/all/filter")
    public List<StudentDTO> getByFilter(
            @RequestParam(name = "query", required = false) @Nullable String query,
            @RequestParam(name = "limit") Integer limit,
            @RequestParam(name = "offset") Integer offset
    ) {
        // TODO: Acá se valida la query pero no los demás (Se pueden especificar valores por defecto en @RequestParam).
        if (query == null || query.isEmpty()) {
            return service.getByQueryBlank(limit, offset);
        }
        return service.getByFilterQuery(query, limit, offset);
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

    // TODO: Retornar un HttpStatus.CREATED si se logra crear una entidad.
    @PostMapping("/add")
    public StudentDTO newStudent(@Valid @RequestBody StudentRequest student) {
        return service.newStudent(student);
    }

    @PutMapping("/edit/{dni}")
    public StudentDTO editStudent(@PathVariable("dni") Long dni, @Valid @RequestBody StudentRequest student) {

        // TODO: Validar `dni` (null y mayor a 10M).

        return service.editStudent(dni, student);
    }

    @PutMapping("/inactive/{dni}")
    public StudentDTO inactiveStudent(@PathVariable("dni") Long dni) {

        // TODO: Validar `dni` (null y mayor a 10M).

        return service.inactiveStudent(dni);
    }
}
