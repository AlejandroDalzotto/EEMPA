package chinchulin.varano.Services.Student;

import java.util.List;
import java.util.Optional;

import chinchulin.varano.Models.Student;

public interface StudentServiceInt {

    List<Student> getAll();

    Optional<Student> getById(Long id);

    Student getByDNI(Long dni);

    Student getByLegajo(Long legajo);

    Student newStudent(Student student);

    Student editStudent(Long id, Student student);

    Student inactiveStudent(Long id);
}
