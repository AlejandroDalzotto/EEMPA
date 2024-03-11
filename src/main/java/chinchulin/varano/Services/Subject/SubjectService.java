package chinchulin.varano.Services.Subject;

import java.util.Collections;
import java.util.List;

import chinchulin.varano.Exceptions.EntityRepeatedException;
import chinchulin.varano.Models.Course;
import chinchulin.varano.Models.Student;
import chinchulin.varano.Payloads.DTO.StudentDTO;
import chinchulin.varano.Payloads.DTO.SubjectDTO;
import chinchulin.varano.Payloads.Request.SubjectRequest;
import chinchulin.varano.Repositories.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chinchulin.varano.Exceptions.EntityNotFoundException;
import chinchulin.varano.Models.Subject;
import chinchulin.varano.Repositories.StudentRepo;
import chinchulin.varano.Repositories.SubjectRepo;

@Service
public class SubjectService implements SubjectServiceInt {

    @Autowired
    SubjectRepo repo;

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    CourseRepo courseRepo;

    @Override
    public List<SubjectDTO> getAll() {
        List<Subject> subjects = repo.findAll();
        return SubjectDTO.fromListSubject(subjects);
    }

    @Override
    public List<SubjectDTO> getAllActive() {
        List<Subject> subjects = repo.getAllActive();
        return SubjectDTO.fromListSubject(subjects);
    }

    @Override
    public SubjectDTO add(SubjectRequest newSubject) {

        Course course = courseRepo.getByName(newSubject.getCourse_name());

        if (course == null) {
            throw new EntityNotFoundException("El curso " + newSubject.getCourse_name() + " no se ha podido encontrar.");
        }

        Subject existByName = repo.getByName(newSubject.getName());

        if (existByName != null) {
            throw new EntityRepeatedException(
                    String.format("La materia %s ya está registrada en el sistema", newSubject.getName())
            );
        }

        Subject subject = new Subject();

        subject.setActive(true);
        subject.setName(newSubject.getName());
        subject.setCourse(course);
        subject.setStudents(Collections.emptyList());

        repo.save(subject);

        return SubjectDTO.fromSubject(subject);
    }

    @Override
    public SubjectDTO inactiveSubject(Long id) {
        return repo.findById(id)
                .map(subject -> {
                    subject.setActive(!subject.getActive());
                    repo.save(subject);
                    return SubjectDTO.fromSubject(subject);
                })
                .orElseThrow(() -> new EntityNotFoundException("Subject not found with ID: " + id));
    }

    @Override
    public List<StudentDTO> getStudentBySubject(Long id) {
        List<Student> students = studentRepo.getActiveStudentBySubject(id);
        return StudentDTO.fromListStudent(students);
    }

}
