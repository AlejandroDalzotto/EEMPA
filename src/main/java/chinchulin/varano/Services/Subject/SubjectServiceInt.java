package chinchulin.varano.Services.Subject;

import java.util.List;

import chinchulin.varano.Models.Student;
import chinchulin.varano.Models.Subject;

public interface SubjectServiceInt {

    List<Subject> getAll();

    List<Student> getStudentBySubject(Long id);

    List<Subject> getAllActive();

    Subject add(Subject newSubject);

    Subject inactiveSubject(Long id);

}
