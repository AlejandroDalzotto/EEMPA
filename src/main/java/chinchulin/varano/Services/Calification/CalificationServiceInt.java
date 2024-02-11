package chinchulin.varano.Services.Calification;

import java.util.List;

import chinchulin.varano.Payloads.DTO.CalificationDTO;
import chinchulin.varano.Payloads.Request.CalificationRequest;

public interface CalificationServiceInt {

    List<CalificationDTO> getAllGradesByStudent(Long student_dni);

    CalificationDTO addCalification(CalificationRequest request);
}
