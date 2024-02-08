package chinchulin.varano.Payloads.Request;

import chinchulin.varano.Validations.Birthdate;
import chinchulin.varano.Validations.Gender;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class StudentRequest {

    @NotNull(message = "El nombre es un campo obligatorio, por favor provea un nombre.")
    @Size(min = 3, max = 25, message = "El nombre debe contener entre 3 y 25 caracteres.")
    String name;

    @NotNull(message = "El apellido es un campo obligatorio, por favor provea un apellido.")
    @Size(min = 3, max = 30, message = "El apellido debe contener entre 3 y 30 caracteres.")
    String lastName;

    @NotNull(message = "La fecha de nacimiento es un campo obligatorio.")
    @Past(message = "La fecha debe ser anterior a la fecha actual.")
    @Birthdate(message = "La edad del alumno no es suficiente para ingresar al sistema.")
    LocalDate birth;

    @NotNull(message = "El género es un campo obligatorio.")
    @Gender
    String sex;

    @NotNull(message = "La dirección es un campo obligatorio.")
    @Size(min = 5, message = "La dirección debe tener un mínimo de 5 caracteres")
    String address;

    @NotNull(message = "El DNI es un campo obligatorio.")
    @Positive(message = "El DNI no puede ser un número negativo.")
    @Min(value = 10000001, message = "El DNI debe ser un número superior a 10 millones.")
    Integer dni;

    @Nullable
    Long cellPhone;

    @Nullable
    Long linePhone;

    @NotNull(message = "El mail es un campo obligatorio.")
    @Email(message = "El campo mail debe ser un email valido. Por ejemplo: email-ejemplo@dominio.com")
    String mail;

    @NotNull(message = "El legajo es un campo obligatorio.")
    Long legajo;

    @NotNull(message = "La matricula es un campo obligatorio.")
    Long matricula;

    @NotNull(message = "Debe ser especificado si el alumno tiene certificado de nacimiento o no.")
    Boolean birthCert;

    @NotNull(message = "Debe ser especificado si el alumno tiene certificado de estudios o no.")
    Boolean studyCert;

    @NotNull(message = "El alumno debe estar asignado a un curso.")
    Integer course;

    @NotNull(message = "Debe ser especificado si el alumno tiene una condición de discapacidad o no.")
    Boolean disability;

    @NotNull(message = "Debe ser especificado si el alumno tiene certificado de buena salud o no.")
    Boolean health;
}
