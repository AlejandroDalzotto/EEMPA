package chinchulin.varano.Services.Student;

import java.util.List;
import chinchulin.varano.Models.Subject;
import chinchulin.varano.Payloads.DTO.StudentDTO;
import chinchulin.varano.Payloads.Request.StudentRequest;

public interface StudentServiceInt {

    List<StudentDTO> getAllActive();

    List<Subject> getSubjectByStudent(Long id);

    List<StudentDTO> getAll();

    StudentDTO getById(Long id);

    StudentDTO getByDNI(Long dni);

    StudentDTO getByLegajo(Long legajo);

    StudentDTO newStudent(StudentRequest student);

    StudentDTO editStudent(Long id, StudentRequest student);

    StudentDTO inactiveStudent(Long id);

    List<StudentDTO> getByFilterQuery(String query, int limit, int offset);

    List<StudentDTO> getByQueryBlank(int limit, int offset);

    Long count();

    Long countByTerm(String term);
}
