package chinchulin.varano.Services.Student;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import chinchulin.varano.Payloads.DTO.StudentDTO;
import chinchulin.varano.Payloads.Request.StudentRequest;
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

        // TODO: Para ahorrar esto se puede hacer un método en algún paquete tipo `Utils` para que haga esto y no tener que repetirlo.
        studentToSave.setActive(true);
        studentToSave.setAddress(student.getAddress());
        studentToSave.setAge(student.getAge());
        studentToSave.setBirth(student.getBirth());
        studentToSave.setBirthCert(student.getBirthCert());
        studentToSave.setCellPhone(student.getCellPhone());
        studentToSave.setCourse(student.getCourse());
        studentToSave.setDisability(student.getDisability());
        studentToSave.setDni(student.getDni());
        studentToSave.setHealth(student.getHealth());
        studentToSave.setLastName(student.getLastName());
        studentToSave.setLegajo(student.getLegajo());
        studentToSave.setLinePhone(student.getLinePhone());
        studentToSave.setMail(student.getMail());
        studentToSave.setMatricula(student.getMatricula());
        studentToSave.setName(student.getName());
        studentToSave.setSex(student.getSex());
        studentToSave.setStudyCert(student.getStudyCert());
        studentToSave.setSubjects(Collections.emptyList());

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

        student.setAddress(newStudent.getAddress());
        student.setAge(newStudent.getAge());
        student.setBirth(newStudent.getBirth());
        student.setBirthCert(newStudent.getBirthCert());
        student.setCellPhone(newStudent.getCellPhone());
        student.setCourse(newStudent.getCourse());
        student.setDisability(newStudent.getDisability());
        student.setDni(newStudent.getDni());
        student.setHealth(newStudent.getHealth());
        student.setLastName(newStudent.getLastName());
        student.setLegajo(newStudent.getLegajo());
        student.setLinePhone(newStudent.getLinePhone());
        student.setMail(newStudent.getMail());
        student.setMatricula(newStudent.getMatricula());
        student.setName(newStudent.getName());
        student.setSex(newStudent.getSex());
        student.setStudyCert(newStudent.getStudyCert());
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
