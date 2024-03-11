package chinchulin.varano.Models;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "students")
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

    @Column(name = "address", nullable = false, length = 55)
    private String address;

    @Column(name = "dni", nullable = false, unique = true)
    private Long dni;

    @Column(name = "cell_phone")
    private Long cellPhone;

    @Column(name = "line_phone")
    private Long linePhone;

    @Column(name = "mail", unique = true, nullable = false, length = 30)
    private String mail;

    @Column(name = "legajo")
    private Long legajo;

    @Column(name = "matricula", nullable = false)
    private Long matricula;

    @Column(name = "birth_cert", nullable = false)
    private Boolean birthCert;

    @Column(name = "study_cert", nullable = false)
    private Boolean studyCert;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_course")
    private Course course;

    @Column(name = "disability", nullable = false)
    private Boolean disability;

    @Column(name = "health", nullable = false)
    private Boolean health;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<ExamRecord> exams;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<AcademicRecord> records;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_subjects",
            joinColumns = @JoinColumn(name = "id_student"),
            inverseJoinColumns = @JoinColumn(name = "id_subject")
    )
    private List<Subject> subjects;

}
