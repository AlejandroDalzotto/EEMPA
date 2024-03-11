package chinchulin.varano.Payloads.Request;

import chinchulin.varano.Utils.AcademicState;
import chinchulin.varano.Validations.AcademicStudentState;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PromoteStudentRequest {

    @NotNull(message = "El DNI del alumno es un campo obligatorio.")
    @Positive(message = "El DNI del alumno no puede ser un número negativo.")
    private Long student_dni;

    @NotNull(message = "El nombre del curso es un campo obligatorio, por favor provea un nombre.")
    @Size(min = 3, max = 30, message = "El nombre del curso debe contener entre 3 y 30 caracteres.")
    private String new_course;

    @NotNull(message = "El año de estudio es un campo obligatorio.")
    private Short study_year;

    @Nullable
    private String comment = null;

    @NotNull(message = "El estado es un campo obligatorio.")
    @AcademicStudentState
    private String state;
}
