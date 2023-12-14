package chinchulin.varano.Services.Calification;

import java.util.List;

import chinchulin.varano.Models.Calification;

public interface CalificationServiceInt {

    List<Calification> getStudentAndSubject(Long Student, Long subject);
}
