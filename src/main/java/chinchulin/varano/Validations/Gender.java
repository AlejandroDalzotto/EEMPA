package chinchulin.varano.Validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GenderValidator.class)
@Documented
public @interface Gender {
    String message() default "El género debe ser M, F, u O";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
