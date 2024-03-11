package chinchulin.varano.Services.Course;

import java.util.List;

import chinchulin.varano.Payloads.DTO.CourseDTO;
import chinchulin.varano.Payloads.DTO.SimpleCourseDTO;
import chinchulin.varano.Payloads.DTO.StudentDTO;
import chinchulin.varano.Payloads.DTO.SubjectDTO;
import chinchulin.varano.Payloads.Request.CourseRequest;
import chinchulin.varano.Payloads.Request.PromoteStudentRequest;

public interface CourseServiceInt {

    List<CourseDTO> getAll();

    List<SimpleCourseDTO> getSimpleValues(Integer limit, Integer offset);

    SimpleCourseDTO getSimpleValueByName(String course_name);
    List<SimpleCourseDTO> getSimpleValuesByTerm(String term, Integer limit, Integer offset);

    List<SubjectDTO> getSubjectByCourse(String name);

    List<CourseDTO> getAllActive(Integer limit, Integer offset);

    CourseDTO add(CourseRequest course);

    StudentDTO promoteStudent(PromoteStudentRequest newEntry);

    CourseDTO inactiveSubject(String name);

    List<StudentDTO> getStudentByCourseFiltered(String name, String query, Integer limit, Integer offset);

    List<StudentDTO> getStudentByCourseQueryBlank(String name, Integer limit, Integer offset);

    Long countStudents(String course);

    Long countStudentsByTerm(String course, String term);

    Long countCourses();

    Long countCoursesByTerm(String term);
}
