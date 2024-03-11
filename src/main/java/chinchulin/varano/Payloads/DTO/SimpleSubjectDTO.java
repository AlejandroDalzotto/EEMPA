package chinchulin.varano.Payloads.DTO;

import chinchulin.varano.Models.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleSubjectDTO {

    private String subject_name;

    private Integer count_students;

    public static SimpleSubjectDTO fromSubject(Subject subject) {
        return new SimpleSubjectDTO(
                subject.getName(),
                subject.getStudents().size()
        );
    }

    public static List<SimpleSubjectDTO> fromListSubject(List<Subject> subjects) {
        return subjects.stream().map(SimpleSubjectDTO::fromSubject).toList();
    }
}
