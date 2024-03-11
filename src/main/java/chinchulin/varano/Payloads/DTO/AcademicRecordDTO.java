package chinchulin.varano.Payloads.DTO;

import chinchulin.varano.Models.AcademicRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcademicRecordDTO {

    private String student_name;

    private String course_name;

    private Short study_year;

    private String unique_code;

    private String academic_state;

    private String comment = null;

    public static AcademicRecordDTO fromRecord(AcademicRecord record) {

        return new AcademicRecordDTO(
                String.format("%s, %s", record.getStudent().getName(), record.getStudent().getLastName()),
                record.getCourse().getName(),
                record.getStudyYear(),
                record.getUniqueCode(),
                record.getState().getLabel(),
                record.getComment()
        );
    }

    public static List<AcademicRecordDTO> fromListRecord(List<AcademicRecord> records) {
        return records.stream().map(AcademicRecordDTO::fromRecord).toList();
    }
}
