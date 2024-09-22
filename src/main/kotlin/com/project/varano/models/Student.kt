package com.project.varano.models

import com.project.varano.enums.Gender
import jakarta.persistence.*
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDate
import javax.security.auth.Subject

@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "students")
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student")
    private val id: Long,
    @Column(nullable = false, length = 25)
    private val name: String,
    @Column(name = "last_name", nullable = false, length = 30)
    private val lastName: String,
    @Column(nullable = false)
    private val birthdate: LocalDate,
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private val gender: Gender,
    @Column(nullable = false, length = 55)
    private val address: String,
    @Column(nullable = false, unique = true)
    private val dni: Long,
    @Column(name = "cell_phone")
    private val cellPhone: Long? = null,
    @Column(name = "line_phone")
    private val linePhone: Long? = null,
    @Column(name = "mail", unique = true, length = 35)
    private val mail: String? = null,
    @Column(name = "has_legajo")
    private val hasLegajo: Boolean = false,
    @Column(name = "has_matricula", nullable = false)
    private val hasMatricula: Boolean = false,
    @Column(name = "has_birth_cert", nullable = false)
    private val hasBirthCert: Boolean,
    @Column(name = "has_study_cert", nullable = false)
    private val hasStudyCert: Boolean,
    @Column(name = "has_disability", nullable = false)
    private val hasDisability: Boolean,
    private val active: Boolean = true,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_course")
    private val course: Course,

    @Column(name = "has_health_cert", nullable = false)
    private val hasHealthCert: Boolean,

    @OneToMany(mappedBy = "student", cascade = [CascadeType.ALL])
    private val exams: MutableSet<ExamRecord> = mutableSetOf(),

    @OneToMany(mappedBy = "student", cascade = [CascadeType.ALL])
    private val records: MutableSet<AcademicRecord> = mutableSetOf(),

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "student_subjects",
        joinColumns = [JoinColumn(name = "id_student")],
        inverseJoinColumns = [JoinColumn(name = "id_subject")]
    )
    private val subjects: MutableSet<Subject> = mutableSetOf()
)
