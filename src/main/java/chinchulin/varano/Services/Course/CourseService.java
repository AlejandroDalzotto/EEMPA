package chinchulin.varano.Services.Course;

import chinchulin.varano.Exceptions.DataInconsistencyException;
import chinchulin.varano.Exceptions.EntityNotFoundException;
import chinchulin.varano.Exceptions.EntityRepeatedException;
import chinchulin.varano.Models.AcademicRecord;
import chinchulin.varano.Models.Course;
import chinchulin.varano.Models.Student;
import chinchulin.varano.Models.Subject;
import chinchulin.varano.Payloads.DTO.CourseDTO;
import chinchulin.varano.Payloads.DTO.SimpleCourseDTO;
import chinchulin.varano.Payloads.DTO.StudentDTO;
import chinchulin.varano.Payloads.DTO.SubjectDTO;
import chinchulin.varano.Payloads.Request.CourseRequest;
import chinchulin.varano.Payloads.Request.PromoteStudentRequest;
import chinchulin.varano.Repositories.*;
import chinchulin.varano.Utils.AcademicState;
import chinchulin.varano.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class CourseService implements CourseServiceInt {

    private final CourseRepo repo;
    private final SubjectRepo subjectRepo;
    private final StudentRepo studentRepo;
    private final AcademicRecordRepo academicRecordRepo;
    private final CalificationRepo calificationRepo;

    @Autowired
    public CourseService(CourseRepo repo, SubjectRepo subjectRepo, StudentRepo studentRepo, AcademicRecordRepo academicRecordRepo, CalificationRepo calificationRepo) {
        this.repo = repo;
        this.subjectRepo = subjectRepo;
        this.studentRepo = studentRepo;
        this.academicRecordRepo = academicRecordRepo;
        this.calificationRepo = calificationRepo;
    }

    @Override
    public List<CourseDTO> getAll() {
        List<Course> courses = repo.findAll();
        return CourseDTO.fromListCourse(courses);
    }

    @Override
    public List<SimpleCourseDTO> getSimpleValues(Integer limit, Integer offset) {
        List<Course> courses = repo.getAllActive(limit, offset);

        return SimpleCourseDTO.fromListCourse(courses);
    }

    @Override
    public List<SimpleCourseDTO> getSimpleValuesByTerm(String term, Integer limit, Integer offset) {
        List<Course> courses = repo.getAllActiveByTerm(term, limit, offset);

        return SimpleCourseDTO.fromListCourse(courses);
    }

    @Override
    public SimpleCourseDTO getSimpleValueByName(String course_name) {
        Course course = repo.getByName(course_name);

        if (course == null) {
            throw new EntityNotFoundException(String.format("El curso %s no se encuentra registrado", course_name));
        }

        return SimpleCourseDTO.fromCourse(course);
    }

    @Override
    public List<CourseDTO> getAllActive(Integer limit, Integer offset) {
        List<Course> courses = repo.getAllActive(limit, offset);
        return CourseDTO.fromListCourse(courses);
    }

    @Override
    public CourseDTO add(CourseRequest course) {
        Course isRegistered = repo.getByName(course.getName());

        if (isRegistered != null) {
            throw new EntityRepeatedException("El curso " + course.getName() + " ya existe en la base de datos");
        }

        Course courseToSave = new Course();
        courseToSave.setName(course.getName());
        courseToSave.setActive(true);
        courseToSave.setSubjects(Collections.emptyList());
        repo.save(courseToSave);
        return CourseDTO.fromCourse(courseToSave);
    }

    @Override
    public StudentDTO promoteStudent(PromoteStudentRequest newEntry) {

        Course newCourse = repo.getByName(newEntry.getNew_course());

        if (newCourse == null) {
            throw new EntityNotFoundException("El curso " + newEntry.getNew_course() + " no se encuentra registrado.");
        }

        Student student = studentRepo.getByDNI(newEntry.getStudent_dni());

        if (student == null) {
            throw new EntityNotFoundException("El alumno con DNI " + newEntry.getStudent_dni() + " no se encuentra registrado en la base de datos.");
        }

        boolean courseIsAlreadyCompleted = academicRecordRepo.courseIsCompleted(student.getId_student(), newCourse.getId_course()) > 0;

        if (courseIsAlreadyCompleted) {
            throw new DataInconsistencyException("El alumno " + student.getName() + " ya ha cursado " + newCourse.getName());
        }

        Double average = calificationRepo.getAverageOfStudent(student.getId_student(), student.getCourse().getId_course());

        if (average == null) {
            throw new DataInconsistencyException("El alumno " + student.getName() + " no tiene registradas las notas de las materias de " + student.getCourse().getName());
        }

        AcademicRecord oldAcademicRecord = academicRecordRepo.getOneByCourseAndStudent(
                student.getCourse().getId_course(),
                student.getId_student()
        );

        if (oldAcademicRecord != null) {
            oldAcademicRecord.setState(AcademicState.GRADUATED);
            oldAcademicRecord.setAverage(average);
            oldAcademicRecord.setComment(newEntry.getComment());

            academicRecordRepo.save(oldAcademicRecord);
        }

        List<Subject> newSubjects = subjectRepo.getSubjectsByCourse(newCourse.getId_course());
        AcademicRecord newAcademicRecord = new AcademicRecord();

        String newUniqueCode = Utils.generateUUID();

        newAcademicRecord.setStudent(student);
        newAcademicRecord.setUniqueCode(newUniqueCode);
        newAcademicRecord.setCourse(newCourse);
        newAcademicRecord.setState(AcademicState.REGULAR);
        newAcademicRecord.setComment(null);
        newAcademicRecord.setStudyYear((short) LocalDate.now().getYear());
        newAcademicRecord.setAverage(0d);

        student.setCourse(newCourse);
        student.setSubjects(newSubjects);

        academicRecordRepo.save(newAcademicRecord);
        studentRepo.save(student);

        return StudentDTO.fromStudent(student);
    }

    @Override
    public CourseDTO inactiveSubject(String name) {
        Course course = repo.getByName(name);

        if (course == null) {
            throw new EntityNotFoundException("El curso " + name + " no se ha encontrado.");
        }

        course.setActive(false);
        repo.save(course);
        return CourseDTO.fromCourse(course);
    }

    @Override
    public List<StudentDTO> getStudentByCourseFiltered(String name, String query, Integer limit, Integer offset) {
        Course course = repo.getByName(name);

        if (course == null) {
            throw new EntityNotFoundException("El curso " + name + " no se encuentra registrado");
        }

        List<Student> students = studentRepo.getByCourseFiltered(course.getId_course(), query, limit, offset);

        return StudentDTO.fromListStudent(students);
    }

    @Override
    public List<StudentDTO> getStudentByCourseQueryBlank(String name, Integer limit, Integer offset) {
        Course course = repo.getByName(name);

        if (course == null) {
            throw new EntityNotFoundException("El curso " + name + " no se encuentra registrado");
        }

        List<Student> students = studentRepo.getByCourseQueryBlank(course.getId_course(), limit, offset);

        return StudentDTO.fromListStudent(students);
    }

    @Override
    public Long countStudents(String course) {

        Course courseFromDb = repo.getByName(course);

        if (courseFromDb == null) {
            throw new EntityNotFoundException("El curso " + course + " no se encuentra registrado en la base de datos.");
        }

        return (long) studentRepo.countActiveByCourse(courseFromDb.getId_course());
    }

    @Override
    public Long countStudentsByTerm(String course, String term) {

        Course courseFromDb = repo.getByName(course);

        if (courseFromDb == null) {
            throw new EntityNotFoundException("El curso " + course + " no se encuentra registrado en la base de datos.");
        }

        return (long) studentRepo.countActiveByCourseFilteredByTerm(courseFromDb.getId_course(), term);
    }

    @Override
    public Long countCourses() {
        return repo.countActive();
    }

    @Override
    public Long countCoursesByTerm(String term) {
        return repo.countActiveByTerm(term);
    }

    @Override
    public List<SubjectDTO> getSubjectByCourse(String name) {

        Course course = repo.getByName(name);

        if (course == null) {
            throw new EntityNotFoundException("No se ha encontrado el curso " + name);
        }

        List<Subject> subject = subjectRepo.getSubjectsByCourse(course.getId_course());
        return SubjectDTO.fromListSubject(subject);
    }

}
