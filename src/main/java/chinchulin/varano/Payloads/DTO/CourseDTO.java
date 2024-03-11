package chinchulin.varano.Payloads.DTO;

import chinchulin.varano.Models.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

    private String name;

    private List<SubjectDTO> subjects;

    public static CourseDTO fromCourse(Course course) {
        return new CourseDTO(
                course.getName(),
                SubjectDTO.fromListSubject(course.getSubjects())
        );
    }

    public static List<CourseDTO> fromListCourse(List<Course> courses) {
        return courses.stream().map(CourseDTO::fromCourse).toList();
    }
}
