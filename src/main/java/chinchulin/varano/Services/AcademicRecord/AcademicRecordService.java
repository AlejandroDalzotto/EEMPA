package chinchulin.varano.Services.AcademicRecord;

import chinchulin.varano.Exceptions.EntityNotFoundException;
import chinchulin.varano.Exceptions.EntityRepeatedException;
import chinchulin.varano.Models.AcademicRecord;
import chinchulin.varano.Models.Course;
import chinchulin.varano.Models.Student;
import chinchulin.varano.Payloads.DTO.AcademicRecordDTO;
import chinchulin.varano.Payloads.Request.AcademicRecordRequest;
import chinchulin.varano.Repositories.AcademicRecordRepo;
import chinchulin.varano.Repositories.CourseRepo;
import chinchulin.varano.Repositories.StudentRepo;
import chinchulin.varano.Utils.AcademicState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademicRecordService implements AcademicRecordServiceInt {

    private final AcademicRecordRepo repo;

    private final CourseRepo courseRepo;

    private final StudentRepo studentRepo;

    @Autowired
    public AcademicRecordService(AcademicRecordRepo repo, CourseRepo courseRepo, StudentRepo studentRepo) {
        this.repo = repo;
        this.courseRepo = courseRepo;
        this.studentRepo = studentRepo;
    }

    @Override
    public AcademicRecordDTO getOneByCode(String code) {
        AcademicRecord record = repo.getOneByCode(code);

        return AcademicRecordDTO.fromRecord(record);
    }

    @Override
    public List<AcademicRecordDTO> getAllByTerm(String term, Integer limit, Integer offset) {
        List<AcademicRecord> records = repo.getAllByTerm(term, limit, offset);

        return AcademicRecordDTO.fromListRecord(records);
    }

    @Override
    public List<AcademicRecordDTO> getAllByBlankTerm(Integer limit, Integer offset) {
        List<AcademicRecord> records = repo.getAllByBlankTerm(limit, offset);

        return AcademicRecordDTO.fromListRecord(records);
    }

    @Override
    public Short getTotalPagesByTerm(String query) {
        return (short) repo.countByTerm(query);
    }

    @Override
    public Short getTotalPagesByBlankTerm() {
        return (short) repo.countTotal();
    }

    @Override
    public AcademicRecordDTO create(AcademicRecordRequest newEntry) {
        Course course = courseRepo.getByName(newEntry.getCourse_name());

        if (course == null) {
            throw new EntityNotFoundException("El curso " + newEntry.getCourse_name() + " no se encuentra registrado.");
        }

        Student student = studentRepo.getByDNI(newEntry.getStudent_dni());

        if (student == null) {
            throw new EntityNotFoundException("El alumno con DNI " + newEntry.getStudent_dni() + " no se encuentra registrado en la base de datos.");
        }

        AcademicRecord newRecord = new AcademicRecord();

        newRecord.setStudent(student);
        newRecord.setCourse(course);
        newRecord.setComment(newEntry.getComment());
        newRecord.setState(AcademicState.REGULAR);
        newRecord.setAverage(0d);
        newRecord.setStudyYear(newEntry.getStudy_year());

        repo.save(newRecord);

        return AcademicRecordDTO.fromRecord(newRecord);
    }

    @Override
    public AcademicRecordDTO update(String code, AcademicRecordRequest newAttributes) {

        AcademicRecord record = repo.getOneByCode(code);

        AcademicState newState = AcademicState.getStateByLabel(newAttributes.getAcademic_state());

        record.setComment(newAttributes.getComment());
        record.setState(newState);
        record.setAverage(0d);
        record.setStudyYear(newAttributes.getStudy_year());

        repo.save(record);

        return AcademicRecordDTO.fromRecord(record);
    }
}
