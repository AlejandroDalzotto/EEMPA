package chinchulin.varano.Services.Subject;

import java.util.Collections;
import java.util.List;

import chinchulin.varano.Models.Module;
import chinchulin.varano.Models.Student;
import chinchulin.varano.Payloads.DTO.StudentDTO;
import chinchulin.varano.Payloads.DTO.SubjectDTO;
import chinchulin.varano.Payloads.Request.SubjectRequest;
import chinchulin.varano.Repositories.ModuleRepo;
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
    ModuleRepo moduleRepo;

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

        Module module = moduleRepo.getByName(newSubject.getModule_name());

        if (module == null) {
            throw new EntityNotFoundException("El modulo " + newSubject.getModule_name() + " no se ha podido encontrar.");
        }

        Subject subject = new Subject();

        subject.setActive(true);
        subject.setName(newSubject.getName());
        subject.setStudents(Collections.emptyList());

        subject.setModule(module);
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
