package com.stack.knowledge.domain.student.application.spi

import com.stack.knowledge.domain.student.domain.Student

interface CommandStudentPort {
    fun save(student: Student): Student
}