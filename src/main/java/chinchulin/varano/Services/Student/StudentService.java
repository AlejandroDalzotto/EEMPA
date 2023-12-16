package chinchulin.varano.Services.Student;

import java.util.List;
import java.util.Optional;

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
    public List<Student> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Student> getById(Long id) {
        return Optional.ofNullable(repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student with id " + id + " not found")));
    }

    @Override
    public Student getByDNI(Long dni) {
        return repo.getByDNI(dni);
    }

    @Override
    public Student getByLegajo(Long legajo) {
        return repo.getByLegajo(legajo);
    }

    @Override
    public Student newStudent(Student student) {
        return repo.save(student);
    }

    // TODO: Estaría bueno considerar reemplazar esto con implementación para un
    // PATCH request porque esto es una banda de data para la pobre red
    @Override
    public Student editStudent(Long id, Student newStudent) {
        return repo.findById(id)
                .map(student -> {
                    student.setActive(newStudent.getActive());
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
                    student.setSubjects(newStudent.getSubjects());
                    return repo.save(student);
                })
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + id));
    }

    @Override
    public Student inactiveStudent(Long id) {
        return repo.findById(id)
                .map(student -> {
                    student.setActive(!student.getActive());
                    return repo.save(student);
                })
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + id));
    }

    @Override
    public List<Student> getAllActive() {
        return repo.getAllActive();
    }

    @Override
    public List<Subject> getSubjectByStudent(Long id) {
        return subjectRepo.getSubjectByStudent(id);
    }

    @Override
    public List<Student> getByFilterQuery(String query, int limit, int offset){
        return repo.getByFilterQuery(query, limit, offset);
    }


}
