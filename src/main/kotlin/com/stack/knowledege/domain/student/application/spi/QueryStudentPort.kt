package com.stack.knowledege.domain.student.application.spi

import com.stack.knowledege.domain.student.domain.Student
import com.stack.knowledege.domain.user.domain.User
import java.util.UUID

interface QueryStudentPort {
    fun queryStudentById(id: UUID): Student?
    fun queryStudentsPointDesc(): List<Student>
    fun queryStudentByUser(user: User): Student
    fun existStudentByUser(user: User): Boolean
}