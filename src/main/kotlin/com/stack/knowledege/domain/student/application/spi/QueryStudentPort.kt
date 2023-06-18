package com.stack.knowledege.domain.student.application.spi

import com.stack.knowledege.domain.student.domain.Student
import java.util.UUID

interface QueryStudentPort {
    fun queryStudentById(id: UUID): Student?
    fun queryStudentsPointDesc(): List<Student>
}