package com.project.varano.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor


@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "grades")
data class Grade (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grade")
    private val id: Long,

    @Column(nullable = false)
    private val value: Float = 0.0f,

    @ManyToOne
    @JoinColumn(name = "id_student", nullable = false)
    private val student: Student,

    @ManyToOne
    @JoinColumn(name = "id_subject", nullable = false)
    private val subject: Subject
)