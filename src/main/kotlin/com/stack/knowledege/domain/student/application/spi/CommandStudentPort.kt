package com.stack.knowledege.domain.student.application.spi

import com.stack.knowledege.domain.student.domain.Student

interface CommandStudentPort {
    fun save(student: Student): Student
}