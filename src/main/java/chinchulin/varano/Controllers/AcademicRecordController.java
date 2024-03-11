package chinchulin.varano.Controllers;

import chinchulin.varano.Payloads.ApiResponse;
import chinchulin.varano.Payloads.DTO.AcademicRecordDTO;
import chinchulin.varano.Payloads.Request.AcademicRecordRequest;
import chinchulin.varano.Services.AcademicRecord.AcademicRecordService;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/academic-records")
@RestController
@CrossOrigin("localhost:3000")
public class AcademicRecordController {

    private final AcademicRecordService service;

    @Autowired
    public AcademicRecordController(AcademicRecordService service) {
        this.service = service;
    }


    @GetMapping("/all")
    public ApiResponse<List<AcademicRecordDTO>> getAll(
            @RequestParam(value = "query", required = false) @Nullable String query,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit,
            @RequestParam(value = "offset", defaultValue = "0") Integer offset
    ) {
        if (query == null || query.isEmpty()) {
            List<AcademicRecordDTO> allByBlankTerm =  service.getAllByBlankTerm(limit, offset);

            return new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Registros obtenidos con éxito",
                    true,
                    allByBlankTerm
            );
        }
        List<AcademicRecordDTO> allByTerm = service.getAllByTerm(query, limit, offset);

        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Registros obtenidos con éxito",
                true,
                allByTerm
        );
    }

    @GetMapping("/all/pages")
    public Short getTotalPages(
            @RequestParam(name = "query", required = false) @Nullable String query
    ) {
        if (query == null || query.isEmpty()) {
            return service.getTotalPagesByBlankTerm();
        }
        return service.getTotalPagesByTerm(query);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<AcademicRecordDTO> createRecord(@Valid @RequestBody AcademicRecordRequest newEntry) {
        AcademicRecordDTO record = service.create(newEntry);

        return new ApiResponse<AcademicRecordDTO>(
                HttpStatus.CREATED.value(),
                "Registro guardado correctamente",
                true,
                record
        );
    }

    @PutMapping("/edit/{code}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<AcademicRecordDTO> editRecord(
            @PathVariable("code") String code,
            @Valid @RequestBody AcademicRecordRequest newAttributes
    ) {
        AcademicRecordDTO record = service.update(code, newAttributes);

        return new ApiResponse<AcademicRecordDTO>(
                HttpStatus.CREATED.value(),
                "Los cambios se han guardado correctamente",
                true,
                record
        );
    }
}
