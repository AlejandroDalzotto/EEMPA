package chinchulin.varano.Payloads.DTO;

import chinchulin.varano.Models.Course;
import chinchulin.varano.Models.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleCourseDTO {

    private String course_name;

    private Integer count_subjects;

    private List<SimpleSubjectDTO> last_subjects_record;

    private Integer count_students;

    public static SimpleCourseDTO fromCourse(Course course) {

        return new SimpleCourseDTO(
                course.getName(),
                course.getSubjects().size(),
                SimpleSubjectDTO.fromListSubject(course.getSubjects()),
                course.getStudents().size()
        );
    }

    public static List<SimpleCourseDTO> fromListCourse(List<Course> courses) {
        return courses.stream().map(SimpleCourseDTO::fromCourse).toList();
    }
}
