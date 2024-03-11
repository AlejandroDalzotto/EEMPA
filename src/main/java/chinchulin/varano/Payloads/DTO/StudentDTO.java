package chinchulin.varano.Payloads.DTO;

import chinchulin.varano.Models.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private String name;
    private String lastName;
    private LocalDate birth;
    private String sex;
    private String address;
    private Long dni;
    private Long cellPhone;
    private Long linePhone;
    private String mail;
    private Long legajo;
    private Long matricula;
    private Boolean birthCert;
    private Boolean studyCert;
    private String course;
    private Boolean disability;
    private Boolean health;

    public static StudentDTO fromStudent(Student student) {
        return new StudentDTO(
                student.getName(),
                student.getLastName(),
                student.getBirth(),
                student.getSex(),
                student.getAddress(),
                student.getDni(),
                student.getCellPhone(),
                student.getLinePhone(),
                student.getMail(),
                student.getLegajo(),
                student.getMatricula(),
                student.getBirthCert(),
                student.getStudyCert(),
                student.getCourse().getName(),
                student.getDisability(),
                student.getHealth()
        );
    }

    public static List<StudentDTO> fromListStudent(List<Student> students) {
        return students.stream().map(StudentDTO::fromStudent).toList();
    }
}
