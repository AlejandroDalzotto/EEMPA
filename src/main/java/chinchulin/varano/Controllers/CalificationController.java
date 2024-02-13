package chinchulin.varano.Controllers;

import chinchulin.varano.Payloads.DTO.CalificationDTO;
import chinchulin.varano.Payloads.Request.CalificationRequest;
import chinchulin.varano.Services.Calification.CalificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/calification")
@RestController
@CrossOrigin("localhost:3000")
public class CalificationController {

    @Autowired
    CalificationService service;

    @GetMapping("/grades/{dni}")
    public List<CalificationDTO> getAllGradesByStudent(@PathVariable("dni") Long dni) {

        // TODO: Validar el DNI (null y si es valido, mayor a 10M o cualquier validación necesaria)

        return service.getAllGradesByStudent(dni);
    }

    // TODO: Retornar el objeto más adecuado (Puede modificarse el DTO) con el HttpStatus.CREATED.
    @PostMapping("/add")
    public CalificationDTO addCalificationToStudent(@Valid @RequestBody CalificationRequest request) {

        return service.addCalification(request);
    }
}
