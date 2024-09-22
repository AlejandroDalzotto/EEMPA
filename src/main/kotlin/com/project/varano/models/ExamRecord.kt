package com.project.varano.models

import com.project.varano.enums.ExamState
import jakarta.persistence.*
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "exam_records")
data class ExamRecord(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exam_record")
    private val id: Long,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_student")
    private val student: Student,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_exam")
    private val exam: Exam,

    @Enumerated(EnumType.STRING)
    private val state: ExamState,

    @Column(nullable = false)
    private val attended: Boolean = false,

    @Column(nullable = false)
    private val grade: Float = 0f
)