package chinchulin.varano.Services.Calification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chinchulin.varano.Models.Calification;
import chinchulin.varano.Repositories.CalificationRepo;
import chinchulin.varano.Services.Student_subject.Student_subjectService;

@Service
public class CalificationService implements CalificationServiceInt {

    @Autowired
    CalificationRepo repo;

    @Autowired
    Student_subjectService student_subjectService;

    @Override
    public List<Calification> getStudentAndSubject(Long Student, Long subject) {
        return repo.getByStudentAndSubject(student_subjectService.getId(Student, subject));
    }

}
