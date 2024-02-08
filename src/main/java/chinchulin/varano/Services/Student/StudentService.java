package chinchulin.varano.Services.Student;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import chinchulin.varano.Exceptions.EntityRepeatedException;
import chinchulin.varano.Payloads.DTO.StudentDTO;
import chinchulin.varano.Payloads.Request.StudentRequest;
import chinchulin.varano.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chinchulin.varano.Exceptions.EntityNotFoundException;
import chinchulin.varano.Models.Student;
import chinchulin.varano.Models.Subject;
import chinchulin.varano.Repositories.StudentRepo;
import chinchulin.varano.Repositories.SubjectRepo;

@Service
public class StudentService implements StudentServiceInt {

    @Autowired
    StudentRepo repo;

    @Autowired
    SubjectRepo subjectRepo;

    @Override
    public List<StudentDTO> getAll() {
        List<Student> students = repo.findAll();
        return Student.toListStudentDTO(students);
    }

    @Override
    public StudentDTO getById(Long id) {
        Optional<Student> student = Optional.ofNullable(repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student with id " + id + " not found")));

        if (student.isEmpty()) {
            throw new EntityNotFoundException("Student with id " + id + " not found");
        }

        return student.get().toStudentDTO();
    }

    @Override
    public StudentDTO getByDNI(Long dni) {
        return repo.getByDNI(dni).toStudentDTO();
    }

    @Override
    public StudentDTO getByLegajo(Long legajo) {
        return repo.getByLegajo(legajo).toStudentDTO();
    }

    @Override
    public StudentDTO newStudent(StudentRequest student) {
        Student studentToSave = new Student();

        Student isRegistered = repo.isRegistered((long) student.getDni(), student.getMail(), student.getLegajo());

        if (isRegistered != null) {
            throw new EntityRepeatedException("Algunos datos proporcionados están repetidos. Por favor vuelva a intentarlo.");
        }

        // TODO: Para ahorrar esto se puede hacer un método en algún paquete tipo `Utils` para que haga esto y no tener que repetirlo.
        Utils.copyStudentProperties(student, studentToSave);

        return repo.save(studentToSave).toStudentDTO();
    }

    /**
     * TODO: Estaría bueno considerar reemplazar esto con implementación para un
     * PATCH request porque esto es una banda de data para la pobre red.
     */
    @Override
    public StudentDTO editStudent(Long dni, StudentRequest newStudent) {
        Student student = repo.getByDNI(dni);

        if (student == null || !student.getActive()) {
            throw new EntityNotFoundException("No se ha encontrado al alumno con el DNI " + dni);
        }

        Utils.copyStudentProperties(newStudent, student);
        return repo.save(student).toStudentDTO();
    }

    @Override
    public StudentDTO inactiveStudent(Long dni) {
        Student student = repo.getByDNI(dni);

        if (student == null || !student.getActive()) {
            throw new EntityNotFoundException("No se ha encontrado al alumno con el DNI " + dni);
        }

        student.setActive(false);
        return repo.save(student).toStudentDTO();
    }

    @Override
    public List<StudentDTO> getAllActive() {
        List<Student> students = repo.getAllActive();

        return Student.toListStudentDTO(students);
    }

    @Override
    public List<Subject> getSubjectByStudent(Long id) {
        return subjectRepo.getActiveSubjectByStudent(id);
    }

    @Override
    public List<StudentDTO> getByFilterQuery(String query, int limit, int offset) {
        return Student.toListStudentDTO(repo.getByFilterQuery(query, limit, offset));
    }

    @Override
    public List<StudentDTO> getByQueryBlank(int limit, int offset) {
        return Student.toListStudentDTO(repo.getByQueryBlank(limit, offset));
    }

    @Override
    public Long count() {
        return repo.count();
    }

    @Override
    public Long countByTerm(String term) {
        return (long) repo.countByTerm(term);
    }

}
