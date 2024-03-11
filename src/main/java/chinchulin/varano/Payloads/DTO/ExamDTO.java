package chinchulin.varano.Payloads.DTO;

import chinchulin.varano.Models.Exam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamDTO {
    private String key;
    private String subject;
    private String module;
    private LocalDate date;

    public static ExamDTO fromExam(Exam exam) {
        return new ExamDTO(
                exam.getKey(),
                exam.getSubject().getName(),
                exam.getModule().getName(),
                exam.getDate()
        );
    }

    public static List<ExamDTO> fromListExam(List<Exam> exams) {
        return exams.stream().map(ExamDTO::fromExam).toList();
    }
}
