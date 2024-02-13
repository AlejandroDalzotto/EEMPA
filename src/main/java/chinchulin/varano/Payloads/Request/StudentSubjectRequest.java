package chinchulin.varano.Payloads.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentSubjectRequest {

    @NotNull(message = "El DNI es un campo obligatorio.")
    @Positive(message = "El DNI no puede ser un número negativo.")
    @Min(value = 10000001, message = "El DNI debe ser un número superior a 10 millones.")
    private Long student_dni;

    @NotNull(message = "El nombre es un campo obligatorio, por favor provea un nombre.")
    @Size(min = 3, max = 25, message = "El nombre debe contener entre 3 y 25 caracteres.")
    private String subject_name;
}
