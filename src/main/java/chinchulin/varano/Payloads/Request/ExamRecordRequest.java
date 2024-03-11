package chinchulin.varano.Payloads.Request;

import chinchulin.varano.Validations.ExamStudentState;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExamRecordRequest {

    @NotNull(message = "El estado del alumno es un campo obligatorio.")
    @ExamStudentState
    private String state;

    @NotNull(message = "El DNI es un campo obligatorio.")
    @Positive(message = "El DNI no puede ser un número negativo.")
    private Long student_dni;

    @NotNull(message = "Debe proveerse un nombre para identificar el examen que se registra, este debe ser único.")
    @Size(min = 3, max = 40, message = "El nombre para identificar el examen debe contener entre 3 y 40 caracteres.")
    private String exam_key;

    @Nullable
    private Boolean attended = false;

    @NotNull(message = "La nota del alumno es un campo obligatorio.")
    private Float grade = 0f;
}
