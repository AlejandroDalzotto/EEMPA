package chinchulin.varano.Services.Student_subject;

import chinchulin.varano.Payloads.DTO.SubjectDTO;
import chinchulin.varano.Payloads.Request.StudentSubjectRequest;

public interface Student_subjectServiceInt {
    Long getId(Long student, Long subject);

    SubjectDTO addStudent(StudentSubjectRequest studentSubjectRequest);
}
