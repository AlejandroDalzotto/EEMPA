package chinchulin.varano.Validations;

import chinchulin.varano.Utils.ExamState;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExamStudentStateValidator implements ConstraintValidator<ExamStudentState, String> {
    @Override
    public void initialize(ExamStudentState examStudentState) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && (
                value.equals(ExamState.APPROVED.getLabel()) || value.equals(ExamState.FAILED.getLabel()) || value.equals(ExamState.REGULAR.getLabel())
        );
    }
}