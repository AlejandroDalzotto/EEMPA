package chinchulin.varano.Models;

import java.sql.Date;
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

    @Column(name = "name")
    String name;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "birth")
    Date birth;

    @Column(name = "sex")
    String sex;

    @Column(name = "address")
    String address;

    @Column(name = "dni")
    Integer dni;

    @Column(name = "cell_phone")
    Long cellPhone;

    @Column(name = "line_phone")
    Long linePhone;

    @Column(name = "age")
    int age;

    @Column(name = "mail")
    String mail;

    @Column(name = "legajo")
    Long legajo;

    @Column(name = "matricula")
    Long matricula;

    @Column(name = "birth_cert")
    Boolean birthCert;

    @Column(name = "study_cert")
    Boolean studyCert;

    // Acá decidí ponerle curso porque si le ponía año nos íbamos a confundir
    // dudas sobre si añadir esto como uno de los módulos.
    @Column(name = "course")
    int course;

    @Column(name = "disability")
    Boolean disability;

    @Column(name = "health")
    Boolean health;

    @Column(name = "active")
    Boolean active;

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
                this.getAge(),
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
