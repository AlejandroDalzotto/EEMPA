package chinchulin.varano.Controllers;

import chinchulin.varano.Payloads.ApiResponse;
import chinchulin.varano.Payloads.DTO.CalificationDTO;
import chinchulin.varano.Payloads.Request.CalificationRequest;
import chinchulin.varano.Services.Calification.CalificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/calification")
@RestController
@CrossOrigin("localhost:3000")
public class CalificationController {

    @Autowired
    CalificationService service;

    @GetMapping("/grades/{dni}")
    public ApiResponse<List<CalificationDTO>> getAllGradesByStudent(@PathVariable("dni") Long dni) {

        // TODO: Validar el DNI (null y si es valido, mayor a 10M o cualquier validación necesaria)

        List<CalificationDTO> califications = service.getAllGradesByStudent(dni);

        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Registros obtenidos con éxito",
                true,
                califications
        );
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CalificationDTO> addCalificationToStudent(@Valid @RequestBody CalificationRequest request) {
        CalificationDTO dto = service.addCalification(request);

        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Calificación añadida con éxito",
                true,
                dto
        );
    }
}
