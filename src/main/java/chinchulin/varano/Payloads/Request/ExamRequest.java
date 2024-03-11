package chinchulin.varano.Payloads.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
public class ExamRequest {

    @NotNull(message = "Debe proveerse un nombre para identificar el examen que se registra, este debe ser único.")
    @Size(min = 3, max = 40, message = "El nombre para identificar el examen debe contener entre 3 y 40 caracteres.")
    private String key;

    @NotNull(message = "La información sobre la materia debe ser proveída.")
    @Size(min = 3, max = 25, message = "El nombre de la materia debe contener entre 3 y 25 caracteres.")
    private String subject;

    @NotNull(message = "La fecha del examen debe ser proveída.")
    private LocalDate date;

    @NotNull(message = "Los registros del examen deben ser proveídos, si aun no que tiene registros por favor provea una lista vacía.")
    private Set<ExamRecordRequest> records;

    @NotNull(message = "El nombre del modulo debe ser proveído.")
    private String module;
}
