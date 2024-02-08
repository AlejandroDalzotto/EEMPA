package chinchulin.varano.Validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class BirthdateValidator implements ConstraintValidator<Birthdate, LocalDate> {
    @Override
    public void initialize(Birthdate constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext constraintValidatorContext) {
        if (dateOfBirth == null) {
            return false;
        }

        // Calcula la edad del alumno
        LocalDate currentDate = LocalDate.now();
        int age = currentDate.getYear() - dateOfBirth.getYear();
        if (currentDate.getMonthValue() < 6) {
            age--; // Resta un año si aún no ha pasado el 30 de junio
        }

        // Válida si la edad es mayor o igual a 17
        return age >= 17;
    }
}