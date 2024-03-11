package chinchulin.varano.Payloads.DTO;

import chinchulin.varano.Models.Calification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalificationDTO {

    private Float grade;

    private String student;

    private String subject;

    public static CalificationDTO fromCalification(Calification calification) {
        return new CalificationDTO(
                calification.getValue(),
                calification.getStudent().getName(),
                calification.getSubject().getName()
        );
    }

    public static List<CalificationDTO> fromListCalification(List<Calification> califications) {
        return califications.stream().map(CalificationDTO::fromCalification).toList();
    }
}
