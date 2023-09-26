package com.stack.knowledge.domain.student.application.spi

import com.stack.knowledge.domain.student.domain.Student
import com.stack.knowledge.domain.user.domain.User
import java.util.UUID

interface QueryStudentPort {
    fun queryStudentById(id: UUID): Student?
    fun queryStudentsPointDesc(): List<Student>
    fun queryStudentByUserId(userId: UUID): Student?
    fun existStudentByUser(user: User): Boolean
}