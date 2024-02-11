package chinchulin.varano.Services.Calification;

import chinchulin.varano.Exceptions.EntityNotFoundException;
import chinchulin.varano.Models.Student;
import chinchulin.varano.Models.Student_subject;
import chinchulin.varano.Models.Subject;
import chinchulin.varano.Payloads.DTO.CalificationDTO;
import chinchulin.varano.Payloads.Request.CalificationRequest;
import chinchulin.varano.Repositories.StudentRepo;
import chinchulin.varano.Repositories.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chinchulin.varano.Models.Calification;
import chinchulin.varano.Repositories.CalificationRepo;
import chinchulin.varano.Services.Student_subject.Student_subjectService;

import java.util.List;
import java.util.Objects;

@Service
public class CalificationService implements CalificationServiceInt {

    @Autowired
    CalificationRepo repo;

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    SubjectRepo subjectRepo;

    @Override
    public List<CalificationDTO> getAllGradesByStudent(Long student_dni) {
        return CalificationDTO.fromListCalification(
                // TODO: Esto se puede interpolar a un custom query.
                // Básicamente estamos filtrando todos los registros de la tabla califications
                // y buscando los que tengan un alumno con el DNI que buscamos.
                repo.findAll().stream().filter(calification ->
                        Objects.equals(calification.getStudent().getDni(), student_dni)
                ).toList()
        );
    }

    @Override
    public CalificationDTO addCalification(CalificationRequest request) {

        Student student = studentRepo.getByDNI(request.getStudent_dni());
        Subject subject = subjectRepo.getByName(request.getSubject());

        if (student == null) {
            throw new EntityNotFoundException("El alumno con el DNI " + request.getStudent_dni() + " no se ha encontrado.");
        }

        if (subject == null) {
            throw new EntityNotFoundException("No se ha encontrado la materia " + request.getSubject());
        }

        Calification calification = new Calification();

        calification.setValue(request.getGrade());
        calification.setStudent(student);
        calification.setSubject(subject);

        repo.save(calification);
        return CalificationDTO.fromCalification(calification);
    }

}
