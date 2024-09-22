package com.project.varano.models

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDate


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exams")
data class Exam (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exam")
    private val id: Long,

    @Column(name = "exam_key", nullable = false, length = 40, unique = true)
    private val key: String,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_subject", nullable = false)
    private val subject: Subject,

    @Column(name = "exam_date", nullable = false)
    private val date: LocalDate,

    @OneToMany(mappedBy = "exam", cascade = [CascadeType.ALL])
    private val records: MutableSet<ExamRecord> = mutableSetOf(),

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_module")
    private val module: Module
)