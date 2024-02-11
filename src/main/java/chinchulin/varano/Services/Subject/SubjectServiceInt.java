package chinchulin.varano.Services.Subject;

import java.util.List;

import chinchulin.varano.Payloads.DTO.StudentDTO;
import chinchulin.varano.Payloads.DTO.SubjectDTO;
import chinchulin.varano.Payloads.Request.SubjectRequest;

public interface SubjectServiceInt {

    List<SubjectDTO> getAll();

    List<StudentDTO> getStudentBySubject(Long id);

    List<SubjectDTO> getAllActive();

    SubjectDTO add(SubjectRequest newSubject);

    SubjectDTO inactiveSubject(Long id);

}
