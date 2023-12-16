package chinchulin.varano.Services.Student_subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chinchulin.varano.Exceptions.EntityNotFoundException;
import chinchulin.varano.Models.Student_subject;
import chinchulin.varano.Repositories.Student_subjectRepo;

@Service
public class Student_subjectService implements Student_subjectServiceInt {

    @Autowired
    Student_subjectRepo repo;

    @Override
    public Long getId(Long student, Long subject) {
        try {
            Student_subject result = repo.getOne(student, subject);
            return result.getStudent_subject_Id();
        } catch (Exception ex) {
            throw new EntityNotFoundException(
                    "No valid result found for student: " + student + " and subject: " + subject);
        }
    }

}
