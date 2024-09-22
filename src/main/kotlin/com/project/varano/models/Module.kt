package com.project.varano.models

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "modules")
class Module(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_module")
    private val id: Long,

    @Column(length = 25, nullable = false, unique = true)
    private val name: String,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_course")
    private val course: Course,

    @OneToMany(mappedBy = "module", cascade = [CascadeType.ALL])
    private val exams: MutableSet<Exam> = mutableSetOf()
)