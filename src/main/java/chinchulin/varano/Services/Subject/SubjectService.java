package chinchulin.varano.Services.Subject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chinchulin.varano.Exceptions.EntityNotFoundException;
import chinchulin.varano.Models.Subject;
import chinchulin.varano.Repositories.SubjectRepo;

@Service
public class SubjectService implements SubjectServiceInt {

    @Autowired
    SubjectRepo repo;

    @Override
    public List<Subject> getAll() {
        return repo.findAll();
    }

    @Override
    public List<Subject> getAllActive() {
        return repo.getAllActive();
    }

    @Override
    public Subject add(Subject newSubject) {
        return repo.save(newSubject);
    }

    @Override
    public Subject inactiveSubject(Long id) {
        return repo.findById(id)
                .map(subject -> {
                    subject.setActive(!subject.getActive());
                    return repo.save(subject);
                })
                .orElseThrow(() -> new EntityNotFoundException("Subject not found with ID: " + id));

    }

}
