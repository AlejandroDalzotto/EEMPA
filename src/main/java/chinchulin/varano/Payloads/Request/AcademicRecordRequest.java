package chinchulin.varano.Payloads.Request;

import chinchulin.varano.Validations.AcademicStudentState;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class AcademicRecordRequest {

    @NotNull(message = "El DNI es un campo obligatorio.")
    @Positive(message = "El DNI no puede ser un número negativo.")
    private Long student_dni;

    @NotNull(message = "El nombre del curso es un campo obligatorio, por favor provea un nombre.")
    @Size(min = 3, max = 30, message = "El nombre del curso debe contener entre 3 y 30 caracteres.")
    private String course_name;

    @NotNull(message = "El año de estudio es un campo obligatorio.")
    @Positive(message = "El año de estudio no puede ser negativo.")
    @Min(value = 1900, message = "El año mínimo permitido es 1900.")
    @Max(value = 2155, message = "El año máximo permitido es 2155.")
    private Short study_year;

    @NotNull(message = "El estado académico es un campo obligatorio.")
    @AcademicStudentState
    private String academic_state;

    private String comment = null;

}
