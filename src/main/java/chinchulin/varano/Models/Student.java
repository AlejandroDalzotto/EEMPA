package chinchulin.varano.Models;

import java.time.LocalDate;
import java.util.List;

import chinchulin.varano.Payloads.DTO.StudentDTO;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "student")
@CrossOrigin("localhost:3000")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student")
    Long id_student;

    @Column(name = "name", nullable = false, length = 25)
    String name;

    @Column(name = "last_name", nullable = false, length = 30)
    String lastName;

    @Column(name = "birth", nullable = false)
    LocalDate birth;

    @Column(name = "sex", nullable = false)
    String sex;

    @Column(name = "address", nullable = false)
    String address;

    @Column(name = "dni", nullable = false, unique = true)
    Integer dni;

    @Column(name = "cell_phone")
    Long cellPhone;

    @Column(name = "line_phone")
    Long linePhone;

    @Column(name = "mail", unique = true, nullable = false)
    String mail;

    @Column(name = "legajo", unique = true)
    Long legajo;

    @Column(name = "matricula", nullable = false)
    Long matricula;

    @Column(name = "birth_cert", nullable = false)
    Boolean birthCert;

    @Column(name = "study_cert", nullable = false)
    Boolean studyCert;

    // Acá decidí ponerle curso porque si le ponía año nos íbamos a confundir
    // dudas sobre si añadir esto como uno de los módulos.
    @Column(name = "course", nullable = false)
    int course;

    @Column(name = "disability", nullable = false)
    Boolean disability;

    @Column(name = "health", nullable = false)
    Boolean health;

    @Column(name = "active", nullable = false)
    Boolean active = true;

    @ManyToMany
    @JsonBackReference(value = "subjects")
    @JoinTable(
            name = "student_subject",
            joinColumns = @JoinColumn(name = "id_student"),
            inverseJoinColumns = @JoinColumn(name = "id_subject")
    )
    List<Subject> subjects;

    public StudentDTO toStudentDTO() {
        return new StudentDTO(
                this.getName(),
                this.getLastName(),
                this.getBirth(),
                this.getSex(),
                this.getAddress(),
                this.getDni(),
                this.getCellPhone(),
                this.getLinePhone(),
                this.getMail(),
                this.getLegajo(),
                this.getMatricula(),
                this.getBirthCert(),
                this.getStudyCert(),
                this.getCourse(),
                this.getDisability(),
                this.getHealth()
        );
    }

    public static List<StudentDTO> toListStudentDTO(List<Student> students) {
        return students.stream().map(Student::toStudentDTO).toList();
    }
}
