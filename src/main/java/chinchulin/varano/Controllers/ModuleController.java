package chinchulin.varano.Controllers;

import chinchulin.varano.Payloads.ApiResponse;
import chinchulin.varano.Payloads.DTO.ModuleDTO;
import chinchulin.varano.Payloads.Request.ModuleRequest;
import chinchulin.varano.Services.Module.ModuleService;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/module")
public class ModuleController {

    @Autowired
    private ModuleService service;

    @GetMapping("/all")
    public ApiResponse<List<ModuleDTO>> getAllModules(
            @RequestParam(name = "query", required = false) @Nullable String query,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit,
            @RequestParam(name = "offset", defaultValue = "0") Integer offset
    ) {
        if (query == null || query.isEmpty()) {
            List<ModuleDTO> modules = service.getAllModules(limit, offset);
            return new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Registros obtenidos con éxito",
                    true,
                    modules
            );
        }

        List<ModuleDTO> modulesFiltered = service.getAllByTerm(query, limit, offset);

        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Registros obtenidos con éxito",
                true,
                modulesFiltered
        );
    }

    @GetMapping("/all-by-course/{course}")
    public ApiResponse<List<ModuleDTO>> getAllByCourse(@PathVariable("course") String course_name) {
        List<ModuleDTO> modules = service.getAllByCourse(course_name);

        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Registros obtenidos con éxito",
                true,
                modules
        );
    }

    @GetMapping("/all/pages")
    public Long getTotalPages(
            @RequestParam(name = "query", required = false) @Nullable String query
    ) {
        if (query == null || query.isEmpty()) {
            return service.countModules();
        }
        return service.countModulesByTerm(query);
    }

    @PostMapping("/save-module")
    public ApiResponse<ModuleDTO> saveModule(@RequestBody @Valid ModuleRequest newEntry) {
        ModuleDTO module = service.saveModule(newEntry);

        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Registro obtenido con éxito",
                true,
                module
        );
    }

    @PutMapping("/update-module/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ModuleDTO> updateModule(@PathVariable("name") String module_name, @RequestBody @Valid ModuleRequest newAttributes) {
        ModuleDTO module = service.updateModule(module_name, newAttributes);

        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Registro guardado con éxito",
                true,
                module
        );
    }

    @DeleteMapping("/delete-module/{name}")
    public ApiResponse<ModuleDTO> deleteModule(@PathVariable("name") String module_name) {
        ModuleDTO module = service.deleteModule(module_name);

        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Registro deshabilitado con éxito",
                true,
                module
        );
    }
}
