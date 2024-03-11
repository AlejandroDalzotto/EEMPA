package chinchulin.varano.Validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AcademicStudentStateValidator.class)
@Documented
public @interface AcademicStudentState {

    String message() default "El estado debe ser Regular, Se recibió, Abandonó o Repetidor.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
