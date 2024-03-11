package chinchulin.varano.Validations;

import chinchulin.varano.Utils.AcademicState;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class AcademicStudentStateValidator implements ConstraintValidator<AcademicStudentState, String> {
    @Override
    public void initialize(AcademicStudentState constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value != null && AcademicState.isValidState(value);
    }
}
