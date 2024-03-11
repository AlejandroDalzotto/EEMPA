package chinchulin.varano.Services.Exam;

import chinchulin.varano.Payloads.DTO.ExamDTO;
import chinchulin.varano.Payloads.DTO.ExamRecordDTO;
import chinchulin.varano.Payloads.DTO.ExamWithRecordsDTO;
import chinchulin.varano.Payloads.Request.ExamRecordRequest;
import chinchulin.varano.Payloads.Request.ExamRequest;

import java.time.LocalDate;
import java.util.List;

public interface ExamServiceInt {

    List<ExamDTO> getAllExams();

    ExamDTO getExamByKey(String key);

    List<ExamRecordDTO> getRecordsByKey(String key, Integer limit, Integer offset);

    List<ExamDTO> getFilteredByTerm(String term, Integer limit, Integer offset);

    List<ExamDTO> getByBlankTerm(Integer limit, Integer offset);

    List<ExamDTO> getAllExamsByDate(LocalDate date);

    List<ExamDTO> getAllExamsBetweenTwoDates(LocalDate start, LocalDate finish);

    ExamDTO saveExam(ExamRequest newEntry);

    ExamRecordDTO registerStudent(ExamRecordRequest newEntry);

    ExamDTO updateExam(String key, ExamRequest newAttributes);

    ExamDTO deleteExamByKey(String key);

    Long countExams();

    Long countExamsByTerm(String term);
}
