package chinchulin.varano.Payloads.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    String name;
    String lastName;
    LocalDate birth;
    String sex;
    String address;
    Integer dni;
    Long cellPhone;
    Long linePhone;
    String mail;
    Long legajo;
    Long matricula;
    Boolean birthCert;
    Boolean studyCert;
    Integer course;
    Boolean disability;
    Boolean health;
}
