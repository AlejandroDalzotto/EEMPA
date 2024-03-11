package chinchulin.varano.Payloads.DTO;

import chinchulin.varano.Models.Exam;
import chinchulin.varano.Models.ExamRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamRecordDTO {

    private String student_name;
    private String exam_key;
    private String subject;
    private String state;
    private Boolean attended;
    private Float grade;

    public static ExamRecordDTO fromExamRecord(ExamRecord exam) {
        return new ExamRecordDTO(
                exam.getStudent().getName() + " " + exam.getStudent().getLastName(),
                exam.getExam().getKey(),
                exam.getExam().getSubject().getName(),
                exam.getState().getLabel(),
                exam.getAttended(),
                exam.getGrade()
        );
    }

    public static List<ExamRecordDTO> fromListExamRecord(List<ExamRecord> exams) {
        return exams.stream().map(ExamRecordDTO::fromExamRecord).toList();
    }
}
