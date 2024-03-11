package chinchulin.varano.Utils;

import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum AcademicState {

    GRADUATED ("Se Recibió"),
    REGULAR ("Regular"),
    DROPPED_OUT ("Abandonó"),
    REPEATER ("Repetidor");

    private final String label;

    AcademicState(String label) {
        this.label = label;
    }

    public static AcademicState getStateByLabel(String label) {
        for (AcademicState state : AcademicState.values()) {
            if (state.getLabel().equalsIgnoreCase(label)) {
                return state;
            }
        }
        return null;
    }

    public static Boolean isValidState(String value) {
        Set<String> validValues = Stream.of(
                AcademicState.GRADUATED.getLabel(),
                AcademicState.DROPPED_OUT.getLabel(),
                AcademicState.REGULAR.getLabel(),
                AcademicState.REPEATER.getLabel()
        ).collect(Collectors.toSet());

        return validValues.contains(value);
    }
}
