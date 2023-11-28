package chinchulin.varano.Services.Subject;

import java.util.List;

import chinchulin.varano.Models.Subject;

public interface SubjectServiceInt {

    List<Subject> getAll();

    List<Subject> getAllActive();

    Subject add(Subject newSubject);

    Subject inactiveSubject(Long id);

}
