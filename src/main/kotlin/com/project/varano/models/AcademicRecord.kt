package com.project.varano.models

import com.project.varano.enums.AcademicState
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "academic_records")
data class AcademicRecord (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_academic_record")
    private val id: Long,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_student")
    private val student: Student,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_course")
    private val course: Course,

    @Column(name = "study_year")
    private val studyYear: Short,

    @Enumerated(EnumType.STRING)
    private val state: AcademicState = AcademicState.REGULAR,

    @Column(nullable = false)
    private val average: Float = 0.0f,

    @Column(name = "unique_record_code", nullable = false, unique = true)
    private val uniqueCode: String,

    @Column(name = "referred_comment")
    private val comment: String? = null
)
