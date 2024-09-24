package com.project.varano.repositories

import com.project.varano.models.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface StudentRepository: JpaRepository<Student, Long> {
    fun findByDniOrMailIgnoreCase(dni: Int, mail: String): Student
}