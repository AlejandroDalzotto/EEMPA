package chinchulin.varano.Services.Student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import chinchulin.varano.Exceptions.EntityNotFoundException;
import chinchulin.varano.Models.Student;
import chinchulin.varano.Repositories.StudentRepo;

public class StudentService implements StudentServiceInt {

    @Autowired
    StudentRepo repo;

    @Override
    public List<Student> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Student> getById(Long id) {
        return repo.findById(id);
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
        return repo.save(newStudent(student));
    }

    // TODO: Estaría bueno considerar reemplazar esto con implementación para un PATCH request porque esto es una banda de data para la pobre red
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
                    student.setLejago(newStudent.getLejago());
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

}
