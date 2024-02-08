package chinchulin.varano.Validations;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BirthdateValidator.class)
@Documented
public @interface Birthdate {
    String message() default "La fecha debe ser valida.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}