package chinchulin.varano.Services.Student;

import java.util.List;
import java.util.Optional;

import chinchulin.varano.Exceptions.EntityRepeatedException;
import chinchulin.varano.Payloads.DTO.StudentDTO;
import chinchulin.varano.Payloads.DTO.SubjectDTO;
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
        return StudentDTO.fromListStudent(students);
    }

    @Override
    public StudentDTO getById(Long id) {
        Optional<Student> student = Optional.ofNullable(repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student with id " + id + " not found")));

        if (student.isEmpty()) {
            throw new EntityNotFoundException("Student with id " + id + " not found");
        }

        return StudentDTO.fromStudent(student.get());
    }

    @Override
    public StudentDTO getByDNI(Long dni) {
        Student student = repo.getByDNI(dni);

        if (student == null || !student.getActive()) {
            throw new EntityNotFoundException("No se ha encontrado al alumno con DNI " + dni);
        }

        return StudentDTO.fromStudent(student);
    }

    @Override
    public StudentDTO getByLegajo(Long legajo) {

        Student student = repo.getByLegajo(legajo);

        if (student == null || !student.getActive()) {
            throw new EntityNotFoundException("No se ha encontrado al alumno con legajo " + legajo);
        }

        return StudentDTO.fromStudent(student);
    }

    @Override
    public StudentDTO newStudent(StudentRequest student) {
        Student studentToSave = new Student();

        Student isRegistered = repo.isRegistered(student.getDni(), student.getMail(), student.getLegajo());

        if (isRegistered != null) {
            throw new EntityRepeatedException("Algunos datos proporcionados están repetidos. Por favor vuelva a intentarlo.");
        }

        Utils.copyStudentProperties(student, studentToSave);

        repo.save(studentToSave);

        return StudentDTO.fromStudent(studentToSave);
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

        repo.save(student);
        return StudentDTO.fromStudent(student);
    }

    @Override
    public StudentDTO inactiveStudent(Long dni) {
        Student student = repo.getByDNI(dni);

        if (student == null || !student.getActive()) {
            throw new EntityNotFoundException("No se ha encontrado al alumno con el DNI " + dni);
        }

        student.setActive(false);

        repo.save(student);
        return StudentDTO.fromStudent(student);
    }

    @Override
    public List<StudentDTO> getAllActive() {
        List<Student> students = repo.getAllActive();

        return StudentDTO.fromListStudent(students);
    }

    @Override
    public List<SubjectDTO> getSubjectByStudent(Long id) {
        List<Subject> subjects = subjectRepo.getActiveSubjectByStudent(id);
        return SubjectDTO.fromListSubject(subjects);
    }

    @Override
    public List<StudentDTO> getByFilterQuery(String query, int limit, int offset) {
        List<Student> students = repo.getByFilterQuery(query, limit, offset);
        return StudentDTO.fromListStudent(students);
    }

    @Override
    public List<StudentDTO> getByQueryBlank(int limit, int offset) {
        List<Student> students = repo.getByQueryBlank(limit, offset);
        return StudentDTO.fromListStudent(students);
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
