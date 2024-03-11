package chinchulin.varano.Validations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExamStudentStateValidator.class)
@Documented
public @interface ExamStudentState {
    String message() default "El estado debe ser Regular, Aprobado o Desaprobado.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
