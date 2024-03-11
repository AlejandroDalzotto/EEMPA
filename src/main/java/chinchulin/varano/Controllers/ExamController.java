package chinchulin.varano.Controllers;

import chinchulin.varano.Payloads.ApiResponse;
import chinchulin.varano.Payloads.DTO.ExamDTO;
import chinchulin.varano.Payloads.DTO.ExamRecordDTO;
import chinchulin.varano.Payloads.DTO.ExamWithRecordsDTO;
import chinchulin.varano.Payloads.Request.ExamRecordRequest;
import chinchulin.varano.Payloads.Request.ExamRequest;
import chinchulin.varano.Services.Exam.ExamService;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    @Autowired
    private ExamService service;

    @GetMapping("/all")
    public ApiResponse<List<ExamDTO>> getAllExams() {
        List<ExamDTO> exams = service.getAllExams();

        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Registro obtenidos con éxito",
                true,
                exams
        );
    }

    @GetMapping("/get/{key}")
    public ApiResponse<ExamDTO> getExamByKey(@PathVariable("key") String key) {
        ExamDTO exam = service.getExamByKey(key);

        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Registro obtenido con éxito",
                true,
                exam
        );
    }

    @GetMapping("/get-records/{key}")
    public ApiResponse<List<ExamRecordDTO>> getExamByKeyWithRecords(
            @PathVariable("key") String key,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit,
            @RequestParam(name = "offset", defaultValue = "0") Integer offset
    ) {
        List<ExamRecordDTO> records = service.getRecordsByKey(key, limit, offset);
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Registro obtenido con éxito",
                true,
                records
        );
    }

    @GetMapping("/all-by-filter")
    public ApiResponse<List<ExamDTO>> getFilteredByTerm(
            @RequestParam(name = "term", required = false) @Nullable String term,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit,
            @RequestParam(name = "offset", defaultValue = "0") Integer offset
    ) {
        if (term == null || term.isEmpty()) {
            List<ExamDTO> exams = service.getByBlankTerm(limit, offset);

            return new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Registros obtenidos con éxito",
                    true,
                    exams
            );
        }

        List<ExamDTO> exams = service.getFilteredByTerm(term, limit, offset);

        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Registros obtenidos con éxito",
                true,
                exams
        );
    }

    @GetMapping("/all-by-date")
    public ApiResponse<List<ExamDTO>> getAllExamsByDate(@RequestParam("date") LocalDate date) {
        List<ExamDTO> exams = service.getAllExamsByDate(date);

        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Registros obtenidos con éxito",
                true,
                exams
        );
    }

    @GetMapping("/all/pages")
    public Long getTotalPages(
            @RequestParam(name = "query", required = false) @Nullable String query
    ) {
        if (query == null || query.isEmpty()) {
            return service.countExams();
        }
        return service.countExamsByTerm(query);
    }

    @GetMapping("/all-between-dates")
    public ApiResponse<List<ExamDTO>> getAllExamsBetweenTwoDates(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("finish") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate finish
    ) {
        List<ExamDTO> exams = service.getAllExamsBetweenTwoDates(start, finish);

        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Registros obtenidos con éxito",
                true,
                exams
        );
    }

    @PostMapping("/save-exam")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ExamDTO> saveExam(@RequestBody @Valid ExamRequest exam) {
        ExamDTO savedExam = service.saveExam(exam);

        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Registro guardado con éxito",
                true,
                savedExam
        );
    }

    @PostMapping("/register-student")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ExamRecordDTO> registerStudentToExam(@RequestBody @Valid ExamRecordRequest newEntry) {
        ExamRecordDTO record = service.registerStudent(newEntry);

        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Registro guardado con éxito",
                true,
                record
        );
    }

    @PutMapping("/update-exam/{key}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ExamDTO> updateExam(@PathVariable("key") String key, @RequestBody @Valid ExamRequest newAttributes) {
        ExamDTO updatedExam = service.updateExam(key, newAttributes);

        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Registro guardado con éxito",
                true,
                updatedExam
        );
    }

    @DeleteMapping("/delete/{key}")
    public ApiResponse<ExamDTO> deleteExam(@PathVariable("key") String key) {
        ExamDTO exam = service.deleteExamByKey(key);

        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Registro deshabilitado con éxito",
                true,
                exam
        );
    }
}
