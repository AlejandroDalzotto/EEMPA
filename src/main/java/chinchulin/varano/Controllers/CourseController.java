package chinchulin.varano.Controllers;

import java.util.List;

import chinchulin.varano.Payloads.ApiResponse;
import chinchulin.varano.Payloads.DTO.CourseDTO;
import chinchulin.varano.Payloads.DTO.SimpleCourseDTO;
import chinchulin.varano.Payloads.DTO.StudentDTO;
import chinchulin.varano.Payloads.DTO.SubjectDTO;
import chinchulin.varano.Payloads.Request.CourseRequest;
import chinchulin.varano.Payloads.Request.PromoteStudentRequest;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import chinchulin.varano.Services.Course.CourseService;

@RequestMapping("/api/course")
@RestController
public class CourseController {

    @Autowired
    CourseService service;

    @GetMapping("/all")
    ApiResponse<List<CourseDTO>> getAll() {
        List<CourseDTO> courses = service.getAll();

        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Registros encontrados con éxito",
                true,
                courses
        );
    }

    @GetMapping("/all-simple")
    ApiResponse<List<SimpleCourseDTO>> getValuesSimple(
            @RequestParam(name = "query", required = false) @Nullable String query,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit,
            @RequestParam(name = "offset", defaultValue = "0") Integer offset
    ) {

        if (query == null || query.isEmpty()) {
            List<SimpleCourseDTO> allSimpleCourse = service.getSimpleValues(limit, offset);

            return new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Registros encontrados con éxito",
                    true,
                    allSimpleCourse
            );
        }

        List<SimpleCourseDTO> allSimpleCourseByTerm = service.getSimpleValuesByTerm(query, limit, offset);

        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Registros encontrados con éxito",
                true,
                allSimpleCourseByTerm
        );
    }

    @GetMapping("/get-simple/{name}")
    ApiResponse<SimpleCourseDTO> getValueSimple(@PathVariable("name") String name) {
        SimpleCourseDTO simpleCourseDTO = service.getSimpleValueByName(name);

        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Registro encontrado con éxito",
                true,
                simpleCourseDTO
        );
    }

    @GetMapping("/get/subject/{name}")
    ApiResponse<List<SubjectDTO>> getSubjectByCourse(@PathVariable("name") String name) {

        // TODO: Validar el parámetro `name`

        List<SubjectDTO> subjects = service.getSubjectByCourse(name);

        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Registros encontrados con éxito",
                true,
                subjects
        );
    }

    @GetMapping("/get/student/{name}")
    ApiResponse<List<StudentDTO>> getStudentByCourse(
            @PathVariable("name") String name,
            @RequestParam(name = "query", required = false) @Nullable String query,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit,
            @RequestParam(name = "offset", defaultValue = "0") Integer offset
    ) {
        if (query == null || query.isEmpty()) {

            List<StudentDTO> resultsByBlank = service.getStudentByCourseQueryBlank(name, limit, offset);

            return new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Registros obtenidos con éxito",
                    true,
                    resultsByBlank
            );
        }

        List<StudentDTO> resultsByTerm = service.getStudentByCourseFiltered(name, query, limit, offset);

        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Registros obtenidos con éxito",
                true,
                resultsByTerm
        );
    }

    @GetMapping("/all/students/pages")
    public Long getTotalPageStudentByCourse(
            @RequestParam(name = "course", required = false) String course,
            @RequestParam(name = "query", required = false) @Nullable String query
    ) {
        if (query == null || query.isEmpty()) {
            return service.countStudents(course);
        }
        return service.countStudentsByTerm(course, query);
    }

    @GetMapping("/all/pages")
    public Long getTotalPages(
            @RequestParam(name = "query", required = false) @Nullable String query
    ) {
        if (query == null || query.isEmpty()) {
            return service.countCourses();
        }
        return service.countCoursesByTerm(query);
    }

    @GetMapping("/active")
    ApiResponse<List<CourseDTO>> getAllActive(
            @RequestParam(name = "limit", defaultValue = "10") Integer limit,
            @RequestParam(name = "offset", defaultValue = "0") Integer offset
    ) {
        List<CourseDTO> courses = service.getAllActive(limit, offset);

        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Registros obtenidos con éxito",
                true,
                courses
        );
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<CourseDTO> add(@Valid @RequestBody CourseRequest course) {
        CourseDTO courseSaved = service.add(course);

        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Registro guardado con éxito",
                true,
                courseSaved
        );
    }

    @PostMapping("/promote-student")
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<StudentDTO> promoteStudent(@Valid @RequestBody PromoteStudentRequest newEntry) {
        StudentDTO student = service.promoteStudent(newEntry);

        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Registro guardado con éxito",
                true,
                student
        );
    }

    @PutMapping("/toggle/{name}")
    ApiResponse<CourseDTO> inactiveSubject(@PathVariable("name") String name) {

        // TODO: Validar el parámetro `name`

        CourseDTO course = service.inactiveSubject(name);

        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Registro deshabilitado con éxito",
                true,
                course
        );
    }
}
