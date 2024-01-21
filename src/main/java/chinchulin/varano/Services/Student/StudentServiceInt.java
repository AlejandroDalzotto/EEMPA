package chinchulin.varano.Services.Student;

import java.util.List;
import java.util.Optional;

import chinchulin.varano.Models.Student;
import chinchulin.varano.Models.StudentAnswer;
import chinchulin.varano.Models.Subject;

public interface StudentServiceInt {

    List<Student> getAllActive();

    List<Subject> getSubjectByStudent(Long id);

    List<Student> getAll();

    Optional<Student> getById(Long id);

    Student getByDNI(Long dni);

    Student getByLegajo(Long legajo);

    Student newStudent(Student student);

    Student editStudent(Long id, Student student);

    Student inactiveStudent(Long id);

    StudentAnswer getByFilterQuery(String query, int limit, int offset);

    StudentAnswer getAmountActive(int limit, int offset);
}
