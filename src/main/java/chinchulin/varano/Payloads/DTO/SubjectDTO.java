package chinchulin.varano.Payloads.DTO;

import chinchulin.varano.Models.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {
    private String name;

    private String module_name;

    private List<StudentDTO> students;

    public static SubjectDTO fromSubject(Subject subject) {
        return new SubjectDTO(
                subject.getName(),
                subject.getModule().getName(),
                StudentDTO.fromListStudent(subject.getStudents())
        );
    }

    public static List<SubjectDTO> fromListSubject(List<Subject> subject) {
        return subject.stream().map(SubjectDTO::fromSubject).toList();
    }
}
