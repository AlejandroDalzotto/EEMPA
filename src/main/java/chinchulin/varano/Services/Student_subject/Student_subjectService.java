package chinchulin.varano.Services.Student_subject;

import chinchulin.varano.Models.Student;
import chinchulin.varano.Models.Subject;
import chinchulin.varano.Payloads.DTO.SubjectDTO;
import chinchulin.varano.Payloads.Request.StudentSubjectRequest;
import chinchulin.varano.Repositories.StudentRepo;
import chinchulin.varano.Repositories.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chinchulin.varano.Exceptions.EntityNotFoundException;
import chinchulin.varano.Models.Student_subject;
import chinchulin.varano.Repositories.Student_subjectRepo;

@Service
public class Student_subjectService implements Student_subjectServiceInt {

    @Autowired
    Student_subjectRepo repo;

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    SubjectRepo subjectRepo;

    @Override
    public Long getId(Long student, Long subject) {
        try {
            Student_subject result = repo.getOne(student, subject);
            return result.getStudent_subject_Id();
        } catch (Exception ex) {
            throw new EntityNotFoundException(
                    "No valid result found for student: " + student + " and subject: " + subject);
        }
    }

    @Override
    public SubjectDTO addStudent(StudentSubjectRequest studentSubjectRequest) {

        Student student = studentRepo.getByDNI(studentSubjectRequest.getStudent_dni());

        if (student == null) {
            throw new EntityNotFoundException("Alumno no registrado en el sistema, por favor provea otro DNI.");
        }

        Subject subject = subjectRepo.getByName(studentSubjectRequest.getSubject_name());

        if (subject == null) {
            throw new EntityNotFoundException("La materia no se ha logrado encontrar, por favor asegúrese de haber ingresado correctamente el nombre.");
        }

        Student_subject studentSubject = new Student_subject();

        studentSubject.setId_student(student);
        studentSubject.setId_subject(subject);

        repo.save(studentSubject);

        return SubjectDTO.fromSubject(subject);
    }

}
