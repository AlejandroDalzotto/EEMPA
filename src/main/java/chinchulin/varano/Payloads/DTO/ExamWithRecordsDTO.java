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
public class ExamWithRecordsDTO {
    private String key;
    private String subject;
    private String module;
    private LocalDate date;
    private List<ExamRecordDTO> records;

    public static ExamWithRecordsDTO fromExam(Exam exam) {
        return new ExamWithRecordsDTO(
                exam.getKey(),
                exam.getSubject().getName(),
                exam.getModule().getName(),
                exam.getDate(),
                ExamRecordDTO.fromListExamRecord(exam.getRecords())
        );
    }

    public static List<ExamWithRecordsDTO> fromListExam(List<Exam> exams) {
        return exams.stream().map(ExamWithRecordsDTO::fromExam).toList();
    }
}
