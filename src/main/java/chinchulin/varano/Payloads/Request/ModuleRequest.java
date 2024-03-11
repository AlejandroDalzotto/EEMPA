package chinchulin.varano.Payloads.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ModuleRequest {

    @NotNull(message = "El nombre es un campo obligatorio, por favor provea un nombre.")
    @Size(min = 3, max = 25, message = "El nombre debe contener entre 3 y 25 caracteres.")
    private String name;

    @NotNull(message = "El nombre del curso es un campo obligatorio, por favor provea un nombre.")
    @Size(min = 3, max = 30, message = "El nombre del curso debe contener entre 3 y 30 caracteres.")
    private String course_name;
}
