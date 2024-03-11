package chinchulin.varano.Services.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import chinchulin.varano.Exceptions.EntityRepeatedException;
import chinchulin.varano.Models.AcademicRecord;
import chinchulin.varano.Models.Course;
import chinchulin.varano.Payloads.DTO.StudentDTO;
import chinchulin.varano.Payloads.DTO.SubjectDTO;
import chinchulin.varano.Payloads.Request.StudentRequest;
import chinchulin.varano.Repositories.AcademicRecordRepo;
import chinchulin.varano.Repositories.CourseRepo;
import chinchulin.varano.Utils.AcademicState;
import chinchulin.varano.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chinchulin.varano.Exceptions.EntityNotFoundException;
import chinchulin.varano.Models.Student;
import chinchulin.varano.Models.Subject;
import chinchulin.varano.Repositories.StudentRepo;
import chinchulin.varano.Repositories.SubjectRepo;

@Service
public class StudentService implements StudentServiceInt {

    private final StudentRepo repo;
    private final SubjectRepo subjectRepo;
    private final CourseRepo courseRepo;
    private final AcademicRecordRepo academicRecordRepo;

    @Autowired
    public StudentService(StudentRepo repo, SubjectRepo subjectRepo, CourseRepo courseRepo, AcademicRecordRepo academicRecordRepo) {
        this.repo = repo;
        this.subjectRepo = subjectRepo;
        this.courseRepo = courseRepo;
        this.academicRecordRepo = academicRecordRepo;
    }

    @Override
    public List<StudentDTO> getAll() {
        List<Student> students = repo.findAll();
        return StudentDTO.fromListStudent(students);
    }

    @Override
    public StudentDTO getById(Long id) {
        Optional<Student> student = Optional.ofNullable(repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student with id " + id + " not found")));

        if (student.isEmpty()) {
            throw new EntityNotFoundException("Student with id " + id + " not found");
        }

        return StudentDTO.fromStudent(student.get());
    }

    @Override
    public StudentDTO getByDNI(Long dni) {
        Student student = repo.getByDNI(dni);

        if (student == null || !student.getActive()) {
            throw new EntityNotFoundException("No se ha encontrado al alumno con DNI " + dni);
        }

        return StudentDTO.fromStudent(student);
    }

    @Override
    public StudentDTO getByLegajo(Long legajo) {

        Student student = repo.getByLegajo(legajo);

        if (student == null || !student.getActive()) {
            throw new EntityNotFoundException("No se ha encontrado al alumno con legajo " + legajo);
        }

        return StudentDTO.fromStudent(student);
    }

    @Override
    public StudentDTO newStudent(StudentRequest newEntry) {

        // Check if unique values are repeated.
        Student isRegistered = repo.isRegistered(newEntry.getDni(), newEntry.getMail());
        if (isRegistered != null) {
            throw new EntityRepeatedException("Algunos datos proporcionados están repetidos. Por favor vuelva a intentarlo.");
        }

        Course course = courseRepo.getByName(newEntry.getCourse_name());

        // Check if the course exists by name.
        if (course == null) {
            throw new EntityNotFoundException("El curso " + newEntry.getCourse_name() + " no se encuentra registrado.");
        }

        Student studentToSave = new Student();

        // Fetch all subjects from the course.
        List<Subject> subjects = subjectRepo.getSubjectsByCourse(course.getId_course());

        Utils.copyStudentProperties(newEntry, studentToSave);
        studentToSave.setCourse(course);
        studentToSave.setSubjects(subjects);
        repo.save(studentToSave);

        // Construct the academic record for the new student
        AcademicRecord academicRecord = new AcademicRecord();

        String uniqueCode = Utils.generateUUID();

        academicRecord.setStudent(studentToSave);
        academicRecord.setUniqueCode(uniqueCode);
        academicRecord.setComment(null);
        academicRecord.setCourse(course);
        academicRecord.setState(AcademicState.REGULAR);
        academicRecord.setStudyYear((short) LocalDate.now().getYear());
        academicRecord.setAverage(0d);

        // Save the new record
        academicRecordRepo.save(academicRecord);

        return StudentDTO.fromStudent(studentToSave);
    }

    /**
     * TODO: Estaría bueno considerar reemplazar esto con implementación para un
     * PATCH request porque esto es una banda de data para la pobre red.
     */
    @Override
    public StudentDTO editStudent(Long dni, StudentRequest newStudent) {
        Student student = repo.getByDNI(dni);

        if (student == null || !student.getActive()) {
            throw new EntityNotFoundException("No se ha encontrado al alumno con el DNI " + dni);
        }

        Utils.copyStudentProperties(newStudent, student);

        repo.save(student);
        return StudentDTO.fromStudent(student);
    }

    @Override
    public StudentDTO inactiveStudent(Long dni) {
        Student student = repo.getByDNI(dni);

        if (student == null || !student.getActive()) {
            throw new EntityNotFoundException("No se ha encontrado al alumno con el DNI " + dni);
        }

        student.setActive(false);

        repo.save(student);
        return StudentDTO.fromStudent(student);
    }

    @Override
    public List<StudentDTO> getAllActive() {
        List<Student> students = repo.getAllActive();

        return StudentDTO.fromListStudent(students);
    }

    @Override
    public List<SubjectDTO> getSubjectByStudent(Long id) {
        List<Subject> subjects = subjectRepo.getActiveSubjectByStudent(id);
        return SubjectDTO.fromListSubject(subjects);
    }

    @Override
    public List<StudentDTO> getByFilterQuery(String query, int limit, int offset) {
        List<Student> students = repo.getByFilterQuery(query, limit, offset);
        return StudentDTO.fromListStudent(students);
    }

    @Override
    public List<StudentDTO> getByQueryBlank(int limit, int offset) {
        List<Student> students = repo.getByQueryBlank(limit, offset);
        return StudentDTO.fromListStudent(students);
    }

    @Override
    public Long count() {
        return (long) repo.countActive();
    }

    @Override
    public Long countByTerm(String term) {
        return (long) repo.countByTerm(term);
    }

}
