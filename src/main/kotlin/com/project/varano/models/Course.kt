package com.project.varano.models

import jakarta.persistence.*
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "courses")
data class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_course")
    private val id: Long,
    @Column(nullable = false, unique = true, length = 30)
    private val name: String,
    private val active: Boolean = true,

    @OneToMany(mappedBy = "course", cascade = [CascadeType.ALL])
    private val subjects: MutableSet<Subject> = mutableSetOf(),

    @OneToMany(mappedBy = "course", cascade = [CascadeType.ALL])
    private val records: MutableSet<AcademicRecord> = mutableSetOf(),

    @OneToMany(mappedBy = "course", cascade = [CascadeType.ALL])
    private val students: MutableSet<Student> = mutableSetOf(),

    @OneToMany(mappedBy = "course", cascade = [CascadeType.ALL])
    private val modules: MutableSet<Module> = mutableSetOf()
)
