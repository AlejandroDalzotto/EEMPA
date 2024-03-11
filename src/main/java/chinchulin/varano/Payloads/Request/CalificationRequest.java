package chinchulin.varano.Payloads.Request;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CalificationRequest {

    @NotNull(message = "La nota es un campo obligatorio.")
    @Positive(message = "La nota debe ser un valor positivo.")
    @Max(value = 10, message = "El valor máximo permitido es 10.")
    @Min(value = 0, message = "El valor mínimo permitido es 0")
    private Float grade;

    @NotNull(message = "El DNI es un campo obligatorio.")
    @Positive(message = "El DNI no puede ser un número negativo.")
    @Min(value = 10000001, message = "El DNI debe ser un número superior a 10 millones.")
    private Long student_dni;

    @NotNull(message = "El nombre de la materia es un campo obligatorio.")
    @Size(min = 3, max = 25, message = "El nombre de la materia debe contener entre 3 y 25 caracteres.")
    private String subject;
}
