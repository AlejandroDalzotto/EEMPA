package chinchulin.varano.Models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
    private Long id_student;

    @Column(name = "name", nullable = false, length = 25)
    private String name;

    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "sex", nullable = false)
    private String sex;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "dni", nullable = false, unique = true)
    private Long dni;

    @Column(name = "cell_phone")
    private Long cellPhone;

    @Column(name = "line_phone")
    private Long linePhone;

    @Column(name = "mail", unique = true, nullable = false)
    private String mail;

    @Column(name = "legajo", unique = true)
    private Long legajo;

    @Column(name = "matricula", nullable = false)
    private Long matricula;

    @Column(name = "birth_cert", nullable = false)
    private Boolean birthCert;

    @Column(name = "study_cert", nullable = false)
    private Boolean studyCert;

    // Acá decidí ponerle curso porque si le ponía año nos íbamos a confundir
    // dudas sobre si añadir esto como uno de los módulos.
    @Column(name = "course", nullable = false)
    private int course;

    @Column(name = "disability", nullable = false)
    private Boolean disability;

    @Column(name = "health", nullable = false)
    private Boolean health;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_subject",
            joinColumns = @JoinColumn(name = "id_student"),
            inverseJoinColumns = @JoinColumn(name = "id_subject")
    )
    private List<Subject> subjects;
}
