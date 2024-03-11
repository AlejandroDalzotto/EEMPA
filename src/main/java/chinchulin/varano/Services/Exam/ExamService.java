package chinchulin.varano.Services.Exam;

import chinchulin.varano.Exceptions.DataInconsistencyException;
import chinchulin.varano.Exceptions.EntityNotFoundException;
import chinchulin.varano.Exceptions.EntityRepeatedException;
import chinchulin.varano.Models.*;
import chinchulin.varano.Models.Module;
import chinchulin.varano.Payloads.DTO.ExamDTO;
import chinchulin.varano.Payloads.DTO.ExamRecordDTO;
import chinchulin.varano.Payloads.DTO.ExamWithRecordsDTO;
import chinchulin.varano.Payloads.Request.ExamRecordRequest;
import chinchulin.varano.Payloads.Request.ExamRequest;
import chinchulin.varano.Repositories.*;
import chinchulin.varano.Utils.ExamState;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class ExamService implements ExamServiceInt {

    private final ExamRepo repo;

    private final SubjectRepo subjectRepo;

    private final StudentRepo studentRepo;

    private final ModuleRepo moduleRepo;

    private final ExamRecordRepo examRecordRepo;

    @Autowired
    public ExamService(
            ExamRepo repo,
            SubjectRepo subjectRepo,
            StudentRepo studentRepo,
            ModuleRepo moduleRepo,
            ExamRecordRepo examRecordRepo
    ) {
        this.repo = repo;
        this.subjectRepo = subjectRepo;
        this.studentRepo = studentRepo;
        this.moduleRepo = moduleRepo;
        this.examRecordRepo = examRecordRepo;
    }

    @Override
    public List<ExamDTO> getAllExams() {
        List<Exam> exams = repo.findAll();

        return ExamDTO.fromListExam(exams);
    }

    @Override
    public ExamDTO getExamByKey(String key) {
        Exam exam = repo.getByKey(key);

        if (exam == null) {
            throw new EntityNotFoundException("El examen " + key + " no se encuentra registrado en la base de datos.");
        }

        return ExamDTO.fromExam(exam);
    }

    @Override
    public List<ExamRecordDTO> getRecordsByKey(String key, Integer limit, Integer offset) {
        Exam exam = repo.getByKey(key);

        if (exam == null) {
            throw new EntityNotFoundException("El examen " + key + " no se encuentra registrado en la base de datos.");
        }

        return ExamRecordDTO.fromListExamRecord(exam.getRecords());
    }

    @Override
    public List<ExamDTO> getFilteredByTerm(String term, Integer limit, Integer offset) {
        List<Exam> exams = repo.getByFilterTerm(term, limit, offset);

        return ExamDTO.fromListExam(exams);
    }

    @Override
    public List<ExamDTO> getByBlankTerm(Integer limit, Integer offset) {
        List<Exam> exams = repo.getByBlankTerm(limit, offset);

        return ExamDTO.fromListExam(exams);
    }

    @Override
    public List<ExamDTO> getAllExamsByDate(LocalDate date) {
        List<Exam> exams = repo.getByDate(date);

        return ExamDTO.fromListExam(exams);
    }

    @Override
    public List<ExamDTO> getAllExamsBetweenTwoDates(LocalDate start, LocalDate finish) {

        List<Exam> exams = repo.getBetweenDates(start, finish);

        return ExamDTO.fromListExam(exams);
    }

    @Override
    public ExamDTO saveExam(ExamRequest newEntry) {

        Exam hasRepeatedKey = repo.getByKey(newEntry.getKey());

        if (hasRepeatedKey != null) {
            throw new EntityRepeatedException("El nombre " + newEntry.getKey() + " ya se encuentra registrado. Por favor provea uno diferente.");
        }

        Subject subject = subjectRepo.getByName(newEntry.getSubject());

        if (subject == null) {
            throw new EntityNotFoundException("La materia " + newEntry.getSubject() + " no se encuentra registrada en la base de datos.");
        }

        Module module = moduleRepo.getByName(newEntry.getModule());

        if (module == null) {
            throw new EntityNotFoundException("El modulo " + newEntry.getModule() + " no se encuentra registrado en la base de datos.");
        }

        Exam exam = new Exam();
        exam.setKey(newEntry.getKey());
        exam.setDate(newEntry.getDate());
        exam.setSubject(subject);
        exam.setRecords(Collections.emptyList());
        exam.setModule(module);

        repo.save(exam);

        return ExamDTO.fromExam(exam);
    }

    @Override
    public ExamRecordDTO registerStudent(ExamRecordRequest newEntry) {

        Exam exam = repo.getByKey(newEntry.getExam_key());

        if (exam == null) {
            throw new EntityNotFoundException("El examen " + newEntry.getExam_key() + " no existe en la base de datos.");
        }

        Student student = studentRepo.getByDNI(newEntry.getStudent_dni());

        if (student == null) {
            throw new EntityNotFoundException("No existe el alumno con el DNI " + newEntry.getStudent_dni());
        }

        if (!student.getSubjects().contains(exam.getSubject())) {
            throw new DataInconsistencyException("El alumno " + student.getName() + " no se encuentra anotado a la materia " + exam.getSubject().getName());
        }

        ExamRecord record = new ExamRecord();

        record.setStudent(student);
        record.setAttended(newEntry.getAttended());
        record.setState(ExamState.getStateByLabel(newEntry.getState()));
        record.setGrade(newEntry.getGrade());
        record.setExam(exam);

        examRecordRepo.save(record);

        List<ExamRecord> records = exam.getRecords();

        records.add(record);

        exam.setRecords(records);
        repo.save(exam);

        return ExamRecordDTO.fromExamRecord(record);
    }

    @Override
    public ExamDTO updateExam(String key, ExamRequest newAttributes) {

        Exam exam = repo.getByKey(key);

        if (exam == null) {
            throw new EntityNotFoundException("El examen " + key + " no se encuentra registrado en la base de datos.");
        }

        Exam hasRepeatedKey = repo.getByKey(newAttributes.getKey());

        if (hasRepeatedKey != null) {
            throw new EntityRepeatedException("El nombre " + newAttributes.getKey() + " ya se encuentra registrado. Por favor provea uno diferente.");
        }

        Subject subject = subjectRepo.getByName(newAttributes.getSubject());

        if (subject == null) {
            throw new EntityNotFoundException("La materia " + newAttributes.getSubject() + " no se encuentra registrada en la base de datos.");
        }

        Module module = moduleRepo.getByName(newAttributes.getModule());

        if (module == null) {
            throw new EntityNotFoundException("El modulo " + newAttributes.getModule() + " no se encuentra registrado en la base de datos.");
        }

        exam.setModule(module);
        exam.setDate(newAttributes.getDate());
        exam.setKey(newAttributes.getKey());
        exam.setSubject(subject);

        repo.save(exam);

        return ExamDTO.fromExam(exam);
    }

    @Override
    public ExamDTO deleteExamByKey(String key) {
        Exam exam = repo.getByKey(key);

        if (exam == null) {
            throw new EntityNotFoundException("El examen " + key + " no se encuentra registrado.");
        }

        repo.delete(exam);
        return ExamDTO.fromExam(exam);
    }

    @Override
    public Long countExams() {
        return repo.countExams();
    }

    @Override
    public Long countExamsByTerm(String term) {
        return repo.countByTerm(term);
    }
}
