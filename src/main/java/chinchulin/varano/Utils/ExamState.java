package chinchulin.varano.Utils;

import lombok.Getter;

@Getter
public enum ExamState {

    REGULAR ("Regular"),
    APPROVED ("Aprobado"),
    FAILED ("Desaprobado");

    private final String label;

    ExamState(String label) {
        this.label = label;
    }

    public static ExamState getStateByLabel(String label) {
        for (ExamState state : ExamState.values()) {
            if (state.getLabel().equalsIgnoreCase(label)) {
                return state;
            }
        }
        return null;
    }
}
