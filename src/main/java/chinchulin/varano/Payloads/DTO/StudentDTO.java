package chinchulin.varano.Payloads.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    String name;
    String lastName;
    Date birth;
    String sex;
    String address;
    Integer dni;
    Long cellPhone;
    Long linePhone;
    Integer age;
    String mail;
    Long legajo;
    Long matricula;
    Boolean birthCert;
    Boolean studyCert;
    Integer course;
    Boolean disability;
    Boolean health;
}
